package com.github.geekarist.lostintransit;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransitTimes {

	private int to1;
	private int to2;
	private int to3;
	private int to4;

	public TransitTimes() {
	}

	public TransitTimes(int to1, int to2, int to3, int to4) {
		this.to1 = to1;
		this.to2 = to2;
		this.to3 = to3;
		this.to4 = to4;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + to1;
		result = prime * result + to2;
		result = prime * result + to3;
		result = prime * result + to4;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransitTimes other = (TransitTimes) obj;
		if (to1 != other.to1)
			return false;
		if (to2 != other.to2)
			return false;
		if (to3 != other.to3)
			return false;
		if (to4 != other.to4)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Times [to1=" + to1 + ", to2=" + to2 + ", to3=" + to3 + ", to4=" + to4 + "]";
	}
	
}
