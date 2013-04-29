package com.automatonizer.view;

import com.automatonizer.model.State;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.touch.client.Point;

public class StateView {
	
	private final State state;
	private Point position;

	public StateView(State state, Point position) {
		this.state = state;
		this.position = position;
	}
	
	public void go(Context2d context) {
		context.arc(position.getX(), position.getY(), 50, 0, 2 * Math.PI);
		context.setFillStyle("red");
		context.fillText(state.getIdentifier(), position.getX(), position.getY());
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

}
