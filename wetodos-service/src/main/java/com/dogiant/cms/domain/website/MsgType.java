package com.dogiant.cms.domain.website;

public enum MsgType {

	EVENT("event"), LOCATION("location"), TEXT("text"), IMAGE("image"), VOICE("voice"), VIDEO("video"), MUSIC("music"), NEWS("news");

	private String type;

	private MsgType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
