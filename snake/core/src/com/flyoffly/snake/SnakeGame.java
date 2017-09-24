package com.flyoffly.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.*;

public class SnakeGame extends Game {
	SpriteBatch batch;
	BitmapFont bitmapFont;
	int heightScore = 0;
	 Music music;
	 Music music_menu;
	@Override
	public void create () {
		music= Gdx.audio.newMusic(Gdx.files.internal("music/bensound-funnysong.mp3"));
		music.setVolume(0.5f);
		music.setLooping(true);
		music_menu = Gdx.audio.newMusic(Gdx.files.internal("music/bensound-memories.mp3"));
		music_menu.setVolume(0.5f);
		music_menu.setLooping(true);
		bitmapFont = new BitmapFont(false);
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
		Load();
	}
	public void Load(){
		try {
			FileInputStream fileInputStream = new FileInputStream("score.board");
			try {
				heightScore = fileInputStream.read();
			} catch ( IOException e ) {
				e.printStackTrace( );
			}
		} catch ( FileNotFoundException e ) {
			heightScore = 0;
		}
	}
	public void Save() throws IOException {
		try {
			
			FileOutputStream fileOutputStream = new FileOutputStream("score.board");
			fileOutputStream.write(heightScore);
			fileOutputStream.close();
		} catch ( FileNotFoundException e ) {
			e.printStackTrace( );
		}
	}
	@Override
	public void dispose () {
		batch.dispose();
		bitmapFont.dispose();
	}
}
