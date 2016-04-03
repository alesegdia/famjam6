package com.alesegdia.famjam6.entity;

import com.badlogic.gdx.Gdx;

public class Gatherer extends Building {

	public Gatherer(float fcost, float scost, float ecr, float fprod, float sprod, float pprod ) {
		super(fcost, scost, ecr);
		this.froncetiteProductionRate = fprod;
		this.sandetiteProductionRate = sprod;
		this.powerProductionRate = pprod;
	}
	
	public void update( PlayerStatus playerStats )
	{
		super.update(playerStats);
		
		if( (connectedToBase && playerStats.power > 0) || this instanceof PowerPlant )
		{
			playerStats.froncetite += this.froncetiteProductionRate * Gdx.graphics.getDeltaTime() * playerStats.fmult;
			playerStats.sandetite += this.sandetiteProductionRate * Gdx.graphics.getDeltaTime() * playerStats.smult;
			playerStats.power += this.powerProductionRate * Gdx.graphics.getDeltaTime() * playerStats.pmult;
		}
	}
	
	// production per second
	public float froncetiteProductionRate = 0f;
	public float sandetiteProductionRate = 0f;
	public float powerProductionRate = 0f;
	
	public boolean connectedToBase = false;

}
