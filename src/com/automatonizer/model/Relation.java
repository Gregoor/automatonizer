package com.automatonizer.model;

public class Relation {
	
	public final State stateFrom;
	public final State stateTo;
	public final String symbol;
	
	public Relation(State stateFrom, State stateTo, String symbol) {
		this.stateFrom = stateFrom;
		this.stateTo = stateTo;
		this.symbol = symbol;
		
		if (stateFrom == null || stateTo == null) {
			throw new IllegalArgumentException("Relation can't be build on null states.");
		}
		if (stateFrom == stateTo) {
			throw new IllegalArgumentException("States can't be equal.");
		}
	}

}
