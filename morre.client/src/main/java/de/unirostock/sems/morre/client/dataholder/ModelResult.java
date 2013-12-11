package de.unirostock.sems.morre.client.dataholder;

import java.io.Serializable;

/**
 * Dataholder Class for one Result produced by an ModelQuery with the {@link de.unirostock.sems.morre.client.Morre MORRE}-Interface
 * extends the {@link de.unirostock.sems.morre.client.dataholder.Model Model} Dataholder Class with a score attribute
 *
 */
public class ModelResult extends Model implements Comparable<ModelResult>, Serializable {

	private static final long serialVersionUID = 2024032602660507720L;
	
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
	
	public Model getModel() {
		return (Model) this;
	}

	@Override
	public String toString() {
		return "ModelResult [modelName=" + modelName + ", modelId=" + modelID + ", score=" + score + "]";
	}

	@Override
	public int compareTo(ModelResult model) {
		return Float.compare( model.getScore(), score );
	}
	
}
