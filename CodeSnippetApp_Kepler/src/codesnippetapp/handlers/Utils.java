package codesnippetapp.handlers;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import codesnippetapp.data.SnippetRepository;

public class Utils {

	/**
	 * Opens save confirmation dialog if repository is modified
	 *  
	 * @param repository
	 * @param shell
	 * @return 0 if successfully saved, 2 if canceled and 1 if No
	 */
	public static int confirmRepositorySave (SnippetRepository repository, Shell shell)
	{
		if (repository.isModified && repository.snippets.size() > 0)
		{
			MessageDialog msgDlg = 	new MessageDialog(shell, 
					"Save Repository?", null, "Repository is modified. Do you want to save it?",
					MessageDialog.QUESTION_WITH_CANCEL, 
					new String[]{ IDialogConstants.YES_LABEL,
		                    IDialogConstants.NO_LABEL,
		                    IDialogConstants.CANCEL_LABEL }, 0);
			
			int ret = msgDlg.open();
			
			if (ret == 2) //Cancel
				return 2;
			
			if (ret == 0) //Yes
			{
				if (!repository.save())
					return 2;
				return 0;
			}
			
			return 1; //No
		}
		
		return 1; //No
	}
}
