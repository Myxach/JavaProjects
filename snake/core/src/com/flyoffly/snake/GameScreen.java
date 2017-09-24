package com.flyoffly.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Random;

public class GameScreen implements Screen {
    private final SnakeGame game;
	private Stage stage;
	private Eat eat;
    private final Snake snake;
    private Label score;
	private Label heightScore;
    private final Random random;
    private final Texture background;
	private final Sound sound;
GameScreen(SnakeGame game) {
		super( );
		random = new Random();
		this.game = game;
		snake = new Snake();
		snake.add();
		Setting.getInstance().Reset();
		generationEat();
		createUI(game);
		background = new Texture("images/background.png");
		game.music.play();
		sound = Gdx.audio.newSound(Gdx.files.internal("sound/snake.wav"));
		sound.setVolume(0,0.5f);
}

private void createUI(SnakeGame game) {
	score = new Label(String.valueOf(snake.getScore( )),new LabelStyle(game.bitmapFont, Color.WHITE));
	heightScore = new Label(String.valueOf(game.heightScore),
								   new LabelStyle(game.bitmapFont, Color.WHITE));
	float prefHeight = score.getPrefHeight( )*2;
	score.setPosition(score.getPrefWidth(), Gdx.graphics.getHeight()- prefHeight);
	UpdateHeightScore();
	stage = new Stage(new ScreenViewport());
	stage.addActor(score);
	stage.addActor(heightScore);
	
	Button button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("images/menu-button.png"))));
	button.addListener(event -> {
		if(Gdx.input.isButtonPressed(0)) {
			stopGame( );
			return true;
		}return false;
	});
	button.setPosition(Gdx.graphics.getWidth()-96,Gdx.graphics.getHeight()-96);
	stage.addActor(button);
	Gdx.input.setInputProcessor(stage);
}

private void generationEat() {
	Setting instance = Setting.getInstance( );
	int widthScreen = Gdx.graphics.getBackBufferWidth( );
	int heightScreen = Gdx.graphics.getBackBufferHeight( );
	
	eat = new Eat(new Vector2(random.nextInt(widthScreen / instance.SNAKE_SIZE ) * instance.SNAKE_SIZE,
									 random.nextInt(heightScreen / instance.SNAKE_SIZE) * instance.SNAKE_SIZE));
}

@Override
    public void show() {


    }
    void Update(float delta){
        snake.Update();
        stage.act(delta);
        if(eat.isCollision(snake)){
        	sound.play();
			generationEat();
			snake.add();
			score.setText(String.valueOf(snake.getScore( )));
			Setting.getInstance().Up();
			if(snake.getScore()>game.heightScore){
				game.heightScore = snake.getScore();
				UpdateHeightScore( );
			}
        }
        if(snake.isColderPart()) {
			stopGame( );
		}
}

private void stopGame( ) {
	game.music.stop();
	game.setScreen(new MenuScreen(game));
}

private void UpdateHeightScore( ) {
	heightScore.setText(String.valueOf(game.heightScore));
	heightScore.setPosition(( Gdx.graphics.getWidth( ) / 2 ) - ( heightScore.getPrefWidth( ) / 2 ),
			Gdx.graphics.getHeight( ) - ( heightScore.getPrefHeight( ) * 2 ));
}

@Override
    public void render(float delta) {
        Update(delta);
        
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
	
		game.batch.begin();
		game.batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		eat.Draw(game.batch);
		game.batch.end();
		snake.Draw(game.batch);
		
		game.batch.begin();
		stage.draw();
		game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
		background.dispose();
    }
}
