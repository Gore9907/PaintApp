package paint;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SquiggleDrawingStrategy implements DrawingStrategy {

    private Squiggle currentSquiggle;

    @Override
    public void onMousePressed(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor) {
        currentSquiggle = new Squiggle(color, thickness);  // Start a new squiggle
        model.addTempDrawable(currentSquiggle);
    }

    @Override
    public void onMouseDragged(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor) {
        if (currentSquiggle != null) {
            currentSquiggle.addPoint(new Point(event.getX(), event.getY()));
            model.notifyChange();
        }
    }

    @Override
    public void onMouseReleased(MouseEvent event, PaintModel model, boolean stroke, double thickness) {
        if (currentSquiggle != null) {
            model.addDrawable(currentSquiggle);
            model.clearTempDrawables();
            currentSquiggle = null;
        }
    }

    @Override
    public void onMouseMoved(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness) {
    }

    @Override
    public void onMouseClicked(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness) {}
}
