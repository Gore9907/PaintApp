package paint;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class Oval extends Shape implements Drawable{
    private Point centre;
    private double x_radius;
    private double y_radius;
    private Color color;
    private Boolean stroke;
    private double thickness;

    public Oval(Point centre, double x_radius, double y_radius, Color color, Boolean stroke, double thickness) {
        this.centre = centre;
        this.x_radius = x_radius;
        this.y_radius = y_radius;
        this.color = color;
        this.stroke = stroke;
        this.thickness = thickness;
    }

    public Point getCentre() {
        return centre;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public double getXRadius() {
        return this.x_radius;
    }

    public double getYRadius() {
        return this.y_radius;
    }

    public void setXRadius(double x_radius) {
        this.x_radius = x_radius;
    }

    public void setYRadius(double y_radius) {
        this.y_radius = y_radius;
    }

    @Override
    public Color getColor(){return this.color;}

    @Override
    public void setColor(Color color){this.color = color;}

    @Override
    public void draw(GraphicsContext g2d) {
        double x = this.getCentre().x;
        double y = this.getCentre().y;
        double x_radius = this.getXRadius();
        double y_radius = this.getYRadius();
        double x_diameter = x_radius * 2;
        double y_diameter = y_radius * 2;
        double topLeftX = x - x_radius;
        double topLeftY = y - y_radius;
        if(this.stroke){
            g2d.setLineWidth(thickness);
            g2d.setStroke(getColor());
            g2d.strokeOval(topLeftX, topLeftY, x_diameter, y_diameter);
        }
        else{
            g2d.setFill(getColor());
            g2d.fillOval(topLeftX, topLeftY, x_diameter, y_diameter);
        }
    }
}

