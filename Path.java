/*

The Path class. A Path object is a piece of the map. It knows
whether there is a Person on it or not, and these Persons can enter()
and leave(). Given an entrance, a Path can report where the exit()
is.

*/

import java.awt.*;
import java.awt.geom.Ellipse2D;

class Path extends Canvas {

  // The amount of space in which Paths have to draw themselves.
  public static int size = 20;
  // True if a person entered or left. Used to speed up redrawing.
  public boolean stateChanged = true;
  protected Color c; // color
  protected boolean iO; // isOccupied
  protected boolean hT; // hasTreasure
  protected TreasureHunter cTH; // currentTreasureHunter
  protected GridLoc location; // location
  protected Map theMap; // theMap
  public Path() {
    Rectangle b = bounds();
  }
  public Path(Map T) {
    super();
    iO = false;
    theMap = T;
  }

  // Return true iff there is a treasure hunter on me.
  public boolean occupied() {
    return iO;
  }

  public GridLoc getLoc() {
    return location;
  }

  // Redraw myself.
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    Rectangle b = bounds();
    g2.setStroke(new BasicStroke(1));
    g2.setColor(Color.lightGray);
    g2.drawRect(0, 0, b.width - 1, b.height - 1);
    g2.setStroke(new BasicStroke(12));

    if (iO) {
      cTH.draw(g2);
    }

    if (hT) {
      Ellipse2D circle = new Ellipse2D.Double(b.width / 3, b.height / 3, b.width / 2, b.height / 2);
      g2.setColor(Color.YELLOW);
      g2.fill(circle);
    }
  }

  // Register that a Person is on me.  Return true if successful,
  // false otherwise.
  public boolean enter(TreasureHunter newTreasureHunter) {
    iO = true;
    cTH = newTreasureHunter;

    if (hT) {
      System.out.println(cTH.score);

      cTH.score = cTH.score + 1;

      System.out.println(cTH.score);

      theMap.updateStatusBar();
      theMap.spawnTreasure();
    }

    hT = false;

    return true;
  }

  // Register that a Person is no longer on me.
  public void leave() {
    iO = false;
    repaint();

  }

  // Update my display.
  public void paint(Graphics g) {
    draw(g);
  }

  // Return true if d is a valid direction for me.
  public boolean exitOK(Direction d) {
    return false;
  };

  // Register that Path r is in Direction d.
  public void register(Path r, Direction d) {};

  // Register that there is no Path in Direction d.
  public void unRegister(Direction d) {};

  // Given that d is the Direction from which a TreasureHunter entered,
  // report where the TreasureHunter will exit.
  public Direction exit(Direction d) {
    return null;
  };

  // Given that d is the Direction from which a TreasureHunter entered,
  // report which Path is next.
  public Path nextPath(Direction d) {
    return null;
  };

  // Return myself as a string.
  public String toString() {
    return "Path";
  };
}
