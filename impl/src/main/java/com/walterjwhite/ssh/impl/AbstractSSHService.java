package com.walterjwhite.ssh.impl;

import com.walterjwhite.shell.api.repository.ShellCommandRepository;
import com.walterjwhite.ssh.api.model.AbstractSSHEntity;
import java.io.IOException;
import javax.inject.Provider;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractSSHService<SSHEntityType extends AbstractSSHEntity> {
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSSHService.class);

  protected final SSHEntityType command;

  protected AbstractSSHService(SSHEntityType command) {
    super();
    this.command = command;
  }

  protected Session setupSSH(final SSHClient sshClient, final String host, final String username)
      throws IOException {
    sshClient.loadKnownHosts();

    // AUTOMATICALLY TRUST THE HOST ...
    sshClient.addHostKeyVerifier(new PromiscuousVerifier());

    sshClient.connect(host);

    // LOGGER.warn("user:" + username);

    // sshClient.authPublickey(System.getProperty("user.name"), publicKeyLocation);
    sshClient.authPublickey(username, getPublicKeyPath(username));

    return sshClient.startSession();
  }

  // TODO: make this configurable
  // TODO: this assumes the public key is in the user's home directory and that that is /home, but
  // that is most certainly not true
  protected String getPublicKeyPath(final String username) {
    if (System.getProperty("ssh.public-key") != null
        && !System.getProperty("ssh.public-key").isEmpty()) {
      LOGGER.warn("ssh.public_key:" + System.getProperty("ssh.public-key"));
      return (System.getProperty("ssh.public-key"));
    }

    LOGGER.warn("ssh.public_key: home ...");
    return ("/home/" + username + "/.ssh/id_ecdsa");
  }

  public void execute(Provider<ShellCommandRepository> shellCommandRepositoryProvider)
      throws Exception {
    try (SSHClient sshClient = new SSHClient()) {
      try (Session session =
          setupSSH(sshClient, command.getHost().getName(), command.getUser().getName())) {
        doExecute(shellCommandRepositoryProvider, sshClient, session);
      }
    }
  }

  protected abstract void doExecute(
      Provider<ShellCommandRepository> shellCommandRepositoryProvider,
      SSHClient sshClient,
      Session session)
      throws Exception;
}
