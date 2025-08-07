package paint;
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
    private String mode = "Circle";
    private PaintModel model;
    private DrawingStrategy drawingStrategy;
    private Color color;
    private boolean stroke;
    private double thickness= 1.0;
    private Color backgroundColor = Color.WHITE;

    public PaintPanel(PaintModel model) {
        super(300, 300);
        this.model = model;
        this.drawingStrategy = new CircleDrawingStrategy();
        this.model.addObserver(this);
        this.color = Color.BLACK;
        this.stroke = false;

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
    }

    /**
     * Controller aspect of this
     */
    public void setMode(String mode) {
        this.mode = mode;
        System.out.println(this.mode);
    }

    public void setDrawingStrategy(DrawingStrategy drawingStrategy) {
        this.drawingStrategy = drawingStrategy;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        update(null,null);
    }

    public void setOutline(){
        this.stroke = true;
    }

    public void setSolid(){
        this.stroke = false;
    }

    public void setDimensions(double width, double height){
        this.setWidth(width);
        this.setHeight(height);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // Later when we learn about inner classes...
        // https://docs.oracle.com/javafx/2/events/DraggablePanelsExample.java.htm

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        if (drawingStrategy != null) {
            if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                drawingStrategy.onMousePressed(mouseEvent, model, this.color, this.stroke, thickness, backgroundColor);
            } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                drawingStrategy.onMouseDragged(mouseEvent, model, this.color, this.stroke, thickness, backgroundColor);
            } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                drawingStrategy.onMouseReleased(mouseEvent, model,stroke , thickness);
            } else if (mouseEventType.equals(MouseEvent.MOUSE_MOVED)) {
                drawingStrategy.onMouseMoved(mouseEvent, model, this.color, this.stroke, thickness);
            } else if (mouseEventType.equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStrategy.onMouseClicked(mouseEvent, model, color,stroke , thickness);
            }
        }
    }
    @Override
    public void update(Observable o, Object arg) {

        GraphicsContext g2d = this.getGraphicsContext2D();
        g2d.setFill(backgroundColor);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Draw all shapes from the model
        for (Drawable drawable : model.getDrawables()) {
            drawable.draw(g2d);
        }

        // Draw all temporary shapes
        for (Drawable tempDrawable : model.getTempDrawables()) {
            tempDrawable.draw(g2d);
        }
    }
}
