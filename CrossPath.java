import java.awt.*;

/*

The CrossPath class.  A CrossPath object has four ends.

*/

class CrossPath extends Path {

  // My line coordinates for drawing myself.
  protected double x1, y1, x2, y2, x3, y3, x4, y4;

  // (end1,end2) and (end3,end4) are the two pairs.
  // The are, in order, always 'north', 'south', 'east', and 'west'.
  protected Direction end1, end2, end3, end4;

  protected Path neighbour1; // The Path in the direction end1.
  protected Path neighbour2; // The Path in the direction end2.
  protected Path neighbour3; // The Path in the direction end3.
  protected Path neighbour4; // The Path in the direction end4.

  public CrossPath( Map T) {
    super( T);

    c = Color.orange;
    end1 = new Direction("north");
    end2 = new Direction("south");
    end3 = new Direction("east");
    end4 = new Direction("west");
    x1 = 0.0;
    y1 = 0.5;
    x2 = 1.0;
    y2 = 0.5;
    x3 = 0.5;
    y3 = 0.0;
    x4 = 0.5;
    y4 = 1.0;
  }

  public boolean exitOK(Direction d) {
    return d.equals(end1) || d.equals(end2) || d.equals(end3) || d.equals(end4);
  }

  // Return true if d is valid for this Path, return false and
  // print an error otherwise.
  protected boolean validDir(Direction d) {
    if (!exitOK(d)) {
      System.err.print("exit(): Not a valid dir for this piece: ");
      System.err.println(end1.direction + " " + end2.direction + " " + d.direction);
      Exception e = new Exception();
      e.printStackTrace(System.out);
      return false;
    }
    return true;
  }

  public void register(Path r, Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        neighbour1 = r;
      } else if (d.equals(end2)) {
        neighbour2 = r;
      } else if (d.equals(end3)) {
        neighbour3 = r;
      } else if (d.equals(end4)) {
        neighbour4 = r;
      }
    }
  }

  public void unRegister(Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        neighbour1 = null;
      } else if (d.equals(end2)) {
        neighbour2 = null;
      } else if (d.equals(end3)) {
        neighbour3 = null;
      } else if (d.equals(end4)) {
        neighbour4 = null;
      }
    }
  }

  // Given that d is the Direction from which a TreasureHunter entered,
  // report where the TreasureHunter will exit.
  public Direction exit(Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        return end2;
      } else if (d.equals(end2)) {
        return end1;
      } else if (d.equals(end3)) {
        return end4;
      } else if (d.equals(end4)) {
        return end3;
      }
    }

    return null;
  }

  // d is the direction that I entered from, and must be one of end1 and end2.
  // Return the Path at the other end.
  public Path nextPath(Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        return neighbour2;
      } else if (d.equals(end2)) {
        return neighbour1;
      } else if (d.equals(end3)) {
        return neighbour4;
      } else if (d.equals(end4)) {
        return neighbour3;
      }
    }

    return null;
  }

  // Redraw myself.
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    g2.setStroke(new BasicStroke(12));

    g.setColor(c);
    Rectangle b = bounds();
    g.drawLine(
        (int) (x1 * b.width), (int) (y1 * b.height),
        (int) (x2 * b.width), (int) (y2 * b.height));
    g.drawLine(
        (int) (x3 * b.width), (int) (y3 * b.height),
        (int) (x4 * b.width), (int) (y4 * b.height));

    super.draw(g);
  }

  public String toString() {
    return "CrossPath";
  };
}
