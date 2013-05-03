package com.automatonizer.view;

import com.automatonizer.model.State;
import com.automatonizer.model.Vector;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.TextMetrics;

public class StateView implements View {

	public static final int RADIUS = 30;
	public final State state;

	public final Vector position;
	private AutomatonCanvas canvas;

	public StateView(AutomatonCanvas canvas, State state, int x, int y) {
		this(canvas, state, new Vector(x, y));
	}

	public StateView(AutomatonCanvas canvas, State state, Vector position) {
		this.canvas = canvas;
		this.state = state;
		this.position = position;
	}

	@Override
	public void draw() {
		drawCircle(RADIUS, "black");
		drawCircle(RADIUS - 2, "white");

		Context2d textCtx = canvas.getTextContext();
		textCtx.setFillStyle("black");
		textCtx.setFont("16px Arial");
		TextMetrics metrics = textCtx.measureText(state.getIdentifier());
		textCtx.fillText(state.getIdentifier(),
				getWorldPosition().x - metrics.getWidth() / 2,
				getWorldPosition().y + textCtx.measureText("M").getWidth() / 2);
	}

	/**
	 * Based on this SO-answer: http://stackoverflow.com/a/7227057/1501916
	 */
	public boolean inRadius(int x, int y) {
		int dx = (int) Math.abs(x - getWorldPosition().x);
		int dy = (int) Math.abs(y - getWorldPosition().y);

		if (dx > RADIUS || dy > RADIUS) return false;
		if (dx + dy <= RADIUS
				|| Math.pow(dx, 2) + Math.pow(dy, 2) <= Math.pow(RADIUS, 2)) return true;

		return false;
	}

	public void setWorldPosition(int x, int y) {
		position.move(x - canvas.offset.x, y - canvas.offset.y);
	}

	public Vector getWorldPosition() {
		return position.clone().plus(canvas.offset);
	}

	private void drawCircle(int radius, String color) {
		Context2d ctx = canvas.getObjectContext();
		ctx.beginPath();
		ctx.setFillStyle(color);
		ctx.arc(getWorldPosition().x, getWorldPosition().y, radius, 0,
				2 * Math.PI);
		ctx.fill();
		ctx.closePath();
	}

}
