package paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class OvalDrawingStrategy implements DrawingStrategy {

    Oval oval;
    @Override
    public void onMousePressed(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor){
        System.out.println("Started Oval");
        Point centre = new Point(event.getX(), event.getY());
        this.oval =new Oval(centre, 0, 0, color, stroke, thickness);
        model.addTempDrawable(oval);
    }
    @Override
    public void onMouseDragged(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor) {
        if (this.oval != null) {
            model.clearTempDrawables();
            Oval tempoval;
            double x_radius = Math.abs(this.oval.getCentre().x - event.getX());
            double y_radius = Math.abs(this.oval.getCentre().y - event.getY());
            tempoval = new Oval(this.oval.getCentre(), x_radius, y_radius, color, stroke, thickness);
            model.addTempDrawable(tempoval);
            model.notifyChange();
            this.oval=tempoval;
        }
    }
    @Override
    public void onMouseReleased(MouseEvent event, PaintModel model, boolean stroke, double thickness){
        if(this.oval!=null){
            // Problematic notion of radius and centre!!
            model.addDrawable(this.oval);
            model.clearTempDrawables();
            model.notifyChange();
            System.out.println("Added Oval");
            this.oval=null;
        }
    }

    @Override
    public void onMouseMoved(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness) {}

    @Override
    public void onMouseClicked(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness) {}
}

