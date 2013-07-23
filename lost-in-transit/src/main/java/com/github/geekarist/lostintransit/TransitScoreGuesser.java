package com.github.geekarist.lostintransit;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Path("/myresource")
public class TransitScoreGuesser {

	private final static String WORK = "2, Place de La Défense, 92800, Puteaux";

	private String baseUrl = "http://www.ratp.fr";

	@GET
	@Produces("text/plain")
	@Path("{home}")
	public String guess(@PathParam("home") String home) throws IOException {
		// 2%2C+Place+de+La+D%C3%A9fense%2C+92800%2C+Puteaux";
		String workParam = URLEncoder.encode(WORK, "UTF-8");
		// 9%2C+Rue+de+la+Croix+Faubin%2C+75011%2C+Paris
		String homeParam = URLEncoder.encode(home, "UTF-8");
		String url = baseUrl + "/itineraires/fr/ratp/resultat-detaille/start/" + homeParam + "/end/" + workParam;
		Document document = Jsoup.parse(new java.net.URL(url), 5000);
		String time = document.select(".trajet .table h2").text();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(time);
		int result = 0;
		if (m.find()) {
			result = Integer.parseInt(m.group());
		}
		return Integer.toString(result * 5);
	}

	public void setBaseUrl(String url) {
		this.baseUrl = url;
	}
}
