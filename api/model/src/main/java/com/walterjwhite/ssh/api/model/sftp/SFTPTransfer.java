package com.walterjwhite.ssh.api.model.sftp;

import com.walterjwhite.ssh.api.model.AbstractSSHEntity;
import com.walterjwhite.ssh.api.model.SSHHost;
import com.walterjwhite.ssh.api.model.SSHUser;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SFTPTransfer extends AbstractSSHEntity {
  @Column(nullable = false, updatable = false)
  protected String localPath;

  @Column(nullable = false, updatable = false)
  protected String remotePath;

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

  public SFTPTransfer() {
    super();
  }

  public String getLocalPath() {
    return localPath;
  }

  public void setLocalPath(String localPath) {
    this.localPath = localPath;
  }

  public String getRemotePath() {
    return remotePath;
  }

  public void setRemotePath(String remotePath) {
    this.remotePath = remotePath;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  public boolean isUpload() {
    return upload;
  }

  public void setUpload(boolean upload) {
    this.upload = upload;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    SFTPTransfer that = (SFTPTransfer) o;
    return upload == that.upload
        && Objects.equals(localPath, that.localPath)
        && Objects.equals(remotePath, that.remotePath);
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.hashCode(), localPath, remotePath, upload);
  }
}
