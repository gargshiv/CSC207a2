// A direction; one of 'north', 'south', 'east' and 'west'.

public class Direction {
  public String direction;

  public Direction(String s) {
    direction = s;
  }

  public boolean equals(Direction d) {
    return d.equals(direction);
  }

  public boolean equals(String s) {
    return s.equals(direction);
  }

  public String toString() {
    return direction;
  }

  public Direction opposite() {
    Direction d = null;
    if (direction.equals("north")) {
      d = new Direction("south");
    } else if (direction.equals("south")) {
      d = new Direction("north");
    } else if (direction.equals("east")) {
      d = new Direction("west");
    } else if (direction.equals("west")) {
      d = new Direction("east");
    }
    return d;
  }
}
