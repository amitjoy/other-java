package com.schneider.dec.rsp.handlers;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.internal.workbench.E4Workbench;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Shell;

import com.schneider.dec.rcp.events.EventConstants;

public class ShowDevicesGroupsHandler {
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
			IEventBroker broker) throws InvocationTargetException,
			InterruptedException {
		IEclipseContext context = E4Workbench.getServiceContext();
		broker.send(EventConstants.SHOW_DEVICES_GROUPS, new Object());
	}

}