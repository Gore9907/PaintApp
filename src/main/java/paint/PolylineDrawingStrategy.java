package paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PolylineDrawingStrategy implements DrawingStrategy{
    private Polyline polyline;
    @Override
    public void onMousePressed(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor) {}

    @Override
    public void onMouseDragged(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor) {}

    @Override
    public void onMouseReleased(MouseEvent event, PaintModel model, boolean stroke, double thickness) {}

    @Override
    public void onMouseMoved(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness) {
        if(polyline != null) {
            Point p = new Point(event.getX(), event.getY());
            Polyline temp = new Polyline(color, thickness);
            for (Point point : polyline.getPoints()) {
                temp.addPoint(point);
            }
            temp.addPoint(p);
            model.clearTempDrawables();
            model.addTempDrawable(temp);
            model.notifyChange();
        }
    }

    @Override
    public void onMouseClicked(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness) {
        Point p = new Point(event.getX(), event.getY());
        if(this.polyline == null) {
            this.polyline = new Polyline(color, thickness);
            polyline.addPoint(p);
        }
        else{
            if(event.getClickCount() == 2){
                this.polyline.addPoint(p);
                model.addDrawable(this.polyline);
                model.clearTempDrawables();
                model.notifyChange();
                System.out.println("Added Polyline");
                this.polyline = null;
            }
            else{
                this.polyline.addPoint(p);
            }
        }
    }

}
