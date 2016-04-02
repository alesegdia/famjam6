package com.alesegdia.famjam6.map;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DebugTerrainRenderer {

	private float[][] map;
	
	private float scale;

	public DebugTerrainRenderer( float[][] map, float scale )
	{
		this.scale = scale;
		this.map = map;
	}

	public void render( ShapeRenderer srend )
	{
		for( int i = 0; i < map.length; i++ )
		{
			for( int j = 0; j < map[0].length; j++ )
			{
				float v = map[i][j];
				if( v < 0.2 )
				{
					srend.setColor(0, 1, 0, 1);
				}
				else if( v < 0.4 )
				{
					srend.setColor(0.5f, 0.3f, 0.3f, 1f);
				}
				else if( v < 0.8 )
				{
					srend.setColor(0.7f, 0.5f, 0.3f, 1f);
				}
				else
				{
					srend.setColor(0, 0, 1, 1);
				}
				srend.rect(i * scale, j * scale, scale, scale);
			}
		}
	}
	
}
