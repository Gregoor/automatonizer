package com.automatonizer.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.automatonizer.model.Relation;
import com.automatonizer.model.State;
import com.automatonizer.model.Vector;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;

public class AutomatonCanvas extends Composite {

	public final static int PX_HEIGHT = 400;
	public final static int PX_WIDTH = 800;
	
	public final HashMap<State, StateView> stateViewMap = new HashMap<State, StateView>();
	public Vector offset = new Vector();

	
	private final static int GRID_SIZE = 20;

	private final Canvas gridCanvas = Canvas.createIfSupported();
	private final Canvas objectCanvas = Canvas.createIfSupported();
	private final Canvas textCanvas = Canvas.createIfSupported();
	private final Canvas upmostCanvas = textCanvas;

	private final ArrayList<RelationView> relationViews = new ArrayList<RelationView>();
	
	public AutomatonCanvas() {
		initCanvas(gridCanvas);
		initCanvas(objectCanvas);
		initCanvas(textCanvas);

		LayoutPanel panel = new LayoutPanel();
		panel.add(gridCanvas);
		panel.add(objectCanvas);
		panel.add(textCanvas);
		initWidget(panel);
		
		State z1, z2, z3;
		z1 = new State("Z1");
		stateViewMap.put(z1, new StateView(this, z1, 50, 50));
		z2 = new State("Z2");
		stateViewMap.put(z2, new StateView(this, z2, 150, 150));
		z3 = new State("Z3");
		stateViewMap.put(z3, new StateView(this, z3, 250, 150, true));
		
	
		relationViews.add(new RelationView(this, new Relation(z1, z2, "a")));
		relationViews.add(new RelationView(this, new Relation(z2, z3, "a")));
		relationViews.add(new RelationView(this, new Relation(z1, z3, "b")));
	}

	public void draw() {
		clearCanvas(gridCanvas);
		clearCanvas(objectCanvas);
		clearCanvas(textCanvas);
		
		drawGrid();
		for (RelationView relationView : relationViews) {
			relationView.draw();
		}
		for (Map.Entry<State, StateView> entry : stateViewMap.entrySet()) {
			entry.getValue().draw();
		}
	}

	public boolean canvasSupported() {
		return objectCanvas != null && textCanvas != null;
	}

	public Context2d getObjectContext() {
		return objectCanvas.getContext2d();
	}

	public Context2d getTextContext() {
		return textCanvas.getContext2d();
	}

	// We need to attach the handlers to the upmost canvas
	public HasAllMouseHandlers getHandlerCanvas() {
		return upmostCanvas;
	}

	public Collection<StateView> getStateViews() {
		return stateViewMap.values();
	}

	private void drawGrid() {
		Context2d ctx = gridCanvas.getContext2d();

		for (int x0 = calcGridOffset(offset.x); x0 < PX_WIDTH; x0 += GRID_SIZE) {
			ctx.moveTo(x0, 0);
			ctx.lineTo(x0, PX_HEIGHT);
		}

		for (int y0 = calcGridOffset(offset.y); y0 < PX_HEIGHT; y0 += GRID_SIZE) {
			ctx.moveTo(0, y0);
			ctx.lineTo(PX_WIDTH, y0);
		}

		ctx.setLineWidth(.5);
		ctx.setStrokeStyle("grey");
		ctx.stroke();
	}

	private int calcGridOffset(double d) {
		return 0;
		// TODO: If we want a moving grid, this needs to be debugged
		// return Math.round((int) ((d == 0 ? d : (GRID_SIZE % d + d) % d) *
		// .9));
	}

	private void initCanvas(Canvas canvas) {
		canvas.setSize(PX_WIDTH + "px", PX_HEIGHT + "px");
		canvas.setCoordinateSpaceHeight(PX_HEIGHT);
		canvas.setCoordinateSpaceWidth(800);
		Style canvasStyle = canvas.getElement().getStyle();
		canvasStyle.setBorderStyle(BorderStyle.SOLID);
		canvasStyle.setBorderWidth(1, Unit.PX);
	}

	private void clearCanvas(Canvas canvas) {
		canvas.setCoordinateSpaceWidth(PX_WIDTH);
	}

}
