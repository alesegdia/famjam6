package com.alesegdia.famjam6.asset;

import com.alesegdia.famjam6.map.Tool;
import com.badlogic.gdx.graphics.Texture;
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
	
	public static TextureRegion cursorTr;
	public static TextureRegion deleteTr;
	public static TextureRegion noPowerTr;
	public static TextureRegion powerTr;
	
	public static TextureRegion froncetiteSymTr;
	public static TextureRegion sandetiteSymTr;
	public static TextureRegion duriroTr;
	public static Texture splashIcon;

	public static void Initialize()
	{
		tileset = new Spritesheet("tileset5x4.png", 5, 5);
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
		
		cursorTr = tileset.get(18);
		deleteTr = tileset.get(19);
		
		noPowerTr = tileset.get(20);
		powerTr = tileset.get(21);
		
		sandetiteSymTr = tileset.get(23);
		froncetiteSymTr = tileset.get(22);
		duriroTr = tileset.get(24);
		
		splashIcon = new Texture("splash.png");
		
	}


	private static void loadTransport(int y_offset, TextureRegion[] trtr) {
		for( int i = 0; i < trtr.length; i++ )
		{
			trtr[i] = tileset.get(y_offset * 5 + i);
		}
	}


	public static TextureRegion getToolCursor(int currentTool) {
		switch( currentTool )
		{
		case Tool.DESTROY: return deleteTr;
		case Tool.PLACE_BASE: return baseExtensionTr;
		case Tool.PLACE_FGATHER: return froncetiteGathererTr;
		case Tool.PLACE_FTRANSP: return froncetiteTransportTr[2];
		case Tool.PLACE_SGATHER: return sandetiteGathererTr;
		case Tool.PLACE_STRANSP: return sandetiteTransportTr[2];
		case Tool.PLACE_PWPLANT: return powerPlantTr;
		case Tool.PLACE_PTRANSP: return powerTransportTr[2];
		case Tool.DURIRO: return duriroTr;
		case Tool.SELECT: return cursorTr;
	
		}
		return baseExtensionTr;
	}
	
}
