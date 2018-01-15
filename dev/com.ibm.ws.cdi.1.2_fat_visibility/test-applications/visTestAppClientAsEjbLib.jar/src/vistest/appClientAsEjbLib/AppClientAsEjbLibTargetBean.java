/*******************************************************************************
 * Copyright (c) 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package vistest.appClientAsEjbLib;

import javax.enterprise.context.ApplicationScoped;

import vistest.framework.TargetBean;
import vistest.qualifiers.InAppClientAsEjbLib;

@ApplicationScoped
@InAppClientAsEjbLib
public class AppClientAsEjbLibTargetBean implements TargetBean {

}
