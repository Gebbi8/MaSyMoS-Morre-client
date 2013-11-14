package de.unirostock.sems.morre.client.dataholder;

public class ModelResult extends Model {

	private float score;
	
	public ModelResult(String modelName, float score, String modelID, long databaseID, String documentURI, String filename) {
		super(modelName, modelID, databaseID, documentURI, filename);
		this.score = score;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ModelResult [modelName=" + modelName + ", modelId=" + modelID + ", score=" + score + "]";
	}
	
}
