/*

The SwitchPath class.  A SwitchPath object has three ends, and a controller
which determines which ends are used.  If a TreasureHunter moves on from the first end,
the switch determines which of the other two ends it leaves from.  If it moves
on from one of the other two ends, it automatically leaves by the first end.

*/

import java.awt.*;

class SwitchPath extends Path {

  // My line coordinates for drawing myself.
  protected double x1, y1, x2, y2, x3, y3;
  // (end1,end2) and (end1,end3) are the two pairs.
  // end1 and end2 are the straight directions (i.e., they are
  // opposite each other), and end1 and end3 form the corner.
  protected Direction end1, end2, end3;
  protected Path neighbour1; // The Path in the direction end1.
  protected Path neighbour2; // The Path in the direction end2.
  protected Path neighbour3; // The Path in the direction end3.
  // Info for my corner portion.
  int startAngle;
  int arcAngle = 90;
  // Whether I am aligned to go straight.
  boolean goingStraight;

  public SwitchPath(Direction e1, Direction e2, Direction e3,  Map T) {
    super( T);
    c = Color.magenta;
    end1 = e1;
    end2 = e2;
    end3 = e3;
  }

  public boolean exitOK(Direction d) {
    return d.equals(end1) || d.equals(end2) || d.equals(end3);
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
  public void register(Path p, Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        neighbour1 = p;
      } else {
        neighbour2 = p;
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

  // Given that d is the Direction from which a Car entered,
  // report where the Car will exit.
  // Note that if d is not end1's Direction, then it will have to
  // exit toward end1.
  public Direction exit(Direction d) {
    if (validDir(d)) {
      return d.equals(end1) ? end2 : end1;
    }

    return null;
  }

  // d is the direction that I entered from, and must be one of end1, end2 and end3.
  // Return the Path at the other end.
  public Path nextPath(Direction d) {
    if (validDir(d)) {
      return d.equals(end1) ? neighbour2 : neighbour1;
    }

    return null;
  }

  // Handle a mouse click.  This will toggle the direction of the switch.
  public boolean handleEvent(Event evt) {
    Object target = evt.target;

    if (evt.id == Event.MOUSE_DOWN && !occupied()) {
      goingStraight = !goingStraight;
      repaint();
      return true;
    }

    // If we get this far, I couldn't handle the event
    return false;
  }

  // Redraw myself.
  public void draw(Graphics g) {
    Rectangle b = bounds();

    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(12));

    // Draw current direction of the switch darker.
    if (goingStraight) {
      g2.setColor(Color.lightGray);
      g2.drawArc(
          (int) (x3 * b.width), (int) (y3 * b.height), b.width, b.height, startAngle, arcAngle);
      g2.setColor(c);
      g2.drawLine(
          (int) (x1 * b.width), (int) (y1 * b.height), (int) (x2 * b.width), (int) (y2 * b.height));
    } else {
      g2.setColor(Color.lightGray);
      g2.drawLine(
          (int) (x1 * b.width), (int) (y1 * b.height), (int) (x2 * b.width), (int) (y2 * b.height));
      g2.setColor(c);
      g2.drawArc(
          (int) (x3 * b.width), (int) (y3 * b.height), b.width, b.height, startAngle, arcAngle);
    }

    super.draw(g);
  }

  public String toString() {
    return "SwitchPath";
  };
}
