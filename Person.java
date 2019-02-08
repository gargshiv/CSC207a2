/*

The Person class.

A person has a delay, which is directly related to the speed -- the faster
the person is moving, the shorter the delay. Each turn, a Person will move
one path piece in the current direction.

*/

public class Person extends Thread {

  protected TreasureHunter treasureHunter; // The TreasureHunter this person represents.
  protected Map theMap; // The Map on which I am running.
  private int totalWeight; // The sum of the weights of my cars.
  private int delay; // The amount of time between each of my turns.
  private boolean forward; // Whether I am moving forward.

  public Person(String threadName, int id) {
    super(threadName);
    treasureHunter = new TreasureHunter(id);
  }

  // Set my delay between moves to d.
  public void setSpeed(int d) {
    delay = d;
  }

  public int getScore() {
    return treasureHunter.score;
  }

  public void setScore(int score) {
    treasureHunter.score = score;
  }

  // Add me to Map T at location loc moving in direction dir.
  public void addToPath(Map T, Direction dir, GridLoc loc) {
    theMap = T;
    theMap.addPerson(this);

    TreasureHunter currPerson = treasureHunter;
    while (currPerson != null) {
      currPerson.setDirection(dir);
      theMap.addCar(loc, currPerson);

      // Now figure out the dir for the next TreasureHunter,
      // and the next loc.

      if (dir.equals("north")) {
        loc.row--;
      } else if (dir.equals("south")) {
        loc.row++;
      } else if (dir.equals("east")) {
        loc.col++;
      } else if (dir.equals("west")) {
        loc.col--;
      }

      Direction nD = currPerson.currentPath.exit(dir);
      Path nextPath = currPerson.currentPath.nextPath(nD);

      // Now I know the Path on which the next currPerson will
      // be.  Find out how it got on to it.
      dir = nextPath.exit(dir.opposite());

      currPerson = currPerson.nextTreasureHunter;
     }
  }

  // Halve my delay.
  public void accelerateALot() {
    delay /= 2;
  }

  // Double my delay.
  public void decelerateALot() {
    delay *= 2;
  }

  // Speed up by a factor of 20ms.
  public void accelerate() {
    delay -= 20;
  }

  // Slow down by a factor of 20ms.
  public void decelerate() {
    delay += 20;
  }

  public void run() {
    while (true) {
      treasureHunter.move();

      // Sleep for 1 second.
      try {
        sleep(delay);
      } catch (InterruptedException e) {
      }
    }
  }
}
