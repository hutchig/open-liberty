/**
 * 
 */
package com.ibm.ws.microprofile.config_fat_tck;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

/**
 * @author hutchig
 *
 */
public class TckSuiteRunner extends Suite {

	private static Class[] TESTS_TO_RUN = null;

	private static Class[] getTestClasses(Class<?> suiteKlass) throws InitializationError {

		Class<?>[] rawTestClasses = getListOfTestClassesJUNIT(suiteKlass);
		Class<?>[] junitWrappers = wrapClassesJUNIT(rawTestClasses);

		return junitWrappers;
	}

	private static Class<?>[] getListOfTestClassesJUNIT(Class<?> suiteKlass) throws InitializationError {
		SuiteClasses annotation = suiteKlass.getAnnotation(SuiteClasses.class);

		if (annotation == null) {
			throw new InitializationError(
					String.format("class '%s' must have a SuiteClasses annotation", suiteKlass.getName()));
		}
		Class<?>[] rawTestClasses = annotation.value();
		return rawTestClasses;
	}

	private static Class<?>[] wrapClassesJUNIT(Class<?>[] rawTestClasses) {

		List results = new ArrayList<Class<?>>(rawTestClasses.length);

		for (int i = 0; i < rawTestClasses.length; i++) {
			Class<?> rawTestClass = rawTestClasses[i];
			results.add(wrap(rawTestClass));
		}

		return (Class<?>[])results.toArray(new Class<?>[0]);

	}

	// TODO make return type more specific
	private static Class<?> wrap(Class<?> rawTestClass) {
		return rawTestClass;
	}

	/**
	 * @param klass
	 * @param builder
	 * @throws InitializationError
	 */
	public TckSuiteRunner(Class<?> klass, RunnerBuilder builder) throws InitializationError {

		// The method below was
		// super(klass, builder);
		// but this calls
		// this(builder, klass, getAnnotatedClasses(klass));

		// So we want to call
		// this(klass, builder.runners(klass, suiteClasses));
		// instead after populating a set of suiteClasses...
		super(klass, builder.runners(klass, getTestClasses(klass)));

	}

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

}
