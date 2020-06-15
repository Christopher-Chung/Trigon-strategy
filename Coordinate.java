import java.util.*;

class Coordinate{
  public int row; //1~8
  public int column; //[9,11,13,15,15,13,11,9]

  public Coordinate(int x, int y){
    row = x;
    column = y;
  }

  public boolean isUp(){
    if (row <= 4){
      return (column % 2 == 1);
    }else{
      return (column % 2 == 0);
    }
  }

  public boolean isDown(){
    return !this.isUp();
  }

  public boolean inBoard(){
    if (column < 1 || row < 1 || row > 8) return false;
    int norm = (row <= 4) ? row : (9 - row);
    return (column <= (norm * 2 + 7));
  }

  public Coordinate goUp(){
    //Exclusively for down tiles
    Coordinate c;
    if (row <= 4){
      c = new Coordinate(row-1,column-1);
    }else if (row == 5){
      c = new Coordinate(4,column);
    }else{
      c = new Coordinate(row-1,column+1);
    }
    return c;
  }

  public String toString(){
    return "("+row+", "+column+")";
  }

  public Coordinate goDown(){
    //Exclusively for up tiles
    Coordinate c;
    if (row < 4){
      c = new Coordinate(row+1,column+1);
    }else if (row == 4){
      c = new Coordinate(5,column);
    }else{
      c = new Coordinate(row+1,column-1);
    }
    return c;
  }
}
