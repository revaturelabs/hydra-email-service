package com.revature.caliber.model;

import java.io.Serializable;

public enum SkillType implements Serializable{
	J2EE("J2EE"),
	NET(".NET"),
	SDET("SDET"),
	BPM("BPM"),
	OTHER("Other");
	
	private String type;

	private SkillType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return type;
	}

}
