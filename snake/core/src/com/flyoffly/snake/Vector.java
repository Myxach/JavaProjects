package com.flyoffly.snake;


public class Vector<T>{
private T[] array;
private int length;
Vector (int size){
	array = (T[])new Object[size];//0,1,2 2,1,0
}Vector(){
	array = (T[])new Object[1];
}
private void resize(int size){
	T[] old = array.clone();
	array =  (T[])new Object[size];
	for(int i =0;i<old.length;i++){
		array[i] = old[i];
	}
}

@Override
public String toString() {
	StringBuilder a = new StringBuilder();
	for(int i = 0; i< length; i++) a.append("[" + array[i] + "]");
	return a.toString();
}

public void add(T element){
	if(length >=array.length) resize(array.length*2);
	array[length++] = element;
}
public void remove(){
	if(length <0) throw new ArrayIndexOutOfBoundsException();
	length--;
}
public void removeAt(int id){
	if(length <0 || id>= length) throw new ArrayIndexOutOfBoundsException();
	for(int i = id; i< length-1; i++){ //0,2,3 1 элемент удаляем, значит переносим 3 = lenght-1, на место 1
		array[i] = array[i+1];
	}
	length--;
}
public void remove(T element){
	for(int i = 0; i< length; i++){
		if(array[i] == element) {
			removeAt(i);
			return;
		}
	}
	throw new IllegalArgumentException();
}
T get(int id){
	if(id>= length) throw new ArrayIndexOutOfBoundsException();
	return array[id];
}
public int getLength(){
	return length;
}
}
