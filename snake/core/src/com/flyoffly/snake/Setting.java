package com.flyoffly.snake;


public class Setting {

private static Setting ourInstance = new Setting();
private float Speed;

static Setting getInstance( ) {
	return ourInstance;
}

final int SNAKE_SIZE = 64;
float SCALE = 1.1f;
final float globalScale = 1.1f;
private Setting( ) {

}
void Reset( ){
	Speed = 1.8f;
	SCALE = 0.5f;
}
void Up( ){
	Speed+=Speed*SCALE;
	SCALE/=globalScale;
}
float getSpeed( ) {
	return Speed;
}
}
