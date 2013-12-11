package de.unirostock.sems.morre.client;

import java.io.Serializable;
import java.util.List;

import de.unirostock.sems.morre.client.dataholder.AnnotationResult;
import de.unirostock.sems.morre.client.dataholder.ModelResult;
import de.unirostock.sems.morre.client.dataholder.PersonResult;
import de.unirostock.sems.morre.client.dataholder.PublicationResult;
import de.unirostock.sems.morre.client.exception.MorreClientException;
import de.unirostock.sems.morre.client.exception.MorreCommunicationException;
import de.unirostock.sems.morre.client.exception.MorreException;

/**
 * Interface for performing search queries to the <a href="http://sems.uni-rostock.de/projects/morre/">MORRE</a> database API
 * @author Martin Peters
 *
 */
public interface Morre extends Serializable {
	
	/**
	 * Searches for a model on the basis of a simple query
	 * 
	 * @param query String
	 * @return List of {@link de.unirostock.sems.morre.client.dataholder.ModelResult ModelResults}
	 * @throws MorreException
	 * @throws MorreClientException
	 * @throws MorreCommunicationException
	 */
	public List<ModelResult> modelQuery( String query ) throws MorreException, MorreClientException, MorreCommunicationException;
	
	/**
	 * Returns the available features for a given query type
	 * 
	 * @param all {@link de.unirostock.sems.morre.client.QueryType queryTypes}
	 * @return List of Strings with all available feature keywords
	 * @throws MorreException
	 * @throws MorreClientException
	 * @throws MorreCommunicationException
	 */
	public List<String> getQueryFeatures( String queryType ) throws MorreException, MorreClientException, MorreCommunicationException;
	
	/**
	 * Performs a more complex query of the given type
	 * The available features can be retrieved by {@link #getQueryFeatures(String) getQueryFeatures}
	 * 
	 * @param model {@link de.unirostock.sems.morre.client.QueryType queryTypes}
	 * @param features
	 * @return List of {@link de.unirostock.sems.morre.client.dataholder.ModelResult ModelResults}
	 * @throws MorreException
	 * @throws MorreClientException
	 * @throws MorreCommunicationException
	 */
	public List<ModelResult> doModelQuery( String queryType, FeatureSet features ) throws MorreException, MorreClientException, MorreCommunicationException;
	
	/**
	 * Performs a simple query with only one keyword field for the given {@link de.unirostock.sems.morre.client.QueryType queryType}
	 * <b>Note:</b> Only some queryTypes are supporting this method!
	 * 
	 * @param model {@link de.unirostock.sems.morre.client.QueryType queryTypes}
	 * @param keyword String
	 * @return List of {@link de.unirostock.sems.morre.client.dataholder.ModelResult ModelResults}
	 * @throws MorreException
	 * @throws MorreClientException
	 * @throws MorreCommunicationException
	 */
	public List<ModelResult> doSimpleModelQuery( String queryType, String keyword ) throws MorreException, MorreClientException, MorreCommunicationException;
	
	/**
	 * Searches for a Person on the basis of the given features
	 * The available features can be retrieved by {@link #getQueryFeatures(String) getQueryFeatures}
	 * 
	 * @param features
	 * @return List of {@link de.unirostock.sems.morre.client.dataholder.PersonResult PersonResults}
	 * @throws MorreException
	 * @throws MorreClientException
	 * @throws MorreCommunicationException
	 */
	public List<PersonResult> doPersonQuery( FeatureSet features ) throws MorreException, MorreClientException, MorreCommunicationException;
	
	/**
	 * Searches for a Publication on the basis of the given features
	 * The available features can be retrieved by {@link #getQueryFeatures(String) getQueryFeatures}
	 * 
	 * @param features
	 * @return List of {@link de.unirostock.sems.morre.client.dataholder.PublicationResult PublicationResults}
	 * @throws MorreException
	 * @throws MorreClientException
	 * @throws MorreCommunicationException
	 */
	public List<PublicationResult> doPublicationQuery( FeatureSet features ) throws MorreException, MorreClientException, MorreCommunicationException;
	
	/** 
	 * Searches for Annotations on the basis of the given query String
	 * 
	 * @param query String
	 * @return List of {@link de.unirostock.sems.morre.client.dataholder.AnnotationResult AnnotationResults}
	 * @throws MorreException
	 * @throws MorreClientException
	 * @throws MorreCommunicationException
	 */
	public List<AnnotationResult> doAnnotationQuery( String query ) throws MorreException, MorreClientException, MorreCommunicationException;
	
}
