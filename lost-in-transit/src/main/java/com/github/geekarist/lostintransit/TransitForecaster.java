package com.github.geekarist.lostintransit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Path("/myresource")
public class TransitForecaster {

	private String baseUrl = "http://www.ratp.fr";

	private int time(String from, String to) throws UnsupportedEncodingException, IOException {
		if (from == null || to == null) {
			return -1;
		}
		String fromParam = URLEncoder.encode(from, "UTF-8");
		String toParam = URLEncoder.encode(to, "UTF-8");
		String url = baseUrl + "/itineraires/fr/ratp/resultat-detaille" //
				+ "/start/" + fromParam + "/end/" + toParam;
		Document document = Jsoup.connect(url).timeout(10000)
				.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
				.referrer("http://www.google.com").get();
		String time = document.select(".trajet .table h2").text();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(time);
		int result = 0;
		if (m.find()) {
			result = Integer.parseInt(m.group());
		}
		return result;
	}

	public void setBaseUrl(String url) {
		this.baseUrl = url;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TransitTimes guess(TransitPlaces places) throws UnsupportedEncodingException, IOException {
		String from = places.getFrom();
		int timeTo1 = time(from, places.getTo1());
		int timeTo2 = time(from, places.getTo2());
		int timeTo3 = time(from, places.getTo3());
		int timeTo4 = time(from, places.getTo4());
		TransitTimes transitTimes = new TransitTimes(timeTo1, timeTo2, timeTo3, timeTo4);
		return transitTimes;
	}

}
