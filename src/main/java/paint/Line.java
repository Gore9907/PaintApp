package paint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line implements Drawable{
    Point p1, p2;
    private Color color;
    private double thickness;
    public Line(Point p1, Point p2, Color color, double thickness) {
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;
        this.thickness = thickness;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Color getColor(){return this.color;}

    public void setColor(Color color){this.color = color;}

    @Override
    public void draw(GraphicsContext g2d) {
        g2d.setLineWidth(thickness);
        g2d.setStroke(getColor());
        g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
    }
}
