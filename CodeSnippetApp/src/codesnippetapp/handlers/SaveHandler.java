/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package codesnippetapp.handlers;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import codesnippetapp.data.SnippetRepository;

public class SaveHandler {
	@CanExecute
	public boolean canExecute(
			SnippetRepository repository) {
		return repository.isModified;
	}

	@Execute
	public void execute(SnippetRepository repository) {
		repository.save();
	}
}
