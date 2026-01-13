import java.util.List;
public class Board extends Piece{
  private Piece[][] layout;
  private int side, depth;
  public Board(int num){
    layout = new Piece[3][3];
    side = (int)Math.pow(3, num);
    depth = num;
    for(int i = 0; i<3; i++){ //<3
      for(int j = 0; j<3; j++){ //<3
        //reccursion in the constructor lets go
        if(depth<=1)
          layout[i][j] = new Piece();
        else
          layout[i][j] = new Board(depth-1);
      }
    }
  }
  //basically toString() would be way too hard without this
  protected String lineToString(int line){
    String result = "";
    for(int i = 0; i<3; i++){ //<3
        result+=layout[3*line/side][i].lineToString(line-(3*line/side)*side/3)+" "; //sort of gross math, huh
    }
    return result;
  }
  public String toString(){
    String result = "";
    for(int i = (int) Math.log10(side); i>=0; i--){
      for(int j = 0; j < Math.log(25*side+25)/Math.log(26); j++)
        result+=" ";
      for(int j = 1; j <= side; j++){
        result+=j/(int)Math.pow(10, i)%10+" ";
        for(double temp = j; temp%3==0; temp/=3)
          result+=" ";
      }
      result+="\n";
    }
    for(int i = 0; i<side; i++){
      result+=Coords.intToString(i+1);
      for(int j = 0; j < Math.log(25*side+25)/Math.log(26)-Coords.intToString(i+1).length(); j++)
        result+= " ";
      result+=lineToString(i)+"\n";
      for(double temp = i+1; temp%3==0; temp/=3)
        result+="\n";
    }
    return result;
  }
  public void changePiece(char player, List<Coords> route, int depthMeter){
    layout[route.get(depthMeter).getY()-1][route.get(depthMeter).getX()-1].changePiece(player, route, depthMeter+1);
  }
  protected String show(){
    return super.toString();
  }
  public void setWinnerDeep(char w){
    super.setWinner(w);
    for(Piece[] r : layout){
      for(Piece c : r)
        c.setWinnerDeep(w);
    }
  }
  public void setWinner(char w){
    if(depth>1){
      for(int i = 0; i<3; i++){ //<3
        for(int j = 0; j<3; j++) //<3
          layout[i][j].setWinner(w);
      }
    }
    boolean diag1Check = true, diag2Check = true;
    for(int i = 0; i<3; i++){ //<3
      boolean horiCheck = true, vertiCheck = true;
      for(int j = 0; j<3; j++){ //<3
        if(layout[i][j].show().charAt(0)!=w)
          horiCheck = false;
        if(layout[j][i].show().charAt(0)!=w)
          vertiCheck = false;
      }
      if(layout[i][i].show().charAt(0)!=w)
        diag1Check = false;
      if(layout[2-i][2-i].show().charAt(0)!=w)
        diag2Check = false;
      if(horiCheck||vertiCheck)
        setWinnerDeep(w);
    }
    if(diag1Check||diag2Check)
      setWinnerDeep(w);
  }
  public boolean isOccupied(List<Coords> route, int depthMeter){
    if(depthMeter==route.size())
      return super.isOccupied(route, depthMeter);
    if((route.get(depthMeter).getX()>3||route.get(depthMeter).getX()<1)||(route.get(depthMeter).getY()>3||route.get(depthMeter).getY()<1))
      return true;
    return layout[route.get(depthMeter).getY()-1][route.get(depthMeter).getX()-1].isOccupied(route, depthMeter+1);
  }
}