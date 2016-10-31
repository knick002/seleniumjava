package apitests;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.*;
import org.junit.runners.Parameterized.Parameters;

public class GetAPI1{
	@Test
	public void simpleTestGetTZ() throws InterruptedException,
		ClientProtocolException, IOException, JSONException {
		String url = "https://maps.googleapis.com/maps/api/timezone/json";
		// Test for a given lat,long that google api gives correct location
		String location_params = "?location=39.6034810,-119.6822510&timestamp=1331161200"; 
		//Create Json
		String json = "{}";
		//Get json to api
		String response = getJson(url + location_params);
		System.out.println(response);
		String expected_resp = "{\"dstOffset\": 0,\"rawOffset\": -28800,\"status\": \"OK\",\"timeZoneId\": \"America/Los_Angeles\",\"timeZoneName\": \"Pacific Standard Time\"}";
		assertEquals(expected_resp, response);
	}

	public static String getJson(String url)
			throws ClientProtocolException, IOException, JSONException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.addRequestProperty("Authorization", "Basic key");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		assertEquals(200, responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String list = response.toString();
		System.out.println(list);
		return list;
	}
		
}