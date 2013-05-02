package com.automatonizer.view;

import com.automatonizer.model.State;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.TextMetrics;
import com.google.gwt.touch.client.Point;

public class StateView {

	private final State state;
	private AutomatonCanvas canvas;
	private Point position;

	public StateView(AutomatonCanvas canvas, State state, Point position) {
		this.canvas = canvas;
		this.state = state;
		this.position = position;
	}

	public void go() {
		drawCircle(30, "black");
		drawCircle(28, "white");

		Context2d textCtx = canvas.getTextContext();
		textCtx.setFillStyle("black");
		textCtx.setFont("16px Arial");
		TextMetrics metrics = textCtx.measureText(state.getIdentifier());
		textCtx.fillText(state.getIdentifier(),
				position.getX() - metrics.getWidth() / 2, position.getY()
						+ textCtx.measureText("M").getWidth() / 2);
	}

	public State getState() {
		return state;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	private void drawCircle(int radius, String color) {
		Context2d ctx = canvas.getObjectContext();
		ctx.beginPath();
		ctx.setFillStyle(color);
		ctx.arc(position.getX(), position.getY(), radius, 0, 2 * Math.PI);
		ctx.fill();
		ctx.closePath();
	}

}
