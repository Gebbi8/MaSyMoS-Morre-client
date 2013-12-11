package de.unirostock.sems.morre.client.dataholder;

import java.io.Serializable;
import java.util.List;

/**
 * Dataholder Class for one Result produced by an PublicationQuery with the {@link de.unirostock.sems.morre.client.Morre MORRE}-Interface
 * contains a {@link de.unirostock.sems.morre.client.dataholder.Publication Publication} Dataholder Object
 *
 */
public class PublicationResult implements Serializable, Comparable<PublicationResult> {
	
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

	@Override
	public int compareTo(PublicationResult publication) {
		return Float.compare(publication.getScore(), score);
	}
	
}
