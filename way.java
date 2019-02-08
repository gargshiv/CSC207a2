import java.awt.*;
public class way extends Path{

    way(Direction d1, Direction d2, Rectangle b, Graphics g) {
        if (d1.direction.equals("east") && d2.direction.equals("west"))
            draw(0.5, 0.5, 1.0, 0.5, 0, true, b, g);
        else if (d1.direction.equals("north") && d2.direction.equals("south"))
            draw(0.5, 0.0, 0.5, 1.5, 0, true, b, g);
        else if (d1.direction.equals("north") && d2.direction.equals("east"))
            draw(0.5, -0.5, 0, 0, 180, false, b, g);
        else if (d1.direction.equals("north") && d2.direction.equals("west"))
            draw(-0.5, -0.5, 0, 0, 270, false, b, g);
        else if (d1.direction.equals("south") && d2.direction.equals("east"))
            draw(0.5, 0.5, 0, 0, 90, false, b, g);
        else if (d1.direction.equals("south") && d2.direction.equals("west"))
            draw(-0.5, 0.5, 0, 0, 0, false, b, g);

    }

    way(Direction d1, Direction d2, Direction d3, Rectangle b, Graphics g) {
        if (d1.direction.equals("east") && d2.direction.equals("west") && d3.direction.equals("north"))
            draw(0.0, 0.5, 1.0, 0.5, 0.5, -0.5, 180, b, g);
        else if (d1.direction.equals("east") && d2.direction.equals("west") && d3.direction.equals("south"))
            draw(0.0, 0.5, 1.0, 0.5, 0.5, 0.5, 90, b, g);
        else if (d1.direction.equals("north") && d2.direction.equals("south") && d3.direction.equals("east"))
            draw(0.5, 0.0, 0.5, 1.0, 0.5, -0.5, 180, b, g);
        else if (d1.direction.equals("north") && d2.direction.equals("south") && d3.direction.equals("west"))
            draw(0.5, 0.0, 0.5, 1.0, -0.5, -0.5, 270, b, g);
        else if (d1.direction.equals("south") && d2.direction.equals("north") && d3.direction.equals("east"))
            draw(0.5, 0.0, 0.5, 1.0, 0.5, 0.5, 90, b, g);
        else if (d1.direction.equals("south") && d2.direction.equals("north") && d3.direction.equals("west"))
            draw(0.5, 0.0, 0.5, 1.0, -0.5, 0.5, 0, b, g);
        else if (d1.direction.equals("west") && d2.direction.equals("east") && d3.direction.equals("north"))
            draw(0.0, 0.5, 1.0, 0.5, -0.5, -0.5, 270, b, g);
        else if (d1.direction.equals("west") && d2.direction.equals("east") && d3.direction.equals("south"))
            draw(0.0, 0.5, 1.0, 0.5, -0.5, 0.5, 0, b, g);
    }

    public void draw(double x1, double y1, double x2, double y2, double x3, double y3, int angle, Rectangle b, Graphics u) {
        Graphics2D g = (Graphics2D)u ;
        //Rectangle b = bounds();
        g.setStroke(new BasicStroke(12));
        //call the other draw from here depending on the direction....reduces excessive code
        //if (goingStraight) {

        g.setColor(Color.lightGray);
        g.drawArc(
                (int) (x3 * b.width), (int) (y3 * b.height), b.width, b.height, angle, 90);
        g.setColor(Color.magenta);
        g.drawLine(
                (int) (x1 * b.width), (int) (y1 * b.height), (int) (x2 * b.width), (int) (y2 * b.height));
        // } else {
        g.setColor(Color.lightGray);
        g.drawLine(
                (int) (x1 * b.width), (int) (y1 * b.height), (int) (x2 * b.width), (int) (y2 * b.height));
        g.setColor(Color.magenta);
        g.drawArc(
                (int) (x3 * b.width), (int) (y3 * b.height), b.width, b.height, angle, 90);
    }


    void draw(double x1, double y1, double x2, double y2, int angle, boolean flag, Rectangle b, Graphics u) {
        Graphics2D g = (Graphics2D)u;
        //g.setStroke(new BasicStroke(12));
        System.out.println("true");
        g.setColor(Color.orange);
        if (flag)
            g.drawLine((int) (x1 * b.width), (int) (y1 * b.height), (int) (x2 * b.width), (int) (y2 * b.height));
        else
            g.drawArc((int) (x1 * b.width), (int) (y1 * b.height), b.width, b.height, angle, 90);

    }
}
