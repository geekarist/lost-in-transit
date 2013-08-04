package com.github.geekarist.lostintransit;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.fest.assertions.Assertions.assertThat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class TransitForecasterTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8089);

	@Test
	public void shouldGuessTransitTimes() throws IOException {
		// GIVEN
		String givenFrom = "9, Rue de la Croix Faubin, 75011, Paris";
		String givenTo1 = "3, Place de La Défense, 92800, Puteaux";
		String givenTo2 = "207, Rue de Bercy, 75012, Paris";
		String givenTo3 = "132, Boulevard Camélinat, 92240, Malakoff";
		String givenTo4 = "40, Boulevard de Port-Royal, 75005, Paris";
		TransitPlaces givenPlaces = new TransitPlaces(givenFrom, givenTo1, givenTo2, givenTo3, givenTo4);

		stubRequestTo(givenTo1, "ratp.html");
		stubRequestTo(givenTo2, "ratp2.html");
		stubRequestTo(givenTo3, "ratp3.html");
		stubRequestTo(givenTo4, "ratp4.html");

		String givenUrl = "http://localhost:8089";

		// WHEN
		TransitForecaster forecaster = new TransitForecaster();
		forecaster.setBaseItineraryUrl(givenUrl);
		TransitTimes times = forecaster.guess(givenPlaces);

		// THEN
		verifyItineraryRequested(givenFrom, givenTo1, givenTo2, givenTo3, givenTo4);
		assertThat(times).isEqualTo(
				new TransitTimes(39, itineraryUrl(givenFrom, givenTo1), 25, itineraryUrl(givenFrom, givenTo2), 42, itineraryUrl(
						givenFrom, givenTo3), 45, itineraryUrl(givenFrom, givenTo4)));
	}

	private void stubRequestTo(String to1, String itineraryTo1) throws IOException {
		String urlRegex = ".*" + Pattern.quote(URLEncoder.encode(to1, "UTF-8"));
		stubFor(get(urlMatching(urlRegex)).willReturn(
				aResponse().withBody(Resources.toString(Resources.getResource(itineraryTo1), Charsets.UTF_8))));
	}

	private void verifyItineraryRequested(String from, String to1, String to2, String to3, String to4)
			throws UnsupportedEncodingException {
		WireMock.verify(getRequestedFor(urlEqualTo(itineraryUrl(from, to1))));
		WireMock.verify(getRequestedFor(urlEqualTo(itineraryUrl(from, to2))));
		WireMock.verify(getRequestedFor(urlEqualTo(itineraryUrl(from, to3))));
		WireMock.verify(getRequestedFor(urlEqualTo(itineraryUrl(from, to4))));
	}

	private String itineraryUrl(String from, String to) throws UnsupportedEncodingException {
		String itineraryTo1 = "/itineraires/fr/ratp/resultat-detaille" + "/start/" + URLEncoder.encode(from, "UTF-8") + "/end/"
				+ URLEncoder.encode(to, "UTF-8");
		return itineraryTo1;
	}
}
