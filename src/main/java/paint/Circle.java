package paint;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class Circle extends Shape implements Drawable {
        private Point centre;
        private double radius;
        private Color color;
        private boolean stroke;
        private double thickness;
        public Circle(Point centre, double radius, Color color, boolean stroke, double thickness) {
                this.centre = centre;
                this.radius = radius;
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

        public double getRadius() {
                return radius;
        }

        public void setRadius(double radius) {
                this.radius = radius;
        }

        @Override
        public Color getColor(){return this.color;}

        @Override
        public void setColor(Color color){this.color = color;}

        @Override
        public void draw(GraphicsContext g2d) {
                double x = this.getCentre().x;
                double y = this.getCentre().y;
                double radius = this.getRadius();
                double diameter = radius * 2;
                double topLeftX = x - radius;
                double topLeftY = y - radius;
                if(this.stroke){
                        g2d.setLineWidth(thickness);
                        g2d.setStroke(getColor());
                        g2d.strokeOval(topLeftX, topLeftY, diameter, diameter);
                }
                else{
                        g2d.setFill(getColor());
                        g2d.fillOval(topLeftX, topLeftY, diameter, diameter);
                }
        }
}
