package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Polyline extends Shape implements Drawable{
    private ArrayList<Point> points;
    private Color color;
    private double thickness;

    public Polyline(Color color, double thickness){
        this.color = color;
        this.points = new ArrayList<Point>();
        this.thickness = thickness;
    }

    public void addPoint(Point point){
        points.add(point);
    }

    public ArrayList<Point> getPoints(){
        return points;
    }
    @Override
    public void draw(GraphicsContext g2d) {
        Color color = this.color;
        ArrayList<Line> list = new ArrayList<>();
        for(int i = 0; i < points.size()-1; i++){
            Point p1 = points.get(i);
            Point p2 = points.get(i+1);
            Line line = new Line(p1, p2, color, thickness);
            list.add(line);
        }
        for(Line line : list){
            line.draw(g2d);
        }
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
