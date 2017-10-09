/**
 * 
 */
package com.ibm.ws.microprofile.config_fat_tck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

/**
 * A JUnit Suite Runner targetted at runing Mavan TCK Suites
 *
 */
public class TckSuiteRunner extends Suite {

	/**
	 * The <code>TckMvn</code> annotation specifies the maven options to be run when
	 * a JUnit suite is annotated with <code>@RunWith(TckSuiteRunner.class)</code>.
	 * 
	 * The parameters work as standard mvn command line parameters and are fed into
	 * 
	 * @see <a href="http://maven.apache.org/shared/maven-invoker/</a> We start by
	 *      running a whole suite (as a MVP). Once we have this running
	 *      <ol>
	 *      <li>1 we can cut it down to a particular test using surefire
	 *      constructs</li>
	 *      <li>2 work on generating a Junit wrapper for one class</li>
	 *      <li>3 alter this suite code to create a suite out of those wrappers</li>
	 *      </ol>
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@Inherited
	public @interface TckMvn {

		String pom() default "pom.xml";

		String goals() default "test";

		String profile() default "liberty-nix";

		String tckSuiteXml() default "tck-suite.xml";

		String options() default "";

	}

	/**
	 * This is the constructor which is 'usually' called from the JUnit framework
	 * 
	 * @param klass
	 * @param builder
	 * @throws InitializationError
	 */
	public TckSuiteRunner(Class<?> klass, RunnerBuilder builder) throws InitializationError {
		super(klass, builder.runners(klass, getTestClasses(klass)));
	}

	/**
	 * This method just encapsultates the getting of the parameters used in a tckMvn
	 * to help with unit testing.
	 * 
	 * @param suiteKlass
	 * @return a array of JUnit test classes
	 * @throws InitializationError
	 */
	private static Class[] getTestClasses(Class<?> suiteKlass) throws InitializationError {
		TckMvn tckMvn = getMvnCli(suiteKlass);
		Class<?>[] junitWrappers = getJunitWrapper(tckMvn);
		return junitWrappers;
	}

	/**
	 * This method's job is to create a Junit Class that can run a mvn build as a
	 * JUnit test. We are a bit constrained by the JUnit framework doing this work
	 * as part of a static constructor and expecting a class to be returned and not
	 * an instance of something else - so we dynamically create an anon inner class
	 * that captures the parameters.
	 *
	 * The heavy lifting of driving the maven invoker is in
	 * 
	 * @see TCKClient
	 * 
	 * @param tckMvn
	 * @return a Junit test class
	 */
	private static Class<?>[] getJunitWrapper(TckMvn tckMvn) {
 		Class<?>[] result = new Class<?>[1];
 		//TCKClient.tckMvn = tckMvn;
		result[0] = TCKClient.class;

		// Creating an anonymous inner subclass results in
		// <testcase classname="com.ibm.ws.microprofile.config_fat_tck.TckSuiteRunner$1"
		// name="initializationError" time="0.006">
		// <error message="Test class should have exactly one public constructor"
		// type="java.lang.Exception">java.lang.Exception: Test class should have
		// exactly one public constructor
		// at
		// com.ibm.ws.microprofile.config_fat_tck.TckSuiteRunner.&lt;init&gt;(TckSuiteRunner.java:64)
		// at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
		// at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
		// </error>
		// So the code below is not a goer!
		// result[0] = new TCKClient() {
		// public TckMvn getTckMvn() {
		// return mvn;
		// }
		// }.getClass();

		// ClassLoader classLoader = TckSuiteRunner.class.getClassLoader();
		// final TckMvn mvn = tckMvn;
		// Class<? extends TCKClient> proxy = new TCKClient() {
		// public TckMvn getTckMvn() {
		// return mvn;
		// }
		// }.getClass();
		//
		// result[0] = (Class<?>) Proxy.newProxyInstance(classLoader, new Class<?>[] {
		// TCKProxy.class },
		// new PassthroughInvocationHandler(proxy));
		return result;
	}

	private static TckMvn getMvnCli(Class<?> suiteKlass) throws InitializationError {
		TckMvn annotation = suiteKlass.getAnnotation(TckMvn.class);
		if (annotation == null) {
			throw new InitializationError(
					String.format("class '%s' must have a TckMvn annotation", suiteKlass.getName()));
		}
		return annotation;
	}

	// private static Class<?>[] wrapClassesMvn(Class<?>[] rawTestClasses) {
	// return rawTestClasses;
	// }
	// private static Class<?>[] getListOfTestClassesSurefire(Class<?>
	// suiteKlass) throws InitializationError {
	//
	//
	// TckSuiteXml annotation = suiteKlass.getAnnotation(TckSuiteXml.class);
	//
	// if (annotation == null) {
	// throw new InitializationError(
	// String.format("class '%s' must have a SuiteClasses annotation",
	// suiteKlass.getName()));
	// }
	//
	// String xml = annotation.value();
	//
	// MavenLauncher mavenLauncher = new MavenLauncher( testClass, sourceName,
	// suffix, cli );
	// return new SurefireLauncher( mavenLauncher );
	//
	// return rawTestClasses;
	// }

	// private static Class<?>[] getListOfTestClassesJUNIT(Class<?> suiteKlass)
	// throws InitializationError {
	// SuiteClasses annotation = suiteKlass.getAnnotation(SuiteClasses.class);
	//
	// if (annotation == null) {
	// throw new InitializationError(
	// String.format("class '%s' must have a SuiteClasses annotation",
	// suiteKlass.getName()));
	// }
	// Class<?>[] rawTestClasses = annotation.value();
	// return rawTestClasses;
	// }

	// private static Class<?>[] wrapClassesJUNIT(Class<?>[] rawTestClasses) {
	//
	// List results = new ArrayList<Class<?>>(rawTestClasses.length);
	//
	// for (int i = 0; i < rawTestClasses.length; i++) {
	// Class<?> rawTestClass = rawTestClasses[i];
	// results.add(wrap(rawTestClass));
	// }
	//
	// return (Class<?>[]) results.toArray(new Class<?>[0]);
	//
	// }

	// // TODO make return type more specific
	// private static Class<?> wrap(Class<?> rawTestClass) {
	// return rawTestClass;
	// }

	/**
	 * @param builder
	 * @param classes
	 * @throws InitializationError
	 */
	public TckSuiteRunner(RunnerBuilder builder, Class<?>[] classes) throws InitializationError {
		super(builder, classes);
	}

	/**
	 * @param klass
	 * @param suiteClasses
	 * @throws InitializationError
	 */
	public TckSuiteRunner(Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
		super(klass, suiteClasses);
	}

	/**
	 * @param klass
	 * @param runners
	 * @throws InitializationError
	 */
	public TckSuiteRunner(Class<?> klass, List<Runner> runners) throws InitializationError {
		super(klass, runners);
	}

	/**
	 * @param builder
	 * @param klass
	 * @param suiteClasses
	 * @throws InitializationError
	 */
	public TckSuiteRunner(RunnerBuilder builder, Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
		super(builder, klass, suiteClasses);
	}

	/**
	 * The <code>SuiteClasses</code> annotation specifies the classes to be run when
	 * a class annotated with <code>@RunWith(Suite.class)</code> is run.
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@Inherited
	public @interface TckSuiteXml {
		String value();
	}
}
