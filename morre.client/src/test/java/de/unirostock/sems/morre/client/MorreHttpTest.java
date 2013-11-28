package de.unirostock.sems.morre.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import de.unirostock.sems.morre.client.dataholder.AnnotationResult;
import de.unirostock.sems.morre.client.dataholder.ModelResult;
import de.unirostock.sems.morre.client.exception.MorreClientException;
import de.unirostock.sems.morre.client.exception.MorreCommunicationException;
import de.unirostock.sems.morre.client.exception.MorreException;
import de.unirostock.sems.morre.client.impl.HttpMorreClient;

@RunWith(JUnit4.class)
public class MorreHttpTest {
	
	private Morre morre = null;
	
	@Before
	public void prepare() throws MalformedURLException {
		morre = new HttpMorreClient( "http://morre.sems.uni-rostock.de:7474/morre/query/" );
	}
	
	@Test
	public void testSimpleModelQuery() throws MorreClientException, MorreCommunicationException, MorreException {
		
		List<ModelResult> result = morre.modelQuery("novak");
		System.out.println( MessageFormat.format("Found {0} models", result.size()) );
		System.out.println( result );
		
	}
	
	@Test
	public void testComplexModelQuery() throws MorreCommunicationException, MorreException {
		
		FeatureSet features = new FeatureSet();
		features.set("FAMILYNAME", "Lloyd");
		
		List<ModelResult> result = morre.doModelQuery(QueryType.PERSON_MODEL_QUERY, features);
		System.out.println( MessageFormat.format("Found {0} models", result.size()) );
		System.out.println( result );
	}
	
	@Test
	public void testAnnotationQuery() throws MorreClientException, MorreCommunicationException, MorreException {
		
		FeatureSet features = new FeatureSet();
		features.set("FAMILYNAME", "Lloyd");
		
		List<AnnotationResult> result = morre.doAnnotationQuery(features);
		System.out.println( MessageFormat.format("Found {0} annotations", result.size()) );
		System.out.println( result );
	}
	
	@Test
	public void testGettingFeatures() throws MorreException, MalformedURLException, IllegalStateException, IOException {
		
		String[] queries = { QueryType.ANNOTATION_MODEL_QUERY, QueryType.ANNOTATION_QUERY, QueryType.CELLML_MODEL_QUERY, QueryType.MODEL_QUERY, QueryType.PERSON_MODEL_QUERY,
				QueryType.PERSON_QUERY, QueryType.PUBLICATION_MODEL_QUERY, QueryType.PUBLICATION_QUERY, QueryType.SIMPLE_CELLML_MODEL_QUERY };
		
		for( int i = 0; i < queries.length; i++ ) {
			System.out.println( queries[i] + ":" );
			System.out.println( morre.getQueryFeatures(queries[i]) );
		}
		
	}

}
