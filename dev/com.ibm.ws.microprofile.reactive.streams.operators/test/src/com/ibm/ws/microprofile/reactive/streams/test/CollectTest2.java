/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.microprofile.reactive.streams.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.streams.operators.CompletionRunner;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.eclipse.microprofile.reactive.streams.operators.spi.ReactiveStreamsEngine;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

/**
 *
 */
public class CollectTest2 extends WASReactiveUT {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        Assert.assertNotNull("list is null", list);

        ReactiveStreamsEngine engine = getEngine();
        Assert.assertNotNull("Engine is null", engine);

        PublisherBuilder<Integer> streamOf = ReactiveStreams.of(1, 2, 3);
        Assert.assertNotNull("streamOf is null", streamOf);

        CompletionRunner<List<Integer>> listOfStream = streamOf.toList();
        Assert.assertNotNull("listOfStream is null", listOfStream);

        CompletionStage<List<Integer>> composed = listOfStream.run(engine);
        Assert.assertNotNull("composed is null", composed);

        List<Integer> awaitedComposed = await(
                composed);
        Assert.assertNotNull("awaitedComposed is null", awaitedComposed);

        assertEquals(awaitedComposed,
                list);
    }

}
