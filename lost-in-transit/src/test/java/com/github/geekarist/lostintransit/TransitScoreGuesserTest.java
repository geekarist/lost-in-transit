package com.github.geekarist.lostintransit;

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

		WireMock.stubFor(WireMock.get(WireMock.urlMatching("/news.html"))
				.willReturn(
						WireMock.aResponse().withBody(
								Resources.toString(
										Resources.getResource("ratp.html"),
										Charsets.UTF_8))));

		// WHEN
		int score = guesser.guess(home);

		// THEN
		// wiremock should respond classpath:ratp.html to
		// "http://www.ratp.fr/itineraires/fr/ratp/resultat-detaille/start/9%2C+Rue+de+la+Croix+Faubin%2C+75011%2C+Paris/end/2%2C+Place+de+La+D%C3%A9fense%2C+92800%2C+Puteaux");
		assertThat(score).isEqualTo(195);
	}
}
