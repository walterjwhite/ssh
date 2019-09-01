package com.walterjwhite.ssh.service;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.property.impl.annotation.Property;
import com.walterjwhite.ssh.api.SFTPTransferService;
import com.walterjwhite.ssh.api.SSHPublicKeyPath;
import com.walterjwhite.ssh.api.model.sftp.SFTPTransfer;
import com.walterjwhite.ssh.executor.SFTPExecutor;
import javax.inject.Inject;
import javax.inject.Provider;

public class DefaultSFTPTransferService implements SFTPTransferService {

  protected final String sshPublicKeyPath;
  protected final Provider<Repository> repositoryProvider;

  @Inject
  public DefaultSFTPTransferService(
      @Property(SSHPublicKeyPath.class) final String sshPublicKeyPath,
      Provider<Repository> repositoryProvider) {
    super();
    this.sshPublicKeyPath = sshPublicKeyPath;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public void transfer(SFTPTransfer... transfers) throws Exception {
    for (SFTPTransfer transfer : transfers) transfer(transfer);
  }

  protected void transfer(SFTPTransfer transfer) throws Exception {
    new SFTPExecutor(sshPublicKeyPath, transfer).execute(repositoryProvider);
  }
}
