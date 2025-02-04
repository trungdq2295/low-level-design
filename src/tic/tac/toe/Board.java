package src.tic.tac.toe;

public class Board {

  private final int[][] grids;
  private final int size;

  public Board(int n) {
    grids = new int[n][n];
    size = n;
  }

  public void makeAMove(int row, int column, int data) {
    if (!isValidMove(row, column)) {
      return;
    }
    grids[row][column] = data;
  }

  public boolean hasWinner() {
    //Horizontal
    for (int i = 0; i < size; i++) {
      boolean result = true;
      for (int j = 0; j < size - 1; j++) {
        if (grids[i][j] == 0 || grids[i][j] != grids[i][j + 1]) {
          result = false;
          break;
        }
      }
      if (result) {
        return true;
      }
    }

    //vertical
    for (int i = 0; i < size; i++) {
      boolean result = true;
      for (int j = 0; j < size - 1; j++) {
        if (grids[j][i] == 0 || grids[j][i] != grids[j + 1][i]) {
          result = false;
          break;
        }
      }
      if (result) {
        return true;
      }
    }

    //diagonals (top-left to bottom-right)
    boolean result = true;
    for (int i = 0; i < size - 1; i++) {
      if (grids[i][i] == 0 || grids[i][i] != grids[i + 1][i + 1]) {
        result = false;
        break;
      }
    }
    if (result) {
      return true;
    }

    result = true;
    //diagonals (bottom-left to top-right)
    for (int i = 0; i < size - 1; i++) {
      if (grids[size - 1 - i][i] == 0 || grids[size - 2 - i][i + 1] != grids[size - 1 - i][i]) {
        return false;
      }
    }
    return result;
  }

  private boolean isValidMove(int row, int column) {
    if (row < 0 || row >= size || column < 0 || column >= size) { //invalid position
      return false;
    }
    return grids[row][column] == 0; // the cell has been marked
  }

}
