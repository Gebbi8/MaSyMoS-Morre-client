package de.unirostock.sems.morre.client.dataholder;

import java.io.Serializable;
import java.util.List;

public class PublicationResult implements Serializable {
	
	private static final long serialVersionUID = 7017246742229358253L;
	
	private float score;
	private Publication publication;
	private List<String> relatedModelsUri;
	
	public PublicationResult(float score, Publication publication, List<String> relatedModelsUri) {
		super();
		this.score = score;
		this.publication = publication;
		this.relatedModelsUri = relatedModelsUri;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public List<String> getRelatedModelsUri() {
		return relatedModelsUri;
	}

	public void setRelatedModelsUri(List<String> relatedModelsUri) {
		this.relatedModelsUri = relatedModelsUri;
	}

	@Override
	public String toString() {
		return "PublicationResult [score=" + score + ", publication="
				+ publication + "]";
	}
	
}
