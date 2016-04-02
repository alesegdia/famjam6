package com.alesegdia.famjam6.entity;

import com.alesegdia.famjam6.GameConstants;
import com.badlogic.gdx.Gdx;
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
	
	public Building( float fcost, float scost, float ecr )
	{
		this.baseFroncetiteCost = fcost;
		this.baseSandetiteCost = scost;
		this.energyConsupmtionRate = ecr;
	}
	
	public final void built( PlayerStatus stats )
	{
		stats.froncetite -= baseFroncetiteCost;
		stats.sandetite -= baseSandetiteCost;
	}
	
	public void update( PlayerStatus stats )
	{
		stats.power -= this.energyConsupmtionRate * Gdx.graphics.getDeltaTime();
	}
	
	public final void destroyed( PlayerStatus stats )
	{
		stats.froncetite += baseFroncetiteCost * GameConstants.DESTROY_REWARD_RATE;
		stats.sandetite += baseSandetiteCost * GameConstants.DESTROY_REWARD_RATE;
	}
	
}
