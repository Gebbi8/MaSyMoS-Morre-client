package de.unirostock.sems.morre.client;

/**
 * All available Query-Types in the {@link de.unirostock.sems.morre.client.Morre MORRE}-Interface
 * <b>Note:</b> Not all queries are feature driver, some only expects a keyword. And remember to check if the return type of the query matches with return type or the
 * method your are going to use to perform this query! 
 *  
 *
 */
public class QueryType {

	public final static String MODEL_QUERY = "model_query";
	public final static String AGGREGATED_MODEL_QUERY = "aggregated_model_query";
	
	public final static String CELLML_MODEL_QUERY = "cellml_model_query";
	public final static String SIMPLE_CELLML_MODEL_QUERY = "simple_cellml_model_query";
	public final static String SIMPLE_SBML_MODEL_QUERY = "simple_sbml_model_query";
	public final static String PERSON_MODEL_QUERY = "person_model_query";
	public final static String PUBLICATION_MODEL_QUERY = "publication_model_query";
	public final static String ANNOTATION_MODEL_QUERY = "annotation_model_query";
	
	public final static String PERSON_QUERY = "person_query";
	public final static String PUBLICATION_QUERY = "publication_query";
	public final static String ANNOTATION_QUERY = "annotation_query";
	
}
