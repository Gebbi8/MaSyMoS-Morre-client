package de.unirostock.sems.morre.client.dataholder;

import java.io.Serializable;

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

}
