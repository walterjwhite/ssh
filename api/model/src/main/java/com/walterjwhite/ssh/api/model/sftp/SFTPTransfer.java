package com.walterjwhite.ssh.api.model.sftp;

import com.walterjwhite.ssh.api.model.AbstractSSHEntity;
import com.walterjwhite.ssh.api.model.SSHHost;
import com.walterjwhite.ssh.api.model.SSHUser;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
// @PersistenceCapable
@Entity
public class SFTPTransfer extends AbstractSSHEntity {
  @Column(nullable = false, updatable = false)
  protected String localPath;

  @Column(nullable = false, updatable = false)
  protected String remotePath;

  @EqualsAndHashCode.Exclude
  @Column(updatable = false)
  protected int timeout;

  @Column(updatable = false)
  protected boolean upload = true;

  public SFTPTransfer(SSHHost host, SSHUser user, String localPath, String remotePath) {
    this(host, user, localPath, remotePath, -1);
  }

  public SFTPTransfer(
      SSHHost host, SSHUser user, String localPath, String remotePath, int timeout) {
    super(host, user);
    this.localPath = localPath;
    this.remotePath = remotePath;
    this.timeout = timeout;
  }
}
