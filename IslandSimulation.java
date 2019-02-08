// Simulate people running around a path.

import java.awt.*;

// class IslandSimulation
/* ----------------------------------------------------------------------
 The class IslandSimulation contains all the methods and instance variables
 neccessary to keep track of and run the island simulation.**/

public class IslandSimulation extends Frame {

  // The Paths on which the Persons run.
  public static Map maps;

  // The Person objects running on the Paths.
  public static Person[] people;

  // The ThreadGroups; all Person objects running on the same Map are in the same
  // ThreadGroup.
    IslandSimulation(){
        maps = new Map();
        people = new Person[2];
    }
    private void initiate (){
      maps.resize(540, 400);
      maps.move(0, 0);
      maps.setBackground(Color.white);
      maps.show();

      //people[0] = new Person("Person 1",1);

      //people[1] = new Person("Person 2",2);

      //people[0].addToPath(maps, new Direction("east"), new GridLoc(2, 2));
      //people[0].setSpeed(620);
      //people[1].addToPath(maps, new Direction("south"), new GridLoc(1, 5));
      //people[1].setSpeed(350);

      //people[0].start();
      //people[1].start();
    }


  // main
  // ------------------------------------------------------------------
  // This is where it all starts.

  public static void main(String[] args) {

    IslandSimulation island = new IslandSimulation();
    island.initiate();
    // Map 2.

  }
}
