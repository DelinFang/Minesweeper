import java.util.Scanner;
import java.util.Random;
public class Minesweeper {
    //I HAVE NO IDEA HOW FORMATTING WORKS IDK WHY ITS WORKING NOW DONT TOUCH IT
    static int width = 0, height = 0, mines = 0;


    public static void uncover(Space[][] board, int x, int y) {
        //basically a floodfill algorithm lmao
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }
        if (!board[x][y].isCovered() || board[x][y].isFlagged()) {
            return;
        }


        board[x][y].uncover();
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};


        if (board[x][y].hasMine()) {
            //condition only occurs if 1st mine user clicks on is bad
            System.out.println("Game over. :(");
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j].uncover();
                }
            }
            printBoard(board);
            System.exit(0);
        }


        if (board[x][y].getAdjacentMines() == 0) {
            for (int k = 0; k < 8; k++) {
                uncover(board, x + dx[k], y + dy[k]);
            }
        }
    }


    public static void calculateAdjacentMines(Space[][] board) {
    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};


    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
            if (!board[i][j].hasMine()) {
                int count = 0;
                for (int k = 0; k < 8; k++) {
                    int newRow = i + dx[k];
                    int newCol = j + dy[k];
                    if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[i].length) {
                        if (board[newRow][newCol].hasMine()) {
                            count++;
                        }
                    }
                }
                board[i][j].setAdjacentMines(count);
            }
        }
    }
}


    //function to print out the board
public static void printBoard(Space[][] board) {
    // Print column headers
    System.out.print("   ");
    for (int j = 0; j < board[0].length; j++) {
        System.out.printf("%2d ", j);
    }
    System.out.println();


    for (int i = 0; i < board.length; i++) {
        System.out.printf("%2d ", i);
        for (int j = 0; j < board[i].length; j++) {
            String space = board[i][j].toString();
            System.out.printf("%2s ", space);
        }
        System.out.println();
    }
}




    //Main function
    public static void main(String[] args) {
        System.out.println("Welcome to MineSweeper!");
        Scanner input = new Scanner(System.in);
        String diff;
        System.out.println("Choose the difficulty, options are easy, medium, hard");
        diff = input.nextLine();


        int startX = 0, startY = 0;
        //take in difficulty
        switch(diff) {
            case "easy":
                width = 9;
                height = 9;
                mines = 10;
                break;
            case "medium":
                width = 16;
                height = 16;
                mines = 40;
                break;
            case "hard":
                width = 30;
                height = 16;
                mines = 99;
                break;
            default:
                System.out.println("Invalid difficulty");
                break;
        }
        //make board and initiate
        Space[][] board = new Space[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = new Space();
            }
        }
    printBoard(board);
    System.out.println("Choose your starting coordinates!");
    startX = input.nextInt();
    startY = input.nextInt();


    //start placing mines
    int curMine = 0;
    Random rand = new Random();


    while (curMine < mines){
        //random generate 2 nums in range, x y coords
        //if not mine already, mark as mine, increment curMine
        //if already mine, just go next
        int x = rand.nextInt(height); //row
        int y = rand.nextInt(width);  //column
        if (!board[x][y].hasMine() && x != startX && y != startY) {
        board[x][y].setMine(true);
        curMine++;
        }
    }


    //Calculate each space's value
    calculateAdjacentMines(board);
    uncover(board, startX, startY);
    printBoard(board);


    //start running the game


    while (true){
        //count uncovered spaces, if its equal to bombcount than the player is done
        int uncoveredSpaces = 0;
         for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j].isCovered()){
                    uncoveredSpaces++;
                }
            }
        }
        if (uncoveredSpaces == mines){
            System.out.println("Congratulations!");
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j].toggleFlag();
                    board[i][j].uncover();
                }
            }
            printBoard(board);
            System.exit(0);
        }
        System.out.println("Pick your move! Your options are: 1. Flag   2. Input coordinates");
        int option = input.nextInt();
        if (option == 1){
            //flag
            System.out.println("Pick a coordinate to flag");
            int flagX = input.nextInt();
            int flagY = input.nextInt();
            board[flagX][flagY].toggleFlag();
            printBoard(board);
            }
        else{
            System.out.println("Pick a coordinate to uncover");
            int newX = input.nextInt();
            int newY = input.nextInt();
            if (board[newX][newY].isFlagged()){
                System.out.println("According to all known laws of aviation, there is no way a bee should be able to fly. Its wings are too small to get its fat little body off the ground. The bee, of course, flies anyway because bees don't care what humans think is impossible.");
            }
            else{
            uncover(board, newX, newY);
            printBoard(board);
                }
            }
        }
    }
}





