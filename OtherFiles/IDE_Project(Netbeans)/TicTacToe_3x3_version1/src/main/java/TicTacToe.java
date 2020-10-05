/**
 * @author Ng Yau Chuan Jason for zendesk Take Home Assignment prototype
 */

// import HashMap to store the board and its values
import java.util.HashMap;
// import Scanner for input of players name and selection of which box
import java.util.Scanner;

public class TicTacToe {
    // declare tic tac toe board using hashmap
    private static HashMap<Integer, String> board = new HashMap<Integer, String>();
    // declare variables for player 1 and player 2
    private static String player1 = "";
    private static String player2 = "";
    
    private static String buffer;
    
    // declare variables for the selected box to enter 'x' and 'o' respectively
    private static int xSelect = 0;
    private static int oSelect = 0;
    
    // declare variable to determine which user's turn is it
    private static int turn = 1;
    
    // declare boolean to determine when to end the game
    private static boolean end = true;
    
    // declare String to store who is the winner
    private static String winner = "";
    
    // declare variable to allow user to play again/exit.
    private static String newGame = "";
    
    // main method
    public static void main(String args[]) {
        // Create Scanner object
        Scanner input = new Scanner(System.in);
        
        // Create functions object and store the size of the board to 3 for 3 x 3
        functions func = new functions(3);
        
        // add items to the HashMap board that stores the key/value pair
        for (int i = 1; i <= func.getSize()*func.getSize(); i++) {
            board.put(i, String.valueOf(i));
        }
        
        /*
        // test if adding of items successful
        System.out.println(board);
        */
        
        // prompt the users to input their names and reprompts if input is empty
        System.out.println("Enter name for Player 1:");
        player1 = input.nextLine();
        System.out.println();   // visual purposes
        while (player1.replaceAll("\\s", "").equals("")) {
            System.out.println("Invalid input. Enter name for Player 1:");
            player1 = input.nextLine();
            System.out.println();   // visual purposes
        }
        System.out.println("Enter name for Player 2:");
        player2 = input.nextLine();
        System.out.println();    // visual purposes
        while (player2.replaceAll("\\s", "").equals("")) {
            System.out.println("Invalid input. Enter name for Player 2:");
            player2 = input.nextLine();
            System.out.println();   // visual purposes
        }
        
        /*
        // test if input of players successful
        System.out.println(player1 + " " + player2);
        */
        
        // while loop to allow user to exit program or play again.
        while (!(newGame.equals("q") || newGame.equals("Q"))) {
            // call function to display tic tac toe board
            func.show(board);

            System.out.println("\n");   // visual purposes

            // while loop to keep prompting until one player wins or a draw happens
            while(end) {
                if (turn % 2 == 1) {
                     // prompt player 1 to enter which box to place an 'x' in
                    System.out.println(player1 + ", choose a box to place an 'x' into:");
                    buffer = input.nextLine();

                    // use validate function to evaluate buffer and reprompt if invalid input
                    xSelect = func.validate(board, buffer, player1, "x");

                    System.out.println();   // visual purposes

                    // change value in the box with the xSelect number
                    board.replace(xSelect, "x");
                }
                else {
                    // prompt player 2 to enter which box to place an 'x' in
                    System.out.println(player2 + ", choose a box to place an 'o' into:");
                    buffer = input.nextLine();

                     // use validate function to evaluate buffer and reprompt if invalid input
                    oSelect = func.validate(board, buffer, player2, "o");

                    System.out.println();   // visual purposes

                     // change value in the box with the xSelect number
                    board.replace(oSelect, "o");
                }

                 // call function to display tic tac toe board
                func.show(board);

                System.out.println("\n");   // visual purposes

                // only the 5th turn onwards will there be a winner and require to check the state of the game.
                if (turn > 4) {
                    winner = func.state(board);
                }

                /*
                // initial condition to exit while loop before implementing evaluation if a player has won.
                if (board.get(9).equals("o") || board.get(9).equals("x")) {
                end = false;
                }
                 */
                // if the winner is player 1 print out and end loop
                switch (winner) {
                    case "x":
                        System.out.println("Congratulations " + player1 + "! You have won.");
                        end = false;
                        break;
                    case "o":
                        System.out.println("Congratulations " + player2 + "! You have won.");
                        end = false;
                        break;
                    default:
                        if (turn == func.getSize()*func.getSize()) {
                            System.out.println("Game ends in a draw.");
                            end = false;
                        }   break;
                }

                // increase turn to allow program to determine whose turn is it and to end with a draw
                turn ++;
            }
            
            System.out.println("Press Enter key to play again or input q to quit the program.");
            newGame = input.nextLine();
            
            // reset board
            for (int i = 1; i <= func.getSize()*func.getSize(); i++) {
                board.replace(i, String.valueOf(i));
            }
            end = true;
            turn = 1;
            winner = "";
        }
    }
}