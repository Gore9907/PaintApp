package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class Eraser extends Squiggle{

    private ArrayList<Point> points;
    private Color color;
    private double thickness;

    public Eraser(Color color, double thickness) {
        super(color, thickness);
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public ArrayList<Point> getPoints() {
        return this.points;
    }

    @Override
    public Color getColor(){return this.color;}

    @Override
    public void setColor(Color color){this.color = color;}

    @Override
    public void draw(GraphicsContext g2d) {
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            Line line = new Line(p1, p2, color, thickness);
            line.draw(g2d);
        }
    }
}
