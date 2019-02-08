/*

The Map class.  A Map object is made up of Paths, and
has zero or more people in it.

*/

import java.awt.*;

public class Map extends Frame {
  // The Panel on which the Map appears.
  MapPanel mapPanel;

  // ------------------------------------------------------------------
  // The following items are the Paths and TreasureHunters on the Map.

  private Path first; // The first Path in the graph.

  // The maximum number of people I can hold.
  private int MAX_PATHS = 10;
  private Object[] personList = new Object[MAX_PATHS]; // The people running on me.
  private int numPersons = 0;

  private Path[][] paths; // The grid of paths.

  // Whether my TreasureHunters are running.  If true, no more Paths can be placed.
  private boolean running = false;

  // ------------------------------------------------------------------
  // The following buttons define the interface for running
  // and saving me.
  private Button runStopButton, quitButton;

  // The following buttons are used to control the TreasureHunters.
  private Button accelButton, decelButton, accelLotsButton, decelLotsButton;

  // The following label is used to display the scores of the TreasureHunters.
  private Label statusLabel;
  Panel p;
  Graphics2D g;

  // Set up a new, simple Map.
  public Map() {
    paths = new Path[10][10];
    mapPanel = new MapPanel();
    p = new Panel();
    runStopButton = new Button("Run");
    quitButton = new Button("Quit");
    accelButton = new Button("Accelerate");
    decelButton = new Button("Decelerate");
    accelLotsButton = new Button("Accelerate A Lot");
    decelLotsButton = new Button("Decelerate A Lot");

    buildPath();

    for (int row = 0; row < paths.length; row++) {
      for (int col = 0; col < paths[0].length; col++) {
        paths[row][col] = new EmptyPath(this);
      }
    }
    Direction s = new Direction("south");
    Direction n = new Direction("north");
    Direction e = new Direction("east");
    Direction w = new Direction("west");


     paths[0][0] = new TwoEndPath(s,e, this);
    System.out.println("true");
    paths[0][1] = new TwoEndPath(s,w, this);

    //paths[0][1].draw(g);

   // connectPaths(paths[0][0], paths[0][1], new Direction("east"));



    ////add the remaining paths here

    spawnTreasure();

    mapPanel.addToPanel(paths);
    mapPanel.setBackground(new Color(152, 251, 152));
  }

  // Add the buttons for placing Paths.
  private void buildPath() {

    add("Center", mapPanel);

    p.setLayout(new GridLayout(0, 1));
    p.add(runStopButton);
    p.add(accelLotsButton);
    p.add(decelLotsButton);
    p.add(accelButton);
    p.add(decelButton);
    p.add(quitButton);
    add("East", p);

    statusLabel = new Label("Player 1: 0 --- Player 2: 0");
    p = new Panel();
    p.add(statusLabel);
    add("South", p);

    pack();
  }

  // Read Path-placing commands from the user.
  public boolean handleEvent(Event evt) {
    Object target = evt.target;

    if (evt.id == Event.ACTION_EVENT) {
      if (target instanceof Button) {
        if ("Run".equals(evt.arg)) {
          running = true;
          for (int i = 0; i < numPersons; i++) {
            ((Person) personList[i]).start();
          }
          ((Button) target).setLabel("Suspend");
        } else if ("Suspend".equals(evt.arg)) {
          for (int i = 0; i < numPersons; i++) {
            ((Person) personList[i]).suspend();
          }
          running = false;
          ((Button) target).setLabel("Resume");
        } else if ("Resume".equals(evt.arg)) {
          for (int i = 0; i < numPersons; i++) {
            ((Person) personList[i]).resume();
          }
          running = false;
          ((Button) target).setLabel("Suspend");
        } else if ("Accelerate".equals(evt.arg)) {
          for (int i = 0; i < numPersons; i++) {
            ((Person) personList[i]).accelerate();
          }
        } else if ("Decelerate".equals(evt.arg)) {
          for (int i = 0; i < numPersons; i++) {
            ((Person) personList[i]).decelerate();
          }
        } else if ("Accelerate A Lot".equals(evt.arg)) {
          for (int i = 0; i < numPersons; i++) {
            ((Person) personList[i]).accelerateALot();
          }
        } else if ("Decelerate A Lot".equals(evt.arg)) {
          for (int i = 0; i < numPersons; i++) {
            ((Person) personList[i]).decelerateALot();
          }
        } else if ("Quit".equals(evt.arg)) {
          for (int i = 0; i < numPersons; i++) {
            ((Person) personList[i]).stop();
          }
          running = false;
          System.exit(0);
        }
        return true;
      }
    }

    // If we get this far, I couldn't handle the event
    return false;
  }

  // If test and r1 != null and r1.exitOK(r1), connect or unrester
  // r1 and r2 depending on whether r2's exits match r1's.
  protected void registerOrUnRegister(boolean test, Path r1, Path r2, Direction d) {
    if (test && r1 != null && r1.exitOK(d)) {
      if (r2.exitOK(d.opposite())) {
        connectPaths(r1, r2, d);
      } else {
        r1.unRegister(d);
      }
    }
  }

  // Connect the Path at (row,col) to its neighbours.
  protected void connectPath(int row, int col) {
    Path r = paths[row][col];

    Direction north = new Direction("north");
    Direction south = new Direction("south");
    Direction east = new Direction("east");
    Direction west = new Direction("west");

    if (r != null) {
      Path rN = row > 0 ? paths[row - 1][col] : null;
      Path rS = row < paths.length - 1 ? paths[row + 1][col] : null;
      Path rE = col < paths[0].length - 1 ? paths[row][col + 1] : null;
      Path rW = col > 0 ? paths[row][col - 1] : null;

      registerOrUnRegister(row > 0, rN, r, south);
      registerOrUnRegister(row < paths.length - 1, rS, r, north);
      registerOrUnRegister(col > 0, rW, r, east);
      registerOrUnRegister(col < paths[0].length - 1, rE, r, west);
    }
  }

  // Connect paths r1 and r2; r2 is in direction d from r1.
  protected void connectPaths(Path r1, Path r2, Direction d) {
    r1.register(r2, d);
    r2.register(r1, d.opposite());
  }

  // paint
  // ------------------------------------------------------------------
  // Paint the display.

  // Add e to the path at location loc.
  public void addCar(GridLoc loc, TreasureHunter e) {
    paths[loc.row][loc.col].enter(e);
    e.setPath(paths[loc.row][loc.col]);
  }

  // update
  // ------------------------------------------------------------------
  // Update the display; tell all my Paths to update themselves.

  public void paint(Graphics g) {
    update(g);
  }

  public void update(Graphics g) {

    mapPanel.repaint();
  }

  // Add T to myself.
  public void addPerson(Person T) {
    personList[numPersons] = T;
    numPersons++;
  }

  public void updateStatusBar() {
    System.out.println(((Person) personList[0]).getScore());
    System.out.println(((Person) personList[1]).getScore());
    statusLabel.setText(
        "Player 1: "
            + ((Person) personList[0]).getScore()
            + " --- Player 2: "
            + ((Person) personList[1]).getScore());

    statusLabel.repaint();
  }

  // Spawn treasure in a random location on the path
  public void spawnTreasure() {
    int row, col;
    // find suitable place for treasure to respawn
    do {
      row = (int) (Math.random() * 10);
      col = (int) (Math.random() * 10);

      System.out.println("Selected Row: " + row + " Col: " + col);
    } while ((paths[row][col] instanceof EmptyPath));
    paths[row][col].hT = true;
    paths[row][col].repaint();
  }

}
