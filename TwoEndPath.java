/*

The TwoEndPath class.  A TwoEndPath object has two ends,
which may or may be not be opposite each other.

*/

import java.awt.*;

class TwoEndPath extends Path {

  protected Direction end1, end2;
  protected Path neighbour1; // The Path in the direction end1.
  protected Path neighbour2; // The Path in the direction end2.
  double x1, y1;
  int startAngle, arcAngle = 90;

  public TwoEndPath(Direction e1, Direction e2,  Map T) {
    super( T);
    c = Color.orange;
    end1 = e1;
    end2 = e2;
  }

  public boolean exitOK(Direction d) {
    return d.equals(end1) || d.equals(end2);
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

  // Register that r is adjacent to me from direction d.
  public void register(Path r, Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        neighbour1 = r;
      } else {
        neighbour2 = r;
      }
    }
  }

  public void unRegister(Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        neighbour1 = null;
      } else if (d.equals(end2)) {
        neighbour2 = null;
      }
    }
  }

  // Given that d is the Direction from which a TreasureHunter entered,
  // report where the TreasureHunter will exit.
  public Direction exit(Direction d) {
    if (validDir(d)) {
      return d.equals(end1) ? end2 : end1;
    }

    return null;
  }
  // d is the direction that I entered from, and must be one of end1 and end2.
  // Return the Path at the other end.
  public Path nextPath(Direction d) {
    if (validDir(d)) {
      return d.equals(end1) ? neighbour2 : neighbour1;
    }

    return null;
  }
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(12));

    g2.setColor(c);
    Rectangle b = bounds();
    g2.drawArc(
            (int) (x1 * b.width), (int) (y1 * b.height), b.width, b.height, startAngle, arcAngle);

    //super.draw(g);
  }
  public String toString() {
    return "TwoEndPath";
  };

}
