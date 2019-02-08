/*

The EmptyPath class.  This is a place on the Map which does not have an actual
piece of path.

*/

class EmptyPath extends Path {

  public EmptyPath(Map T) {
    super(T);
  }

  // Return true if d is a valid direction for me.
  public boolean exitOK(Direction d) {
    return false;
  }

  // Register that Path r is in Direction d.
  public void register(Path r, Direction d) {}

  // Register that there is no Path in Direction d.
  public void unRegister(Direction d) {}

  // Given that d is the Direction from which a TreasureHunter entered,
  // report where the TreasureHunter will exit.
  public Direction exit(Direction d) {
    return null;
  }

  // Given that d is the Direction from which a TreasureHunter entered,
  // report which Path is next.
  public Path nextPath(Direction d) {
    return null;
  }
}
