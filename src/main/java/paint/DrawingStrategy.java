package paint;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public interface DrawingStrategy {

    void onMousePressed(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor);
    void onMouseDragged(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor);
    void onMouseReleased(MouseEvent event, PaintModel model, boolean stroke, double thickness);
    void onMouseMoved(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness);
    void onMouseClicked(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness);
}
