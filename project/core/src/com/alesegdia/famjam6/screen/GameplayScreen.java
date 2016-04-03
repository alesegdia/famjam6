package com.alesegdia.famjam6.screen;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.famjam6.GameConfig;
import com.alesegdia.famjam6.GdxGame;
import com.alesegdia.famjam6.asset.Gfx;
import com.alesegdia.famjam6.entity.PlayerStatus;
import com.alesegdia.famjam6.entity.Upgrade;
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
	private PlayerStatus playerStatus = new PlayerStatus();
	
	private int currentTool = 0;
	
	boolean isMenuOpened = false;

	public GameplayScreen( GdxGame g )
	{
		this.g = g;
	}
	
	@Override
	public void show() {

		this.playerStatus = new PlayerStatus();

		this.map = NoiseGen.GeneratePerlinNoise(100, 100, 4);
		this.debugMapRenderer = new DebugTerrainRenderer(this.map, 8);
		this.scenario = new Scenario(map, 8, playerStatus);
		this.upgrades = new LinkedList<Upgrade>();
		realCamPos = new Vector2(0,0);
		
		upgrades.add(Upgrade.makeFroncetiteEfficiencyUpgrade(0.5f, 10, 10));
		upgrades.add(Upgrade.makeDrillEfficiencyUpgrade(2, 5, 5));
		upgrades.add(Upgrade.makePowerEfficiencyUpgrade(2, 100, 100));
		upgrades.add(Upgrade.makeFroncetiteEfficiencyUpgrade(0.5f, 10, 10));
		upgrades.add(Upgrade.makeDrillEfficiencyUpgrade(2, 5, 5));
		upgrades.add(Upgrade.makeSandetiteEfficiencyUpgrade(0.5f, 10, 10));
		upgrades.add(Upgrade.makePowerEfficiencyUpgrade(0.5f, 10, 10));
	}
	
	Vector2 realCamPos = new Vector2(0,0);
	private List<Upgrade> upgrades;
	
	float cantAffordTimer = 0f;
	Upgrade cantAffordUpgrade;

	@Override
	public void render(float delta)
	{
		
		handlePlayerInput();
		
		//gw.setCam();

		scenario.update();
		
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
		g.srend.setColor(0.3f, 0.3f, 0.4f, 1f);
		g.srend.rect(0, 0, 800, 50);
		g.srend.setColor(0.2f, 0.2f, 0.3f, 1f);
		g.srend.rect(0, 0, 800, 45);
		g.srend.end();

		if( this.cantAffordTimer > 0 )
		{
			int sc = 5;

			g.textCam.update();
			g.srend.setProjectionMatrix(g.textCam.combined);
			g.srend.setAutoShapeType(true);
			g.srend.begin();
			g.srend.set(ShapeType.Filled);
			g.srend.setColor(0,0,0,1);
			g.srend.rect(20, 70, 300, 140);
			g.srend.end();
			
			this.cantAffordTimer -= Gdx.graphics.getDeltaTime();

			g.batch.setProjectionMatrix(g.textCam.combined);
			g.batch.begin();

			g.batch.draw(Gfx.froncetiteSymTr, 30, 120, 0, 0, 8, 8, sc, sc, 0);
			
			g.font.draw(g.batch, this.cantAffordUpgrade.description, 90, 190);
			g.batch.draw(this.cantAffordUpgrade.icon, 30, 160, 0, 0, 8, 8, sc, sc, 0);

			
			if( !this.cantAffordUpgrade.canBuy(playerStatus) )
			{
				g.font.setColor(1,0,0,1);
			}
			g.font.draw(g.batch, "-" + Math.round(this.cantAffordUpgrade.fCost), 80, 150);
			g.batch.draw(Gfx.sandetiteSymTr, 30, 80, 0, 0, 8, 8, sc, sc, 0);

			g.font.draw(g.batch, "-" + Math.round(this.cantAffordUpgrade.sCost), 80, 110);
			g.batch.setColor(1,1,1,1);

			g.font.setColor(1,1,1,1);
			g.batch.end();

		}

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
			g.srend.setColor(0.3f, 0.3f, 0.4f, 1f);
			g.srend.rect(600, 0, 200, 600);
			g.srend.setColor(0.2f, 0.2f, 0.3f, 1f);
			g.srend.rect(605, 0, 200, 600);
			
			// upgrade box
			g.srend.setColor(0,0,0,1);
			g.srend.rect(620, 20, 160, 240);
			
			g.srend.end();

			g.batch.setProjectionMatrix(g.textCam.combined);
			g.batch.begin();

			int sc = 5;
			int sz = 8*4;

			Upgrade toRemove = null;
			for( int i = 0; i < Math.min(upgrades.size(), 5); i++ )
			{
				Upgrade u = upgrades.get(i);
				g.batch.draw(u.icon, 630, 30 + 46 * i, 0, 0, 8, 8, sc, sc, 0);
				g.font.draw(g.batch, u.text, 680, 54 + 46 * i);
				
				if( this.mouseIn(630, 30 + 46 * i, sz, sz) )
				{
					this.showCantAffordMessage(u);
				}
				if( toRemove == null && clickIn( 630, 30 + 46 * i, sz, sz) )
				{
					toRemove = u;
				}
			}
			
			if( toRemove != null )
			{
				if( toRemove.canBuy(playerStatus) )
				{
					toRemove.apply(this.playerStatus);
					this.upgrades.remove((Object)toRemove);
				}
				else
				{
					showCantAffordMessage(toRemove);
				}
			}

			g.batch.draw(Gfx.froncetiteGathererTr, 640, 520, 0, 0, 8, 8, sc, sc, 0);
			g.batch.draw(Gfx.froncetiteTransportTr[1], 720, 520, 0, 0, 8, 8, sc, sc, 0);

			g.batch.draw(Gfx.sandetiteGathererTr, 640, 460, 0, 0, 8, 8, sc, sc, 0);
			g.batch.draw(Gfx.sandetiteTransportTr[1], 720, 460, 0, 0, 8, 8, sc, sc, 0);
			
			g.batch.draw(Gfx.powerPlantTr, 640, 400, 0, 0, 8, 8, sc, sc, 0);
			g.batch.draw(Gfx.powerTransportTr[1], 720, 400, 0, 0, 8, 8, sc, sc, 0);
			
			g.batch.draw(Gfx.baseExtensionTr, 640, 340, 0, 0, 8, 8, sc, sc, 0);
			g.batch.draw(Gfx.duriroTr, 720, 340, 0, 0, 8, 8, sc, sc, 0);
			
			g.batch.draw(Gfx.deleteTr, 640, 280, 0, 0, 8, 8, sc, sc, 0);
			g.batch.draw(Gfx.cursorTr, 720, 280, 0, 0, 8, 8, sc, sc, 0);

			g.batch.end();
			
			if( clickIn(640, 520, sz, sz) ) this.currentTool = Tool.PLACE_FGATHER;
			if( clickIn(720, 520, sz, sz) ) this.currentTool = Tool.PLACE_FTRANSP;
			
			if( clickIn(640, 460, sz, sz) ) this.currentTool = Tool.PLACE_SGATHER;
			if( clickIn(720, 460, sz, sz) ) this.currentTool = Tool.PLACE_STRANSP;
			
			if( clickIn(640, 400, sz, sz) ) this.currentTool = Tool.PLACE_PWPLANT;
			if( clickIn(720, 400, sz, sz) ) this.currentTool = Tool.PLACE_PTRANSP;
			
			if( clickIn(640, 340, sz, sz) ) this.currentTool = Tool.PLACE_BASE;
			if( clickIn(720, 340, sz, sz) ) this.currentTool = Tool.DURIRO;

			if( clickIn(640, 280, sz, sz) ) this.currentTool = Tool.DESTROY;
			if( clickIn(720, 280, sz, sz) ) this.currentTool = Tool.SELECT;
		}
		
		g.batch.setProjectionMatrix(g.textCam.combined);
		g.batch.begin();
		int sc = 5;

		g.batch.draw(Gfx.powerTr, 10, 550, 0, 0, 8, 8, sc, sc, 0);
		g.font.draw(g.batch, "" + Math.floor(playerStatus.getPowerPercent()) + "%", 60, 580);

		g.batch.draw(Gfx.froncetiteSymTr, 10, 510, 0, 0, 8, 8, sc, sc, 0);
		g.font.draw(g.batch, "" + Math.round(playerStatus.froncetite), 60, 540);

		g.batch.draw(Gfx.sandetiteSymTr, 10, 470, 0, 0, 8, 8, sc, sc, 0);
		g.font.draw(g.batch, "" + Math.round(playerStatus.sandetite), 60, 500);
		g.batch.end();

		Vector3 v = g.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		g.batch.setProjectionMatrix(g.cam.combined);
		g.batch.begin();
		g.batch.draw(Gfx.getToolCursor(this.currentTool), Math.round(v.x), Math.round(v.y));
		g.batch.end();

	}

	private void showCantAffordMessage(Upgrade toRemove) {
		this.cantAffordTimer = 3f;
		this.cantAffordUpgrade = toRemove;
	}

	private boolean clickIn(int x, int y, int w, int h) {
		return mouseIn( x, y, w, h ) && Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.justTouched();
	}
	
	private boolean mouseIn( int x, int y, int w, int h )
	{
		int mx, my;
		mx = Gdx.input.getX();
		my = GameConfig.WINDOW_HEIGHT - Gdx.input.getY();
		return  
				mx > x - w/2 && mx < x + w - w/2 &&
				my > y - h/2 && my < y + h - h/2;
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

		
		float speed = 1f;//0.5f;
		float scrollThreshold = 50;
		
		if( Gdx.input.isKeyJustPressed(Input.Keys.TAB) )
		{
			this.isMenuOpened = !this.isMenuOpened;
		}
		
		if( Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ) speed = 1.f;
		
		if( Gdx.input.getX() < scrollThreshold) realCamPos.x -= speed;
		if( Gdx.input.getX() > GameConfig.WINDOW_WIDTH - scrollThreshold) realCamPos.x += speed;
		if( Gdx.input.getY() < scrollThreshold) realCamPos.y += speed;
		if( Gdx.input.getY() > GameConfig.WINDOW_HEIGHT - scrollThreshold) realCamPos.y -= speed;
		
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
		
		if( Gdx.input.isKeyJustPressed(Input.Keys.Q) ) this.currentTool = Tool.PLACE_FGATHER;
		if( Gdx.input.isKeyJustPressed(Input.Keys.W) ) this.currentTool = Tool.PLACE_SGATHER;
		if( Gdx.input.isKeyJustPressed(Input.Keys.A) ) this.currentTool = Tool.PLACE_FTRANSP;
		if( Gdx.input.isKeyJustPressed(Input.Keys.S) ) this.currentTool = Tool.PLACE_STRANSP;
		if( Gdx.input.isKeyJustPressed(Input.Keys.E) ) this.currentTool = Tool.PLACE_PWPLANT;
		if( Gdx.input.isKeyJustPressed(Input.Keys.D) ) this.currentTool = Tool.PLACE_PTRANSP;
		if( Gdx.input.isKeyJustPressed(Input.Keys.R) ) this.currentTool = Tool.PLACE_BASE;		
		
		// click
		if( Gdx.input.justTouched() && Gdx.input.isButtonPressed(Input.Buttons.LEFT) )
		{
			float x, y;
			x = Gdx.input.getX();
			y = Gdx.input.getY();
			Vector3 v = g.cam.unproject(new Vector3(x + 18, y - 20, 0));
			if( !this.isMenuOpened || (this.isMenuOpened && Gdx.input.getX() < 560) )
			{
				scenario.tryApplyTool(v.x, v.y, this.currentTool);				
			}
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
