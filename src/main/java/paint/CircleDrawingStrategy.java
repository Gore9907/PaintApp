package paint;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CircleDrawingStrategy implements DrawingStrategy {

    Circle circle;
    @Override
    public void onMousePressed(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor){
        System.out.println("Started Circle");
        Point centre = new Point(event.getX(), event.getY());
        this.circle=new Circle(centre, 0, color, stroke, thickness);
        model.addTempDrawable(this.circle);
    }
    @Override
    public void onMouseDragged(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor) {
        if (this.circle != null) {
            model.clearTempDrawables();
            Circle tempcircle;
            double radius = Math.sqrt(
                    Math.pow(this.circle.getCentre().x - event.getX(), 2) +
                            Math.pow(this.circle.getCentre().y - event.getY(), 2)
            );
            tempcircle = new Circle(this.circle.getCentre(), radius, color, stroke, thickness);
            model.addTempDrawable(tempcircle);
            model.notifyChange();
        }
    }
    @Override
    public void onMouseReleased(MouseEvent event, PaintModel model, boolean stroke, double thickness){
            if(this.circle!=null){
                // Problematic notion of radius and centre!!
                double radius = Math.sqrt(
                        Math.pow(this.circle.getCentre().x - event.getX(), 2) +
                                Math.pow(this.circle.getCentre().y - event.getY(), 2)
                );
                this.circle.setRadius(radius);
                model.addDrawable(this.circle);
                model.clearTempDrawables();
                model.notifyChange();
                System.out.println("Added Circle");
                this.circle=null;
            }
    }


    @Override
    public void onMouseMoved(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness) {}

    @Override
    public void onMouseClicked(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness) {}
}
