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

package jpa10callback.listeners.defaultlistener;

import jpa10callback.AbstractCallbackListener;

public class DefaultCallbackListenerPublic extends AbstractCallbackListener {
    private final static DefaultCallbackListenerPublic _singleton = new DefaultCallbackListenerPublic();

    public final static AbstractCallbackListener getSingleton() {
        return _singleton;
    }

    public final static void reset() {
        _singleton.resetCallbackData();
    }

    protected void prePersist(Object entity) {
        _singleton.doPrePersist(ProtectionType.PT_PUBLIC);
    }

    protected void postPersist(Object entity) {
        _singleton.doPostPersist(ProtectionType.PT_PUBLIC);
    }

    protected void preUpdate(Object entity) {
        _singleton.doPreUpdate(ProtectionType.PT_PUBLIC);
    }

    protected void postUpdate(Object entity) {
        _singleton.doPostUpdate(ProtectionType.PT_PUBLIC);
    }

    protected void preRemove(Object entity) {
        _singleton.doPreRemove(ProtectionType.PT_PUBLIC);
    }

    protected void postRemove(Object entity) {
        _singleton.doPostRemove(ProtectionType.PT_PUBLIC);
    }

    protected void postLoad(Object entity) {
        _singleton.doPostLoad(ProtectionType.PT_PUBLIC);
    }
}
