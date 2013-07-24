package com.github.geekarist.lostintransit;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.fest.assertions.Assertions.assertThat;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class TransitScoreGuesserTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8089);

	@Test
	public void shouldGuessTransitScore() throws IOException {
		// GIVEN
		String home = "9, Rue de la Croix Faubin, 75011, Paris";
		TransitScoreGuesser guesser = new TransitScoreGuesser();
		guesser.setBaseUrl("http://localhost:8089");

		// Any request returns ratp.html
		stubFor(get(urlMatching(".*")).willReturn(
				aResponse().withBody(Resources.toString(Resources.getResource("ratp.html"), Charsets.UTF_8))));

		// WHEN
		String score = guesser.guess(home);

		// THEN
		WireMock.verify(getRequestedFor(urlEqualTo("/itineraires/fr/ratp/resultat-detaille" //
				+ "/start/9%2C+Rue+de+la+Croix+Faubin%2C+75011%2C+Paris" //
				+ "/end/2%2C+Place+de+La+D%C3%A9fense%2C+92800%2C+Puteaux")));
		assertThat(score).isEqualTo("39 min");
	}
}
