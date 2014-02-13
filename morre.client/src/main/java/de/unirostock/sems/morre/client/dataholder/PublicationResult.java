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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((publication == null) ? 0 : publication.hashCode());
		result = prime
				* result
				+ ((relatedModelsUri == null) ? 0 : relatedModelsUri.hashCode());
		result = prime * result + Float.floatToIntBits(score);
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
		PublicationResult other = (PublicationResult) obj;
		if (publication == null) {
			if (other.publication != null)
				return false;
		} else if (!publication.equals(other.publication))
			return false;
		if (relatedModelsUri == null) {
			if (other.relatedModelsUri != null)
				return false;
		} else if (!relatedModelsUri.equals(other.relatedModelsUri))
			return false;
		if (Float.floatToIntBits(score) != Float.floatToIntBits(other.score))
			return false;
		return true;
	}
	
}
