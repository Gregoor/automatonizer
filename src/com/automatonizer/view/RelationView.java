package com.automatonizer.view;


import com.automatonizer.model.Relation;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.touch.client.Point;

public class RelationView implements View {

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
		Point start, end;
		start = stateViewFrom.getWorldPosition();
		end = stateViewTo.getWorldPosition();

		Context2d ctx = canvas.getObjectContext();
		ctx.beginPath();
		ctx.moveTo(start.getX(), start.getY());
		ctx.lineTo(end.getX(), end.getY());
		ctx.stroke();
		ctx.closePath();
	}

}
