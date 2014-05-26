package de.unirostock.sems.morre.client.dataholder;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Dataholder Object for a model, which is going to be inserted into the database with the {@link de.unirostock.sems.morre.client.MorreCrawlerInterface MorreCrawlerInterface}
 *
 */
public class CrawledModel implements Serializable {

	private static final long serialVersionUID = 2002369276523885214L;

	private transient static Gson gson = null;
	private transient static Type metaType = new TypeToken<Map<String, String>>(){}.getType();

	public static final String TYPE_CELLML	= "CELLML";
	public static final String TYPE_SBML	= "SBML";
	public static final String TYPE_SEDML	= "SEDML";

	public static final String META_CRAWLED_DATE = "crawledDate";
	public static final String META_VERSION_DATE = "versionDate";
	public static final String META_SOURCE = "source";
	//	public static final String META_TYPE = "type";

	public static final String SOURCE_PMR2 = "PMR2";
	public static final String SOURCE_BIOMODELS_DB = "BMDB";

	private String fileId;
	private String versionId;
	private String xmldoc;
	private String modelType;
	private String meta;

	private Map<String,List<String>> parentMap = new HashMap<String, List<String>>();
	private Map<String, String> metaMap = new HashMap<String, String>(5);
	//private String meta;

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
		if( meta != null && !meta.isEmpty() && metaMap == null )
			processMeta();
		
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

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public String getMeta() {
		return meta;
	}

	protected void processMeta() {
		// creates gson instance, if necessary
		if( gson == null )
			gson = new Gson();

		// parses the meta string into a map
		metaMap = gson.fromJson(meta, metaType);
	}

	@Override
	public String toString() {
		return "CrawledModel [fileId=" + fileId + ", versionId=" + versionId
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		result = prime * result + ((metaMap == null) ? 0 : metaMap.hashCode());
		result = prime * result
				+ ((modelType == null) ? 0 : modelType.hashCode());
		result = prime * result
				+ ((parentMap == null) ? 0 : parentMap.hashCode());
		result = prime * result
				+ ((versionId == null) ? 0 : versionId.hashCode());
		result = prime * result + ((xmldoc == null) ? 0 : xmldoc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CrawledModel other = (CrawledModel) obj;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		if (metaMap == null) {
			if (other.metaMap != null)
				return false;
		} else if (!metaMap.equals(other.metaMap))
			return false;
		if (modelType == null) {
			if (other.modelType != null)
				return false;
		} else if (!modelType.equals(other.modelType))
			return false;
		if (parentMap == null) {
			if (other.parentMap != null)
				return false;
		} else if (!parentMap.equals(other.parentMap))
			return false;
		if (versionId == null) {
			if (other.versionId != null)
				return false;
		} else if (!versionId.equals(other.versionId))
			return false;
		if (xmldoc == null) {
			if (other.xmldoc != null)
				return false;
		} else if (!xmldoc.equals(other.xmldoc))
			return false;
		return true;
	}

}
