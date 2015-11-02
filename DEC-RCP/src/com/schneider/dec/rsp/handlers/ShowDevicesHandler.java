package com.schneider.dec.rsp.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import com.schneider.dec.rcp.events.EventConstants;

public class ShowDevicesHandler {

	MPart devicesView;

	@Execute
	public void execute(
			@Named(IServiceConstants.ACTIVE_SELECTION) Object[] selection,
			EPartService partService, IEventBroker broker) {
		if (selection != null)
			if (selection[1].equals("DEVICE_GROUP")) {
				devicesView = partService.findPart("dec-rcp.part.devices.list");
				devicesView.setVisible(true);
				partService.activate(devicesView);
				broker.send(EventConstants.SHOW_DEVICES, selection[0]);
			}
	}

	@CanExecute
	public boolean canExecute() {
		// TODO Your code goes here
		return true;
	}

}