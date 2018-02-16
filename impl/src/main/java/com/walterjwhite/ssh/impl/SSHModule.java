package com.walterjwhite.ssh.impl;

import com.google.inject.AbstractModule;
import com.walterjwhite.ssh.api.SFTPTransferService;
import com.walterjwhite.ssh.api.SSHCommandService;
import com.walterjwhite.ssh.impl.service.DefaultSFTPTransferService;
import com.walterjwhite.ssh.impl.service.DefaultSSHCommandService;

public class SSHModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(SSHCommandService.class).to(DefaultSSHCommandService.class);
    bind(SFTPTransferService.class).to(DefaultSFTPTransferService.class);
  }
}
