package codesnippetapp.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;

import codesnippetapp.views.SearchDialog;

public class FindCommandHandler {
	@Execute
	public void executeFind(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
			IEclipseContext ctx) {
		
		SearchDialog dlg = ContextInjectionFactory
				.make(SearchDialog.class, ctx);

		if (dlg.open() != Dialog.OK)
			return;
	}
}