package com.walterjwhite.ssh.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** superclass used by both SSHCommand and SFTP (file transfers). */
@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
// @PersistenceAware
@MappedSuperclass
public abstract class AbstractSSHEntity extends AbstractEntity {
  @ManyToOne @JoinColumn protected SSHHost host;

  @ManyToOne @JoinColumn protected SSHUser user;
}
