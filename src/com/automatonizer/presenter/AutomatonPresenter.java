package com.automatonizer.presenter;

import com.automatonizer.view.AutomatonView;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
		display = new AutomatonView();
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	private void bind() {
		display.getInput().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
			}
		});
	}
}
