package com.github.geekarist.lostintransit;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransitTimes {

	private int to1;
	private int to2;
	private int to3;
	private int to4;
	private String itineraryTo1;
	private String itineraryTo2;
	private String itineraryTo3;
	private String itineraryTo4;

	public TransitTimes() {
	}

	public TransitTimes(int to1, String itineraryTo1, int to2, String itineraryTo2, int to3, String itineraryTo3, int to4,
			String itineraryTo4) {
		this.to1 = to1;
		this.to2 = to2;
		this.to3 = to3;
		this.to4 = to4;
		this.itineraryTo1 = itineraryTo1;
		this.itineraryTo2 = itineraryTo2;
		this.itineraryTo3 = itineraryTo3;
		this.itineraryTo4 = itineraryTo4;
	}

	public void setTo1(int to1) {
		this.to1 = to1;
	}

	public void setTo2(int to2) {
		this.to2 = to2;
	}

	public void setTo3(int to3) {
		this.to3 = to3;
	}

	public void setTo4(int to4) {
		this.to4 = to4;
	}

	public int getTo1() {
		return to1;
	}

	public int getTo2() {
		return to2;
	}

	public int getTo3() {
		return to3;
	}

	public int getTo4() {
		return to4;
	}

	public String getItineraryTo1() {
		return itineraryTo1;
	}

	public void setItineraryTo1(String itineraryTo1) {
		this.itineraryTo1 = itineraryTo1;
	}

	public String getItineraryTo2() {
		return itineraryTo2;
	}

	public void setItineraryTo2(String itineraryTo2) {
		this.itineraryTo2 = itineraryTo2;
	}

	public String getItineraryTo3() {
		return itineraryTo3;
	}

	public void setItineraryTo3(String itineraryTo3) {
		this.itineraryTo3 = itineraryTo3;
	}

	public String getItineraryTo4() {
		return itineraryTo4;
	}

	public void setItineraryTo4(String itineraryTo4) {
		this.itineraryTo4 = itineraryTo4;
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
