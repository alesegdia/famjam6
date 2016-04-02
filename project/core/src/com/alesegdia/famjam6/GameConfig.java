package com.alesegdia.famjam6;

public class GameConfig {

	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	
	public static final float RATIO = ((float)WINDOW_WIDTH) / ((float)WINDOW_HEIGHT);
	public static final float SIZE = 64f + 32f + 16f;
	public static final float VIEWPORT_WIDTH = SIZE * 1.5f;
	public static final float VIEWPORT_HEIGHT = VIEWPORT_WIDTH / RATIO;
	
	public static final float METERS_TO_PIXELS = 16f;
	public static final float PIXELS_TO_METERS = 1f / METERS_TO_PIXELS;

}
