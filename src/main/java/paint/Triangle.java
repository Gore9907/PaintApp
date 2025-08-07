package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape implements Drawable{
    private Point p1, p2, p3;
    double [] xPoints, yPoints;
    int nPoints;
    private Color color;
    private boolean stroke;
    private double thickness;

    public Triangle(Point p1, Point p2, Point p3, Color color, boolean stroke, double thickness) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        xPoints = new double[3];
        yPoints = new double[3];
        xPoints[0] = p1.x;
        yPoints[0] = p1.y;
        xPoints[1] = p2.x;
        yPoints[1] = p2.y;
        xPoints[2] = p3.x;
        yPoints[2] = p3.y;
        nPoints = 3;
        this.color = color;
        this.stroke = stroke;
        this.thickness = thickness;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
        xPoints[0] = p1.x;
        yPoints[0] = p1.y;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
        xPoints[1] = p2.x;
        yPoints[1] = p2.y;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
        xPoints[2] = p3.x;
        yPoints[2] = p3.y;
    }

    @Override
    public Color getColor(){return this.color;}

    @Override
    public void setColor(Color color){this.color = color;}

    @Override
    public void draw(GraphicsContext g2d) {
        g2d.setFill(getColor());
        if(this.stroke){
            g2d.setLineWidth(thickness);
            g2d.setStroke(getColor());
            g2d.strokePolygon(xPoints, yPoints, nPoints);
        }
        else{
            g2d.setFill(getColor());
            g2d.fillPolygon(xPoints, yPoints, nPoints);
        }
    }
}
