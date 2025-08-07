package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public abstract class Shape {
    public abstract void draw(GraphicsContext g2d);
    public abstract Color getColor();
    public abstract void setColor(Color color);
}
