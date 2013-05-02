package com.automatonizer.presenter;

import com.automatonizer.view.AutomatonCanvas;
import com.automatonizer.view.OverView;
import com.automatonizer.view.StateView;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.ui.HasWidgets;

public class AutomatonPresenter {

	private boolean moveCanvas = false;
	private Point lastPosition;

	private StateView moveState;

	private final OverView overView;
	private final AutomatonCanvas automatonView;

	public AutomatonPresenter(HasWidgets container) {
		overView = new OverView();
		automatonView = overView.automatonView;

		bind();
		container.clear();
		container.add(overView.asWidget());
	}

	private void bind() {
		HasAllMouseHandlers canvas = automatonView.getHandlerCanvas();
		canvas.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				StateView stateView = findStateAt(event.getX(), event.getY());
				if (stateView != null) {
					moveState = stateView;
					disableMoveMode();
					return;
				}
				moveCanvas = true;
			}
		});
		canvas.addMouseUpHandler(new MouseUpHandler() {
			@Override
			public void onMouseUp(MouseUpEvent event) {
				disableMoveMode();
				moveState = null;
			}
		});
		canvas.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				disableMoveMode();
				moveState = null;
			}
		});
		canvas.addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if (moveCanvas) {
					if (lastPosition != null) {
						double x = event.getX() - lastPosition.getX();
						double y = event.getY() - lastPosition.getY();
						automatonView.offset = automatonView.offset
								.plus(new Point(x, y));

						overView.draw();
					}

					lastPosition = new Point(event.getX(), event.getY());
				} else if (moveState != null) {
					moveState.setPosition(new Point(event.getX(), event.getY()));

					overView.draw();
				}
			}
		});
	}

	private void disableMoveMode() {
		moveCanvas = false;
		lastPosition = null;
	}

	private StateView findStateAt(int x, int y) {
		for (StateView stateView : automatonView.getStateViews()) {
			if (stateView.inRadius(x, y)) return stateView;
		}
		return null;
	}
}
