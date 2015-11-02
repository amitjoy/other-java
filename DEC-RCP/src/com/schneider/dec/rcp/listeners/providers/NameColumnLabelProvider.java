package com.schneider.dec.rcp.listeners.providers;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

public class NameColumnLabelProvider extends ColumnLabelProvider {

	private int index;
	private TableViewer tableViewer;
	
	public  NameColumnLabelProvider(TableViewer viewer){
		super();
		this.tableViewer = viewer;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof String) {
			return element.toString();
		}
		return null;
	}
	
	@Override
	public void update(ViewerCell cell) {
		cell.setText(String.valueOf(tableViewer.getTable().indexOf(
				(TableItem) cell.getItem())));
		index = tableViewer.getTable().indexOf((TableItem) cell.getItem());
		super.update(cell);
	}

	@Override
	public Color getBackground(Object element) {
		if (index % 2 != 0)
			return new Color(Display.getCurrent(), 255, 229, 204);
		else
			return new Color(Display.getCurrent(), 255, 255, 255);
	}
}
