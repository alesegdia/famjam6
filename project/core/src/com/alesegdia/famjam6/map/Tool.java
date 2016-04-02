package com.alesegdia.famjam6.map;

public class Tool {

	public static final int PLACE_FGATHER = 0;
	public static final int PLACE_SGATHER = 1;
	public static final int PLACE_FTRANSP = 2;
	public static final int PLACE_STRANSP = 3;
	public static final int PLACE_PWPLANT = 4;
	public static final int PLACE_PTRANSP = 5;
	public static final int PLACE_BASE    = 6;
	public static final int DESTROY       = 7;
	public static final int NUM_TOOLS 	  = 8;
	
	public static String getToolString( int t )
	{
		switch(t)
		{
		case PLACE_SGATHER: return "sandetite gatherer";
		case PLACE_FGATHER: return "froncetite gatherer";
		case PLACE_STRANSP: return "sandetite transport";
		case PLACE_FTRANSP: return "froncetite transport";
		case PLACE_PWPLANT: return "power plant";
		case PLACE_PTRANSP: return "power transport";
		case PLACE_BASE: return "base extension";
		case DESTROY: return "destroy";
		default: return "<INVALID TOOL>";
		}
	}

	public static boolean isPlacementTool(int currentTool) {
		return currentTool >= PLACE_FGATHER && currentTool < DESTROY;
	}
	
}
