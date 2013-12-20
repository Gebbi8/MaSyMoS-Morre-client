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
	 * Returns all available versions of this model as String List
	 * 
	 * @param fileId
	 * @return
	 * @throws MorreCommunicationException 
	 * @throws MorreException 
	 * @throws MalformedURLException 
	 */
	public List<String> getModelHistory( String fileId ) throws MorreClientException, MorreCommunicationException, MorreException;
	
	/**
	 * Returns one specific version of a model as dataholder object
	 * 
	 * @param fileId
	 * @param versionId
	 * @return
	 * @throws MorreCommunicationException 
	 * @throws MorreException 
	 * @throws MalformedURLException 
	 */
	public CrawledModel getModelVersion( String fileId, String versionId ) throws MorreClientException, MorreCommunicationException, MorreException;
	
	/**
	 * Returns the latest version of a model as dataholder object
	 * 
	 * @param fileId
	 * @return
	 * @throws MorreCommunicationException 
	 * @throws MorreException 
	 * @throws MalformedURLException 
	 */
	public CrawledModel getLatestModelVersion( String fileId ) throws MorreClientException, MorreCommunicationException, MorreException;
	
	/**
	 * Adds a model to the database
	 * 
	 * @param model
	 * @return
	 * @throws MorreCommunicationException 
	 * @throws MalformedURLException 
	 */
	public boolean addModel( CrawledModel model ) throws MorreClientException, MorreCommunicationException, MorreException;
	
}
