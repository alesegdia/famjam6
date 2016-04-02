package com.alesegdia.famjam6.screen;

import com.alesegdia.famjam6.GameConfig;
import com.alesegdia.famjam6.GdxGame;
import com.alesegdia.famjam6.asset.Gfx;
import com.alesegdia.famjam6.map.DebugTerrainRenderer;
import com.alesegdia.famjam6.map.NoiseGen;
import com.alesegdia.famjam6.map.Scenario;
import com.alesegdia.famjam6.map.Tool;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GameplayScreen implements Screen {

	private GdxGame g;
	private float[][] map;
	private DebugTerrainRenderer debugMapRenderer;
	private Scenario scenario;
	
	private int currentTool = 0;
	
	boolean isMenuOpened = false;

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
	
	Vector2 realCamPos = new Vector2(0,0);

	@Override
	public void render(float delta)
	{
		
		handlePlayerInput();
		
		//gw.setCam();
        
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        g.cam.update();
        g.batch.setProjectionMatrix(g.cam.combined);
		g.batch.begin();
		this.scenario.render(g.batch);
		g.batch.end();
		
		g.textCam.update();
		g.srend.setProjectionMatrix(g.textCam.combined);
		g.srend.setAutoShapeType(true);
		g.srend.begin();
		g.srend.set(ShapeType.Filled);
		//this.debugMapRenderer.render(g.srend);
		g.srend.setAutoShapeType(true);
		g.srend.set(ShapeType.Filled);
		g.srend.setColor(0.5f, 0f, 0f, 1f);
		g.srend.rect(0, 0, 800, 50);
		g.srend.setColor(0.3f, 0.f, 0.f, 1f);
		g.srend.rect(0, 0, 800, 45);
		g.srend.end();
		
		g.batch.setProjectionMatrix(g.textCam.combined);
		g.batch.begin();
		g.font.draw(g.batch, Tool.getToolString(this.currentTool), 10, 25);
		g.batch.end();
		
		g.batch.setProjectionMatrix(g.textCam.combined);
		g.batch.begin();
		g.font.draw(g.batch, Tool.getToolString(this.currentTool), 10, 25);
		g.batch.end();
		
		if( isMenuOpened )
		{
			g.textCam.update();
			g.srend.setProjectionMatrix(g.textCam.combined);
			g.srend.setAutoShapeType(true);
			g.srend.begin();
			g.srend.set(ShapeType.Filled);
			//this.debugMapRenderer.render(g.srend);
			g.srend.setAutoShapeType(true);
			g.srend.set(ShapeType.Filled);
			g.srend.setColor(0.5f, 0f, 0f, 1f);
			g.srend.rect(600, 0, 200, 600);
			g.srend.setColor(0.3f, 0.f, 0.f, 1f);
			g.srend.rect(605, 0, 200, 600);
			g.srend.end();

			g.batch.setProjectionMatrix(g.textCam.combined);
			g.batch.begin();
			g.batch.draw(Gfx.froncetiteGathererTr, 640, 400, 0, 0, 8, 8, 6, 6, 0);
			g.batch.draw(Gfx.froncetiteTransportTr[1], 720, 400, 0, 0, 8, 8, 6, 6, 0);

			g.batch.draw(Gfx.sandetiteGathererTr, 640, 340, 0, 0, 8, 8, 6, 6, 0);
			g.batch.draw(Gfx.sandetiteTransportTr[1], 720, 340, 0, 0, 8, 8, 6, 6, 0);
			
			g.batch.draw(Gfx.powerPlantTr, 640, 280, 0, 0, 8, 8, 6, 6, 0);
			g.batch.draw(Gfx.powerTransportTr[1], 720, 280, 0, 0, 8, 8, 6, 6, 0);
			
			g.batch.draw(Gfx.baseExtensionTr, 640, 220, 0, 0, 8, 8, 6, 6, 0);
			
			g.batch.draw(Gfx.deleteTr, 640, 160, 0, 0, 8, 8, 6, 6, 0);
			g.batch.draw(Gfx.cursorTr, 720, 160, 0, 0, 8, 8, 6, 6, 0);

			g.batch.draw(Gfx.froncetiteTerrainTr, 640, 70, 0, 0, 8, 8, 6, 6, 0);
			g.font.draw(g.batch, "x100", 700, 100);

			g.batch.draw(Gfx.sandetiteTerrainTr, 640, 10, 0, 0, 8, 8, 6, 6, 0);
			g.font.draw(g.batch, "x100", 700, 40);

			g.batch.end();
			
			int sz = 8*6;
			if( clickIn(640, 400, sz, sz) ) this.currentTool = Tool.PLACE_FGATHER;
			if( clickIn(720, 400, sz, sz) ) this.currentTool = Tool.PLACE_FTRANSP;
			
			if( clickIn(640, 340, sz, sz) ) this.currentTool = Tool.PLACE_SGATHER;
			if( clickIn(720, 340, sz, sz) ) this.currentTool = Tool.PLACE_STRANSP;
			
			if( clickIn(640, 280, sz, sz) ) this.currentTool = Tool.PLACE_PWPLANT;
			if( clickIn(720, 280, sz, sz) ) this.currentTool = Tool.PLACE_PWTRANSP;
			
			if( clickIn(640, 220, sz, sz) ) this.currentTool = Tool.PLACE_BASE;
			if( clickIn(640, 160, sz, sz) ) this.currentTool = Tool.DESTROY;
			if( clickIn(720, 160, sz, sz) ) this.currentTool = Tool.SELECT;
		}
		
		Vector3 v = g.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		g.batch.setProjectionMatrix(g.cam.combined);
		g.batch.begin();
		g.batch.draw(Gfx.getToolCursor(this.currentTool), Math.round(v.x), Math.round(v.y));
		g.batch.end();
		

	}

	private boolean clickIn(int x, int y, int w, int h) {
		int mx, my;
		mx = Gdx.input.getX();
		my = GameConfig.WINDOW_HEIGHT - Gdx.input.getY();
		return Gdx.input.isButtonPressed(Input.Buttons.LEFT) &&
				mx > x - w/4 && mx < x + w/4 &&
				my > y + h/4 && my < y + 3 * h / 4 ;
	}

	private void handlePlayerInput() {
		
		int fcx, fcy;
		fcx = Gdx.input.getX();
		fcy = Gdx.input.getY();
		
		if( fcx < 0 ) fcx = 0;
		if( fcx > 800 - 40) fcx = 800 - 40;

		if( fcy < 38 ) fcy = 38;
		if( fcy > 600 ) fcy = 600;
		
		Gdx.input.setCursorPosition(fcx, fcy);

		
		float speed = 0.5f;
		float scrollThreshold = 100;
		
		if( Gdx.input.isKeyJustPressed(Input.Keys.TAB) )
		{
			this.isMenuOpened = !this.isMenuOpened;
		}
		
		if( Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ) speed = 1.f;
		
		if( Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.getX() < scrollThreshold) realCamPos.x -= speed;
		if( Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.getX() > GameConfig.WINDOW_WIDTH - scrollThreshold) realCamPos.x += speed;
		if( Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.getY() < scrollThreshold) realCamPos.y += speed;
		if( Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.getY() > GameConfig.WINDOW_HEIGHT - scrollThreshold) realCamPos.y -= speed;

		float l = GameConfig.VIEWPORT_WIDTH / 2f;
		float b = GameConfig.VIEWPORT_HEIGHT / 2f;
		float r = this.scenario.widthInTiles() * 8f - GameConfig.VIEWPORT_WIDTH / 2f;
		float t = this.scenario.heightInTiles() * 8f - GameConfig.VIEWPORT_HEIGHT / 2f;
		
		if( realCamPos.x < l ) realCamPos.x = l ;
		if( realCamPos.y < b ) realCamPos.y = b ;
		
		if( realCamPos.x > r ) realCamPos.x = r ;
		if( realCamPos.y > t ) realCamPos.y = t ;
		
		float k = 1;
		g.cam.position.x = Math.round(realCamPos.x * k) / k;
		g.cam.position.y = Math.round(realCamPos.y * k) / k;
		
		//g.cam.position.x = realCamPos.x;
		//g.cam.position.y = realCamPos.y;
		
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
			Vector3 v = g.cam.unproject(new Vector3(x + 18, y - 20, 0));
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
