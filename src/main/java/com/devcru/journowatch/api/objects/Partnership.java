package com.devcru.journowatch.api.objects;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Partnership {

	@JsonProperty("uuid")
	private UUID uuid;
	@JsonProperty("journouuid")
	private UUID journouuid;
	@JsonProperty("venueuuid")
	private UUID venueuuid;
	@JsonProperty("type")
	private int type; // -1 default, 0 full-time, 1 freelance

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public UUID getJournouuid() {
		return journouuid;
	}

	public void setJournouuid(UUID journouuid) {
		this.journouuid = journouuid;
	}

	public UUID getVenueuuid() {
		return venueuuid;
	}

	public void setVenueuuid(UUID venueuuid) {
		this.venueuuid = venueuuid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
