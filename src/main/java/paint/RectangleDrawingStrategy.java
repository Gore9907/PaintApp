package paint;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class RectangleDrawingStrategy implements DrawingStrategy {
    Rectangle rectangle;

    @Override
    public void onMousePressed(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor){
        System.out.println("Started Rectangle");
        Point point1 = new Point(event.getX(), event.getY());
        this.rectangle = new Rectangle(point1, 0, 0, color, stroke, thickness);
        model.addTempDrawable(rectangle);
    }
    @Override
    public void onMouseDragged(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness, Color backgroundColor){
        if(this.rectangle!=null) {
            model.clearTempDrawables();
            Rectangle temprec;
            Point point2 = new Point(event.getX(), event.getY());
            double topLeftX, topLeftY;
            double width, height;
            if (this.rectangle.getPoint().x <= point2.x) {
                topLeftX = this.rectangle.getPoint().x;
                width = point2.x - topLeftX;
            } else {
                topLeftX = point2.x;
                width = this.rectangle.getPoint().x - point2.x;
            }
            if (this.rectangle.getPoint().y <= point2.y) {
                topLeftY = this.rectangle.getPoint().y;
                height = point2.y - topLeftY;
            } else {
                topLeftY = point2.y;
                height = this.rectangle.getPoint().y - point2.y;
            }
            Point topLeft = new Point(topLeftX, topLeftY);
            temprec = new Rectangle(topLeft, width, height, color, stroke, thickness);
            model.addTempDrawable(temprec);
            model.notifyChange();
        }
    }
    @Override
    public void onMouseReleased(MouseEvent event, PaintModel model, boolean stroke, double thickness){
            if(this.rectangle!=null) {
                Point point2 = new Point(event.getX(), event.getY());
                double topLeftX, topLeftY;
                double width, height;
                if (this.rectangle.getPoint().x <= point2.x) {
                    topLeftX = this.rectangle.getPoint().x;
                    width = point2.x - topLeftX;
                } else {
                    topLeftX = point2.x;
                    width = this.rectangle.getPoint().x - point2.x;
                }
                if (this.rectangle.getPoint().y <= point2.y) {
                    topLeftY = this.rectangle.getPoint().y;
                    height = point2.y - topLeftY;
                } else {
                    topLeftY = point2.y;
                    height = this.rectangle.getPoint().y - point2.y;
                }
                Point topLeft = new Point(topLeftX, topLeftY);
                this.rectangle.setPoint(topLeft);
                this.rectangle.setHeight(height);
                this.rectangle.setWidth(width);
                model.addDrawable(this.rectangle);
                model.clearTempDrawables();
                model.notifyChange();
                System.out.println("Added Rectangle");
                this.rectangle = null;
            }
    }

    @Override
    public void onMouseMoved(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness) {}

    @Override
    public void onMouseClicked(MouseEvent event, PaintModel model, Color color, boolean stroke, double thickness) {}
}
