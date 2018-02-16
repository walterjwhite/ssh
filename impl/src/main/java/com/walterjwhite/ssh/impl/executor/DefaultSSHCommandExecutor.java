package com.walterjwhite.ssh.impl.executor;

import com.walterjwhite.shell.api.repository.ShellCommandRepository;
import com.walterjwhite.ssh.api.model.command.SSHCommand;
import com.walterjwhite.ssh.impl.AbstractSSHService;
import com.walterjwhite.ssh.impl.SSHCommandProcessExecution;
import java.io.IOException;
import javax.inject.Provider;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSSHCommandExecutor extends AbstractSSHService<SSHCommand> {
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSSHCommandExecutor.class);

  public DefaultSSHCommandExecutor(SSHCommand command) {
    super(command);
  }

  /**
   * This works fine for commands that return in a finite amount of time. Lastly, it waits until the
   * sshCommand is done before processing the input / output.
   *
   * @throws IOException
   */
  @Override
  protected void doExecute(
      Provider<ShellCommandRepository> shellCommandRepositoryProvider,
      SSHClient sshClient,
      Session session)
      throws IOException, InterruptedException {
    LOGGER.info("running in sshCommand");
    session.allocateDefaultPTY();
    try (final Session.Command sshCommand =
        session.exec(command.getShellCommand().getCommandLine())) {
      final SSHCommandProcessExecution sshCommandProcessExecution =
          new SSHCommandProcessExecution(
              sshCommand.getInputStream(),
              sshCommand.getErrorStream(),
              sshCommand.getOutputStream(),
              command,
              sshCommand);
      sshCommandProcessExecution.run();
    }
    LOGGER.info("DONE running in sshCommand");
  }
}
