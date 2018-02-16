package com.walterjwhite.ssh.api.model.command;

import com.walterjwhite.shell.api.model.ShellCommand;
import com.walterjwhite.ssh.api.model.SSHHost;
import com.walterjwhite.ssh.api.model.SSHUser;
import javax.persistence.Column;
import javax.persistence.Entity;

/** Provided class name will perform execution and evaluation of results. */
@Entity
public class SSHExpectCommand extends SSHCommand {
  @Column(nullable = false)
  protected String commandExecutionClassName;

  public SSHExpectCommand(
      SSHHost host, SSHUser user, ShellCommand shellCommand, String commandExecutionClassName) {
    super(host, user, shellCommand);
    this.commandExecutionClassName = commandExecutionClassName;
  }

  public SSHExpectCommand(String commandExecutionClassName) {
    super();
    this.commandExecutionClassName = commandExecutionClassName;
  }

  public SSHExpectCommand() {
    super();
  }

  public String getCommandExecutionClassName() {
    return commandExecutionClassName;
  }

  public void setCommandExecutionClassName(String commandExecutionClassName) {
    this.commandExecutionClassName = commandExecutionClassName;
  }
}
