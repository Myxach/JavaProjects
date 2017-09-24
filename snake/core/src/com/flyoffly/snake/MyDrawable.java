package com.flyoffly.snake;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;

public interface MyDrawable{
	void Draw(SpriteBatch spriteBatch );
	void Update( );
	default boolean isCollision(MyDrawable drawable){
		return getRectangle().overlaps(drawable.getRectangle());
	}
	Rectangle getRectangle( );
}
