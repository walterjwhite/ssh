package com.walterjwhite.ssh.api.model.command;

import com.walterjwhite.shell.api.model.ShellCommand;
import com.walterjwhite.ssh.api.model.AbstractSSHEntity;
import com.walterjwhite.ssh.api.model.SSHHost;
import com.walterjwhite.ssh.api.model.SSHUser;
import java.util.Objects;
import javax.persistence.*;

// TODO: primary key should include the host and user running the command
@Entity
public class SSHCommand extends AbstractSSHEntity {
  @ManyToOne @JoinColumn protected ShellCommand shellCommand;

  public SSHCommand(SSHHost host, SSHUser user, ShellCommand shellCommand) {
    super(host, user);
    this.shellCommand = shellCommand;
  }

  public SSHCommand() {
    super();
  }

  public ShellCommand getShellCommand() {
    return shellCommand;
  }

  public void setShellCommand(ShellCommand shellCommand) {
    this.shellCommand = shellCommand;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    SSHCommand that = (SSHCommand) o;
    return Objects.equals(shellCommand, that.shellCommand);
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.hashCode(), shellCommand);
  }

  @Override
  public String toString() {
    return "SSHCommand{"
        + "shellCommand="
        + shellCommand
        + ", host="
        + host
        + ", user="
        + user
        + '}';
  }
}
