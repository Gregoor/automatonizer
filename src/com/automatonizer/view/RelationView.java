package com.automatonizer.view;

import com.automatonizer.model.Relation;
import com.automatonizer.model.Vector;
import com.google.gwt.canvas.dom.client.Context2d;

public class RelationView implements View {

	private final static int ARROW_LENGTH = 10;

	private final Relation relation;
	private AutomatonCanvas canvas;
	private StateView stateViewFrom;
	private StateView stateViewTo;

	public RelationView(AutomatonCanvas canvas, Relation relation) {
		this.canvas = canvas;
		this.relation = relation;

		stateViewFrom = canvas.stateViewMap.get(relation.stateFrom);
		stateViewTo = canvas.stateViewMap.get(relation.stateTo);

		if (stateViewFrom == null || stateViewTo == null) {
			throw new IllegalArgumentException(
					"Can't draw relation for non-visible states.");
		}
	}

	public void draw() {
		Vector start, end, dir, line, middle;
		start = stateViewFrom.getWorldPosition();
		end = stateViewTo.getWorldPosition();
		line = end.clone().minus(start);
		dir = line.clone().normalize();
		middle = start.clone().plus(dir.clone().mult(line.calcLength() / 2));

		start.plus(dir.clone().mult(StateView.RADIUS));
		end.plus(dir.clone().neg().mult(StateView.RADIUS));

		double angle = Math.atan2(end.y - start.y, end.x - start.x);

		Context2d ctx = canvas.getObjectContext();
		ctx.beginPath();
		ctx.moveTo(start.x, start.y);
		ctx.lineTo(end.x, end.y);
		ctx.lineTo(end.x - ARROW_LENGTH * Math.cos(angle - Math.PI / 6), end.y
				- ARROW_LENGTH * Math.sin(angle - Math.PI / 6));
		ctx.moveTo(end.x, end.y);
		ctx.lineTo(end.x - ARROW_LENGTH * Math.cos(angle + Math.PI / 6), end.y
				- ARROW_LENGTH * Math.sin(angle + Math.PI / 6));
		ctx.stroke();
		ctx.closePath();
		
		ctx = canvas.getTextContext();
		ctx.setFont("13px Arial");
		ctx.fillText(relation.symbol, middle.x, middle.y);
	}

}
