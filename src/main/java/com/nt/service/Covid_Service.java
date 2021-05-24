package com.nt.service;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.nt.entity.Covid_Model;

@Service
public class Covid_Service {

	private static String Virous_Data = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	List<Covid_Model> newStats = new ArrayList<>();

	

	public List<Covid_Model> fatchDataAb() {
		String body = null;

		try {

			HttpClient newHttpClient = HttpClient.newHttpClient();
			HttpRequest build = HttpRequest.newBuilder().uri(URI.create(Virous_Data)).build();

			HttpResponse<String> send = newHttpClient.send(build, HttpResponse.BodyHandlers.ofString());
			body = send.body();
			StringReader csvBodyReader = new StringReader(body);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

			for (CSVRecord record : records) {
				Covid_Model locationState = new Covid_Model();
				String state = record.get("Province/State");
				String country = record.get("Country/Region");
				String size1 = record.get(record.size() - 1);
				String size2 = record.get(record.size() - 2);
				
				locationState.setState(state);
				locationState.setCountry(country);
				int latestCases = Integer.parseInt(size1);
				int prevDayCases = Integer.parseInt(size2);
				locationState.setLatestTotalCases(latestCases);
				
				int dif=latestCases - prevDayCases;
				locationState.setDiffFromPrevDay(dif);
				newStats.add(locationState);

			}
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return newStats;

	}

}
