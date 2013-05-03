package com.automatonizer.model;

public class Vector implements Cloneable {

	public double x;
	public double y;

	public Vector() {
		this(0, 0);
	}

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vector) {
			Vector v = (Vector) obj;
			return (x == v.x) && (y == v.y);
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Vector( " + x + " / " + y + " )";
	}

	public Vector clone() {
		return new Vector(x, y);
	}

	public void move(Vector v) {
		move(v.x, v.y);
	}

	public Vector move(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Vector normalize() {
		double length = calcLength();
		this.x /= length;
		this.y /= length;
		return this;
//		return div((double) Math.round(length));
	}
	
	public double calcLength() {
		// TODO: Wouldn't it be nice if this was cached?
		return Math.sqrt(pow(x) + pow(y));
	}

	// All kinds of math operations on the podouble
	// Does not create new instances, instead we can chain it.
	public Vector plus(Vector v) {
		return plus(v.x, v.y);
	}

	public Vector plus(double c) {
		return plus(c, c);
	}

	public Vector plus(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector minus(Vector v) {
		return minus(v.x, v.y);
	}

	public Vector minus(double c) {
		return minus(c, c);
	}

	public Vector minus(double x, double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public Vector mult(Vector v) {
		return mult(v.x, v.y);
	}

	public Vector mult(double c) {
		return mult(c, c);
	}

	public Vector mult(double x, double y) {
		this.x *= x;
		this.y *= y;
		return this;
	}

	public Vector div(Vector v) {
		return mult(v.x, v.y);
	}

	public Vector div(double c) {
		return div(c, c);
	}

	public Vector div(double x, double y) {
		this.x = this.x / x;
		this.y = this.y / y;
		return this;
	}
	
	public Vector neg() {
		return mult(-1);
	}

	private double pow(double x) {
		return Math.pow(x, 2);
	}

}
