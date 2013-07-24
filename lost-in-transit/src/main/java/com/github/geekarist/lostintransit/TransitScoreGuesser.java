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

	private String baseUrl = "http://www.ratp.fr";

	@GET
	@Produces("text/plain")
	@Path("{home}/{work}")
	public String guess(@PathParam("home") String home, @PathParam("work") String work) throws IOException {
		String workParam = URLEncoder.encode(work, "UTF-8");
		String homeParam = URLEncoder.encode(home, "UTF-8");
		String url = baseUrl + "/itineraires/fr/ratp/resultat-detaille" //
				+ "/start/" + homeParam //
				+ "/end/" + workParam;
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
				.referrer("http://www.google.com").get();
		String time = document.select(".trajet .table h2").text();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(time);
		int result = 0;
		if (m.find()) {
			result = Integer.parseInt(m.group());
		}
		return Integer.toString(result) + " min";
	}

	public void setBaseUrl(String url) {
		this.baseUrl = url;
	}
}
