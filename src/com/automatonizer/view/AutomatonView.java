package com.automatonizer.view;

import com.automatonizer.model.State;
import com.automatonizer.presenter.AutomatonPresenter.Display;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AutomatonView extends Composite implements Display {

	private final static int TEXT_BOX_PX_HEIGHT = 30;
	private final static int CANVAS_PX_HEIGHT = 400;
	private final static int CANVAS_PX_WIDTH = 800;
	
	private Point offset = new Point();

	private TextBox textBox;
	private Canvas canvas;

	public AutomatonView() {
		DockLayoutPanel panel = new DockLayoutPanel(Unit.EM);
		initWidget(panel);
		canvas = Canvas.createIfSupported();

		textBox = new TextBox();
		if (canvas == null) {
			panel.add(new Label(
					"Ain't no Canvas support in yo internet entering software. Get some Chrome, bisch."));
			return;
		}

		textBox.setWidth("792px");
		canvas.setWidth(CANVAS_PX_WIDTH+"px");
		canvas.setCoordinateSpaceHeight(CANVAS_PX_HEIGHT);
		canvas.setCoordinateSpaceWidth(800);
		canvas.setHeight(CANVAS_PX_HEIGHT + "px");
		Style canvasStyle = canvas.getElement().getStyle();
		canvasStyle.setBorderStyle(BorderStyle.SOLID);
		canvasStyle.setBorderWidth(1, Unit.PX);

		panel.setHeight(TEXT_BOX_PX_HEIGHT + CANVAS_PX_HEIGHT + "px");
		panel.addSouth(textBox, 2);
		panel.add(canvas);

		Context2d context = canvas.getContext2d();
		context.beginPath();
//		context.fillRect(50, 50, 50, 50);
		new StateView(new State("Z1"), new Point(50, 50)).go(context);
		new StateView(new State("Z2"), new Point(150, 150)).go(context);
		context.fill();
		context.closePath();
	}

	@Override
	public HasClickHandlers getCanvas() {
		return canvas;
	}

	@Override
	public HasClickHandlers getInput() {
		return textBox;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
