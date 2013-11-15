package de.unirostock.sems.morre.client.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import de.unirostock.sems.morre.client.FeatureSet;
import de.unirostock.sems.morre.client.Morre;
import de.unirostock.sems.morre.client.QueryType;
import de.unirostock.sems.morre.client.dataholder.AnnotationResult;
import de.unirostock.sems.morre.client.dataholder.ModelResult;
import de.unirostock.sems.morre.client.dataholder.PersonResult;

public class HttpMorreClient implements Morre, Serializable {

	private static final long serialVersionUID = 6215972631957486031L;
	private final Log log = LogFactory.getLog( getClass() );

	private URL morreUrl = null;
	private HttpClient httpClient = null;
	private Gson gson = null;

	private java.lang.reflect.Type singleListType;
	private java.lang.reflect.Type completeType;
	private java.lang.reflect.Type modelResultType;
	private java.lang.reflect.Type featureListType;

	private final String KEY_KEYWORDS = "keywords";
	private final String KEY_FEATURES = "features";

	public HttpMorreClient(String morreUrl) throws MalformedURLException {
		this.morreUrl = new URL(morreUrl);
		httpClient = HttpClientBuilder.create().build();
		gson = new Gson();

		completeType = new TypeToken<List<Map<String, JsonElement>>>(){}.getType();
		singleListType = new TypeToken<List<String>>(){}.getType();
		modelResultType = new TypeToken<List<ModelResult>>(){}.getType();
		featureListType = new TypeToken<List<String>>(){}.getType();
	}

	@Override
	public List<ModelResult> modelQuery(String query) {
		
		FeatureSet features = new FeatureSet();
		features.set( "keyword", query );
		
		String resultString = null;
		try {
			resultString = doQuery( QueryType.MODEL_QUERY, features);
		} catch (MalformedURLException e) {

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<ModelResult> result = gson.fromJson(resultString, modelResultType);
		return result;
	}

	@Override
	public List<String> getQueryFeatures(String queryType) throws IOException, IllegalStateException {

		HttpGet request = new HttpGet( new URL(morreUrl, queryType).toString() );
		HttpResponse response = httpClient.execute(request);

		// reads in the result
		StringBuilder result = new StringBuilder();
		BufferedReader resultReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = resultReader.readLine()) != null) {
			//append              
			result.append(line);
		}
		
		return gson.fromJson(result.toString(), featureListType);
	}

	@Override
	public List<ModelResult> doModelQuery(String queryType, FeatureSet features) {

		String resultString = null;
		try {
			resultString = doQuery(queryType, features);
		} catch (MalformedURLException e) {

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<ModelResult> result = gson.fromJson(resultString, modelResultType);
		return result;
	}

	@Override
	public List<PersonResult> doPersonQuery(FeatureSet features) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnnotationResult> doAnnotationQuery(FeatureSet features) {
		// TODO Auto-generated method stub
		return null;
	}


	private String doQuery( String queryType, FeatureSet features ) throws MalformedURLException, ClientProtocolException, IOException {

		// Serialize the feature set

		Entry<List<String>, List<String>> separateLists = features.getFeatures();
		HashMap<String, JsonElement> complete = new HashMap<String, JsonElement>();

		// First parse the feature and value list
		complete.put( KEY_FEATURES, gson.toJsonTree( separateLists.getKey(), singleListType ) );
		complete.put( KEY_KEYWORDS, gson.toJsonTree( separateLists.getValue(), singleListType ) );

		String jsonFeatures = gson.toJson( complete );

		// generates the request
		HttpPost request = new HttpPost( new URL(morreUrl, queryType).toString() );
		// adds the json string as package
		request.setEntity( new StringEntity(jsonFeatures, ContentType.APPLICATION_JSON) );

		// execute!
		HttpResponse response = httpClient.execute(request);

		// reads in the result
		StringBuilder result = new StringBuilder();
		BufferedReader resultReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = resultReader.readLine()) != null) {
			//append              
			result.append(line);
		}

		return result.toString();
	}

}
