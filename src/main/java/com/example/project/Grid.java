package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        grid = new Sprite[size][size];
        this.size = size;

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                grid[i][j] = new Dot(i, j);
            }
        }
        
    }

    public Sprite[][] getGrid(){return grid;}


    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[grid.length - 1 - s.getY()][s.getX()] = s; // takes the xy cartesien plane coordinates and converts them to 2d array indexes
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        
        grid[size - s.getY() - 1][s.getX()] = s; // places the sprite in the new spot
        
        //puts a dot in the spot of the old place where the sprite was
        if(direction.equals("w")){ //moves sprite up
            grid[size - s.getY()][s.getX()] = new Dot(s.getX() , s.getY() - 1); // puts a dot one unit below the new location
        } 
        if(direction.equals("s")){ // moves sprite down
            grid[size - s.getY() - 2][s.getX()] = new Dot(s.getX(), s.getY()); //puts a dot one unit above the new location
        }
        if(direction.equals("a")){ // moves sprite left
            grid[size - s.getY() - 1][s.getX() + 1] = new Dot(s.getX() + 1, s.getY()); // puts a dot one unit to the right of the new location
        }
        if(direction.equals("d")){ // moves sprite right
            grid[size - s.getY() - 1][s.getX() - 1] = new Dot(s.getX() - 1, s.getY()); // puts a dot one unit to the left of the new location          
        }
    }


    public void display() { //print out the current grid to the screen 
        for (int i = 0; i < grid.length; i++) { // iterates through the 2d array with row major
            for (int j = 0; j < grid[0].length; j++) {
                if( grid[i][j] instanceof Dot ){
                    System.out.print("[ ]"); // if the space is a dot then it is empty
                   }
                   if(grid[i][j] instanceof Player ) { // print p for player
                    System.out.print("[P]");
    
                   }
                   
                   if((grid[i][j] instanceof Treasure) && !(grid[i][j] instanceof Trophy)){ // prints t for treasure
                    System.out.print("[T]");
    
                   }
                   if(grid[i][j] instanceof Trophy){ // prints r for trpohy
                    System.out.print("[R]");
    
                   }
                   if(grid[i][j] instanceof Enemy){ // prints e for enemy
                    System.out.print("[E]");
    
                   }
               
            }
            System.out.println(); // makes a new line after each row
        }
    }
    
    public void gameover(){ //use this method to display a loss
    }

    public void win(){ //use this method to display a win 
    }


}