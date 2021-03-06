package com.alesegdia.famjam6.screen;

import com.alesegdia.famjam6.GdxGame;
import com.alesegdia.famjam6.asset.Gfx;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class InstructionsScreen implements Screen {

	private GdxGame g;

	public InstructionsScreen( GdxGame g )
	{
		this.g = g;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	String instructions = "open menu with <TAB>\n" +
						  "pick resources with drill\n" +
						  "build power plants\n" +
						  "build transports\n" +
						  "build gatherers\n" +
						  "buy upgrades\n" +
						  "exploit the planet!\n\n" +
						  "WARNING!!\n" +
						  "if energy > 200%, buildings will start to break";
	
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		g.batch.setProjectionMatrix(g.menuCam.combined);
		g.batch.begin();
		g.batch.draw(Gfx.splashIcon, 50, 50);
		g.batch.end();
		
		g.batch.setProjectionMatrix(g.cam2.combined);
		g.batch.begin();
		g.fontBig.draw(g.batch, this.instructions, -420, 40, 1000, 1, false);
		g.batch.end();
		

		if( Gdx.input.justTouched() )
		{
			g.setScreen(g.gameplayScreen);
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
