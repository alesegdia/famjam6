package com.alesegdia.famjam6.asset;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Gfx {

	public static Spritesheet tileset;
	public static TextureRegion froncetiteGathererTr;
	public static TextureRegion sandetiteGathererTr;
	public static TextureRegion baseExtensionTr;
	public static TextureRegion powerPlantTr;
	public static TextureRegion[] froncetiteTransportTr;
	public static TextureRegion[] sandetiteTransportTr;
	public static TextureRegion[] powerTransportTr;
	
	public static TextureRegion froncetiteTerrainTr;
	public static TextureRegion sandetiteTerrainTr;
	public static TextureRegion lowTerrainTr;
	public static TextureRegion highTerrainTr;
	
	
	public static void Initialize()
	{
		tileset = new Spritesheet("tileset5x4.png", 4, 5);
		froncetiteGathererTr = tileset.get(4);
		sandetiteGathererTr = tileset.get(8);
		baseExtensionTr = tileset.get(14);
		powerPlantTr = tileset.get(9);

		// transports
		froncetiteTransportTr = new TextureRegion[3];
		sandetiteTransportTr = new TextureRegion[3];
		powerTransportTr = new TextureRegion[3];
		
		loadTransport( 1, froncetiteTransportTr );
		loadTransport( 2, sandetiteTransportTr );
		loadTransport( 3, powerTransportTr );
		
		froncetiteTerrainTr = tileset.get(1);
		sandetiteTerrainTr = tileset.get(0);
		highTerrainTr = tileset.get(2);
		lowTerrainTr = tileset.get(3);
		
	}


	private static void loadTransport(int y_offset, TextureRegion[] trtr) {
		for( int i = 0; i < trtr.length; i++ )
		{
			trtr[i] = tileset.get(y_offset * 5 + i);
		}
	}
	
}
