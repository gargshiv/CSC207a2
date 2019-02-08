/*

The EWNPath class.  An EWNPath object has three ends.

*/

class EWNPath extends SwitchPath {
  public EWNPath( Map T) {
    super(new Direction("east"), new Direction("west"), new Direction("north"), T);
    startAngle = 180;
    x1 = 0.0;
    y1 = 0.5;
    x2 = 1.0;
    y2 = 0.5;
    x3 = 0.5;
    y3 = -0.5;
  }

  public String toString() {
    return "EWNPath";
  }
}
