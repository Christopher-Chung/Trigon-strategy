import java.util.*;

//Trigon strategy
//Coding representation:
//Treat full hex as binary number '111111'=63 and start from 'up'
//For example, '010000'='000100'=('000001')='up'    ('000010')='down'
//2s: '000011'=up-down    '001100'=left/up-right/down   '000110'=right/up-left/down
//3s: '000111' '100011' '001110' '110001' '111000' '011100'
//4s: '111100' '011110' '100111' '110011' '001111' '111001'
//6s: '111111'
//Triangle: "up","down" Pivot="tip"
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

  public Trigon(){
    board = new Board();
    scanner = new Scanner(System.in);
    coordinates = new Coordinate[96];
    initializeCord();
  }

  public void play(){
    while (true){
      turn();
    }
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
    String s1,s2,s3;
    s1 = scanner.nextLine().trim();
    s2 = scanner.nextLine().trim();
    s3 = scanner.nextLine().trim();
    move[] bestSolution = new move[3];
    float maxScore = Integer.MIN_VALUE;
    determine(s1,s2,s3,bestSolution,maxScore);
    determine(s1,s3,s2,bestSolution,maxScore);
    determine(s2,s1,s3,bestSolution,maxScore);
    determine(s2,s3,s1,bestSolution,maxScore);
    determine(s3,s2,s1,bestSolution,maxScore);
    determine(s3,s1,s2,bestSolution,maxScore);
    for (int i = 0; i < 3; i ++){
      System.out.println(bestSolution[i]);
      board.put(bestSolution[i].piece,bestSolution[i].pivot);
      board.flush();
    }
    //System.out.println(board);
  }

  public void determine(String s1, String s2, String s3, move[] bestSolution, float maxScore){
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
          //System.out.println(board2.canFit(s3,coordinates[k]));
          //System.out.println(board2.isOccupied(coordinates[2]));
          if (board2.canFit(s3,coordinates[k]) == false) continue;
          board3 = new Board(board2);
          board3.put(s3, coordinates[k]);
          board3.flush();
          float score = board3.countscore();
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
