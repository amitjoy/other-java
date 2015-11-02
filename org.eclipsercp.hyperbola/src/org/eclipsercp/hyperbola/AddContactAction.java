/*******************************************************************************
 * Copyright (c) 2005 Jean-Michel Lemieux, Jeff McAffer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Hyperbola is an RCP application developed for the book
 *     Eclipse Rich Client Platform - 
 *         Designing, Coding, and Packaging Java Applications
 * See http://eclipsercp.org
 *
 * Contributors:
 *     Jean-Michel Lemieux and Jeff McAffer - initial API and implementation
 *******************************************************************************/
package org.eclipsercp.hyperbola;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipsercp.hyperbola.model.Session;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.XMPPException;

public class AddContactAction extends AbstractHandler {
	public final static String ID = "org.eclipsercp.hyperbola.addContact";

	public AddContactAction() {
	
	}
	
	public AddContactAction(IWorkbenchWindow window) {
		
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		AddContactDialog d = new AddContactDialog(window.getShell());
		int code = d.open();
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		RosterGroup group = (RosterGroup) selection.getFirstElement();
		if (code == Window.OK) {
			Roster list = Session.getInstance().getConnection().getRoster();
			String user = d.getUserId() + "@" + d.getServer();
			String[] groups = new String[] { group.getName() };
			try {
				list.createEntry(user, d.getNickname(), groups);
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
