/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.microprofile.config.test;

import org.apache.maven.cli.MavenCli;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import app1.web.TestServletA;
import componenttest.annotation.Server;
import componenttest.annotation.TestServlet;
import componenttest.custom.junit.runner.FATRunner;
import componenttest.topology.impl.LibertyServer;
import componenttest.topology.utils.FATServletClient;

/**
 * Example Shrinkwrap FAT project:
 * <li> Application packaging is done in the @BeforeClass, instead of ant scripting.
 * <li> Injects servers via @Server annotation. Annotation value corresponds to the
 * server directory name in 'publish/servers/%annotation_value%' where ports get
 * assigned to the LibertyServer instance when the 'testports.properties' does not
 * get used.
 * <li> Specifies an @RunWith(FATRunner.class) annotation. Traditionally this has been
 * added to bytecode automatically by ant.
 * <li> Uses the @TestServlet annotation to define test servlets. Notice that no @Test
 * methods are defined in this class. All of the @Test methods are defined on the test
 * servlet referenced by the annotation, and will be run whenever this test class runs.
 */
@RunWith(FATRunner.class)
public class SimpleTest extends FATServletClient {

    public static final String APP_NAME = "app1";

    @Server("FATServer")
    @TestServlet(servlet = TestServletA.class, path = APP_NAME + "/TestServletA")
    public static LibertyServer server1;

    @BeforeClass
    public static void setUp() throws Exception {
        server1.startServer();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        server1.stopServer();
    }

    @Test
    public void verifyArtifactoryDependency() throws Exception {
    	System.out.println(" in verifyArtifactoryDependency ");
		MvnCmd mvnCmd = new MvnCmd("/Users/hutchig/git/mpConfigTckRunner/tck");
		mvnCmd.run();
    }
    
    
	public class MvnCmd {
		private static final int REQUIRED_PARMS = 2;

		public MvnCmd(String project) {
			this.project = project;
		}

		String project;
		String profile = "liberty-nix";
		String suite = "tck-suite.xml";
		String[] goals = new String[] { "clean", "install" };

		public void run() {
			MavenCli cli = new MavenCli();
			cli.doMain(this.toRunnableStringArray(), project, System.out, System.out);
		}

		 String[] toRunnableStringArray() {
			String[] cmd = new String[2 + goals.length];
			cmd[0] = "-P" + profile;
			cmd[1] = "-DsuiteXmlFile=" + suite;
			System.arraycopy(goals, 0, cmd, REQUIRED_PARMS, goals.length);
			return cmd;
		}
	}


}
