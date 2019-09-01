package com.walterjwhite.ssh.service;

import com.walterjwhite.ssh.api.SSHCommandService;
import com.walterjwhite.ssh.api.model.SSHHost;
import com.walterjwhite.ssh.api.model.SSHUser;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class SSHTest {

  protected SSHCommandService sshCommandService;

  protected SSHHost sshHost;
  protected Set<SSHHost> sshHosts;
  protected SSHUser sshUser;

  @Before
  public void before() {
    sshHost = new SSHHost("localhost");
    sshHosts = new HashSet<>();
    sshHosts.add(sshHost);
    sshUser = new SSHUser("user", null, sshHosts);

    System.setProperty("ssh.public-key", "/home/user/.ssh/java_id_ecdsa");
  }

  @Test
  public void testSomething() throws Exception {
    //    final SSHCommand testCommand = new SSHCommand(sshHost, sshUser, "ls -al /tmp", 10);
    /*
        final SSHCommand testCommand =
            new SSHCommand(sshHost, sshUser, new ShellCommand("tail -f /tmp/file", 10));

        sshCommandService.execute(testCommand);
        LOGGER.info("\n\n\n\n\nexecution complete:");
        LOGGER.info("return code:" + testCommand.getShellCommand().getReturnCode());

        for (CommandOutput commandOutput : testCommand.getShellCommand().getOutputs())
          LOGGER.info("output:" + commandOutput.getOutput());
        for (CommandError commandOutput : testCommand.getShellCommand().getErrors())
          LOGGER.info("output:" + commandOutput.getOutput());
    */
  }
}
