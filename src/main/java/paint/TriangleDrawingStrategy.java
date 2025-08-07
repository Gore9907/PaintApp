package paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TriangleDrawingStrategy implements DrawingStrategy{
    private Triangle triangle;
    private Point defaultpoint = new Point(0, 0);
    @Override
    public void onMousePressed(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor) {
        if(this.triangle == null) {
            System.out.println("Started Triangle");
            Point point1 = new Point(event.getX(), event.getY());
            this.triangle = new Triangle(point1, defaultpoint, defaultpoint, color, stroke, thickness);
        } else if(this.triangle.getP2() != defaultpoint && this.triangle.getP3() == defaultpoint) {
            Point point3;
            point3 = new Point(event.getX(), event.getY());
            this.triangle.setP3(point3);
            model.addDrawable(this.triangle);
            model.clearTempDrawables();
            model.notifyChange();
            System.out.println("Added Triangle");
            this.triangle = null;
        }
    }

    @Override
    public void onMouseDragged(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor) {
        if(this.triangle != null) {
            model.clearTempDrawables();
            Point point1 = this.triangle.getP1();
            Point point2 = this.triangle.getP2();
            if (point2 == defaultpoint) {
                Line l;
                point2 = new Point(event.getX(), event.getY());
                l = new Line(point1, point2, color, thickness);
                model.addTempDrawable(l);
                model.notifyChange();
            }
        }
    }

    @Override
    public void onMouseReleased(MouseEvent event, PaintModel model, boolean stroke, double thickness) {
        if(this.triangle != null){
            Point point2 = this.triangle.getP2();
            if(point2 == defaultpoint) {
                point2 = new Point(event.getX(), event.getY());
                this.triangle.setP2(point2);
                model.clearTempDrawables();
                model.notifyChange();
            }
        }
    }
    @Override
    public void onMouseMoved(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness) {
        if(this.triangle != null) {
            model.clearTempDrawables();
            Point point1 = this.triangle.getP1();
            Point point2 = this.triangle.getP2();
            Point point3 = this.triangle.getP3();
            if (point2 != defaultpoint && point3 == defaultpoint) {
                point3 = new Point(event.getX(), event.getY());
                Triangle temp = new Triangle(point1, point2, point3, color, stroke, thickness);
                model.addTempDrawable(temp);
                model.notifyChange();
            }
        }
    }

    @Override
    public void onMouseClicked(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness) {}

}
