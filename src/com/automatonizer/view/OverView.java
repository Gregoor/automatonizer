package com.automatonizer.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class OverView extends Composite {

	private final static int TEXT_BOX_PX_HEIGHT = 30;
	
	private TextBox textBox;
	
	public AutomatonCanvas automatonView = new AutomatonCanvas();

	public OverView() {
		DockLayoutPanel panel = new DockLayoutPanel(Unit.EM);
		initWidget(panel);

		textBox = new TextBox();
		if (!automatonView.canvasSupported()) {
			panel.add(new Label(
					"Ain't no Canvas support in yo internet entering software. Get some Chrome, bisch."));
			return;
		}

		textBox.setWidth("792px");

		panel.setHeight(TEXT_BOX_PX_HEIGHT + AutomatonCanvas.PX_HEIGHT + "px");
		panel.addSouth(textBox, 2);
		panel.add(automatonView);
		
		draw();
	}
	
	public void draw() {
		automatonView.draw();
	}

	public HasClickHandlers getInput() {
		return textBox;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
