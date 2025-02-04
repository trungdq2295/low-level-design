package src.tic.tac.toe;

public class Game {

  private Board board;

  private Player firstPlayer;

  private Player secondPlayer;

  private Player currentPlayer;

  private boolean isDone = false;

  public Game(int n, int firstPlayer, int secondPlayer) {
    this.board = new Board(n);
    this.firstPlayer = new Player(firstPlayer);
    this.secondPlayer = new Player(secondPlayer);
    this.currentPlayer = this.firstPlayer;
  }

  public Game(Board board, Player firstPlayer, Player secondPlayer) {
    this.board = board;
    this.firstPlayer = firstPlayer;
    this.secondPlayer = secondPlayer;
    this.currentPlayer = firstPlayer;
    System.out.println("Game started!");
  }

  public void makeAMove(int row, int column) {
    if (isDone) {
      System.out.println("Game already finished");
      return;
    }
    int data = getData();
    board.makeAMove(row, column, data);
    if (board.hasWinner()) {
      System.out.println("Congratulation: " + currentPlayer.getName());
      isDone = true;
    } else {
      switchPlayer();
    }
  }

  private void switchPlayer() {
    if (currentPlayer == firstPlayer) {
      currentPlayer = secondPlayer;
    } else {
      currentPlayer = firstPlayer;
    }
  }

  private int getData() {
    return currentPlayer == firstPlayer ? 1 : 2;
  }
}
