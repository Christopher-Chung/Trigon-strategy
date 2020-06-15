import java.util.*;


class Board{
  Vector<Vector<Boolean>> b;

  public String toString(){
    return b.toString();
  }

  public Board(){
    b = new Vector<Vector<Boolean>>(8);
    for (int j = 1; j <= 4; j ++){
      Vector<Boolean> vec = new Vector<Boolean>(j*2+7);
      for (int k = 0; k < (j*2+7); k ++){
        vec.add(false);
      }
      b.add(vec);
    }
    for (int j = 4; j >= 1; j --){
      Vector<Boolean> vec = new Vector<Boolean>(j*2+7);
      for (int k = 0; k < (j*2+7); k ++){
        vec.add(false);
      }
      b.add(vec);
    }
  }

  public Board(Board board){
    b = new Vector<Vector<Boolean>>(8);
    for (int j = 1; j <= 4; j ++){
      Vector<Boolean> vec = new Vector<Boolean>(j*2+7);
      for (int k = 0; k < (j*2+7); k ++){
        vec.add(board.b.get(j-1).get(k));
      }
      b.add(vec);
    }
    for (int j = 4; j >= 1; j --){
      Vector<Boolean> vec = new Vector<Boolean>(j*2+7);
      for (int k = 0; k < (j*2+7); k ++){
        vec.add(board.b.get(8-j).get(k));
      }
      b.add(vec);
    }
  }

  public boolean isOccupied(Coordinate c) throws ArrayIndexOutOfBoundsException{
    try{
      b.get(c.row-1).get(c.column-1);
    }catch (ArrayIndexOutOfBoundsException e){
      System.out.println(this);
      System.out.println(c);
    }
    return b.get(c.row-1).get(c.column-1);
  }

  public void set(Coordinate c, boolean value){
    b.get(c.row-1).set(c.column-1,value);
  }

  public boolean canFit(String shape, Coordinate pivot){
    if (shape.equals("up") || shape.equals("down")){
      if (shape.equals("up")){
        if (pivot.isUp() == false || isOccupied(pivot)) return false;
        Coordinate center = pivot.goDown();
        if (center.inBoard() == false || isOccupied(center)) return false;
        center.column ++;
        if (center.inBoard() == false || isOccupied(center)) return false;
        center.column -= 2;
        if (center.inBoard() == false || isOccupied(center)) return false;
        return true;
      }else{
        if (pivot.isDown() == false || isOccupied(pivot)) return false;
        Coordinate center = pivot.goUp();
        if (center.inBoard() == false || isOccupied(center)) return false;
        center.column ++;
        if (center.inBoard() == false || isOccupied(center)) return false;
        center.column -= 2;
        if (center.inBoard() == false || isOccupied(center)) return false;
        return true;
      }
    }else if (shape.equals("Hor-udud") || shape.equals("Hor-dudu")){
      if (shape.equals("Hor-udud")){
        if (pivot.isUp() == false || isOccupied(pivot)) return false;
        Coordinate c = new Coordinate(pivot.row,pivot.column+1);
        if (c.inBoard() == false || isOccupied(c)) return false;
        c.column ++;
        if (c.inBoard() == false || isOccupied(c)) return false;
        c.column ++;
        if (c.inBoard() == false || isOccupied(c)) return false;
        return true;
      }else{
        if (pivot.isDown() == false || isOccupied(pivot)) return false;
        Coordinate c = new Coordinate(pivot.row,pivot.column+1);
        if (c.inBoard() == false || isOccupied(c)) return false;
        c.column ++;
        if (c.inBoard() == false || isOccupied(c)) return false;
        c.column ++;
        if (c.inBoard() == false || isOccupied(c)) return false;
        return true;
      }
    }else if (shape.equals("L-dudu") || shape.equals("L-udud")){
      if (shape.equals("L-dudu")){
        if (pivot.isDown() == false || isOccupied(pivot)) return false;
        Coordinate c = pivot.goUp();
        if (c.inBoard() == false || isOccupied(c)) return false;
        c.column --;
        if (c.inBoard() == false || isOccupied(c)) return false;
        c = c.goUp();
        if (c.inBoard() == false || isOccupied(c)) return false;
        return true;
      }else{
        if (pivot.isUp() == false || isOccupied(pivot)) return false;
        Coordinate c = new Coordinate(pivot.row,pivot.column-1);
        if (c.inBoard() == false || isOccupied(c)) return false;
        c = c.goUp();
        if (c.inBoard() == false || isOccupied(c)) return false;
        c.column --;
        if (c.inBoard() == false || isOccupied(c)) return false;
        return true;
      }
    }else if (shape.equals("R-dudu") || shape.equals("R-udud")){
      if (shape.equals("R-dudu")){
        if (pivot.isDown() == false || isOccupied(pivot)) return false;
        Coordinate c = pivot.goUp();
        if (c.inBoard() == false || isOccupied(c)) return false;
        c.column ++;
        if (c.inBoard() == false || isOccupied(c)) return false;
        c = c.goUp();
        if (c.inBoard() == false || isOccupied(c)) return false;
        return true;
      }else{
        if (pivot.isUp() == false || isOccupied(pivot)) return false;
        Coordinate c = new Coordinate(pivot.row,pivot.column+1);
        if (c.inBoard() == false || isOccupied(c)) return false;
        c = c.goUp();
        if (c.inBoard() == false || isOccupied(c)) return false;
        c.column ++;
        if (c.inBoard() == false || isOccupied(c)) return false;
        return true;
      }
    }else{
      if (pivot.isDown() == false) return false;
      if (shape.charAt(0) == '1' && isOccupied(pivot)) return false;
      Coordinate c = new Coordinate(pivot.row,pivot.column+1);
      if (shape.charAt(1) == '1'){
        if (c.inBoard() == false || isOccupied(c)) return false;
      }
      c.column -= 2;
      if (shape.charAt(5) == '1'){
        if (c.inBoard() == false || isOccupied(c)) return false;
      }
      c = c.goDown();
      if (shape.charAt(4) == '1'){
        if (c.inBoard() == false || isOccupied(c)) return false;
      }
      c.column ++;
      if (shape.charAt(3) == '1'){
        if (c.inBoard() == false || isOccupied(c)) return false;
      }
      c.column ++;
      if (shape.charAt(2) == '1'){
        if (c.inBoard() == false || isOccupied(c)) return false;
      }
      return true;
    }
  }

  public void put(String shape, Coordinate pivot){
    if (shape.charAt(0) != '0') set(pivot,true);
    Coordinate c;
    if (shape.equals("up") || shape.equals("down")){
      if (shape.equals("up")){
        c = pivot.goDown();
      }else{
        c = pivot.goUp();
      }
      set(c,true);
      c.column ++; set(c,true);
      c.column -= 2; set(c,true);
    }else if (shape.equals("Hor-udud") || shape.equals("Hor-dudu")){
      set(pivot,true);
      c = new Coordinate(pivot.row,pivot.column+1);
      set(c,true);
      c.column ++; set(c,true);
      c.column ++; set(c,true);
    }else if (shape.equals("L-dudu") || shape.equals("L-udud")){
      if (shape.equals("L-dudu")){
        c = pivot.goUp(); set(c,true);
        c.column --; set(c,true);
        c = c.goUp(); set(c,true);
      }else{
        c = new Coordinate(pivot.row,pivot.column-1); set(c,true);
        c = c.goUp(); set(c,true);
        c.column --; set(c,true);
      }
    }else if (shape.equals("R-dudu") || shape.equals("R-udud")){
      if (shape.equals("R-dudu")){
        c = pivot.goUp(); set(c,true);
        c.column ++; set(c,true);
        c = c.goUp(); set(c,true);
      }else{
        c = new Coordinate(pivot.row,pivot.column+1); set(c,true);
        c = c.goUp(); set(c,true);
        c.column ++; set(c,true);
      }
    }else{
      c = new Coordinate(pivot.row,pivot.column+1);
      if (shape.charAt(1) == '1') set(c,true);
      c.column -= 2;
      if (shape.charAt(5) == '1') set(c,true);
      c = c.goDown();
      if (shape.charAt(4) == '1') set(c,true);
      c.column ++;
      if (shape.charAt(3) == '1') set(c,true);
      c.column ++;
      if (shape.charAt(2) == '1') set(c,true);
    }
  }

  public void isFull(boolean[][] canFlush){
    for (int i = 0; i < 8; i ++){
      Coordinate c = new Coordinate(i+1, 1);
      for (int j = 0; j < b.get(i).size(); j ++){
        if (isOccupied(c) == false) canFlush[0][i] = false;
        c.column ++;
      }
    }
    //L
    for (int i = 0; i < 4; i ++){
      Coordinate c = new Coordinate(8, 2*i+2);
      for (int j = 0; j < (i+4); j ++){
        if (isOccupied(c) == false) canFlush[1][i] = false;
        c.column --;
        if (isOccupied(c) == false) canFlush[1][i] = false;
        c = c.goUp();
      }
      if (isOccupied(c) == false) canFlush[1][i] = false;
    }
    for (int i = 0; i < 4; i ++){
      Coordinate c = new Coordinate(8-i, b.get(7-i).size());
      for (int j = 0; j < (7-i); j ++){
        if (isOccupied(c) == false) canFlush[1][i+4] = false;
        c = c.goUp();
        if (isOccupied(c) == false) canFlush[1][i+4] = false;
        c.column --;
      }
      if (isOccupied(c) == false) canFlush[1][i+4] = false;
    }
    //R
    for (int i = 0; i < 4; i ++){
      Coordinate c = new Coordinate(8, 2*(4-i));
      for (int j = 0; j < (i+4); j ++){
        if (isOccupied(c) == false) canFlush[2][i] = false;
        c.column ++;
        if (isOccupied(c) == false) canFlush[2][i] = false;
        c = c.goUp();
      }
      if (isOccupied(c) == false) canFlush[2][i] = false;
    }
    for (int i = 0; i < 4; i ++){
      Coordinate c = new Coordinate(8-i, 1);
      for (int j = 0; j < (7-i); j ++){
        if (isOccupied(c) == false) canFlush[2][i+4] = false;
        c = c.goUp();
        if (isOccupied(c) == false) canFlush[2][i+4] = false;
        c.column ++;
      }
      if (isOccupied(c) == false) canFlush[2][i+4] = false;
    }
  }

  public void flush(){
    //Pick out all 24 lines
    //Then remove all at the same time
    boolean[][] canFlush = new boolean[3][8];
    for (int i = 0; i < 3; i ++){
      for (int j = 0; j < 8; j ++){
        canFlush[i][j] = true;
      }
    }
    isFull(canFlush);
    for (int i = 0; i < 8; i ++){
      if (canFlush[0][i]){
        for (int j = 0; j < b.get(i).size(); j ++){
          set(new Coordinate(i+1,j+1),false);
        }
      }
    }
    //L
    for (int i = 0; i < 4; i ++){
      if (canFlush[1][i]){
        Coordinate c = new Coordinate(8, 2*i+2);
        for (int j = 0; j < (i+4); j ++){
          set(c,false);
          c.column --; set(c,false);
          c = c.goUp();
        }
        set(c,false);
      }
    }
    for (int i = 0; i < 4; i ++){
      if (canFlush[1][i+4]){
        Coordinate c = new Coordinate(8-i, b.get(7-i).size());
        for (int j = 0; j < (7-i); j ++){
          set(c,false);
          c = c.goUp(); set(c,false);
          c.column --;
        }
        set(c,false);
      }
    }
    //R
    for (int i = 0; i < 4; i ++){
      if (canFlush[2][i]){
        Coordinate c = new Coordinate(8, 2*(4-i));
        for (int j = 0; j < (i+4); j ++){
          set(c,false);
          c.column ++; set(c,false);
          c = c.goUp();
        }
        set(c,false);
      }
    }
    for (int i = 0; i < 4; i ++){
      if (canFlush[2][i+4]){
        Coordinate c = new Coordinate(8-i, 1);
        for (int j = 0; j < (7-i); j ++){
          set(c,false);
          c = c.goUp(); set(c,false);
          c.column ++;
        }
        set(c,false);
      }
    }
  }

  public float countscore(){
    float sum = 0;
    for (int i = 1; i <= 8; i ++){
      for (int j = 1; j <= b.get(i-1).size(); j ++){
        Coordinate c = new Coordinate(i,j);
        if (isOccupied(c)) continue;
        sum -= 1.8;
        //Three adjacent triangles
        c.column += 1;
        if (c.inBoard()){
          if (isOccupied(c) == false) sum ++;
        }else{
          //sum ++;
        }
        c.column -= 2;
        if (c.inBoard()){
          if (isOccupied(c) == false) sum ++;
        }else{
          //sum ++;
        }
        c.column ++;
        if (c.isUp()){
          c = c.goDown();
          if (c.inBoard()){
            if (isOccupied(c) == false) sum ++;
          }else{
            //sum ++;
          }
        }else{
          c = c.goUp();
          if (c.inBoard()){
            if (isOccupied(c) == false) sum ++;
          }else{
            //sum ++;
          }
        }
      }
    }
    //Calculate score
    return sum;
  }
}
