package com.walterjwhite.ssh;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.ssh.api.model.AbstractSSHEntity;
import java.io.File;
import java.io.IOException;
import javax.inject.Provider;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

public abstract class AbstractSSHService<SSHEntityType extends AbstractSSHEntity> {
  protected final String sshPublicKeyPath;
  protected final SSHEntityType command;

  protected AbstractSSHService(final String sshPublicKeyPath, SSHEntityType command) {
    super();
    this.command = command;
    this.sshPublicKeyPath = sshPublicKeyPath;
  }

  protected Session setupSSH(final SSHClient sshClient, final String host, final String username)
      throws IOException {
    sshClient.loadKnownHosts();

    // AUTOMATICALLY TRUST THE HOST ...
    sshClient.addHostKeyVerifier(new PromiscuousVerifier());

    sshClient.connect(host);

    // LOGGER.warn("user:" + username);

    // sshClient.authPublickey(System.getProperty("user.name"), publicKeyLocation);
    sshClient.authPublickey(username, getPublicKeyPath());

    return sshClient.startSession();
  }

  // TODO: make this configurable
  // TODO: this assumes the public key is in the user's home directory and that that is /home, but
  // that is most certainly not true
  protected String getPublicKeyPath() {
    if (sshPublicKeyPath != null && !sshPublicKeyPath.isEmpty()) return sshPublicKeyPath;

    return getDefaultPublicKeyPath();
  }

  // TODO: make the key type configurable as well
  protected String getDefaultPublicKeyPath() {
    return System.getProperty("user.home") + File.separator + ".ssh" + File.separator + "id_ecdsa";
  }

  public void execute(Provider<Repository> repositoryProvider) throws Exception {
    try (SSHClient sshClient = new SSHClient()) {
      try (Session session =
          setupSSH(sshClient, command.getHost().getName(), command.getUser().getName())) {
        doExecute(repositoryProvider, sshClient, session);
      }
    }
  }

  protected abstract void doExecute(
      Provider<Repository> repositoryProvider, SSHClient sshClient, Session session)
      throws Exception;
}
