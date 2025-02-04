package src.tic.tac.toe;

public class Player {

  private int id;

  private String name;

  public Player(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public Player(int id) {
    this.id = id;
  }

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }
}
