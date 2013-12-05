package de.unirostock.sems.morre.client;

import java.util.List;

import de.unirostock.sems.morre.client.dataholder.CrawledModel;

public interface MorreCrawlerInterface {
	
	/**
	 * Returns all available versions of this model as String List
	 * 
	 * @param fileId
	 * @return
	 */
	public List<String> getModelHistory( String fileId );
	
	/**
	 * Returns one specific version of a model as dataholder object
	 * 
	 * @param fileId
	 * @param versionId
	 * @return
	 */
	public CrawledModel getModelVersion( String fileId, String versionId );
	
	/**
	 * Returns the latest version of a model as dataholder object
	 * 
	 * @param fileId
	 * @return
	 */
	public CrawledModel getLatestModelVersion( String fileId );
	
	/**
	 * Adds a model to the database
	 * 
	 * @param model
	 * @return
	 */
	public boolean addModel( CrawledModel model );
	
}
