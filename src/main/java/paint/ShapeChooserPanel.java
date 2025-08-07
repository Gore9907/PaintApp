package paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;


public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

        private View view;

    public ShapeChooserPanel(View view) {
        this.view = view;

        String[] shapes = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline", "Oval", "Triangle", "Eraser" };
        String[] imageFiles = {
                "circle.png", "rectangle.png", "Square.png",
                "squiggle.png", "polyline.png", "Oval.png", "Triangle.png", "eraser.png"
        };

        for (int i = 0; i < shapes.length; i++) {
            String shape = shapes[i];
            String imageFile = imageFiles[i];

            Button button = new Button();
            ImageView imageView = new ImageView(loadImage(imageFile));
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);

            button.setGraphic(imageView);
            button.setMinWidth(100);

            button.setUserData(shape);

            this.add(button, 0, i);

            button.setOnAction(this);
        }
    }

    private Image loadImage(String fileName) {
        String path = "/img/" + fileName;
        return new Image(Objects.requireNonNull(
                getClass().getResourceAsStream(path)
        ));
    }

        @Override
        public void handle(ActionEvent event) {
                Button clickedButton = (Button) event.getSource();
                String command = (String) clickedButton.getUserData();
                DrawingStrategy strategy = getDrawingStrategy(command);
                view.setMode(command);
                view.setDrawingStrategy(strategy);
        }

        private DrawingStrategy getDrawingStrategy(String command) {
            switch (command) {
                case "Polyline":
                    return new PolylineDrawingStrategy();
                case "Triangle":
                    return new TriangleDrawingStrategy();
                case "Circle":
                    return new CircleDrawingStrategy();
                case "Rectangle":
                    return new RectangleDrawingStrategy();
                case "Square":
                    return new SquareDrawingStrategy();
                case "Squiggle":
                    return new SquiggleDrawingStrategy();
                case "Oval":
                    return new OvalDrawingStrategy();
                case "Eraser":
                    return new EraserDrawingStrategy();
                default:
                    throw new IllegalArgumentException("Invalid drawing command: " + command);
            }
        }
}


