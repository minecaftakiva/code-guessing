import java.util.Scanner;
import java.util.ArrayList;
public class Main {
  static Scanner sc = new Scanner(System.in);
  public static void main(String[] args) {
    System.out.println("Enter board depth");
    play(sc.nextInt());
  }
  public static void play(int size){
    Board b = new Board(size);
    ArrayList<Coords> moves = new ArrayList<Coords>();
    for(int i = 1; i<size; i++)
      moves.add(new Coords((int) (Math.random()*3)+1, (int) (Math.random()*3)+1));
    while(true){
      turn(size, 'X', moves, b);
      turn(size, 'O', moves, b);
    }
  }
  public static void turn(int size, char player, ArrayList<Coords> moves, Board b){
    System.out.println(b);
    if(b.isOccupied(moves, 0)){
      for(int i = 0; i<moves.size(); i++)
        moves.set(i, new Coords((int) (Math.random()*3)+1, (int) (Math.random()*3)+1));
    }
    int x = 2, y = 2;
    for(int j = 0; j<moves.size(); j++){
      x+=(moves.get(j).getX()-1)*Math.pow(3, size-j-1);
      y+=(moves.get(j).getY()-1)*Math.pow(3, size-j-1);
    }
    Coords place = new Coords(x, y);
    System.out.println(player+" turn in square centered at "+place);
    moves.add(new Coords(sc.next()));
    while(b.isOccupied(moves, 0)){
      System.out.println(player+" turn in square centered at "+place);
      moves.set(size-1, new Coords(sc.next()));
    }
    b.changePiece(player, moves, 0);
    b.setWinner(player);
    moves.remove(0);
  }
}