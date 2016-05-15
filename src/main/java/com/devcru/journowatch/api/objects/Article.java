package com.devcru.journowatch.api.objects;

import java.sql.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {

	@JsonProperty("uuid")
	private UUID uuid;
	@JsonProperty("authorname")
	private String authorname;
	@JsonProperty("title")
	private String title;
	@JsonProperty("brief")
	private String brief;
	@JsonProperty("datepublished")
	private Date date;
	@JsonProperty("overallscore")
	private int overallscore;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getAuthorName() {
		return authorname;
	}

	public void setAuthorName(String authorName) {
		this.authorname = authorName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getOverallscore() {
		return overallscore;
	}

	public void setOverallscore(int overallscore) {
		this.overallscore = overallscore;
	}

}
