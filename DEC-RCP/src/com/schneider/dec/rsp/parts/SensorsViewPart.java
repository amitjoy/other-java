package com.schneider.dec.rsp.parts;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.schneider.dec.rcp.events.EventConstants;
import com.schneider.dec.rcp.listeners.providers.DevicesGroupsViewerSelectionListener;
import com.schneider.dec.rcp.listeners.providers.IDColumnLabelProvider;
import com.schneider.dec.rcp.listeners.providers.NameColumnLabelProvider;
import com.schneider.dec.rcp.listeners.providers.SensorsViewerSelectionListener;
import com.schneider.dec.rcp.viewer.filter.FilteredTable;
import com.schneider.dec.rcp.viewer.filter.FilteredTable.FilterMatcher;

public class SensorsViewPart {
	MPart devicesGroupsView;
	MPart devicesView;
	MPart sensorsView;
	TableViewer viewer;

	@Inject
	EPartService partService;

	@Inject
	IEclipseContext context;

	@Inject
	IEventBroker broker;

	@Inject
	ESelectionService selectionService;

	@Inject
	public SensorsViewPart() {
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		devicesGroupsView = partService.findPart("dec-rcp.part.devices.groups");
		devicesView = partService.findPart("dec-rcp.part.devices.list");
		sensorsView = partService.findPart("dec-rcp.part.devices.sensors");

		FilterMatcher matcher = new FilterMatcher();
		FilteredTable table = new FilteredTable(parent, SWT.SINGLE | SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION, matcher,
				true);
		viewer = table.getViewer();
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.getTable().addSelectionListener(
				new SensorsViewerSelectionListener(viewer, devicesGroupsView,
						devicesView, sensorsView, partService,
						selectionService, context));

		TableViewerColumn idColumnViewer = new TableViewerColumn(viewer,
				SWT.NONE);
		idColumnViewer.getColumn().setWidth(50);
		idColumnViewer.getColumn().setText("#");
		idColumnViewer.setLabelProvider(new IDColumnLabelProvider(viewer));

		TableViewerColumn deviceGroupsNameColumnViewer = new TableViewerColumn(
				viewer, SWT.NONE);
		deviceGroupsNameColumnViewer.getColumn().setWidth(800);
		deviceGroupsNameColumnViewer.getColumn().setText("Sensor Name");
		deviceGroupsNameColumnViewer
				.setLabelProvider(new NameColumnLabelProvider(viewer));
		viewer.getTable().setHeaderVisible(true);
	}

	@Focus
	public void onFocus() {
	}

	@Inject
	@Optional
	public void listDevices(
			@UIEventTopic(EventConstants.SHOW_SENSORS) final Object data,
			@Named(IServiceConstants.ACTIVE_SHELL) Shell shell)
			throws InvocationTargetException, InterruptedException {
		final List<String> list = new ArrayList<String>();
		ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
		dialog.run(false, false, new IRunnableWithProgress() {

			@Override
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				monitor.beginTask("Fetching Sensors Names Under Device "+data, 100);
				for (int i = 0; i < 5; i++) {
					TimeUnit.SECONDS.sleep(2);
					monitor.worked(20);
					if (i == 3)
						for (int j = 0; j < 100; j++) {
							list.add("C");
						}
				}
				viewer.setInput(list);
				monitor.done();
			}
		});
	}

}