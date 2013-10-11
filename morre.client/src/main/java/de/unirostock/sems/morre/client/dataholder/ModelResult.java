package de.unirostock.sems.morre.client.dataholder;

public class ModelResult {

	private String modelName;
	private float score;
	private String modelID;
	private long databaseID;
	private String documentURI;
	private String filename;
	
	
	public ModelResult(String modelName, float score, String modelID, long databaseID, String documentURI, String filename) {
		super();
		this.modelName = modelName;
		this.score = score;
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


	public float getScore() {
		return score;
	}


	public void setScore(float score) {
		this.score = score;
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
