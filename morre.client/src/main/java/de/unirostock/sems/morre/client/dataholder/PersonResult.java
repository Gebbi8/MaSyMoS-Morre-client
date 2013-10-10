package de.unirostock.sems.morre.client.dataholder;

import java.util.List;

public class PersonResult {

	private float score;
	private Person person;
	private List<String> relatedModelsUri;
	
	public PersonResult(float score, Person person, List<String> relatedModelsUri) {
		super();
		this.score = score;
		this.person = person;
		this.relatedModelsUri = relatedModelsUri;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<String> getRelatedModelsUri() {
		return relatedModelsUri;
	}

	public void setRelatedModelsUri(List<String> relatedModelsUri) {
		this.relatedModelsUri = relatedModelsUri;
	}

	@Override
	public String toString() {
		return "PersonResult [score=" + score + ", person=" + person + "]";
	}
	
	
}
