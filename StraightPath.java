/*

The StraightPath class.  A StraightPath object has two ends,
which must be opposite each other.

*/

import java.awt.*;

class StraightPath extends TwoEndPath {

  // The multipliers for the endpoints of the StraightPath.
  double x1, y1, x2, y2;

  public StraightPath(Direction e1, Direction e2, Map T) {
    super(e1, e2, T);
    c = Color.orange;
  }

  // Redraw myself.
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    g2.setStroke(new BasicStroke(12));
    g2.setColor(c);
    Rectangle b = bounds();
    g2.drawLine(
        (int) (x1 * b.width), (int) (y1 * b.height), (int) (x2 * b.width), (int) (y2 * b.height));

    super.draw(g2);
  }

  public String toString() {
    return "StraightPath";
  };
}
