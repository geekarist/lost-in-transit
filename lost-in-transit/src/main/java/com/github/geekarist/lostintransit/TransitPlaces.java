package com.github.geekarist.lostintransit;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransitPlaces {

	private String from;

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo1(String to1) {
		this.to1 = to1;
	}

	public void setTo2(String to2) {
		this.to2 = to2;
	}

	public void setTo3(String to3) {
		this.to3 = to3;
	}

	public void setTo4(String to4) {
		this.to4 = to4;
	}

	private String to1;
	private String to2;
	private String to3;
	private String to4;

	public TransitPlaces() {
	}

	public TransitPlaces(String from, String to1, String to2, String to3, String to4) {
		this.from = from;
		this.to1 = to1;
		this.to2 = to2;
		this.to3 = to3;
		this.to4 = to4;
	}

	public String getFrom() {
		return from;
	}

	public String getTo1() {
		return to1;
	}

	public String getTo2() {
		return to2;
	}

	public String getTo3() {
		return to3;
	}

	public String getTo4() {
		return to4;
	}

}
