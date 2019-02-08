import java.awt.*;
import java.awt.geom.Ellipse2D;

/*

The TreasureHunter class. It has weight, c, and can draw()
and move().

*/

class TreasureHunter {

  // My score.
  public int score = 0;
  // The Path that I am currently occupying.
  public Path currentPath;
  // My c.
  protected Color color = Color.blue;
  // My weight, in stone.
  protected int weight;
  // The TreasureHunter that immediately follows me.
  protected TreasureHunter nextTreasureHunter;
  // The direction in which I entered the current Path.
  protected Direction dir;
  // My ID number.
  private int id;

  public TreasureHunter(int id) {
    this.id = id;
  }

  // Set me moving in direction d.
  public void setDirection(Direction d) {
    dir = d;
  }

  // Place this TreasureHunter on Path r.
  public void setPath(Path r) {
    currentPath = r;
  }

  // Move forward one PathPiece; t is the current PathPiece.  Tell
  // all of the cars I am pulling to move as well.
  public void move() {

    Direction nD = currentPath.exit(dir);

    Direction nextDir = nD.opposite();
    Path nextPath = currentPath.nextPath(dir);
    dir = nextDir;

    if (nextPath.enter(this)) {
      currentPath.leave();
      currentPath = nextPath;

      // We have to call this here rather than within currentPath.enter()
      // because otherwise the wrong Path is used...
      currentPath.repaint();

      if (nextTreasureHunter != null) {
        nextTreasureHunter.move();
      }
    }
  }

  // Return true if the current Path is a SwitchPath and I am going
  // straight through it.
  public boolean SwitchStraight() {

    if (currentPath instanceof SwitchPath) {
      return true;
    }
    return false;
  }

  // Return true if the current Path is a SwitchPath and I am going
  // around a corner.
  public boolean SwitchCorner() {

    if (currentPath instanceof SwitchPath) {
      return true;
    }
    return false;
  }

  // Redraw myself.
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    GridLoc myLoc = currentPath.location;
    Rectangle b = currentPath.bounds();

    // the polygon to draw on the screen.
    Polygon p;

    double width = b.width;
    double height = b.height;

    Ellipse2D circle = new Ellipse2D.Double(width / 3, height / 3, width / 2, height / 2);

    g2.setColor(color);
    g2.fill(circle);
    g2.setStroke(new BasicStroke(2));
    g2.draw(circle);

    g2.setColor(Color.black);
    g2.setStroke(new BasicStroke(5));
    g2.setFont(new Font("default", Font.BOLD, 16));
    g2.drawString(new Integer(id).toString(), (int) width / 2, (int) height / 2);
  }

  public String toString() {
    return "TreasureHunter " + id;
  };
}
