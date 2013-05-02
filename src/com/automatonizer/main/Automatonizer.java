package com.automatonizer.main;

import com.automatonizer.presenter.AutomatonPresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Automatonizer implements EntryPoint {

	@Override
	public void onModuleLoad() {
		new AutomatonPresenter(RootPanel.get());
	}
}
