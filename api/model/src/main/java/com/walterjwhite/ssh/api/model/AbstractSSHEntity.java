package com.walterjwhite.ssh.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.util.Objects;
import javax.persistence.*;

/** superclass used by both SSHCommand and SFTP (file transfers). */
@MappedSuperclass
public abstract class AbstractSSHEntity extends AbstractEntity {
  @ManyToOne @JoinColumn protected SSHHost host;

  @ManyToOne @JoinColumn protected SSHUser user;

  public AbstractSSHEntity(SSHHost host, SSHUser user) {
    super();
    this.host = host;
    this.user = user;
  }

  public AbstractSSHEntity() {
    super();
  }

  public SSHHost getHost() {
    return host;
  }

  public void setHost(SSHHost host) {
    this.host = host;
  }

  public SSHUser getUser() {
    return user;
  }

  public void setUser(SSHUser user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractSSHEntity that = (AbstractSSHEntity) o;
    return Objects.equals(host, that.host) && Objects.equals(user, that.user);
  }

  @Override
  public int hashCode() {

    return Objects.hash(host, user);
  }
}
