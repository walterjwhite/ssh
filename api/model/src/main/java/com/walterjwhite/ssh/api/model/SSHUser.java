package com.walterjwhite.ssh.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class SSHUser extends AbstractNamedEntity {
  @Column protected String publicKey;

  @ManyToMany @JoinTable protected Set<SSHHost> hosts = new HashSet<>();

  public SSHUser(String name) {
    super(name);
  }

  public SSHUser(String username, String publicKey, Set<SSHHost> hosts) {
    super(username);

    this.publicKey = publicKey;
    if (hosts != null && !hosts.isEmpty()) this.hosts.addAll(hosts);
  }

  public SSHUser() {
    super();
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  public Set<SSHHost> getHosts() {
    return hosts;
  }

  public void setHosts(Set<SSHHost> hosts) {
    this.hosts = hosts;
  }
}
