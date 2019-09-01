package com.walterjwhite.ssh.api.model.command;

import com.walterjwhite.shell.api.model.ShellCommand;
import com.walterjwhite.ssh.api.model.AbstractSSHEntity;
import com.walterjwhite.ssh.api.model.SSHHost;
import com.walterjwhite.ssh.api.model.SSHUser;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// TODO: primary key should include the host and user running the command
@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
// @PersistenceCapable
@Entity
public class SSHCommand extends AbstractSSHEntity {
  @ManyToOne @JoinColumn protected ShellCommand shellCommand;

  public SSHCommand(SSHHost host, SSHUser user, ShellCommand shellCommand) {
    super(host, user);
    this.shellCommand = shellCommand;
  }
}
