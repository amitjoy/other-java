package codesnippetapp.views;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import codesnippetapp.data.SnippetData;
import codesnippetapp.data.SnippetRepository;

public class SearchDialog extends Dialog {

	private Text codeSearchTxt, descSearchText;
	private SnippetRepository repository;
	private java.util.List<SnippetData> searchResult = null;
	private String codeCriteria ,descCriteria;

	@Inject
	public SearchDialog(Shell parentShell, SnippetRepository repository) {
		super(parentShell);
		this.repository = repository;
	}

	@Override
	protected Control createDialogArea(Composite parent) 
	{
		Composite dlgArea = (Composite) super.createDialogArea(parent);
		dlgArea.setLayout(new GridLayout(2, false));

		GridData gridData = null;

		Label lbl1 = new Label(dlgArea, SWT.None);
		lbl1.setText("Find in Code:");

		codeSearchTxt = new Text(dlgArea, SWT.BORDER | SWT.MULTI);
		gridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.FILL;
		codeSearchTxt.setLayoutData(gridData);

		Label lbl2 = new Label(dlgArea, SWT.None);
		lbl2.setText("Find in Desc:");

		descSearchText = new Text(dlgArea, SWT.BORDER | SWT.MULTI);
		gridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.FILL;
		descSearchText.setLayoutData(gridData);

		getShell().setText("Find Snippets");

		return dlgArea;
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(400,150);
	}
	
	@Override
	protected boolean isResizable() {
		return true;
	}
	
	@Override
	protected void okPressed() {
		//Get search strings from input fields
		codeCriteria = codeSearchTxt.getText().trim().toLowerCase();
		descCriteria = descSearchText.getText().trim().toLowerCase();
		
		
		int codeCriteriaLen = codeCriteria.length();
		int descCriteriaLen = descCriteria.length();
		
		//Check if search strings are empty
		if (codeCriteriaLen == 0 && descCriteriaLen == 0)
		{
			MessageDialog.openError(getShell(), "Empty Criteria", "No search criteria entered. Please enter atleast one search criteria");
			return;
		}
		
		//We will store search results in searchResult member variable
		searchResult = new ArrayList<>();
		
		//Iterate over snippets in the repository and 
		//perform a simple text based search
		List<SnippetData> snippets = repository.snippets;
		for (SnippetData snippet : snippets)
		{
			String snippetCode = snippet.code;
			String descCode = snippet.description;
			
			//We will look for match of search criteria 
			//in snippet code OR description
			
			//First check if there is a match in code
			if (snippetCode != null && codeCriteriaLen > 0)
			{
				if (snippetCode.toLowerCase().indexOf(codeCriteria) >= 0)
				{
					//Match found in the code. Add this snippet to result
					searchResult.add(snippet);
				}
				continue;
			}
			
			//Now check if there is a match in snippet description
			if (descCode != null && descCriteriaLen > 0)
			{
				if (descCode.toLowerCase().indexOf(descCriteria) >= 0)
				{
					//Mtach found. Add the snippet to result
					searchResult.add(snippet);
				}
			}
		}
		
		//Call super method, whch will close the dialog box
		super.okPressed();
	}
	
	@Override
	protected void cancelPressed() {
		searchResult = null;
		super.cancelPressed();
	}
	
	public List<SnippetData> getSearchResult() {
		return searchResult;
	}	
	
	public String getCodeSearchCriteria()
	{
		return codeCriteria;
	}
	
	public String getDescriptionSearchCriteria()
	{
		return descCriteria;
	}
	
}
