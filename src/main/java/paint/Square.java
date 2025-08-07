package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape implements Drawable{
    private Point point;
    private double length;
    private Color color;
    private boolean stroke;
    private double thickness;

    public Square(Point topLeft, double width, double height, Color color, boolean stroke, double thickness) {
        this.point = topLeft;
        this.length = Math.max(width, height);
        this.color = color;
        this.stroke = stroke;
        this.thickness = thickness;
    }
    public Point getPoint() {return this.point;}

    public void setPoint(Point point) {this.point = point;}

    public double getLength() {return this.length;}

    public void updateLength(double newWidth, double newHeight) {
        this.length = Math.max(newWidth, newHeight);
    }

    public void setWidth(double width) {
        this.updateLength(width, this.length);
    }

    public void setHeight(double height) {
        this.updateLength(height, this.length);
    }

    @Override
    public Color getColor(){return this.color;}

    @Override
    public void setColor(Color color){this.color = color;}

    @Override
    public void draw(GraphicsContext g2d) {
        g2d.setFill(getColor());
        double x = this.getPoint().x;
        double y = this.getPoint().y;
        if(this.stroke){
            g2d.setLineWidth(thickness);
            g2d.setStroke(getColor());
            g2d.strokeRect(x, y, length, length);
        }
        else{
            g2d.setFill(getColor());
            g2d.fillRect(x, y, length, length);
        }
    }
}
