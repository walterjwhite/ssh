package com.walterjwhite.ssh.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import com.walterjwhite.ssh.api.model.command.SSHCommand;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
// @PersistenceCapable
@Entity
public class SSHHost extends AbstractNamedEntity {
  @Column protected int port;

  @Column(unique = true)
  protected String hostkey;

  @OneToMany protected List<SSHCommand> commands = new ArrayList<>();

  public SSHHost(String hostname, int port, String hostkey) {
    super(hostname);
    this.port = port;
    this.hostkey = hostkey;
  }

  public SSHHost(String hostname) {
    super(hostname);
  }
}
