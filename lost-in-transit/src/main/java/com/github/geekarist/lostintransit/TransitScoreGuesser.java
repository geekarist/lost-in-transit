package com.github.geekarist.lostintransit;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Path("/myresource")
public class TransitScoreGuesser {

	private final static String WORK = "2, Place de La Défense, 92800, Puteaux";
	private final static String URL = "http://www.ratp.fr/itineraires/fr/ratp/resultat-detaille/start/9%2C+Rue+de+la+Croix+Faubin%2C+75011%2C+Paris/end/2%2C+Place+de+La+D%C3%A9fense%2C+92800%2C+Puteaux";

	@GET
	@Produces("text/plain")
	public int guess(String home) throws IOException {
		Document document = Jsoup.parse(new java.net.URL(URL), 5000);
		String time = document.select(".trajet .table h2").text();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(time);
		int result = 0;
		if (m.find()) {
			result = Integer.parseInt(m.group());
		}
		System.out.println(time);
		return result * 5;
	}
}
