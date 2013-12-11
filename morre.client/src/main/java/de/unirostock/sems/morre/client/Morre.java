package de.unirostock.sems.morre.client;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.List;

import de.unirostock.sems.morre.client.dataholder.AnnotationResult;
import de.unirostock.sems.morre.client.dataholder.ModelResult;
import de.unirostock.sems.morre.client.dataholder.PersonResult;
import de.unirostock.sems.morre.client.exception.MorreClientException;
import de.unirostock.sems.morre.client.exception.MorreCommunicationException;
import de.unirostock.sems.morre.client.exception.MorreException;

/**
 * Interface for interacting with the <a href="http://sems.uni-rostock.de/projects/morre/">MORRE</a> database API
 * @author Martin Peters
 *
 */
public interface Morre extends Serializable {
	
	/**
	 * Searches for a model on the basis of a simple query
	 * 
	 * @param query
	 * @return List of ModelResults
	 * @throws MorreCommunicationException 
	 * @throws MorreClientException 
	 */
	public List<ModelResult> modelQuery( String query ) throws MorreException, MorreClientException, MorreCommunicationException;
	
	/**
	 * Returns the available features for a given query type
	 * 
	 * @param queryType
	 * @return
	 * @throws MalformedURLException 
	 * @throws IOException 
	 * @throws MorreException 
	 * @throws IllegalStateException 
	 */
	public List<String> getQueryFeatures( String queryType ) throws MorreException, MorreClientException, MorreCommunicationException;
	
	/**
	 * Performs a more complex query of the given type
	 * The available features can be retrieved by {@link #getQueryFeatures(String) getQueryFeatures}
	 * 
	 * @param queryType
	 * @param features
	 * @return
	 * @throws MorreCommunicationException 
	 * @throws MorreException 
	 */
	public List<ModelResult> doModelQuery( String queryType, FeatureSet features ) throws MorreException, MorreClientException, MorreCommunicationException;
	
	/**
	 * Searches for a Person on the basis of the given features
	 * The available features can be retrieved by {@link #getQueryFeatures(String) getQueryFeatures}
	 * 
	 * @param features
	 * @return
	 */
	public List<PersonResult> doPersonQuery( FeatureSet features ) throws MorreException, MorreClientException, MorreCommunicationException;
	
	/** 
	 * Searches for Annotations on the basis of the gives features
	 * The available features can be retrieved by {@link #getQueryFeatures(String) getQueryFeatures}
	 * 
	 * @param features
	 * @return
	 */
	public List<AnnotationResult> doAnnotationQuery( String query ) throws MorreException, MorreClientException, MorreCommunicationException;
	
}
