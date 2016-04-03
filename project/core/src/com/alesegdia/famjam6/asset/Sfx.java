package com.alesegdia.famjam6.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sfx {

	public static Music maintheme;

	public static void Initialize()
	{
		maintheme = Gdx.audio.newMusic(Gdx.files.internal("illo_un_keba.ogg"));
		maintheme.setLooping(true);
		maintheme.setVolume(0.5f);
		maintheme.play();
	}

}
