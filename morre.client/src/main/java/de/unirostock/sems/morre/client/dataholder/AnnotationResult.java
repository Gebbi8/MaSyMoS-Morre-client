package de.unirostock.sems.morre.client.dataholder;

import java.io.Serializable;
import java.util.List;

/**
 * Dataholder Class for one Result produced by an AnnotationQuery with the {@link de.unirostock.sems.morre.client.Morre MORRE}-Interface
 *
 */
public class AnnotationResult implements Serializable, Comparable<AnnotationResult> {
	
	private static final long serialVersionUID = -3824665970559332181L;
	
	private float score;
	private String uri;
	private List<String> relatedModelsUri;
	
	public AnnotationResult(float score, String uri,
			List<String> relatedModelsUri) {
		super();
		this.score = score;
		this.uri = uri;
		this.relatedModelsUri = relatedModelsUri;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<String> getRelatedModelsUri() {
		return relatedModelsUri;
	}

	public void setRelatedModelsUri(List<String> relatedModelsUri) {
		this.relatedModelsUri = relatedModelsUri;
	}

	@Override
	public String toString() {
		return "AnnotationResult [score=" + score + ", uri=" + uri + "]";
	}
	
	@Override
	public int compareTo(AnnotationResult result) {
		return Float.compare( result.getScore(), score );
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((relatedModelsUri == null) ? 0 : relatedModelsUri.hashCode());
		result = prime * result + Float.floatToIntBits(score);
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
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
		AnnotationResult other = (AnnotationResult) obj;
		if (relatedModelsUri == null) {
			if (other.relatedModelsUri != null)
				return false;
		} else if (!relatedModelsUri.equals(other.relatedModelsUri))
			return false;
		if (Float.floatToIntBits(score) != Float.floatToIntBits(other.score))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
}
