package com.alesegdia.famjam6.entity;

import com.badlogic.gdx.math.Vector2;

public class Building {

	// position in the tilemap
	public Vector2 position;
	
	// energy consumption per second
	public float energyConsupmtionRate = 0f;
	
	// base froncetite cost to build
	public float baseFroncetiteCost = 0f;
	
	// base sandetite cost to build
	public float baseSandetiteCost = 0f;
	
	// level of upgrade
	public int upgradeLevel = 0;
	
}
