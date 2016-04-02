package com.alesegdia.famjam6.map;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.alesegdia.famjam6.asset.Gfx;
import com.alesegdia.famjam6.entity.BaseExtension;
import com.alesegdia.famjam6.entity.Building;
import com.alesegdia.famjam6.entity.FroncetiteGatherer;
import com.alesegdia.famjam6.entity.FroncetiteTransport;
import com.alesegdia.famjam6.entity.Gatherer;
import com.alesegdia.famjam6.entity.PowerPlant;
import com.alesegdia.famjam6.entity.PowerTransport;
import com.alesegdia.famjam6.entity.SandetiteGatherer;
import com.alesegdia.famjam6.entity.SandetiteTransport;
import com.alesegdia.famjam6.entity.Transport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Scenario {

	private Building[][] buildingsMap;
	private TextureRegion[][] buildingsGraphicsMap;
	
	private List<Building> buildingsList = new LinkedList<Building>();
	private List<Gatherer> gathererList = new LinkedList<Gatherer>();

	private float[][] terrainMap = null;
	private float scale;
	private TileTerrainRenderer terrainRenderer;
	
	public Scenario( float[][] terrainMap, float scale )
	{
		this.scale = scale;
		this.terrainMap = terrainMap;
		
		this.terrainRenderer = new TileTerrainRenderer(terrainMap, scale);
		allocateBuildingMaps();
	}
	
	private void allocateBuildingMaps()
	{
		assert( terrainMap != null );
		
		this.buildingsMap = new Building[terrainMap.length][];
        this.buildingsGraphicsMap = new TextureRegion[terrainMap.length][];
        
        for (int i = 0; i < buildingsMap.length; i++)
        {
            this.buildingsMap[i] = new Building[terrainMap[0].length];
            this.buildingsGraphicsMap[i] = new TextureRegion[terrainMap[0].length];
            Arrays.fill(this.buildingsMap[i], null);
            Arrays.fill(this.buildingsGraphicsMap[i], null);
        }
        
	}
	
	public void render( SpriteBatch batch )
	{
		this.terrainRenderer.render(batch);
		for( int i = 0; i < this.buildingsGraphicsMap.length; i++ )
		{
			for( int j = 0; j < this.buildingsGraphicsMap[0].length; j++ )
			{
				TextureRegion tr = this.buildingsGraphicsMap[i][j];
				if( tr != null )
				{
					batch.draw(tr, i * this.scale, j * this.scale);					
				}
			}
		}
	}
	
	public boolean tryApplyTool( float x, float y, int currentTool )
	{
		boolean okop = false;
		int scx = scaleCoord(x);
		int scy = scaleCoord(y);
		
		if( scx >= 0 && scx < this.buildingsMap.length && scy >= 0 && scy < this.buildingsMap[0].length  )
		{
			if( Tool.isPlacementTool(currentTool) )
			{
				if( this.buildingsMap[scx][scy] == null )
				{
	
					Building b = null;
					switch(currentTool)
					{
					case Tool.PLACE_BASE: b = new BaseExtension(); break;
					case Tool.PLACE_FGATHER: b = new FroncetiteGatherer(); break;
					case Tool.PLACE_FTRANSP: b = new FroncetiteTransport(); break;
					case Tool.PLACE_PTRANSP: b = new PowerTransport(); break;
					case Tool.PLACE_PWPLANT: b = new PowerPlant(); break;
					case Tool.PLACE_SGATHER: b = new SandetiteGatherer(); break;
					case Tool.PLACE_STRANSP: b = new SandetiteTransport(); break;
					}
	
					// BOOM!!
					if( b == null )	assert(false);
					
					boolean canPlace = true;
					
					float vt = this.terrainMap[scx][scy];
					if( b instanceof FroncetiteGatherer && vt >= 0.2f )
					{
						canPlace = false;
					}
					
					if(b instanceof SandetiteGatherer && vt <= 0.8f)
					{
						canPlace = false;
					}
					
					if( b instanceof PowerPlant	&& (vt < 0.2f || vt >= 0.8f) )
					{
						
						System.out.println(vt);
						canPlace = false;
					}
	
					if( canPlace )
					{
						this.buildingsMap[scx][scy] = b;
						this.buildingsList.add(b);
						
						this.buildingsGraphicsMap[scx][scy] = getBuildingTile(b, scx, scy);
						
						if( b instanceof Gatherer )
						{
							this.gathererList.add((Gatherer)b);
						}
						notifyBuildingAdded( scx, scy, b );
						okop = true;					
					}
				}
			}
			else
			{
				if( currentTool == Tool.DESTROY )
				{
					Building b = this.buildingsMap[scx][scy];
					if( b != null )
					{
						this.notifyBuildingRemoved( scx, scy, b );
						this.buildingsList.remove((Object)b);
						if( b instanceof Gatherer )
						{
							this.gathererList.remove((Object)b);
						}
						this.buildingsMap[scx][scy] = null;
						this.buildingsGraphicsMap[scx][scy] = null;
						okop = true;
					}
				}
			}
		}
		
		return okop;
	}
	
	public Building getBuilding( int x, int y )
	{
		return this.buildingsMap[x][y];
	}
	
	private TextureRegion getBuildingTile(Building b, int scx, int scy) {
		
		if( b instanceof SandetiteGatherer ) return Gfx.sandetiteGathererTr;
		if( b instanceof FroncetiteGatherer ) return Gfx.froncetiteGathererTr;
		if( b instanceof PowerPlant ) return Gfx.powerPlantTr;
		if( b instanceof BaseExtension ) return Gfx.baseExtensionTr;
		
		if( b instanceof SandetiteTransport ) return Gfx.sandetiteTransportTr[2];
		if( b instanceof FroncetiteTransport ) return Gfx.froncetiteTransportTr[2];
		if( b instanceof PowerTransport ) return Gfx.powerTransportTr[2];

		
		return null;
	}

	private void notifyBuildingRemoved(int scx, int scy, Building b) {
		
	}

	private void notifyBuildingAdded(int scx, int scy, Building b) {
		
	}

	int scaleCoord( float c )
	{
		return (int) Math.floor(c / scale);
	}
	
	Vector2 getMapPos( float x, float y )
	{
		return new Vector2((float)Math.floor(x / scale), (float)Math.floor(y / scale));
	}
	
}
