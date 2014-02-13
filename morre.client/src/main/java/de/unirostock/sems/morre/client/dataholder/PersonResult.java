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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
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
		PersonResult other = (PersonResult) obj;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
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
