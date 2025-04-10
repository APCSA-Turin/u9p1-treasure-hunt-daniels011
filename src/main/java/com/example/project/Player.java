package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite { // child of sprite
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y); // calls the super class constructor sprite
        treasureCount = 0; // sets the count to 0 for treasure
        numLives = 2; // temporary lives counter to pass the tests
    
    }


    public int getTreasureCount(){return treasureCount;} // returns the treasure count
    public int getLives(){return numLives;} // returns the number of lives
    public boolean getWin(){return win;} // returns if the player has won or not
    public void setLives(int lives){ // sets the number of lives to a given value for the difficulty
        numLives = lives;
    }
    @Override
    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "Player:" + "(" + getX() + ","+ getY() + ")";
    }
    @Override
    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        return "Player:" + "[" + (size - 1 - getY()) + "]" + "[" + getX() + "]";
    }
  
    //move method should override parent class, sprite
    @Override
    public void move(String direction) { //move the (x,y) coordinates of the player
        direction = direction.toLowerCase();
        if (direction.equals("w")) { // moves the player up by 1
            setY(getY() + 1);
        } else if (direction.equals("s")) { //moves the player down by 1
            setY(getY() - 1);
        } else if (direction.equals("d")) { // moves the player to the right by 1
            setX(getX() + 1);
        } else if (direction.equals("a")) { // moves the player to the left by 1
            setX(getX() - 1);
        }
    }

    
    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
        //numTreasures is the total treasures at the beginning of the game
        if (obj instanceof Treasure && (obj instanceof Trophy == false)){ // first checks to see if the obj is treasure and not a trophy
            treasureCount++; // adds to the treasure count if its a treasure
        } else if (obj instanceof Enemy){ // checks if its a enemy
            numLives--; // takes a live off since its a enemy
            if (numLives == 0){ // checks to see if the player has run out of lives
                win = false; // player has lost
            }
            
        } else if (obj instanceof Trophy){ // checks if the obj is a trophy
            if (treasureCount == numTreasures){ // if the treasure count has reached the desired number of treasures goal
                win = true; // player wins the game
                
            }
        }
    } 


    public boolean isValid(int size, String direction){ //check grid boundaries
        //generates a new temporary coord point to be changed
        int newX = getX();
        int newY = getY();
        
        //updates the temp coordinate to the possible location that it could be after moving in that direction
        direction = direction.toLowerCase();
        if (direction.equals("w")) { // increments the new y value by 1 since it goes up
            newY += 1;
        } else if (direction.equals("s")) { // decrements the new y value by 1 since it goes down
            newY -= 1;
        } else if (direction.equals("d")) { // increments the new x value by 1 since it goes right
            newX += 1;
        } else if (direction.equals("a")) { // decrements the new x value by 1 since it goes left
            newX -= 1;
        }
        
        //checks if the coordinates are out of bounds
        if (newX < 0) { // left bound
            return false;
        } else if (newX >= size) { // right bound
            return false;
        } else if (newY < 0) { //upper bound
            return false;  
        } else if (newY >= size) { //lower bound
            return false;
        } else {
            return true; // if all the correct bounds are met then it is a valid movement
        }
    }


   
}