package com.flyoffly.snake;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Snake implements MyDrawable {
	enum Dir{
		UP(new Vector2(0,1)),
		DOWN(new Vector2(0,-1)),
		LEFT(new Vector2(-1,0)),
		RIGHT(new Vector2(1,0));
		final Vector2 direction;
		Dir(Vector2 dir){
			direction = dir;
		}
	}
	private final ShapeRenderer renderer;
	private Vector2 position = new Vector2();
	private final Vector<Snake> part = new Vector<>(2);
	private Dir dir = Dir.RIGHT;
	int getScore(){
		return part.getLength();
	}
	Snake( ) {
		super( );
		renderer = new ShapeRenderer( );
	}
	
	void add(){
		Snake snake = new Snake();
			snake.position = new Vector2(-500,-5000);
		 part.add(snake);
	}
	public void  Draw(SpriteBatch spriteBatch){
		renderer.setColor(Color.YELLOW);
		Setting instance = Setting.getInstance( );
		
		renderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());
		renderer.begin(ShapeType.Filled);
		renderer.rect(position.x, position.y, instance.SNAKE_SIZE, instance.SNAKE_SIZE);
		renderer.end();
		for(int i=0;i<part.getLength();i++){
			part.get(i).Draw(spriteBatch);
		}
		//		renderer.flush();
	}
	boolean isColderPart( ){
		for(int i=0;i<part.getLength();i++){
			Rectangle rectangle = part.get(i).getRectangle( );
			if( getRectangle( ).overlaps(rectangle) ){
				return true;
			}
		}
		return false;
	}
	private float timer;
	public void Update(){
		Setting instance = Setting.getInstance( );
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)) dir = Dir.LEFT;
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) dir = Dir.RIGHT;
		if(Gdx.input.isKeyPressed(Keys.UP)) dir = Dir.UP;
		if(Gdx.input.isKeyPressed(Keys.DOWN)) dir = Dir.DOWN;
		
		if( timer >= ( 1 / instance.getSpeed( ) ) ) move(instance);
		else timer+=Gdx.graphics.getDeltaTime();
	}

private void move(Setting instance ) {
	
	for( int i = part.getLength()-1; i > 0; i--){
		Snake snake = part.get(i - 1);
		part.get(i).position = new Vector2(snake.position.x, snake.position.y);
	}//Сзади по направлению головы, передвигаем кусочек змейки на место следующего куска, то-есть
	//2,1,0,H->_,2or1,0,H->_,2,1or0,H->_,2,1,0,H
	if( 1 <= part.getLength( ) ) {
		part.get(0).position = new Vector2(position.x, position.y);
	}//Голова не находиться в массиве тел, поэтому самую первую часть, мы перемещаем отдельно
	
	position=new Vector2(position.x + ( dir.direction.x * instance.SNAKE_SIZE ),
								position.y + ( dir.direction.y * instance.SNAKE_SIZE  ));//вот теперь можно переместить и голову
	
	timer = 0;//сбрасываем таймер
	borderCheck(instance);//проверяем выход за границы
}

private void borderCheck(Setting instance) {
	if( 0 > position.x ) position.x = Gdx.graphics.getWidth()- instance.SNAKE_SIZE;
	if( 0 > position.y ) position.y = Gdx.graphics.getHeight()- instance.SNAKE_SIZE;
	if( position.x > ( Gdx.graphics.getWidth( ) - instance.SNAKE_SIZE ) ) position.x = 0;
	if( position.y > ( Gdx.graphics.getHeight( ) - instance.SNAKE_SIZE ) ) position.y = 0;
}

public Rectangle getRectangle(){
	Setting instance = Setting.getInstance( );
	return new Rectangle(position.x, position.y, instance.SNAKE_SIZE, instance.SNAKE_SIZE);
	}

public Vector2 getPosition( ) {
	return position;
}

public void setPosition(Vector2 position) {
	this.position = position;
}
}
