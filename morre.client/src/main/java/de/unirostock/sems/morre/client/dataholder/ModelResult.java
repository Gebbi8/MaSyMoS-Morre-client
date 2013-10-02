package de.unirostock.sems.morre.client.dataholder;

public class ModelResult {

	private String modelName;
	private float score;
	private String modelId;
	private long databaseId;
	private String documentUri;
	private String filename;
	
	
	public ModelResult(String modelName, float score, String modelId,
			long databaseId, String documentUri, String filename) {
		super();
		this.modelName = modelName;
		this.score = score;
		this.modelId = modelId;
		this.databaseId = databaseId;
		this.documentUri = documentUri;
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


	public String getModelId() {
		return modelId;
	}


	public void setModelId(String modelId) {
		this.modelId = modelId;
	}


	public long getDatabaseId() {
		return databaseId;
	}


	public void setDatabaseId(long databaseId) {
		this.databaseId = databaseId;
	}


	public String getDocumentUri() {
		return documentUri;
	}


	public void setDocumentUri(String documentUri) {
		this.documentUri = documentUri;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "ModelResult [modelName=" + modelName + ", modelId=" + modelId + "]";
	}
	
}
