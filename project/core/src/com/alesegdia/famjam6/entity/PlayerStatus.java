package com.alesegdia.famjam6.entity;

import com.alesegdia.famjam6.GameConstants;

public class PlayerStatus {

	public float froncetite = GameConstants.INITIAL_FRONCETITE;
	public float sandetite = GameConstants.INITIAL_SANDETITE;
	public float power;
	public float getPowerPercent() {
		return this.power * 100 / GameConstants.POWER_MAX;
	}
	
}
