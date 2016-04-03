package com.alesegdia.famjam6.entity;

import com.alesegdia.famjam6.GameConstants;

public class PlayerStatus {

	public float froncetite = 0;
	public float sandetite = 0;
	public float power;
	
	public float fmult = 1;
	public float smult = 1;
	public float pmult = 1;
	
	public float dmult = 1;
	
	public boolean godMode = false;
	
	public int getPowerPercent() {
		return (int) ((Math.round(this.power * 100) / GameConstants.POWER_MAX));
	}
	
}
