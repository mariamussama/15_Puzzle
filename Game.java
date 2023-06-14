import java.util.Random;
import java.util.Scanner;

class Game {
  public static void main(String[] args) {
    System.out.println("Welcome to the 15-puzzle game!");
    String input;
    do{
      Ctrl ctrl = new Ctrl();
      ctrl.play();
      System.out.println("Play again (Y/N)?");
      Scanner sc= new Scanner(System.in);
      input = sc.nextLine();
      while (input.length() != 1 || (input.charAt(0) != 'Y' && input.charAt(0) != 'y' && input.charAt(0) != 'N' && input.charAt(0) != 'n')) {
        System.out.println("Invalid input");
        System.out.println("Play again (Y/N)?");
        input = sc.nextLine();
      }

      }while (input.equals("Y") || input.equals("y")); // play again

  }
}

class Puzzle {
byte[][] board = new byte[4][4];
public Puzzle(){

  for (int i = 0; i < 4; i++) {// initialize the board to 0
    for (int j = 0; j < 4; j++) {
      board[i][j] = 0;
    }
  } 

  // byte count = 1;
  //   for (int i = 0; i < 4; i++) {
  //   for (int j = 0; j < 4; j++) {
  //     board[i][j] = count;
  //     count++;
  //   }
  // }
  // board[3][3] = 15;
  // board[3][2] = 0;


  do {
    for (int i = 0; i < 15; i++) {// randomly generate the board
      Random rand = new Random();
      int x = rand.nextInt(4);
      int y = rand.nextInt(4);
      if (board[x][y] == 0) {
        board[x][y] = (byte) (i+1);
      } else {
        i--;
      }
    }
  } while (sorted() == true); // to ensure the initial board is not sorted

}
private boolean sorted() { // check if the board is sorted
  byte count = 1;
  for (int i = 0; i < 4; i++) {
    for (int j = 0; j < 4; j++) {
      if (board[i][j] != count) {
        return false;
      }
      count++;
    }
  }
  return true;
}

public byte[][] getBoard() {// return the board
  return board;
}

private void update(int x, int y, int xnew, int ynew) {// update the board
      board[xnew][ynew] = board[x][y];
      board[x][y] = 0;
  }

public boolean validate(String input) { // validate the move
    int x = input.charAt(0) - 'a';
    int y = input.charAt(1) - '1';

    if ((x+1)<4 && board[x+1][y] == 0) { // check if the move is valid
        update(x, y, x+1, y);
    }
    else if ((y+1) <4 && board[x][y+1] == 0) {
      update(x, y, x, y+1);
    }
    else if ((x-1) >=0 && board[x-1][y] == 0) {
      update(x, y, x-1, y);
    }
    else if ((y-1) >=0 && board[x][y-1] == 0) {
      update(x, y, x, y-1);
    }
    else 
    System.out.println("No possible move");

    byte count = 1;
    for (int i = 0; i < 4; i++) { // check if the board is sorted
      for (int j = 0; j < 4; j++) {
        if (board[i][j] != count) {
          return false;
        }
        count++;
        if (count == 16) {
          count = 0;
        }
      }
} 
System.out.println("You win!");
return true;

}
}

class View {
public String read() { // read the input
    Scanner sc= new Scanner(System.in);
    String input;
    boolean v = false;
    do{
      System.out.println("Your move: ");
      input = sc.nextLine();
      v = false;
      if (input.length() != 2) {
        System.out.println("Invalid input");
      } else if (input.charAt(0) < 'a' || input.charAt(0) > 'd') {
        System.out.println("Invalid input");
      } else if (input.charAt(1) < '1' || input.charAt(1) > '4') {
        System.out.println("Invalid input");
      } else {
        v = true;
      }
    } while (v == false);
    return input;
  }
public void print(byte[][] board) {// print the board

  System.out.print("  ");
  for (int i = 0; i < 4; i++) {
    System.out.printf("%2d ", i+1);
  }
  System.out.println();
  String str = "abcd";
    for (int i = 0; i < 4; i++) {
      System.out.print(str.charAt(i)+ " ");
    for (int j = 0; j < 4; j++) {
      if (board[i][j] == 0) {
        System.out.print("   ");
      }
      else
        System.out.printf("%2d ", board[i][j]);
    }
    System.out.println();
  }
  }
}

class Ctrl {
  Puzzle puzzle = new Puzzle();
  View view = new View();
  public void play() {
    String input = new String();
    int move = 0;
    // glue both interfaces
    do{
      view.print(puzzle.getBoard());
      input = view.read();
      move ++;
    } while (puzzle.validate(input) == false);

    if (move == 1) // check if the move is 1 or many moves
      System.out.println("Well done! You solved the puzzle in "+move+" move!");
    else
      System.out.println("Well done! You solved the puzzle in "+move+" moves!");
  }
}

