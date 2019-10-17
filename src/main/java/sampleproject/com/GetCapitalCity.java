package main.java.sampleproject.com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetCapitalCity {

	public static void main(String[] args) throws ParseException {
		String answer = "";
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter country name or code: ");
			String input = scanner.nextLine();
			getCapitalCity(input);
			System.out.println("Do you want to continue: Y or N?");
			answer = scanner.next();
		} while (answer.equalsIgnoreCase("Y"));

	}

	public static String getCapitalCity(String input) throws ParseException {
		String capitalCity = "";

		try {
			String baseUrl = "https://restcountries.eu/rest/v2/";

			if (!input.isEmpty() && input != null && input.length() <= 3) {
				baseUrl = baseUrl + "alpha/" + input;
			} else {
				baseUrl = baseUrl + "name/" + input;
			}
			System.out.println();
			URL url = new URL(baseUrl);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if (conn.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String output = br.readLine();

				conn.disconnect();
				JSONParser parser = new JSONParser();
				if (output.startsWith("[")) {
					JSONArray jsonArray = (JSONArray) parser.parse(output);
					for (int i = 0, size = jsonArray.size(); i < size; i++) {
						JSONObject jsonObject = (JSONObject) jsonArray.get(i);
						if (jsonObject.get("name").toString().equalsIgnoreCase(input)
								|| jsonObject.get("alpha2Code").toString().equalsIgnoreCase(input)
								|| jsonObject.get("alpha3Code").toString().equalsIgnoreCase(input)) {
							capitalCity = (String) jsonObject.get("capital");
							System.out.println("Capital City: " + capitalCity);
							break;
						}
					}
				} else {
					JSONObject jsonObject = (JSONObject) parser.parse(output);
					capitalCity = (String) jsonObject.get("capital");
					System.out.println("Capital City: " + capitalCity);
				}
			} else {
				capitalCity = "Sorry: Country name or code does not exist";
				System.out.println(capitalCity);
			}
		} catch (MalformedURLException | FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return capitalCity;
	}
}
