package com.automatonizer.presenter;

import com.automatonizer.view.OverView;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class AutomatonPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getCanvas();

		HasClickHandlers getInput();

		Widget asWidget();
	}

	private final Display display;
	
	public AutomatonPresenter() {
		display = new OverView();
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	private void bind() {
	}
}
