package com.automatonizer.model;

public class State {

	private final String identifier;

	public State(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof State)) return false;

		if (((State) obj).getIdentifier().equals(identifier)) return true;

		return false;
	}

	public String getIdentifier() {
		return identifier;
	}

}
