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

package jpa10callback.listeners.orderofinvocation.c2;

import jpa10callback.AbstractCallbackListener;

public class XMLCallbackListenerProtectedC2 extends AbstractCallbackListener {
    private static final XMLCallbackListenerProtectedC2 _singleton = new XMLCallbackListenerProtectedC2();

    public final static AbstractCallbackListener getSingleton() {
        return _singleton;
    }

    public final static void reset() {
        _singleton.resetCallbackData();
    }

    protected void prePersistCallback(Object entity) {
        _singleton.doPrePersist(ProtectionType.PT_PROTECTED);
    }

    protected void postPersistCallback(Object entity) {
        _singleton.doPostPersist(ProtectionType.PT_PROTECTED);
    }

    protected void preUpdateCallback(Object entity) {
        _singleton.doPreUpdate(ProtectionType.PT_PROTECTED);
    }

    protected void postUpdateCallback(Object entity) {
        _singleton.doPostUpdate(ProtectionType.PT_PROTECTED);
    }

    protected void preRemoveCallback(Object entity) {
        _singleton.doPreRemove(ProtectionType.PT_PROTECTED);
    }

    protected void postRemoveCallback(Object entity) {
        _singleton.doPostRemove(ProtectionType.PT_PROTECTED);
    }

    protected void postLoadCallback(Object entity) {
        _singleton.doPostLoad(ProtectionType.PT_PROTECTED);
    }
}