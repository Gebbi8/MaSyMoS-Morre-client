package de.unirostock.sems.morre.client.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
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
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import de.unirostock.sems.morre.client.FeatureSet;
import de.unirostock.sems.morre.client.Morre;
import de.unirostock.sems.morre.client.QueryType;
import de.unirostock.sems.morre.client.dataholder.AnnotationResult;
import de.unirostock.sems.morre.client.dataholder.ModelResult;
import de.unirostock.sems.morre.client.dataholder.PersonResult;
import de.unirostock.sems.morre.client.exception.MorreClientException;
import de.unirostock.sems.morre.client.exception.MorreCommunicationException;
import de.unirostock.sems.morre.client.exception.MorreException;

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

	private final String ERROR_KEY_RESULTS = "#Results";
	private final String ERROR_KEY_EXCEPTION = "Exception";

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
	public List<ModelResult> modelQuery(String query) throws MorreClientException, MorreCommunicationException, MorreException {

		FeatureSet features = new FeatureSet();
		features.set( "keyword", query );
		
		return doModelQuery(QueryType.MODEL_QUERY, features);
	}

	@Override
	public List<String> getQueryFeatures(String queryType) throws MorreException, MorreClientException, MorreCommunicationException {
		
		try {
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
			
			List<String> featureList = gson.fromJson(result.toString(), featureListType);
			
			return featureList;
		} catch (JsonSyntaxException e) {
			throw new MorreException("Can not parse the FeatureSet List!", e);
		} catch (MalformedURLException e) {
			// Wrong formatted URL.
			throw new MorreClientException("Exception while building the request url", e);
		} catch (IOException e) {
			// Something went wrong with the communication
			throw new MorreCommunicationException("Error while HTTP Request.", e);
		}
		
		
	}

	@Override
	public List<ModelResult> doModelQuery(String queryType, FeatureSet features) throws MorreClientException, MorreCommunicationException, MorreException {
		
		// perform the query
		String resultString = doQuery(queryType, features);

		// Lets try to parse the shit out of it!

		List<ModelResult> result = null;
		try {
			// trying to parse the result correctly
			result = gson.fromJson(resultString, modelResultType);
		}
		catch (JsonSyntaxException e) {
			// **** first catch block ****
			// first attempt failed -> try to get a error message out of the result

			try {
				List<String> errorResult = gson.fromJson(resultString, featureListType);

				if( errorResult == null || errorResult.isEmpty() ) {
					// the errorResult List is empty
					throw new MorreException( "Empty result, no error description." );
				}				
				else if( errorResult.get(0).equals(ERROR_KEY_RESULTS) ) {
					// A result return. If the second value is null, the database could not find an entry.
					if( errorResult.get(1).equals("0") ) {
						// null for no entry found!
						// TODO think about the null return. Shouldn't we just return an empty List?
						return null;
					}

				}
				else if( errorResult.get(0).equals(ERROR_KEY_EXCEPTION) ) {
					// We've got a database exception! Let's throw it!
					if( errorResult.size() >= 2 )
						// there is a second parameter, specifying the exact error
						throw new MorreException( errorResult.get(1) );
					else
						// no explaining parameter, just an error.
						throw new MorreException();
				}
				else {
					// Something unknown was returned
					throw new MorreException( MessageFormat.format("Unknown Result was returned by MORRE: {0}", errorResult) );
				} 

			}
			catch (JsonSyntaxException e2) {
				// second attempt to parse failed, two. Now our fates rests in God's hands... (... or we just throw an exception)
				throw new MorreCommunicationException("Can not even parse the error message. Check for corrupt JSON!", e2);
			}

			// **** first catch block ****
		}


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


	private String doQuery( String queryType, FeatureSet features ) throws MorreClientException, MorreCommunicationException {
		
		try {
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
		} catch (MalformedURLException e) {
			// Wrong formatted URL. We can definitely blame the library user for this.
			// Exception the awesome library developer uses it by himself, than we have to blame someone else... ;)
			throw new MorreClientException("Exception while building the request url", e);
		} catch (IOException e) {
			// Something went wrong with the communication
			throw new MorreCommunicationException("Error while HTTP Request.", e);
		}

	}

}
