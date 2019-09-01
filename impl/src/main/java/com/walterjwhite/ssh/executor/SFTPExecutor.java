package com.walterjwhite.ssh.executor;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.property.impl.annotation.Property;
import com.walterjwhite.ssh.AbstractSSHService;
import com.walterjwhite.ssh.api.SSHPublicKeyPath;
import com.walterjwhite.ssh.api.model.sftp.SFTPTransfer;
import javax.inject.Provider;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.xfer.FileSystemFile;
import net.schmizz.sshj.xfer.scp.SCPFileTransfer;

public class SFTPExecutor extends AbstractSSHService<SFTPTransfer> {
  public SFTPExecutor(
      @Property(SSHPublicKeyPath.class) final String sshPublicKeyPath, SFTPTransfer command) {
    super(sshPublicKeyPath, command);
  }

  @Override
  protected void doExecute(
      Provider<Repository> repositoryProvider, SSHClient sshClient, Session session)
      throws Exception {
    final SCPFileTransfer scpFileTransfer = sshClient.newSCPFileTransfer();

    if (command.isUpload())
      scpFileTransfer.upload(new FileSystemFile(command.getLocalPath()), command.getRemotePath());
    else
      scpFileTransfer.download(command.getRemotePath(), new FileSystemFile(command.getLocalPath()));
  }
}
