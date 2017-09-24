package com.flyoffly.snake;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Eat implements  MyDrawable {

private final Vector2 position;
Texture texture;
Sprite sprite;
Eat(Vector2 position){
	super( );
	this.position = position;
	texture = new Texture("images/apple.png");
	sprite = new Sprite(texture);
	sprite.setPosition(position.x,position.y);
	sprite.setSize(Setting.getInstance().SNAKE_SIZE,Setting.getInstance().SNAKE_SIZE);
}
@Override
public void Draw(SpriteBatch spriteBatch) {
	sprite.draw(spriteBatch);
	//	spriteBatch.draw(texture,position.x,position.y, Setting.getInstance().SNAKE_SIZE, Setting.getInstance().SNAKE_SIZE);
	/*renderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());
	renderer.begin(ShapeType.Filled);
	renderer.rect(position.x,position.y, Setting.getInstance().SNAKE_SIZE, Setting.getInstance().SNAKE_SIZE);
	renderer.end();
	*/
}

@Override
public void Update( ) {

}

@Override
public Rectangle getRectangle( ) {
	Setting instance = Setting.getInstance( );
	return new Rectangle(position.x,position.y, instance.SNAKE_SIZE, instance.SNAKE_SIZE);
}
}
