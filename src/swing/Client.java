package swing;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONValue;
import com.google.gson.Gson;

public class Client {
	
	
	/** show contacts ***/
	public String getContacts() throws UnsupportedOperationException, IOException {
		HttpResponse response = null;
		String output2 = "";
		try {

			@SuppressWarnings("deprecation")
			DefaultHttpClient httpClient = new DefaultHttpClient();
			// HttpClient client = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet("http://localhost:8080/Rest-hw12/api/FinalProject/showcontact");
			// getRequest.addHeader("accept", "application/json");
			// response = client.execute(getRequest);
			response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				output2 = output2 + output;
				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		return output2;

	}

	/** save contacts ***/
	public void saveContact(String name, String family, long homephone, long cellphone, String email) {

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/Rest-hw12/api/FinalProject/addcontact");
			// Contact c=new Contact(name,family,homephone,cellphone,email);
			// ObjectMapper mapper = new ObjectMapper();
			// String jsonInString = mapper.writeValueAsString(c);

			Map obj = new HashMap();
			obj.put("name", name);
			obj.put("family", family);
			obj.put("homephone", homephone);
			obj.put("cellphone", cellphone);
			obj.put("email", email);
			String jsonText = JSONValue.toJSONString(obj);
			StringEntity input = new StringEntity(jsonText, ContentType.APPLICATION_JSON);
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

//	/** save role ***/
//	public void saverole(String username) throws UnsupportedOperationException, IOException {
//
//		try {
//
//			DefaultHttpClient httpClient = new DefaultHttpClient();
//			HttpPost postRequest = new HttpPost("http://localhost:8080/Rest-hw12/api/FinalProject/save");
//			// Contact c=new Contact(name,family,homephone,cellphone,email);
//			// ObjectMapper mapper = new ObjectMapper();
//			// String jsonInString = mapper.writeValueAsString(c);
//
//			Map obj = new HashMap();
//			obj.put("username", username);
//			obj.put("password", 1);
//			obj.put("role", 5);
//
//			String jsonText = JSONValue.toJSONString(obj);
//			StringEntity input = new StringEntity(jsonText, ContentType.APPLICATION_JSON);
//			postRequest.setEntity(input);
//
//			HttpResponse response = httpClient.execute(postRequest);
//
//			if (response.getStatusLine().getStatusCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//			}
//
//			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
//
//			String output;
//			System.out.println("Output from Server .... \n");
//			while ((output = br.readLine()) != null) {
//				System.out.println(output);
//			}
//
//			httpClient.getConnectionManager().shutdown();
//
//		} catch (MalformedURLException e) {
//
//			e.printStackTrace();
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//
//	}

	public String validation(String username, String password) {
		String output2 = "";
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/Rest-hw12/api/FinalProject/login");
			// Contact c=new Contact(name,family,homephone,cellphone,email);
			// ObjectMapper mapper = new ObjectMapper();
			// String jsonInString = mapper.writeValueAsString(c);
			Map obj = new HashMap();
			obj.put("username", username);
			obj.put("password", password);
			obj.put("role", 4);

			String jsonText = JSONValue.toJSONString(obj);
			StringEntity input = new StringEntity(jsonText, ContentType.APPLICATION_JSON);
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

//			if (response.getStatusLine().getStatusCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				output2 = output2 + output;
				System.out.println("output:"+output);
			}

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return output2;

	}

}
