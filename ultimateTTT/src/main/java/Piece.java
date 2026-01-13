import java.util.List;

public class Piece{
  private boolean winner, won;
  public Piece(){
    winner = true;
    won = false;
  }
  public String toString(){
    if(!won)
      return ".";
    return winner? "X":"O";
  }
  protected String lineToString(int line){
    return toString();
  }
  protected String show(){
    return toString();
  }
  public void setWinnerDeep(char w){
    setWinner(w);
  }
  public void setWinner(char w){
    if(w=='X'){
      winner = true;
      won = true;
      return;
    }
    if(w=='O'){
      winner = false;
      won = true;
    }
  }
  public void changePiece(char player, List<Coords> route, int depthMeter){
    setWinner(player);
  }
  public boolean isOccupied(List<Coords> route, int depthMeter){
    return won;
  }
}