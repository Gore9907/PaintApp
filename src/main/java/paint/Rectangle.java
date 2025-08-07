package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape implements Drawable{
    private Point point;
    private double width, height;
    private Color color;
    private boolean stroke;
    private double thickness;
    public Rectangle(Point topLeft, double width, double height, Color color, boolean stroke, double thickness) {
        this.point = topLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        this.stroke = stroke;
        this.thickness = thickness;
    }
    public Point getPoint() {return this.point;}

    public void setPoint(Point point) {this.point = point;}

    public double getWidth() {return this.width;}

    public void setWidth(double width) {this.width = width;}

    public double getHeight() {return this.height;}

    public void setHeight(double height) {this.height = height;}

    @Override
    public Color getColor(){return this.color;}

    @Override
    public void setColor(Color color){this.color = color;}

    @Override
    public void draw(GraphicsContext g2d) {
        g2d.setFill(getColor());
        double x = this.getPoint().x;
        double y = this.getPoint().y;
        double width = this.getWidth();
        double height = this.getHeight();
        if(this.stroke){
            g2d.setLineWidth(thickness);
            g2d.setStroke(getColor());
            g2d.strokeRect(x, y, width, height);
        }
        else{
            g2d.setFill(getColor());
            g2d.fillRect(x, y, width, height);
        }
    }
}
