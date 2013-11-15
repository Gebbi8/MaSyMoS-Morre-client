package de.unirostock.sems.morre.client.dataholder;

import java.io.Serializable;
import java.util.List;

public class AnnotationResult implements Serializable {
	
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
	
}
