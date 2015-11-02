package com.schneider.dec.rcp.listeners.providers;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

public class SensorsViewerSelectionListener implements SelectionListener {

	MPart devicesGroupsView;
	MPart devicesView;
	MPart sensorsView;
	TableViewer viewer;

	EPartService partService;
	ESelectionService selectionService;
	IEclipseContext context;

	Object item;

	public SensorsViewerSelectionListener(TableViewer viewer,
			MPart devicesGroupsView, MPart devicesView, MPart sensorsView,
			EPartService partService, ESelectionService selectionService,
			IEclipseContext context) {
		super();
		this.devicesGroupsView = devicesGroupsView;
		this.devicesView = devicesView;
		this.sensorsView = sensorsView;
		this.viewer = viewer;
		this.partService = partService;
		this.selectionService = selectionService;
		this.context = context;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.item instanceof TableItem) {
			item = (TableItem) e.item;
			selectionService.setSelection(new Object[] {
					((Widget) item).getData(), "SENSOR" });
		}

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
