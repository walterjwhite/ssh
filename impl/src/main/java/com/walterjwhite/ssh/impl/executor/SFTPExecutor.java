package com.walterjwhite.ssh.impl.executor;

import com.walterjwhite.shell.api.repository.ShellCommandRepository;
import com.walterjwhite.ssh.api.model.sftp.SFTPTransfer;
import com.walterjwhite.ssh.impl.AbstractSSHService;
import javax.inject.Provider;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.xfer.FileSystemFile;
import net.schmizz.sshj.xfer.scp.SCPFileTransfer;

public class SFTPExecutor extends AbstractSSHService<SFTPTransfer> {
  public SFTPExecutor(SFTPTransfer command) {
    super(command);
  }

  @Override
  protected void doExecute(
      Provider<ShellCommandRepository> shellCommandRepositoryProvider,
      SSHClient sshClient,
      Session session)
      throws Exception {
    final SCPFileTransfer scpFileTransfer = sshClient.newSCPFileTransfer();

    if (command.isUpload())
      scpFileTransfer.upload(new FileSystemFile(command.getLocalPath()), command.getRemotePath());
    else
      scpFileTransfer.download(command.getRemotePath(), new FileSystemFile(command.getLocalPath()));
  }
}
