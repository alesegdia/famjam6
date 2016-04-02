package com.alesegdia.famjam6.entity;

import com.badlogic.gdx.math.Vector2;

public class Building {

	// position in the tilemap
	public Vector2 position = new Vector2(0,0);
	
	// energy consumption per second
	public float energyConsupmtionRate = 0f;
	
	// base froncetite cost to build
	public float baseFroncetiteCost = 0f;
	
	// base sandetite cost to build
	public float baseSandetiteCost = 0f;
	
	// level of upgrade
	public int upgradeLevel = 0;

	public void setPosition(int scx, int scy) {
		position.set(scx, scy);
	}
	
}
