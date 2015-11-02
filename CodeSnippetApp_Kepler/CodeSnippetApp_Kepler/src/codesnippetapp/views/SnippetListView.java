 
package codesnippetapp.views;

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
import org.eclipse.swt.widgets.TableItem;

import codesnippetapp.CodeSnippetAppConstants;
import codesnippetapp.data.SnippetData;
import codesnippetapp.data.SnippetRepository;

public class SnippetListView {
	
	private static String SNIPPET_AT_MOUSE_CLICK = "snippet_at_mouse_click";
	private static int newSnippetCounter = 1;	
	
	TableViewer snippetsList;
	
	@Inject
	public SnippetListView() {
	}
	
	@PostConstruct
	public void postConstruct(Composite parent, final IEclipseContext ctx, 
			EMenuService menuService, final IEventBroker eventBroker) {
		snippetsList = new TableViewer(parent,SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
		
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
		SnippetData snippetData)
	{
		//We will just refresh the entire table
		snippetsList.refresh();
	}
	
}