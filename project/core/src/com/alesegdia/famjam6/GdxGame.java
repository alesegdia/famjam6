package com.alesegdia.famjam6;

import com.alesegdia.famjam6.asset.Gfx;
import com.alesegdia.famjam6.screen.GameplayScreen;
import com.alesegdia.famjam6.screen.SplashScreen;
import com.alesegdia.famjam6.util.RNG;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GdxGame extends Game {
	public SpriteBatch batch;
	
	public ShapeRenderer srend;
	public OrthographicCamera cam;
	public OrthographicCamera menuCam;
	public OrthographicCamera textCam;
	
	public BitmapFont font;
	public BitmapFont fontBig;
	public BitmapFont fontRlyBig;
	
	public GameplayScreen gameplayScreen;

	private SplashScreen splashScreen;
	
	
	@Override
	public void create () {
		
		RNG.rng = new RNG();
		
		Gdx.input.setCursorCatched(true);
		
		// create batches
		batch = new SpriteBatch();
		srend = new ShapeRenderer();
		
		// game camera setup
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(12, 12 * (h / w));
        cam.setToOrtho(false, GameConfig.VIEWPORT_WIDTH, GameConfig.VIEWPORT_HEIGHT);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

        // menu camera setup
        menuCam = new OrthographicCamera();
        menuCam.setToOrtho(false, GameConfig.VIEWPORT_WIDTH, GameConfig.VIEWPORT_HEIGHT);
        menuCam.position.set(menuCam.viewportWidth / 2f, menuCam.viewportHeight / 2f, 0);
        menuCam.update();
        
        // text camera setup
        textCam = new OrthographicCamera();
        textCam.setToOrtho(false, 800, 600);
        textCam.position.set(400, 300, 0);
        textCam.update();
        
        // font setup
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("visitor1.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 32;
		font = generator.generateFont(parameter); // font size 12 pixels
		parameter.size = 30;

		fontBig = generator.generateFont(parameter); // font size 12 pixels
		fontRlyBig = generator.generateFont(parameter); // font size 12 pixels
        
		// assets setup
        Gfx.Initialize();
        
        splashScreen = new SplashScreen(this);
        gameplayScreen = new GameplayScreen(this);
        
        setScreen(splashScreen);

		generator.dispose();
		
	}

	@Override
	public void render () {
		super.render();
	}
}
