package com.walterjwhite.ssh.impl.executor;

import com.walterjwhite.shell.api.repository.ShellCommandRepository;
import com.walterjwhite.ssh.api.model.command.SSHExpectCommand;
import com.walterjwhite.ssh.impl.AbstractSSHService;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.inject.Provider;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;

// @Component
public class ExpectSSHCommandExecutor extends AbstractSSHService<SSHExpectCommand> {
  public ExpectSSHCommandExecutor(SSHExpectCommand command) {
    super(command);
  }

  @Override
  protected void doExecute(
      Provider<ShellCommandRepository> shellCommandRepositoryProvider,
      SSHClient sshClient,
      Session session)
      throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
          InvocationTargetException, InstantiationException {
    // @NOTE: deprecated for now
    //
    //    CommandExecutionResult shellCommand = new CommandExecutionResult();
    //    sshCommand.setShellCommand(shellCommand);
    //    try (final Session.Shell shell = session.startShell()) {
    //      try (final Expect expect =
    //          CommandExecutionUtil.setupExpectHandler(
    //              applicationEventPublisher,
    //              shellCommand,
    //              sshCommand.getTimeout(),
    //              shell.getInputStream(),
    //              shell.getErrorStream(),
    //              shell.getOutputStream())) {
    //        //expect.sendLine(sshCommand.getCommandLine());
    //        //    SSHExpectScript sshExpectScript =
    //        //        (SSHExpectScript)
    //        //            Class.forName(sshCommand.getCommandExecutionClassName())
    //        //                .getConstructor(SSHClient.class, Session.class)
    //        //                .newInstance(/*sshClient, */ session);
    //        //    sshExpectScript.run();
    //
    //        CommandExecutionUtil.setReturnCode(expect, shellCommand);
    //      }
    //    }
  }
}
