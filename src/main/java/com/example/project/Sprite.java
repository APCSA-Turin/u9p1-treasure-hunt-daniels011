package com.example.project;

public class Sprite {
    private int x, y;

    public Sprite(int x, int y) {
        this.x = x; //initialises x
        this.y = y; // initalises y
    }

    public int getX(){return x;} // returns x
    public int getY(){return y;} // returns y

    public void setX(int x){ // sets x 
        this.x = x;
    }
    public void setY(int y){ // sets y 
        this.y = y;
    }

    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "(" + x + ","+ y + ")";
    }

    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        return "[" + (size - 1 - y) + "]" + "[" + x + "]";
    }
    

    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }



}
