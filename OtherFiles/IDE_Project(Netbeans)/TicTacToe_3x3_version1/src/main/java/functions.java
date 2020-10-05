/**
 * @author Ng Yau Chuan Jason for zendesk Take Home Assignment prototype
 */

// import HashMap to store the board and its values
import java.util.HashMap;
// import Scanner for input of players name and selection of which box
import java.util.Scanner;

public class functions {
    private int size;
    
    public functions(int n) {
        setSize(n);
    }
    
    public int getSize() {
        return size;
    }
    
    private void setSize(int n) {
        size = n;
    }
    
    // function to display the tic tac toe board
    public void show(HashMap board){
        // for loop to display the tic tac toe board first for loop for each row
        for (int i = 0; i < getSize(); i ++){
            // second for loop for each column
            for (int j = 1; j <= getSize(); j++){
                // i*n + j to get from 1 to 9 the keys for board
                System.out.print(board.get(i*getSize() + j));
                if (j < getSize()){
                    System.out.print(" | ");                                                            // visual purposes
                }
            }
            if (i < getSize()-1){
                System.out.println();                                                                   // visual purposes
                System.out.println("-----------");                                                      // visual purposes
            }
        }
    }
    
    // function that checks the board for a winner and returns the respective symbol or returns empty string if no winner
    public String state(HashMap board) {
        // check if the box number 1 is filled with an 'x' or 'o'
        if (!board.get(1).equals("1")) {
            // check if box 2 and 3 contains the same String as box 1 and return the winning symbol
            if (board.get(1).equals(board.get(2)) && board.get(1).equals(board.get(3))) {
                return board.get(1).toString();
            }
            // check if box 5 and 9 contains the same String as box 1 and return the winning symbol
            else if (board.get(1).equals(board.get(5)) && board.get(1).equals(board.get(9))) {
                return board.get(1).toString();
            }
            // check if box 4 and 7 contains the same String as box 1 and return the winning symbol
            else if (board.get(1).equals(board.get(4)) && board.get(1).equals(board.get(7))) {
                return board.get(1).toString();
            }
        }
        // check if the box number 5 is filled with an 'x' or 'o'
        if (!board.get(5).equals("5")) {
            // check if box 2 and 8 contains the same String as box 5 and return the winning symbol
            if (board.get(5).equals(board.get(2)) && board.get(5).equals(board.get(8))) {
                return board.get(5).toString();
            }
            // check if box 4 and 6 contains the same String as box 5 and return the winning symbol
            else if (board.get(5).equals(board.get(4)) && board.get(5).equals(board.get(6))) {
                return board.get(5).toString();
            }
            // check if box 3 and 7 contains the same String as box 5 and return the winning symbol
            else if (board.get(5).equals(board.get(3)) && board.get(5).equals(board.get(7))) {
                return board.get(5).toString();
            }
        }
        // check if the box number 9 is filled with an 'x' or 'o'
        if (!board.get(9).equals("9")) {
            // check if box 3 and 6 contains the same String as box 9 and return the winning symbol
            if (board.get(9).equals(board.get(3)) && board.get(9).equals(board.get(6))) {
                return board.get(9).toString();
            }
            // check if box 7 and 8 contains the same String as box 9 and return the winning symbol
            else if (board.get(9).equals(board.get(7)) && board.get(9).equals(board.get(8))) {
                return board.get(9).toString();
            }
        }
        // return empty String if no winner yet
        return "";
    }
    
    public boolean isNumeric (String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    // validate if input from player is a valid input
    public int validate (HashMap board, String str, String player, String symbol) {
        // Create Scanner object
        Scanner input = new Scanner(System.in);
        
        // check if input is numeric, check if input is within the size of the board, check if input box is not empty. If any of this conditions is true, the program will reprompt for another input.
        while (!isNumeric(str) || Integer.parseInt(str) < 1 || Integer.parseInt(str) > getSize() * getSize() || board.get(Integer.parseInt(str)).equals("x") || board.get(Integer.parseInt(str)).equals("o")) {
            System.out.println("Invalid input. Please try again. " + player + ", choose a box to place an " + symbol + " into:");
            str = input.nextLine();
        }
        
        return Integer.parseInt(str);
    }
}