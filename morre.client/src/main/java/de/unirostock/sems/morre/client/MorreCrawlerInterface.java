package de.unirostock.sems.morre.client;

import java.net.MalformedURLException;
import java.util.List;

import de.unirostock.sems.morre.client.dataholder.CrawledModel;
import de.unirostock.sems.morre.client.exception.MorreClientException;
import de.unirostock.sems.morre.client.exception.MorreCommunicationException;
import de.unirostock.sems.morre.client.exception.MorreException;

/**
 * 
 * Interface for model crawler to push models to the MORRE Database API 
 * 
 * @author Martin Peters
 *
 */
public interface MorreCrawlerInterface {
	
	/**
	 * Returns all available versions of this model as String List.
	 *
	 * @param fileId the file id
	 * @return the model history
	 * @throws MorreClientException the morre client exception
	 * @throws MorreCommunicationException the morre communication exception
	 * @throws MorreException the morre exception
	 */
	public List<String> getModelHistory( String fileId ) throws MorreClientException, MorreCommunicationException, MorreException;
	
	/**
	 * Returns one specific version of a model as dataholder object.
	 *
	 * @param fileId the file id
	 * @param versionId the version id
	 * @return the model version
	 * @throws MorreClientException the morre client exception
	 * @throws MorreCommunicationException the morre communication exception
	 * @throws MorreException the morre exception
	 */
	public CrawledModel getModelVersion( String fileId, String versionId ) throws MorreClientException, MorreCommunicationException, MorreException;
	
	/**
	 * Returns the latest version of a model as dataholder object.
	 *
	 * @param fileId the file id
	 * @return the latest model version
	 * @throws MorreClientException the morre client exception
	 * @throws MorreCommunicationException the morre communication exception
	 * @throws MorreException the morre exception
	 */
	public CrawledModel getLatestModelVersion( String fileId ) throws MorreClientException, MorreCommunicationException, MorreException;
	
	/**
	 * Adds a model to the database.
	 *
	 * @param model the model
	 * @return true, if adds the model
	 * @throws MorreClientException the morre client exception
	 * @throws MorreCommunicationException the morre communication exception
	 * @throws MorreException the morre exception
	 */
	public boolean addModel( CrawledModel model ) throws MorreClientException, MorreCommunicationException, MorreException;
	
}
