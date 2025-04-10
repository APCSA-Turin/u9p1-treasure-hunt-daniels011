package com.example.project;
import java.util.Scanner;

public class Game {
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size, String difficulty) {
        this.size = size; //initalises the instance variable size for given difficulty
        initialize(difficulty); //initalises using the diffculty given in the main method
        play(); // starts the game logic
    }

    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (player.getWin()){ //first checks to see if the player has won
                clearScreen();
                System.out.println("Congrats you win!");
                break; // ends the loop
            } else if (player.getLives() == 0){ // checks if the player has lost by seeing if they ran out of lives
                clearScreen();
                System.out.println("We regret to inform you that you lost :(");
                break; // ends the lop
            }
            clearScreen(); // clears the screen and adds space between grids
            System.out.println(" "); 
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            
            grid.display(); // calls the display method to show the grid
            System.out.println(player.getRowCol(size)); //shows the 2D array coordinates
            System.out.println(player.getCoords()); // shows the cartesian plane coordinates
            System.out.println("Treasure Collected: " + player.getTreasureCount());
            System.out.println("Lives remaining: " + player.getLives());
            System.out.print("Enter a direction (w,a,s,d) or 'q' to exit: ");
            String input = scanner.nextLine(); // takes input from the player

            if (input.equals("q")) { // checks to see if the player would like to end the game
                break;
            }

            if (player.isValid(size, input)) { // calls the isvalid method to see if the input that the player made is valid meaning its not out of bounds and can be done
                Object obj = null;

                // finds the object that the player wants to move to be able to interact with it
                if (input.equals("w")) {
                    obj = grid.getGrid()[(size - 1) - player.getY() - 1][player.getX()]; // checks the object one above the object
                } else if (input.equals("s")) {
                    obj = grid.getGrid()[size - 1 - player.getY() + 1][player.getX()]; // checks the object one below the object
                } else if (input.equals("a")) {
                    obj = grid.getGrid()[(size - 1) - player.getY()][player.getX() - 1]; //checks the object to the left of the object
                } else if (input.equals("d")) {
                    obj = grid.getGrid()[(size - 1) - player.getY()][player.getX() + 1]; // checks the object to the right of the object
                }

                player.interact(grid.getGrid().length, input, treasures.length, obj); // calls the interact method to handle the lives and the num of treausres using the object
                player.move(input); // moves the player to the new location
                grid.placeSprite(player, input); // renders the player on the screen and on the grid
                
            }
        }
    }

    public void initialize(String difficulty) {
        // makes a new blank grid 
        grid = new Grid(size);
        
        trophy = new Trophy(size - 1, size - 1); // places the trophy on the bottom right
        if (difficulty.equals("h")){ // hard mode
            enemies = new Enemy[1]; // makes a enemy array
            enemies[0] = new Enemy(1, 1); // makes an  enemy at (1,1)
            player = new Player(0, 2); // places the player at (0,2)
            player.setLives(1); // sets the lives to 1 because it is hard mode
        }
        if (difficulty.equals("e")){ // easy mode
        enemies = new Enemy[1]; // makes an enemy array
        enemies[0] = new Enemy(1, 1); //places enemy at (1,1)
        
        player = new Player(0, 2); // places the player at (0,2)
        player.setLives(5); // sets lives to 5 because it is easy mode
        }
        if (difficulty.equals("m")){ // medium mode
            enemies = new Enemy[2]; // two enemy aray
            enemies[0] = new Enemy(1, 1); // enemy at (1,1)
            enemies[1] = new Enemy(size - 2, size - 2); // enemy at close to top right
            player = new Player(0, 2); // places the player at (0,2)
            player.setLives(2); // sets lives to 2 because it is medium mode
            }
        treasures = new Treasure[2]; // two treasure array creation
        treasures[0] = new Treasure(0, size - 1); // first tresure at top left
        treasures[1] = new Treasure(size - 1, 0); // second treasure at bottom right

        grid.placeSprite(player); // renders the player in the grid
        grid.placeSprite(trophy); // renders the trophy in the grid
        grid.placeSprite(enemies[0]); // renders the first enemy in the grid
        if (difficulty.equals("m")){ // if the difficulty is medium then there is a second enemy (hard mode grid is too small to have a second enemy)
        grid.placeSprite(enemies[1]); // renders second enemy
        }
        grid.placeSprite(treasures[0]); // renders the first treasure
        grid.placeSprite(treasures[1]); // renders the second treasure
    }

    public static void main(String[] args) { // main method to have all of the difficulty and play again handling
        Scanner scanner = new Scanner(System.in);
        while (true){
        System.out.println("Choose difficulty (e, m , h)"); // prompts the user to choose their difficulty
        String difficulty = scanner.nextLine(); // collects the user response
        if (difficulty.equals("e")) { // easy mode
            new Game(10, difficulty); // sets the grid size to 10 and the diffculty to e and calls the game constructor
        } else if (difficulty.equals("m")) { // medium mode
            new Game(6, difficulty); // sets the grid size to 6 and the diffculty to m and calls the game constructor

        } else if (difficulty.equals("h")) { //hard mode
            new Game(4, difficulty); // sets the grid size to 4 and the diffculty to h and calls the game constructor
        }
        System.out.println("would you like to play again (y/n)"); // prompts the user to play again
        String choice = scanner.nextLine(); // collects reponse
        if (choice.equals("n")){ // if the user does not want to play again the loop terminates
            break;
       
        }  
   
    }
    }
}

