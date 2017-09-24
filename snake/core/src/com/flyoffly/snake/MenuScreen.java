package com.flyoffly.snake;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.IOException;

public class MenuScreen implements Screen {

private final Stage stage;
public final Texture background;
private SnakeGame snakeGame;
	MenuScreen(SnakeGame game) {
		super( );
		snakeGame = game;
		snakeGame.music_menu.play();
		background = new Texture("images/background.png");
		TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("images/button.png")));
		com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup verticalGroup = new VerticalGroup();
		stage = new Stage(new ScreenViewport( ));
		stage.addActor(verticalGroup);
		verticalGroup.align(0);
		verticalGroup.columnAlign(2);
		verticalGroup.setFillParent(true);
		verticalGroup.setHeight(96*2+4);
		Button button = createButton(textureRegionDrawable,event -> {
			if ( Gdx.input.isButtonPressed(0) ) {
				snakeGame.music_menu.stop();
				snakeGame.setScreen(new GameScreen(snakeGame));
				return true;
			}
			return false;
		},"PLAY GAME");
		Button exit = createButton(textureRegionDrawable,event -> {
			if ( Gdx.input.isButtonPressed(0) ) {
				try {
					snakeGame.Save();
				} catch ( IOException e ) {
					e.printStackTrace( );
				}
				Gdx.app.exit();
				return true;
			}
			return false;
		},"EXIT GAME");
		verticalGroup.addActor(button);
		verticalGroup.addActor(exit);
		Gdx.input.setInputProcessor(stage);
	}

private Button createButton(TextureRegionDrawable textureRegionDrawable, EventListener eventListener,String text) {
	Button button = new ImageButton(textureRegionDrawable, textureRegionDrawable, textureRegionDrawable);
	button.addListener(eventListener);
	Label label = new Label(text,new Skin(Gdx.files.getFileHandle("skin.json", Files.FileType.Internal)),"medium", Color.RED);
	label.setFillParent(true);
	label.setAlignment(0);
	button.addActor(label);
	return button;
}

@Override
public void show( ) {

}

@Override
public void render(float delta) {
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	stage.act(delta);
	snakeGame.batch.begin();
	snakeGame.batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	snakeGame.batch.end();
	stage.draw();
}

@Override
public void resize(int width, int height) {

}

@Override
public void pause( ) {

}

@Override
public void resume( ) {

}

@Override
public void hide( ) {

}

@Override
public void dispose( ) {
}
}
