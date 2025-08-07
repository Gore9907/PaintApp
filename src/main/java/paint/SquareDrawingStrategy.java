package paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SquareDrawingStrategy implements DrawingStrategy {
    Square square;


    @Override
    public void onMousePressed(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor){
        System.out.println("Started Square");
        Point point1 = new Point(event.getX(), event.getY());
        this.square = new Square(point1, 0, 0, color, stroke, thickness);
        model.addTempDrawable(square);
    }

    @Override
    public void onMouseDragged(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor){
        if(this.square!=null) {
            model.clearTempDrawables();
            Square tempsquare;
            Point point2 = new Point(event.getX(), event.getY());
            double topLeftX, topLeftY;
            double width, height;
            if (this.square.getPoint().x <= point2.x) {
                topLeftX = this.square.getPoint().x;
                width = point2.x - topLeftX;
            } else {
                topLeftX = point2.x;
                width = this.square.getPoint().x - point2.x;
            }
            if (this.square.getPoint().y <= point2.y) {
                topLeftY = this.square.getPoint().y;
                height = point2.y - topLeftY;
            } else {
                topLeftY = point2.y;
                height = this.square.getPoint().y - point2.y;
            }
            Point topLeft = new Point(topLeftX, topLeftY);
            tempsquare = new Square(topLeft, width, height, color, stroke, thickness);
            model.addTempDrawable(tempsquare);
            model.notifyChange();
        }
    }
    @Override
    public void onMouseReleased(MouseEvent event, PaintModel model, boolean stroke, double thickness){
        if(this.square!=null) {
            Point point2 = new Point(event.getX(), event.getY());
            double topLeftX, topLeftY;
            double width, height;
            if (this.square.getPoint().x <= point2.x) {
                topLeftX = this.square.getPoint().x;
                width = point2.x - topLeftX;
            } else {
                topLeftX = point2.x;
                width = this.square.getPoint().x - point2.x;
            }
            if (this.square.getPoint().y <= point2.y) {
                topLeftY = this.square.getPoint().y;
                height = point2.y - topLeftY;
            } else {
                topLeftY = point2.y;
                height = this.square.getPoint().y - point2.y;
            }
            Point topLeft = new Point(topLeftX, topLeftY);
            this.square.setPoint(topLeft);
            this.square.setHeight(height);
            this.square.setWidth(width);
            model.addDrawable(this.square);
            model.clearTempDrawables();
            model.notifyChange();
            System.out.println("Added Square");
            this.square = null;
        }
    }

    @Override
    public void onMouseMoved(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness) {}

    @Override
    public void onMouseClicked(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness) {}

}
