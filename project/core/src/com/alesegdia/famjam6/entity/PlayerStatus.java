package com.alesegdia.famjam6.entity;

import com.alesegdia.famjam6.GameConstants;

public class PlayerStatus {

	public float froncetite = GameConstants.INITIAL_FRONCETITE;
	public float sandetite = GameConstants.INITIAL_SANDETITE;
	public float power;
	
	public float fmult = 1;
	public float smult = 1;
	public float pmult = 1;
	
	public float dmult = 1;
	
	public boolean godMode = false;
	
	public int getPowerPercent() {
		float k = Math.round((Math.round(this.power) * 100) / GameConstants.POWER_MAX );
		k = Math.round(k / 5f) * 5f;
		return Math.round(k);
	}
	
}
