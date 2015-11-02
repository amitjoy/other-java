 
package codesnippetapp.views;

import java.util.Arrays;
import java.util.Comparator;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.swt.modeling.EMenuService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import codesnippetapp.CodeSnippetAppConstants;
import codesnippetapp.data.SnippetData;
import codesnippetapp.data.SnippetRepository;

public class SnippetListView {
	
	private static String SNIPPET_AT_MOUSE_CLICK = "snippet_at_mouse_click";
	private static int newSnippetCounter = 1;	
	
	TableViewer snippetsList;
	
	@PostConstruct
	public void postConstruct(Composite parent, final IEclipseContext ctx, 
			EMenuService menuService, final IEventBroker eventBroker) {
			
		snippetsList = new TableViewer(parent,SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
		
		//Set CSS ID for the TableViewer
		snippetsList.getTable().setData("org.eclipse.e4.ui.css.id", "snippetsList");
		
		SnippetRepository repository = ctx.get(SnippetRepository.class);

		snippetsList.setContentProvider(new IStructuredContentProvider() {
			
			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}
			
			@Override
			public void dispose() {
			}
			
			@Override
			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof SnippetRepository)
				{
					return ((SnippetRepository)inputElement).snippets.toArray();
				}
				
				return new Object[]{};
			}
		});
		
		
		snippetsList.setInput(repository);
		
		//Register pop-up menu
		menuService.registerContextMenu(snippetsList.getTable(), "codesnippetapp.snippetlist.popupmenu");
		
		//Add mouse listener to check if there is a snippet at mouse click
		snippetsList.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e)
			{
				if (e.button == 1) //Ignore if left mouse button
					return;
				//Get snippet at the location of mouse click
				TableItem itemAtClick = snippetsList.getTable().getItem(new 
					Point(e.x, e.y));
				if (itemAtClick != null)
				{
					//Add selected snippet to the context
					ctx.set(SNIPPET_AT_MOUSE_CLICK, itemAtClick.getData());
				}
				else
				{
					//No snippet at the mouse click. Remove the variable
				 	ctx.remove(SNIPPET_AT_MOUSE_CLICK);
				}
			}
		});
		
		//Add selection change listener for the TableViewer. 
		//Broadcast onSnippetSelectionChange event in the event handler
		snippetsList.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection)event.getSelection();
				SnippetData snippetData = (SnippetData)selection.getFirstElement();
				if (snippetData != null)
					eventBroker.post(CodeSnippetAppConstants.SNIPPET_SELECTION_CHANGE_EVENT,
							snippetData);
			}
		});
		
		eventBroker.subscribe(CodeSnippetAppConstants.REPOSITORY_OPENED, new EventHandler() {
			
			@Override
			public void handleEvent(Event event) {
				
				Object data = event.getProperty(IEventBroker.DATA);
				if (data != null && data instanceof SnippetRepository)
				{
					SnippetRepository repository = (SnippetRepository)data;
					snippetsList.setInput(repository);
					snippetsList.refresh();
					if (repository.snippets.size() > 0)
						snippetsList.setSelection(new StructuredSelection(repository.snippets.get(0)));
				}
				
			}
		});
		
	}
	
	@Inject @Optional
	public void onAddNewSnippet (@UIEventTopic(CodeSnippetAppConstants.NEW_SNIPPET_EVENT) Object data,
			SnippetRepository repository)
	{
		SnippetData newSnippet = new SnippetData("Untitled" + (newSnippetCounter++));
		repository.snippets.add(newSnippet);
		snippetsList.refresh();
		snippetsList.setSelection(new StructuredSelection(newSnippet));
		
	}
	
	@Inject @Optional
	public void onSnippetSaved(@UIEventTopic (CodeSnippetAppConstants.SNIPPET_SAVED_EVENT) 
		SnippetData snippetData, SnippetRepository repository)
	{
		//We will just refresh the entire table
		snippetsList.refresh();
		repository.isModified = true;
	}
	
	@Inject @Optional
	public void onSnippetDelete (@UIEventTopic (CodeSnippetAppConstants.SNIPPET_DELETE_EVENT) Object obj, SnippetRepository repository, IEventBroker eventBroker)
	{
		//Get the selected snippet
		StructuredSelection selection = (StructuredSelection)snippetsList.getSelection();
		SnippetData snippetData = (SnippetData)selection.getFirstElement();
		if (snippetData == null)
			return; //TODO: display message
		
		//Remove selected snippet from the repository
		repository.snippets.remove(snippetData);
		
		//Publish before delete event so that search results view could update
		//search results
		eventBroker.post(CodeSnippetAppConstants.BEFORE_SNIPPET_DELETE_EVENT, snippetData);
		
		//If this was the last snippet in the repository, then remove it from the TableViewer
		//and publish selection change event with null snippet. This will inform
		//SnippetDetailsView that there is no current snippet selection. In this case 
		//SnippetDetailsView will reset all display fields.
		if (repository.snippets.size() == 0)
		{
			snippetsList.remove(snippetData);
			eventBroker.post(CodeSnippetAppConstants.SNIPPET_SELECTION_CHANGE_EVENT, null);
			return;
		}
		
		//If there are one or more snippets after deleting the current one,
		//we want to set selection to the snippet that was just before the 
		//deleted snippet. 
		//Get the index of deleted snippet and set selection to one before that index. 
		Table snippetsTable = snippetsList.getTable();
		TableItem[] tableItems = snippetsTable.getItems();
		
		TableItem[] selectedItems = snippetsTable.getSelection();
		
		int index = Arrays.binarySearch(tableItems, selectedItems[0], new Comparator<TableItem>() {
		    public int compare(TableItem o1, TableItem o2)
		    {
		    	if (o1.getData() == o2.getData())
		    		return 0;
		    	else
		    	{
		    		//We don't really care if one is greater or lesser 
		    		//than the other if they are not equal
		    		return -1; 
		    	}
		    }
		});
		
		SnippetData nextSnippetSelection = null;
		if (index >= 1)
			nextSnippetSelection = (SnippetData)tableItems[index-1].getData();
		else if (index < tableItems.length-1)
			nextSnippetSelection = (SnippetData)tableItems[index+1].getData();
		
		snippetsList.remove(snippetData);
		
		if (nextSnippetSelection != null)
			snippetsList.setSelection(new StructuredSelection(nextSnippetSelection));
	}
	
	
	@Inject @Optional
	public void searchResultSelectionChanged(@UIEventTopic (CodeSnippetAppConstants.SEARCH_RESULT_SELECTION_CHANGE_EVENT) SnippetData snippet)
	{
		//Set selection to the snippet selected in the search results view
		snippetsList.setSelection(new StructuredSelection(snippet));
	}
	
}