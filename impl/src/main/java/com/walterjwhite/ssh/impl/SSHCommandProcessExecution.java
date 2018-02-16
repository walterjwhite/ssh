package com.walterjwhite.ssh.impl;

import com.walterjwhite.shell.impl.AbstractProcessExecution;
import com.walterjwhite.ssh.api.model.command.SSHCommand;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;
import net.schmizz.sshj.connection.channel.direct.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSHCommandProcessExecution extends AbstractProcessExecution {
  private static final Logger LOGGER = LoggerFactory.getLogger(SSHCommandProcessExecution.class);

  protected final SSHCommand command;
  protected final Session.Command sshCommand;

  public SSHCommandProcessExecution(
      InputStream inputStream,
      InputStream errorStream,
      OutputStream outputStream,
      SSHCommand command,
      Session.Command sshCommand) {
    super(command.getShellCommand(), inputStream, errorStream, outputStream);
    this.command = command;
    this.sshCommand = sshCommand;
  }

  protected void doSetTimeout(int timeoutInSeconds) throws Exception {
    sshCommand.join(timeoutInSeconds, TimeUnit.SECONDS);
  }

  @Override
  protected void kill() throws IOException {
    super.kill();

    // Wait some time for the process to exit:
    sshCommand.join(1, TimeUnit.SECONDS);
  }

  @Override
  protected int getReturnCode() throws InterruptedException, IOException {
    return (sshCommand.getExitStatus());
  }

  protected int getTimeout() {
    return (command.getShellCommand().getTimeout());
  }
}
