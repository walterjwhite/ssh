package com.walterjwhite.ssh.impl.service;

import com.walterjwhite.shell.api.repository.ShellCommandRepository;
import com.walterjwhite.ssh.api.SSHCommandService;
import com.walterjwhite.ssh.api.model.command.SSHCommand;
import com.walterjwhite.ssh.api.model.command.SSHExpectCommand;
import com.walterjwhite.ssh.impl.executor.DefaultSSHCommandExecutor;
import com.walterjwhite.ssh.impl.executor.ExpectSSHCommandExecutor;
import javax.inject.Inject;
import javax.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSSHCommandService implements SSHCommandService {
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSSHCommandService.class);

  protected final Provider<ShellCommandRepository> shellCommandRepositoryProvider;

  @Inject
  public DefaultSSHCommandService(Provider<ShellCommandRepository> shellCommandRepositoryProvider) {
    super();
    this.shellCommandRepositoryProvider = shellCommandRepositoryProvider;
  }

  public void execute(SSHCommand... commands) throws Exception {
    for (SSHCommand command : commands) execute(command);
  }

  protected void execute(SSHCommand command) throws Exception {
    new DefaultSSHCommandExecutor(command).execute(shellCommandRepositoryProvider);
  }

  public void execute(SSHExpectCommand... commands) throws Exception {
    for (SSHExpectCommand command : commands) execute(command);
  }

  protected void execute(SSHExpectCommand command) throws Exception {
    new ExpectSSHCommandExecutor(command).execute(shellCommandRepositoryProvider);
  }
}
