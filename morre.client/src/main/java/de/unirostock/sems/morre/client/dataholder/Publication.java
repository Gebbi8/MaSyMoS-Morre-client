package de.unirostock.sems.morre.client.dataholder;

import java.util.List;

public class Publication {
	
	private String title;
	private String journal;
	private String synopsis;
	private int year;
	private List<Person> authors;
	
	public Publication(String title, String journal, String synopsis, int year, List<Person> authors) {
		super();
		this.title = title;
		this.journal = journal;
		this.synopsis = synopsis;
		this.year = year;
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Person> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Person> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "Publication [title=" + title + ", year=" + year + "]";
	}
	
}
