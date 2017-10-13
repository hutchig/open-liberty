
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
package com.ibm.ws.microprofile.config_fat_tck;

import org.apache.maven.cli.MavenCli;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import componenttest.topology.utils.FATServletClient;

/**
 * Example Shrinkwrap FAT project:
 * <li>Application packaging is done in the @BeforeClass, instead of ant
 * scripting.
 * <li>Injects servers via @Server annotation. Annotation value corresponds to
 * the server directory name in 'publish/servers/%annotation_value%' where ports
 * get assigned to the LibertyServer instance when the 'testports.properties'
 * does not get used.
 * <li>Specifies an @RunWith(FATRunner.class) annotation. Traditionally this has
 * been added to bytecode automatically by ant.
 * <li>Uses the @TestServlet annotation to define test servlets. Notice that
 * no @Test methods are defined in this class. All of the @Test methods are
 * defined on the test servlet referenced by the annotation, and will be run
 * whenever this test class runs.
 */
// @RunWith(FATRunner.class)
public class TCKClient extends FATServletClient {

	// public static final String APP_NAME = "app1";

	// @Server("FATServer")
	// @TestServlet(servlet = TestServletA.class, path = APP_NAME + "/TestServletA")
	// public static LibertyServer server1;

	@BeforeClass
	public static void setUp() throws Exception {
		// Create a WebArchive that will have the file name 'app1.war' once it's written
		// to a file
		// Include the 'app1.web' package and all of it's java classes and sub-packages
		// Include a simple index.jsp static file in the root of the WebArchive
		// WebArchive app1 = ShrinkWrap.create(WebArchive.class, APP_NAME +
		// ".war").addPackages(true, "app1.web")
		// .addAsWebInfResource(new File("test-applications/" + APP_NAME +
		// "/resources/index.jsp"));
		// Write the WebArchive to 'publish/servers/FATServer/apps/app1.war' and print
		// the contents
		// ShrinkHelper.exportAppToServer(server1, app1);

		// server1.startServer();
		System.out.println("\nTCK1");

	}

	@AfterClass
	public static void tearDown() throws Exception {
		// server1.stopServer();
		System.out.println("\nTCK3");
	}

	@Test
	public void test1() throws Exception {
	   
		// System.out.println("\nTCK2");
		// InvocationRequest request = new DefaultInvocationRequest();
		// request.setPomFile( new File( "/path/to/pom.xml" ) );
		// request.setGoals( Arrays.asList( "clean", "install" ) );
		//
		// Invoker invoker = new DefaultInvoker();
		// invoker.execute( request );
		// MavenCli maven = new MavenCli();
		// maven.doMain(new String[]{"install"}, "path/to/project/root", System.out,
		// System.out);

		MvnCmd mvnCmd;

		System.out.println(" in main ");
		mvnCmd = new MvnCmd("/Users/hutchig/git/mpConfigTckRunner/tck");
		mvnCmd.run();

		// MavenCli cli = new MavenCli();
		// cli.doMain(
		// new String[] { "-P liberty-nix", "-DsuiteXmlFile=tck-suite.xml", "clean",
		// "install" }, "/Users/hutchig/git/mpConfigTckRunner/tck", System.out,
		// System.out
		// );
		System.out.println("After calling embedded maven");
	}

	class MvnCmd {
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

// package com.ibm.ws.microprofile.config_fat_tck;
//
// import org.junit.AfterClass;
// import org.junit.BeforeClass;
// import org.junit.Test;
//
// import com.ibm.ws.microprofile.config_fat_tck.TckSuiteRunner.TckMvn;
//
// import app1.web.TestServletA;
// import componenttest.annotation.Server;
// import componenttest.annotation.TestServlet;
// import componenttest.topology.impl.LibertyServer;
// import componenttest.topology.utils.FATServletClient;
//
// public abstract class TCKClient extends FATServletClient{
// public abstract TckMvn getTckMvn();
//
// public static final String APP_NAME = "app1";
//
// public TCKClient(){
// super();
// }
//
// @Server("FATServer")
// @TestServlet(servlet = TestServletA.class, path = APP_NAME + "/TestServletA")
// public static LibertyServer server1;
// public static TckMvn tckMvn;
//
// @BeforeClass
// public static void setUp() throws Exception {
// System.out.println("TCK: @BeforeClass");
// }
//
// @AfterClass
// public static void tearDown() throws Exception {
// System.out.println("TCK: @AfterClass");
// }
//
// @Test
// public void test1() throws Exception {
// System.out.println("TCK: @Test" /*+ tckMvn.pom() + " "+ tckMvn.goals() + " "
// + tckMvn.tckSuiteXml() + " " + tckMvn.options()*/);
// }
//
// }
