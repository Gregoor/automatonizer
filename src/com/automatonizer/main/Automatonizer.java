package com.automatonizer.main;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Automatonizer implements EntryPoint {

	@Override
	public void onModuleLoad() {
		Canvas canvas = Canvas.createIfSupported();
		TextBox textBox = new TextBox();
		textBox.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {

			}
		});

		if (canvas == null) {
			RootPanel
					.get()
					.add(new Label(
							"Ain't no Canvas support in yo internet entering software. Get some Chrome, bisch."));
			return;
		}
		
		textBox.setWidth("792px");
		canvas.setWidth("800px");
		canvas.setHeight("400px");
		Style canvasStyle = canvas.getElement().getStyle();
		canvasStyle.setBorderStyle(BorderStyle.SOLID);
		canvasStyle.setBorderWidth(1, Unit.PX);

		RootPanel.get().add(canvas);
		RootPanel.get().getElement()
				.appendChild(Document.get().createBRElement());
		RootPanel.get().add(textBox);

		Context2d ctx = canvas.getContext2d();
		ctx.beginPath();
		ctx.fillRect(50, 50, 50, 50);
		ctx.closePath();
	}
}
