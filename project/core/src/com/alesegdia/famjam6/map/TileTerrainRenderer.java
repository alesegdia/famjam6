package com.alesegdia.famjam6.map;

import com.alesegdia.famjam6.asset.Gfx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileTerrainRenderer {
	
	private float[][] map;
	private float scale;

	public TileTerrainRenderer( float[][] map, float scale )
	{
		this.map = map;
		this.scale = scale;
	}

	public void render( SpriteBatch batch )
	{
		for( int i = 0; i < map.length; i++ )
		{
			for( int j = 0; j < map[0].length; j++ )
			{
				TextureRegion tr = null;
				float v = map[i][j];
				if( v < 0.2 )
				{
					tr = Gfx.froncetiteTerrainTr;
				}
				else if( v < 0.4 )
				{
					tr = Gfx.lowTerrainTr;
				}
				else if( v < 0.8 )
				{
					tr = Gfx.highTerrainTr;
				}
				else
				{
					tr = Gfx.sandetiteTerrainTr;
				}
				batch.draw(tr, i * scale, j * scale);
			}
		}
	}
	

}
