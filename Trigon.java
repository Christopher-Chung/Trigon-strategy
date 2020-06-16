import java.util.*;

//Trigon strategy
//Coding representation:
//Treat full hex as binary number '111111'=63 and start from 'up'
//For example, '1-up' '1-down'
//2s: 'up-down' (pivot is up)    '110000'=left/up-right/down   '100001'=right/up-left/down
//3s: '100011' 110001' '111000' '3-hor' '3-r' '3-l'
//4s: '111100' '011110' '100111' '110011' '001111' '111001'
//6s: '111111'
//Triangle: "4-up","4-down" Pivot="tip"
//Line: "Hor-udud" "Hor-dudu" "L-dudu" "L-udud" "R-dudu" "R-udud"
//For Hor, Pivot=leftmost; Else, most bottom tile

class move{
  String piece;
  Coordinate pivot;

  public move(String p, Coordinate c){
    piece = p;
    pivot = c;
  }

  public String toString(){
    return piece + ": " + pivot.toString();
  }
}

public class Trigon{

  Board board,board1,board2,board3;
  Scanner scanner;
  Coordinate[] coordinates;
  HashSet<String> legalInputs;
  int maxScore;

  public Trigon(){
    board = new Board();
    scanner = new Scanner(System.in);
    coordinates = new Coordinate[96];
    legalInputs = new HashSet<String>(27);
    initializeCord();
    initializeInputset();
  }

  public void play(){
    while (true){
      turn();
    }
  }

  public void initializeInputset(){
    legalInputs.add("1-up");
    legalInputs.add("1-down");
    legalInputs.add("4-up");
    legalInputs.add("4-down");
    legalInputs.add("up-down");
    legalInputs.add("110000");
    legalInputs.add("100001");
    legalInputs.add("3-l");
    legalInputs.add("3-r");
    legalInputs.add("3-hor");
    legalInputs.add("111000");
    legalInputs.add("110001");
    legalInputs.add("100011");
    legalInputs.add("Hor-dudu");
    legalInputs.add("Hor-udud");
    legalInputs.add("R-udud");
    legalInputs.add("R-dudu");
    legalInputs.add("L-udud");
    legalInputs.add("L-dudu");
    legalInputs.add("110011");
    legalInputs.add("111001");
    legalInputs.add("111100");
    legalInputs.add("100111");
    legalInputs.add("111111");
    legalInputs.add("001111");
    legalInputs.add("011110");
  }

  public void initializeCord(){
    int count = 0;
    for (int i = 0; i < 9; i ++,count++){
      coordinates[count] = new Coordinate(1,i+1);
    }
    for (int i = 0; i < 11; i ++,count++){
      coordinates[count] = new Coordinate(2,i+1);
    }
    for (int i = 0; i < 13; i ++,count++){
      coordinates[count] = new Coordinate(3,i+1);
    }
    for (int i = 0; i < 15; i ++,count++){
      coordinates[count] = new Coordinate(4,i+1);
    }
    for (int i = 0; i < 15; i ++,count++){
      coordinates[count] = new Coordinate(5,i+1);
    }
    for (int i = 0; i < 13; i ++,count++){
      coordinates[count] = new Coordinate(6,i+1);
    }
    for (int i = 0; i < 11; i ++,count++){
      coordinates[count] = new Coordinate(7,i+1);
    }
    for (int i = 0; i < 9; i ++,count++){
      coordinates[count] = new Coordinate(8,i+1);
    }
  }

  public void turn(){
    String[] s = new String[3];
    for (int i = 0; i < 3; i ++){
      s[i] = scanner.nextLine().trim();
      while (legalInputs.contains(s[i]) == false || s[i].equals("CommandList")){
        if (s[i].equals("CommandList")){
          System.out.println("The set of all shapes is: ");
          System.out.println(legalInputs);
          System.out.println("\n\n");
          s[i] = scanner.nextLine().trim();
        }else{
          System.out.println("Invalid input; please reenter. Type CommandList for all shapes.\n");
          s[i] = scanner.nextLine().trim();
        }
      }
    }
    move[] bestSolution = new move[3];
    maxScore = Integer.MIN_VALUE;
    //Test all permutations
    determine(s[0],s[1],s[2],bestSolution);
    determine(s[0],s[2],s[1],bestSolution);
    determine(s[1],s[0],s[2],bestSolution);
    determine(s[1],s[2],s[0],bestSolution);
    determine(s[2],s[1],s[0],bestSolution);
    determine(s[2],s[0],s[1],bestSolution);
    System.out.println(maxScore);
    for (int i = 0; i < 3; i ++){
      System.out.println(bestSolution[i]);
      board.put(bestSolution[i].piece,bestSolution[i].pivot);
      board.flush();
    }
    System.out.println("");
  }

  public void determine(String s1, String s2, String s3, move[] bestSolution){
    //Iterate through all grids
    for (int i = 0; i < 96; i ++){
      if (board.canFit(s1,coordinates[i]) == false) continue;
      board1 = new Board(board);
      board1.put(s1, coordinates[i]);
      board1.flush();
      for (int j = 0; j < 96; j ++){
        if (board1.canFit(s2,coordinates[j]) == false) continue;
        board2 = new Board(board1);
        board2.put(s2, coordinates[j]);
        board2.flush();
        for (int k = 0; k < 96; k ++){
          if (board2.canFit(s3,coordinates[k]) == false) continue;
          board3 = new Board(board2);
          board3.put(s3, coordinates[k]);
          board3.flush();
          int score = board3.countscore();
          if (score > maxScore){
            bestSolution[0] = new move(s1,coordinates[i]);
            bestSolution[1] = new move(s2,coordinates[j]);
            bestSolution[2] = new move(s3,coordinates[k]);
            maxScore = score;
          }
        }
      }
    }
  }

  public static void main(String[] args){
    Trigon game = new Trigon();
    game.play();
  }
}
