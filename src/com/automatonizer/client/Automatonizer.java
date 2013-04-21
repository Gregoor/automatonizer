package com.automatonizer.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Automatonizer implements EntryPoint {

	@Override
	public void onModuleLoad() {
		Canvas canvas = Canvas.createIfSupported();

		if (canvas == null) {
			RootPanel
					.get()
					.add(new Label(
							"Ain't no Canvas support in yo internet entering software. Get some Chrome, bisch."));
			return;
		}
		canvas.setWidth("500px");
		canvas.setHeight("500px");
		RootPanel.get().add(canvas);

		Context2d ctx = canvas.getContext2d();
		ctx.beginPath();
		ctx.fillRect(0, 0, 200, 200);
		ctx.closePath();
	}
}
