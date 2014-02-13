package de.unirostock.sems.morre.client.dataholder;

import java.io.Serializable;

/**
 * Dataholder Class for a Model
 * @see ModelResult
 *
 */
public class Model implements Serializable {

	private static final long serialVersionUID = -7484517560145746179L;
	
	protected String modelName;
	protected String modelID;
	protected long databaseID;
	protected String documentURI;
	protected String filename;
	
	
	public Model(String modelName, String modelID, long databaseID, String documentURI, String filename) {
		super();
		this.modelName = modelName;
		this.modelID = modelID;
		this.databaseID = databaseID;
		this.documentURI = documentURI;
		this.filename = filename;
	}


	public String getModelName() {
		return modelName;
	}


	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelID() {
		return modelID;
	}


	public void setModelID(String modelId) {
		this.modelID = modelId;
	}


	public long getDatabaseID() {
		return databaseID;
	}


	public void setDatabaseID(long databaseId) {
		this.databaseID = databaseId;
	}


	public String getDocumentURI() {
		return documentURI;
	}


	public void setDocumentURI(String documentUri) {
		this.documentURI = documentUri;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "ModelResult [modelName=" + modelName + ", modelId=" + modelID + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (databaseID ^ (databaseID >>> 32));
		result = prime * result
				+ ((documentURI == null) ? 0 : documentURI.hashCode());
		result = prime * result
				+ ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((modelID == null) ? 0 : modelID.hashCode());
		result = prime * result
				+ ((modelName == null) ? 0 : modelName.hashCode());
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
		Model other = (Model) obj;
		if (databaseID != other.databaseID)
			return false;
		if (documentURI == null) {
			if (other.documentURI != null)
				return false;
		} else if (!documentURI.equals(other.documentURI))
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (modelID == null) {
			if (other.modelID != null)
				return false;
		} else if (!modelID.equals(other.modelID))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		return true;
	}

}
