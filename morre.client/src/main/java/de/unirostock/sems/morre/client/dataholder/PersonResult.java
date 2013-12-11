package de.unirostock.sems.morre.client.dataholder;

import java.io.Serializable;
import java.util.List;

/**
 * Dataholder Class for one Result produced by an PersonQuery with the {@link de.unirostock.sems.morre.client.Morre MORRE}-Interface
 * contains a {@link de.unirostock.sems.morre.client.dataholder.Person Person} Dataholder Object
 *
 */
public class PersonResult implements Serializable, Comparable<PersonResult> {

	private static final long serialVersionUID = -7962256029915028650L;
	
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

	@Override
	public int compareTo(PersonResult person) {
		return Float.compare(person.getScore(), score);
	}
	
	
}
