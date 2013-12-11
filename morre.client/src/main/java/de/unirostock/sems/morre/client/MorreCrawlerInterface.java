package de.unirostock.sems.morre.client;

import java.net.MalformedURLException;
import java.util.List;

import de.unirostock.sems.morre.client.dataholder.CrawledModel;
import de.unirostock.sems.morre.client.exception.MorreCommunicationException;

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
	 * @throws MalformedURLException 
	 */
	public List<String> getModelHistory( String fileId ) throws MalformedURLException, MorreCommunicationException;
	
	/**
	 * Returns one specific version of a model as dataholder object
	 * 
	 * @param fileId
	 * @param versionId
	 * @return
	 * @throws MorreCommunicationException 
	 * @throws MalformedURLException 
	 */
	public CrawledModel getModelVersion( String fileId, String versionId ) throws MalformedURLException, MorreCommunicationException;
	
	/**
	 * Returns the latest version of a model as dataholder object
	 * 
	 * @param fileId
	 * @return
	 * @throws MorreCommunicationException 
	 * @throws MalformedURLException 
	 */
	public CrawledModel getLatestModelVersion( String fileId ) throws MalformedURLException, MorreCommunicationException;
	
	/**
	 * Adds a model to the database
	 * 
	 * @param model
	 * @return
	 * @throws MorreCommunicationException 
	 * @throws MalformedURLException 
	 */
	public boolean addModel( CrawledModel model ) throws MalformedURLException, MorreCommunicationException;
	
}
