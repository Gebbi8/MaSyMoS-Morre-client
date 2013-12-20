package de.unirostock.sems.morre.client.dataholder;

import java.util.List;
import java.util.Map;

/**
 * Dataholder Object for a model, which is going to be inserted into the database with the {@link de.unirostock.sems.morre.client.MorreCrawlerInterface MorreCrawlerInterface}
 *
 */
public class CrawledModel {
	
	public static final String TYPE_CELLML	= "CELLML";
	public static final String TYPE_SBML	= "SBML";
	public static final String TYPE_SEDML	= "SEDML";
	
	private String fileId;
	private String versionId;
	private String xmldoc;
	private String modelType;
	private Map<String,List<String>> parentMap;
	private Map<String, String> metaMap;
	
	public CrawledModel(String fileId, String versionId, String xmldoc,
			Map<String, List<String>> parentMap, Map<String, String> metaMap, String modelType) {
		super();
		this.fileId = fileId;
		this.versionId = versionId;
		this.xmldoc = xmldoc;
		this.parentMap = parentMap;
		this.metaMap = metaMap;
		this.modelType = modelType;
	}

	public Map<String, String> getMetaMap() {
		return metaMap;
	}
	public void setMetaMap(Map<String, String> metaMap) {
		this.metaMap = metaMap;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getXmldoc() {
		return xmldoc;
	}
	public void setXmldoc(String xmldoc) {
		this.xmldoc = xmldoc;
	}
	public Map<String, List<String>> getParentMap() {
		return parentMap;
	}
	public void setParentMap(Map<String, List<String>> parentMap) {
		this.parentMap = parentMap;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

}
