package com.alesegdia.famjam6.screen;

import com.alesegdia.famjam6.GdxGame;
import com.alesegdia.famjam6.map.DebugTerrainRenderer;
import com.alesegdia.famjam6.map.NoiseGen;
import com.alesegdia.famjam6.map.Scenario;
import com.alesegdia.famjam6.map.Tool;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public class GameplayScreen implements Screen {

	private GdxGame g;
	private float[][] map;
	private DebugTerrainRenderer debugMapRenderer;
	private Scenario scenario;
	
	private int currentTool = 0;

	public GameplayScreen( GdxGame g )
	{
		this.g = g;
	}
	
	@Override
	public void show() {

		this.map = NoiseGen.GeneratePerlinNoise(100, 100, 4);
		this.debugMapRenderer = new DebugTerrainRenderer(this.map, 8);
		this.scenario = new Scenario(map, 8);
		
	}

	@Override
	public void render(float delta)
	{
		
		handlePlayerInput();
		
		//gw.setCam();
        
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		g.srend.setProjectionMatrix(g.cam.combined);
		g.srend.setAutoShapeType(true);
		g.srend.begin();
		g.srend.set(ShapeType.Filled);
		this.debugMapRenderer.render(g.srend);
		g.srend.end();

        g.cam.update();
        g.batch.setProjectionMatrix(g.cam.combined);
		g.batch.begin();
		this.scenario.render(g.batch);
		g.batch.end();
		
		g.textCam.update();
		g.batch.setProjectionMatrix(g.textCam.combined);
		g.batch.begin();
		g.font.draw(g.batch, Tool.getToolString(this.currentTool), 10, 25);
		//g.font.draw(g.batch, gac.gauge(), 50, 30);
		g.batch.end();
		
		g.batch.setProjectionMatrix(g.menuCam.combined);
		g.batch.begin();
		//g.batch.draw(Gfx.pickupsSheet.get(9), 1, 1);
		g.batch.end();

	}

	private void handlePlayerInput() {
		
		float speed = 0.5f;
		
		if( Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ) speed = 1.f;
		
		if( Gdx.input.isKeyPressed(Input.Keys.A) ) g.cam.position.x -= speed;
		if( Gdx.input.isKeyPressed(Input.Keys.D) ) g.cam.position.x += speed;
		if( Gdx.input.isKeyPressed(Input.Keys.W) ) g.cam.position.y += speed;
		if( Gdx.input.isKeyPressed(Input.Keys.S) ) g.cam.position.y -= speed;

		if( g.cam.position.x < 0 ) g.cam.position.x = 0 ;
		if( g.cam.position.y < 0 ) g.cam.position.y = 0 ;
		
		if( Gdx.input.isKeyJustPressed(Input.Keys.NUM_1) ) this.currentTool = Tool.PLACE_FGATHER;
		if( Gdx.input.isKeyJustPressed(Input.Keys.NUM_2) ) this.currentTool = Tool.PLACE_SGATHER;
		if( Gdx.input.isKeyJustPressed(Input.Keys.NUM_3) ) this.currentTool = Tool.PLACE_FTRANSP;
		if( Gdx.input.isKeyJustPressed(Input.Keys.NUM_4) ) this.currentTool = Tool.PLACE_STRANSP;
		if( Gdx.input.isKeyJustPressed(Input.Keys.NUM_5) ) this.currentTool = Tool.PLACE_PWPLANT;
		if( Gdx.input.isKeyJustPressed(Input.Keys.NUM_6) ) this.currentTool = Tool.PLACE_PTRANSP;
		if( Gdx.input.isKeyJustPressed(Input.Keys.NUM_7) ) this.currentTool = Tool.PLACE_BASE;		
		
		if( Gdx.input.isKeyJustPressed(Input.Keys.Q) )
		{
			this.currentTool--;
			if( this.currentTool < 0 )
			{
				this.currentTool = Tool.NUM_TOOLS - 1;
			}
		}
		
		if( Gdx.input.isKeyJustPressed(Input.Keys.E) )
		{
			this.currentTool++;
			if( this.currentTool >= Tool.NUM_TOOLS )
			{
				this.currentTool = 0;
			}
		}
		
		// click
		if( Gdx.input.justTouched() && Gdx.input.isButtonPressed(Input.Buttons.LEFT) )
		{
			float x, y;
			x = Gdx.input.getX();
			y = Gdx.input.getY();
			Vector3 v = g.cam.unproject(new Vector3(x, y, 0));
			scenario.tryApplyTool(v.x, v.y, this.currentTool);
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	
}
