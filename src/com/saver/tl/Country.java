package com.saver.tl;

public enum Country {
	en("ENGLISH"), zh("CHINESE"), jp("JAPAN"), kor("KOREA"), fra("FRANCE"), spa(
			"SPAIN"), th("THAILAND"), ru("Russia"), pt("PORTUGAL"), de(
			"GERMANY"), it("ITALY"), el("GREECE"), chut("SWEDEN"), cht(
			"OLDCHINESE");
	private String name;

	private Country() {
	}

	private Country(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
