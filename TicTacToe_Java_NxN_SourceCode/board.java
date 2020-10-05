/**
 * @author Ng Yau Chuan Jason for zendesk Take Home Assignment prototype
 */

// import HashMap to store the board and its values
import java.util.HashMap;
// import Scanner for input of players name and selection of which box
import java.util.Scanner;

public class board {
    // declare variables for size and board
    private int size;
    private HashMap<Integer, String> board = new HashMap<Integer, String>();
    
    // empty constructor without parameters.
    public board() {
    }
    
    // constructor that assigns values to the size and populate the board
    public board(int n) {
        setSize(n);
        
        // add items to the HashMap board that stores the key/value pair
        for (int i = 1; i <= size*size; i++) {
            board.put(i, String.valueOf(i));
        }
    }
    
    // get method for size to retrieve outside class if needed
    public int getSize() {
        return size;
    }
    
    // set method for size to edit the value
    private void setSize(int n) {
        size = n;
    }
    
    // get method for board to retrieve outside class if needed
    public HashMap getBoard() {
        return board;
    }
    
    // replace method to change the value of the input key in board
    public void replace(int n, String str) {
        board.replace(n, str);
    }
    
    // function to display the tic tac toe board
    public void show(){
        int len = String.valueOf(size*size).length();
        // for loop to display the tic tac toe board first for loop for each row
        for (int i = 0; i < size; i ++){
            // second for loop for each column
            for (int j = 1; j <= size; j++){
                if (len > 1) {
                    for (int k = 0; k < (len - board.get(i*size + j).length()); k++) {
                        System.out.print(" ");
                    }
                }
                // i*n + j to get from 1 to 9 the keys for board
                System.out.print(board.get(i*size + j));
                if (j < size){
                    System.out.print(" | ");                                                            // visual purposes
                }
            }
            if (i < size-1){
                System.out.println();                                                                   // visual purposes
                System.out.println("-----------");                                                      // visual purposes
            }
        }
    }
    
    // function that checks the board for a winner and returns the respective symbol or returns empty string if no winner
    public String check(int n) {
        // checks for diagonal(\) win with n being the center position
        if (board.get(n).equals(board.get(n-size-1)) && board.get(n).equals(board.get(n+size+1))) {
            //System.out.println(1);    // for debugging
            return board.get(n);
        }
        // checks for vertical(|) win with n being the center position
        else if(board.get(n).equals(board.get(n-size)) && board.get(n).equals(board.get(n+size))) {
            //System.out.println(2);    // for debugging
            return board.get(n);
        }
        // checks for diagonal(/) win with n being the center position
        else if(board.get(n).equals(board.get(n-size+1)) && board.get(n).equals(board.get(n+size-1))) {
            //System.out.println(3);    // for debugging
            return board.get(n);
        }
        // checks for horizontal(-) win with n being the center position. n&size > 1 check as n cannot be in the first(1) or last column(0)
        else if(n%size > 1 && board.get(n).equals(board.get(n-1)) && board.get(n).equals(board.get(n+1))) {
            //System.out.println(4);    // for debugging
            return board.get(n);
        }
        // checks for diagonal(\) win with n being the bottom right position. n%size != 1 and n%size != 2 as n cannot be in the first 2 columns
        else if (n%size != 1 && n%size != 2 && board.get(n).equals(board.get(n-size-1)) && board.get(n).equals(board.get(n-2*size-2))) {
            //System.out.println(5);    // for debugging
            return board.get(n);
        }
        // checks for vertical(|) win with n being the bottom position
        else if (board.get(n).equals(board.get(n-size)) && board.get(n).equals(board.get(n-2*size))) {
            //System.out.println(6);    // for debugging
            return board.get(n);
        }
        // checks for diagonal(/) win with n being the bottom left position. n%size != 0 and n%size != size-1 as n cannot be in the last 2 columns
        else if (n%size != 0 && n%size != size-1 && board.get(n).equals(board.get(n-size+1)) && board.get(n).equals(board.get(n-2*size+2))) {
            //System.out.println(7);    // for debugging
            return board.get(n);
        }
        // checks for horizontal(-) win with n being the most left position. n%size != 0 and n%size != size-1 as n cannot be in the last 2 columns
        else if (n%size != 0 && n%size != size-1 && board.get(n).equals(board.get(n+1)) && board.get(n).equals(board.get(n+2))) {
            //System.out.println(8);    // for debugging
            return board.get(n);
        }
        // checks for diagonal(\) win with n being the top left position. n%size != 0 and n%size != size-1 as n cannot be in the last 2 columns
        else if (n%size != 0 && n%size != size-1 && board.get(n).equals(board.get(n+size+1)) && board.get(n).equals(board.get(n+2*size+2))) {
            //System.out.println(9);    // for debugging
            return board.get(n);
        }
        // checks for horizontal(|) win with n being the top position
        else if (board.get(n).equals(board.get(n+size)) && board.get(n).equals(board.get(n+2*size))) {
            //System.out.println(10);    // for debugging
            return board.get(n);
        }
        // checks for diagonal(/) win with n being the top right position. n%size != 1 and n%size != 2 as n cannot be in the first 2 columns
        else if (n%size != 1 && n%size != 2 && board.get(n).equals(board.get(n+size-1)) && board.get(n).equals(board.get(n+2*size-2))) {
            //System.out.println(11);    // for debugging
            return board.get(n);
        }
        // checks for horizontal(-) win with n being the most right position. n%size != 1 and n%size != 2 as n cannot be in the first 2 columns
        else if (n%size != 1 && n%size != 2 && board.get(n).equals(board.get(n-1)) && board.get(n).equals(board.get(n-2))) {
            //System.out.println(12);    // for debugging
            return board.get(n);
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
    public String validate (String str, String player, String symbol) {
        // Create Scanner object
        Scanner input = new Scanner(System.in);
        
        // check if input is numeric, check if input is within the size of the board, check if input box is not empty. If any of this conditions is true, the program will reprompt for another input.
        while (str.replaceAll("\\s", "").equals("") || !isNumeric(str) || Integer.parseInt(str) < 1 || Integer.parseInt(str) > size * size || board.get(Integer.parseInt(str)).equals("x") || board.get(Integer.parseInt(str)).equals("o")) {
            System.out.println("Invalid input. Please try again. " + player + ", choose a box to place an " + symbol + " into:");
            str = input.nextLine();
			System.out.println();	// visual purposes
        }
        
        return str;
    }
}