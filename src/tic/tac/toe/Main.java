package src.tic.tac.toe;

public class Main {
  public static void main(String[] args) {
    Board board = new Board(3);
    Player firstPlayer = new Player(1, "Trung");
    Player secondPlayer = new Player(2, "Nguyen");
    Game game = new Game(board, firstPlayer, secondPlayer);
    //Horizontal
//    game.makeAMove(0, 0);
//    game.makeAMove(1, 0);
//    game.makeAMove(0, 1);
//    game.makeAMove(1, 1);
//    game.makeAMove(0, 2);
//    game.makeAMove(1, 2);
//
//    //Vertical
//    board = new Board(3);
//    game = new Game(board, firstPlayer, secondPlayer);
//    game.makeAMove(0, 0);
//    game.makeAMove(0, 1);
//    game.makeAMove(1, 0);
//    game.makeAMove(1, 1);
//    game.makeAMove(2, 0);
//
//    //Diagonals - left to right
//    board = new Board(3);
//    game = new Game(board, firstPlayer, secondPlayer);
//    game.makeAMove(0, 0);
//    game.makeAMove(0, 1);
//    game.makeAMove(1, 1);
//    game.makeAMove(1, 2);
//    game.makeAMove(2, 2);

    //Diagonals - righ to left
    board = new Board(3);
    game = new Game(board, secondPlayer, firstPlayer);
    game.makeAMove(0,2);
    game.makeAMove(0,1);
    game.makeAMove(1,1);
    game.makeAMove(1,0);
    game.makeAMove(2,0);
  }
}