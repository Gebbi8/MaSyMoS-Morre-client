package de.unirostock.sems.morre.client;

import java.util.List;

import de.unirostock.sems.morre.client.dataholder.ModelResult;

public interface Morre {
	
	public List<ModelResult> modelQuery( String query );
	
	public List<String> getQueryFeautures( String queryType );
	public List<ModelResult> doModelQuery( String queryType, FeatureSet features );
	public List<String> doPersonQuery( FeatureSet features );
	public List<String> doAnnotationQuery( FeatureSet features );
	
}
