/**
 * @author Ng Yau Chuan Jason for zendesk Take Home Assignment prototype
 */

// import Scanner for input of players name and selection of which box
import java.util.Scanner;

public class TicTacToe {
    // declare variables for player 1 and player 2
    private static String player1 = "";
    private static String player2 = "";
    
    // declare variable for storing input Strings to validate
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
        
        // prompt the users to input their names and reprompts if input is empty
        System.out.println("Enter name for Player 1:");
        player1 = input.nextLine();
        System.out.println();   // visual purposes
        // validation to check if player1 is a valid input, not empty or not white space
        while (player1.replaceAll("\\s", "").equals("")) {
            System.out.println("Invalid input. Enter name for Player 1:");
            player1 = input.nextLine();
            System.out.println();   // visual purposes
        }
        System.out.println("Enter name for Player 2:");
        player2 = input.nextLine();
        System.out.println();    // visual purposes
        // validation to check if player1 is a valid input, not empty or not white space
        while (player2.replaceAll("\\s", "").equals("")) {
            System.out.println("Invalid input. Enter name for Player 2:");
            player2 = input.nextLine();
            System.out.println();   // visual purposes
        }
        
        // while loop to allow user to exit program or play again.
        while (!(newGame.equals("n") || newGame.equals("N"))) {
            // create board object to use isNumeric method
            board ticTacToe = new board();
            
            // prompt the user to choose the game size
            System.out.println("Enter size for Tic Tac Toe board, an integer more than or equal to 3:");
            buffer = input.nextLine();
            System.out.println();   // visual purposes
            
            while (buffer.replaceAll("\\s", "").equals("") || !ticTacToe.isNumeric(buffer) || Integer.parseInt(buffer) < 3 ) {
                System.out.println("Invalid input. Please try again. Enter size for Tic Tac Toe board, an integer more than or equal to 3:");
                buffer = input.nextLine();
                System.out.println();   // visual purposes
            }
            
            // recreate board object and store the size of the board to buffer for N x N board
            ticTacToe = new board(Integer.parseInt(buffer));
            
            // call function to display tic tac toe board
            ticTacToe.show();

            System.out.println("\n");   // visual purposes

            // while loop to keep prompting until one player wins or a draw happens
            while (end) {
                if (turn % 2 == 1) {
                     // prompt player 1 to enter which box to place an 'x' in
                    System.out.println(player1 + ", choose a box to place an 'x' into:");
                    buffer = input.nextLine();

                    // use validate function to evaluate buffer and reprompt if invalid input
                    buffer = ticTacToe.validate(buffer, player1, "x");
					
					xSelect = Integer.parseInt(buffer);
					
                    System.out.println();   // visual purposes

                    // change value in the box with the xSelect number
                    ticTacToe.replace(xSelect, "x");
                }
                else {
                    // prompt player 2 to enter which box to place an 'x' in
                    System.out.println(player2 + ", choose a box to place an 'o' into:");
                    buffer = input.nextLine();

                     // use validate function to evaluate buffer and reprompt if invalid input
                    buffer = ticTacToe.validate(buffer, player2, "o");
					
					oSelect = Integer.parseInt(buffer);
					
                    System.out.println();   // visual purposes

                     // change value in the box with the xSelect number
                    ticTacToe.replace(oSelect, "o");
                }

                 // call function to display tic tac toe board
                ticTacToe.show();

                System.out.println("\n");   // visual purposes

                // only the 5th turn onwards will there be a winner and require to check the state of the game.
                if (turn > 4) {
                    winner = ticTacToe.check(Integer.parseInt(buffer));
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
                        if (turn == ticTacToe.getSize()*ticTacToe.getSize()) {
                            System.out.println("Game ends in a draw.");
                            end = false;
                        }   break;
                }

                // increase turn to allow program to determine whose turn is it and to end with a draw
                turn ++;
            }
            
            System.out.println("Would you like to play again?(Y/N): ");
            newGame = input.nextLine();
            
			while (!(newGame.equals("n") || newGame.equals("N")) && !(newGame.equals("y") || newGame.equals("Y"))) {
				System.out.println("Invalid input. Please try again. Would you like to play again?(Y/N): ");
                newGame = input.nextLine();
                System.out.println();   // visual purposes
			}
			
            // reset board
            for (int i = 1; i <= ticTacToe.getSize()*ticTacToe.getSize(); i++) {
                ticTacToe.replace(i, String.valueOf(i));
            }
            end = true;
            turn = 1;
            winner = "";
        }
    }
}