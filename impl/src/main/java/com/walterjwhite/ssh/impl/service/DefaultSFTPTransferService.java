package com.walterjwhite.ssh.impl.service;

import com.walterjwhite.shell.api.repository.ShellCommandRepository;
import com.walterjwhite.ssh.api.SFTPTransferService;
import com.walterjwhite.ssh.api.model.sftp.SFTPTransfer;
import com.walterjwhite.ssh.impl.executor.SFTPExecutor;
import javax.inject.Inject;
import javax.inject.Provider;

public class DefaultSFTPTransferService implements SFTPTransferService {

  protected final Provider<ShellCommandRepository> shellCommandRepositoryProvider;

  @Inject
  public DefaultSFTPTransferService(
      Provider<ShellCommandRepository> shellCommandRepositoryProvider) {
    super();
    this.shellCommandRepositoryProvider = shellCommandRepositoryProvider;
  }

  @Override
  public void transfer(SFTPTransfer... transfers) throws Exception {
    for (SFTPTransfer transfer : transfers) transfer(transfer);
  }

  protected void transfer(SFTPTransfer transfer) throws Exception {
    new SFTPExecutor(transfer).execute(shellCommandRepositoryProvider);
  }
}
