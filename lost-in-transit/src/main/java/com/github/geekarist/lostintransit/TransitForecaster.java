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

	private String baseItineraryUrl = "http://www.ratp.fr";

	private int time(String from, String to) throws UnsupportedEncodingException, IOException {
		if (from == null || to == null) {
			return -1;
		}
		String url = itineraryUrl(from, to);
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

	private String itineraryUrl(String from, String to) throws UnsupportedEncodingException {
		String fromParam = (from == null ? null : URLEncoder.encode(from, "UTF-8"));
		String toParam = (to == null ? null : URLEncoder.encode(to, "UTF-8"));
		String url = baseItineraryUrl + "/itineraires/fr/ratp/resultat-detaille" //
				+ "/start/" + fromParam + "/end/" + toParam;
		return url;
	}

	public void setBaseItineraryUrl(String url) {
		this.baseItineraryUrl = url;
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
		TransitTimes transitTimes = new TransitTimes(timeTo1, itineraryUrl(from, places.getTo1()), timeTo2, itineraryUrl(from,
				places.getTo2()), timeTo3, itineraryUrl(from, places.getTo3()), timeTo4, itineraryUrl(from, places.getTo4()));
		return transitTimes;
	}
}
