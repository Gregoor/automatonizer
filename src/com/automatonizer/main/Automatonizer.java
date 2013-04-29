package com.automatonizer.main;

import com.automatonizer.presenter.MachinePresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Automatonizer implements EntryPoint {

	@Override
	public void onModuleLoad() {
		new MachinePresenter().go(RootPanel.get());
	}
}
