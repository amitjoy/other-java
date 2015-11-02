package codesnippetapp.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

import codesnippetapp.CodeSnippetAppConstants;

public class SnippetRepository {
	public ArrayList<SnippetData> snippets = new ArrayList<>();
	public String repositoryPath;
	public boolean isModified = false;

	public SnippetRepository()
	{
		isModified = true;
	}
	
	public boolean save()
	{
		if (repositoryPath == null)
		{
			FileDialog fileDlg = new FileDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
			repositoryPath = fileDlg.open();
			if (repositoryPath == null)
				return false;
		} 
		
		return doSave();
	}

	public boolean doSave()
	{
		try
		{
			FileOutputStream outStream = new FileOutputStream(new File(repositoryPath));
			ObjectOutputStream objOutStream = new ObjectOutputStream(outStream);
			objOutStream.writeObject(snippets);
			outStream.close();
		} catch (Exception e)
		{
			MessageDialog.open(MessageDialog.ERROR, Display.getCurrent().getActiveShell(), "Error Saving Repository", e.getLocalizedMessage(),0);
			return false;
		}
		
		isModified = false;
		return true;
	}
	
	public void open(String repositoryPath, IEventBroker eventBroker) {
		
		try
		{
			FileInputStream inStream = new FileInputStream(new File(repositoryPath));
			ObjectInputStream objInStream = new ObjectInputStream(inStream);
			ArrayList<SnippetData> snippets = (ArrayList<SnippetData>)objInStream.readObject();
			this.snippets = snippets;
			this.repositoryPath = repositoryPath;
			eventBroker.post(CodeSnippetAppConstants.REPOSITORY_OPENED, this);
		}
		catch (Exception e)
		{
			MessageDialog.open(MessageDialog.ERROR, Display.getCurrent().getActiveShell(), "Error Saving Repository", e.getLocalizedMessage(),0);
		}
		isModified = false;
	}

}
