package com.automatonizer.model;

public class Relation {
	
	private final State stateFrom;
	private final State stateTo;
	private final String symbol;
	
	public Relation(State stateFrom, State stateTo, String symbol) {
		this.stateFrom = stateFrom;
		this.stateTo = stateTo;
		this.symbol = symbol;
	}

	public State getStateFrom() {
		return stateFrom;
	}

	public State getStateTo() {
		return stateTo;
	}

	public String getSymbol() {
		return symbol;
	}
	
}
