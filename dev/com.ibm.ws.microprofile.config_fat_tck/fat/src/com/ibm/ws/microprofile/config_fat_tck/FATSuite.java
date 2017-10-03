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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

@RunWith(TckSuiteRunner.class)
@SuiteClasses({
  SimpleTest.class,
})
public class FATSuite {
}

/*
 * The parent of the @RunWith(TckSuiteRunner) class
 * above looks for
 * the value (annotation default parameter name) of SuiteClasses
 * in the getAnnotatedClasses method of the junit Suite class. 
 */
//@SuiteClasses({
//                SimpleTest.class,
//})

/*
 * We replace the implementation of   private static Class<?>[] getAnnotatedClasses(Class<?> klass) throws InitializationError {
 * in TckSuiteRunner with something that returns a Class<?>[] array of wrapper classes.
 * 
 * As it is called statically from
 * 
 *     public Suite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
 *      this(builder, klass, getAnnotatedClasses(klass));
 *     }
 *
 * We have to replace the use of that contructor
 * 
 */

