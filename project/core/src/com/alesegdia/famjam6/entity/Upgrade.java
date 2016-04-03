package com.alesegdia.famjam6.entity;

import com.alesegdia.famjam6.asset.Gfx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Upgrade {

	public float fMult = 0;
	public float sMult = 0;
	public float pMult = 0;
	public float dMult = 0;
	public String text;
	public TextureRegion icon;
	public float fCost;
	public float sCost;
	
	public String description;
	
	public static Upgrade makeFroncetiteEfficiencyUpgrade( float qtt, float fcost, float scost )
	{
		Upgrade u = new Upgrade();
		u.fMult = qtt;
		u.text = "x" + qtt;
		u.icon = Gfx.froncetiteGathererTr;
		u.fCost = fcost;
		u.sCost = scost;
		u.description = "efficiency +";
		return u;
	}
	
	public static Upgrade makeSandetiteEfficiencyUpgrade( float qtt, float fcost, float scost )
	{
		Upgrade u = new Upgrade();
		u.sMult = qtt;
		u.text = "x" + qtt;
		u.icon = Gfx.sandetiteGathererTr;
		u.fCost = fcost;
		u.sCost = scost;
		u.description = "efficiency +";

		return u;
	}
	
	public static Upgrade makePowerEfficiencyUpgrade( float qtt, float fcost, float scost )
	{
		Upgrade u = new Upgrade();
		u.pMult = qtt;
		u.text = "x" + qtt;
		u.icon = Gfx.powerPlantTr;
		u.fCost = fcost;
		u.sCost = scost;
		u.description = "efficiency +";

		return u;
	}
	
	public static Upgrade makeDrillEfficiencyUpgrade( float qtt, float fcost, float scost )
	{
		Upgrade u = new Upgrade();
		u.dMult = qtt;
		u.text = "x" + qtt;
		u.icon = Gfx.duriroTr;
		u.fCost = fcost;
		u.sCost = scost;
		u.description = "efficiency +";

		return u;
	}

	public void apply(PlayerStatus playerStatus)
	{
		playerStatus.froncetite -= this.fCost;
		playerStatus.sandetite -= this.sCost;
		
		playerStatus.pmult += this.pMult;
		playerStatus.smult += this.sMult;
		playerStatus.fmult += this.fMult;
		playerStatus.dmult += this.dMult;
		
	}
	
	public boolean canBuy( PlayerStatus playerStatus )
	{
		return playerStatus.froncetite - this.fCost >= 0 && playerStatus.sandetite - this.sCost >= 0;
	}
	
	
}
