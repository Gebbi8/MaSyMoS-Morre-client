package de.unirostock.sems.morre.client.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
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
import de.unirostock.sems.morre.client.MorreCrawlerInterface;
import de.unirostock.sems.morre.client.QueryType;
import de.unirostock.sems.morre.client.dataholder.AnnotationResult;
import de.unirostock.sems.morre.client.dataholder.CrawledModel;
import de.unirostock.sems.morre.client.dataholder.ModelResult;
import de.unirostock.sems.morre.client.dataholder.PersonResult;
import de.unirostock.sems.morre.client.exception.MorreClientException;
import de.unirostock.sems.morre.client.exception.MorreCommunicationException;
import de.unirostock.sems.morre.client.exception.MorreException;

public class HttpMorreClient implements Morre, MorreCrawlerInterface, Serializable {

	private static final long serialVersionUID = 6215972631957486031L;
	
	private final Log log = LogFactory.getLog( getClass() );

	private URL morreUrl = null;
	private HttpClient httpClient = null;
	private Gson gson = null;

	private Type singleListType;
//	private Type completeType;
	private Type featureListType;
	private Type modelResultType;
	private Type personResultType;
	private Type annotationResultType;

	private final String KEY_KEYWORDS = "keywords";
	private final String KEY_FEATURES = "features";
	private static final String KEY_SINGLE_KEYWORD = "keyword";

	private final String ERROR_KEY_RESULTS = "#Results";
	private final String ERROR_KEY_EXCEPTION = "Exception";

	public HttpMorreClient(String morreUrl) throws MalformedURLException {
		this.morreUrl = new URL(morreUrl);
		httpClient = HttpClientBuilder.create().build();
		gson = new Gson();

//		completeType = new TypeToken<List<Map<String, JsonElement>>>(){}.getType();
		singleListType = new TypeToken<List<String>>(){}.getType();
		featureListType = new TypeToken<List<String>>(){}.getType();

		modelResultType = new TypeToken<List<ModelResult>>(){}.getType();
		personResultType = new TypeToken<List<PersonResult>>(){}.getType();
		annotationResultType = new TypeToken<List<AnnotationResult>>(){}.getType();
	}

	@Override
	public List<ModelResult> modelQuery(String query) throws MorreClientException, MorreCommunicationException, MorreException {
		String resultString = performSimpleQuery(QueryType.MODEL_QUERY, query);
		return parseQueryResult(resultString, modelResultType);
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
		String resultString = performQuery(queryType, features);
		return parseQueryResult(resultString, modelResultType);
	}

	@Override
	public List<PersonResult> doPersonQuery(FeatureSet features) throws MorreClientException, MorreCommunicationException, MorreException {
		// perform the query
		String resultString = performQuery(QueryType.PERSON_QUERY, features);
		return parseQueryResult(resultString, personResultType);
	}

	@Override
	public List<AnnotationResult> doAnnotationQuery(FeatureSet features) throws MorreClientException, MorreCommunicationException, MorreException {
		// perform the query
		String resultString = performQuery(QueryType.ANNOTATION_QUERY, features);
		return parseQueryResult(resultString, annotationResultType);
	}

	private <R> List<R> parseQueryResult( String resultString, Type parseType ) throws MorreClientException, MorreCommunicationException, MorreException {

		// Lets try to parse the shit out of it!

		List<R> result = null;
		try {
			// trying to parse the result correctly
			result = gson.fromJson(resultString, parseType);
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
	
	private String performQuery( String queryType, FeatureSet features ) throws MorreClientException, MorreCommunicationException {

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
	
	private String performSimpleQuery( String queryType, String keyword ) throws MorreClientException, MorreCommunicationException {
		
		try {
			// Serialize the feature set

			HashMap<String, String> parameter = new HashMap<>();

			// Put in the Keyword
			parameter.put(KEY_SINGLE_KEYWORD, keyword);
			String jsonFeatures = gson.toJson( parameter );

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

	@Override
	public List<String> getModelHistory(String fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CrawledModel getModelVersion(String fileId, String versionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CrawledModel getLatestModelVersion(String fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addModel(CrawledModel model) {
		// TODO Auto-generated method stub
		return false;
	}

}
