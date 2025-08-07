package paint;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Optional;

public class View implements EventHandler<ActionEvent> {

        private TextField heightField;
        private TextField widthField;
        private PaintModel paintModel;
        private PaintPanel paintPanel;
        private ShapeChooserPanel shapeChooserPanel;
        private Label labelMode;
        private Label lableCanvasSize;
        private Button resizeButton;

        public View(PaintModel model, Stage stage) {
            this.paintModel = model;
            this.labelMode = new Label("Mode: Circle");
            this.paintPanel = new PaintPanel(this.paintModel);
            this.shapeChooserPanel = new ShapeChooserPanel(this);
            this.lableCanvasSize = new Label("Canvas Dimensions: " +
                        (int) paintPanel.getWidth() + " x " +
                        (int) paintPanel.getHeight());
            this.widthField = new TextField(String.valueOf((int) paintPanel.getWidth()));
            this.widthField.setPrefWidth(50);
            this.heightField = new TextField(String.valueOf((int) paintPanel.getHeight()));
            this.heightField.setPrefWidth(50);
            this.resizeButton = new Button("Resize");
            this.resizeButton.setOnAction(event -> resizeCanvas());

            BorderPane root = new BorderPane();
            root.setTop(createMenuBar());
            root.setCenter(this.paintPanel);
            root.setLeft(this.shapeChooserPanel);
            HBox bottomBar = new HBox(10);
            bottomBar.setPadding(new Insets(10, 10, 10, 10));
            bottomBar.getChildren().addAll(this.labelMode,
                    this.lableCanvasSize,
                    new Label("Width:"), this.widthField,
                    new Label("Height:"), this.heightField,
                    this.resizeButton);
            root.setBottom(bottomBar);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Paint");
            stage.show();
        }

        public PaintModel getPaintModel() {
                return this.paintModel;
        }

        // ugly way to do this?
        public void setMode(String mode){
            this.paintPanel.setMode(mode);
            this.labelMode.setText("Mode: " + mode);
        }

        public void setDrawingStrategy(DrawingStrategy strategy){
                this.paintPanel.setDrawingStrategy(strategy);
        }
        private MenuBar createMenuBar() {

                MenuBar menuBar = new MenuBar();
                Menu menu;
                MenuItem menuItem;

                // A menu for File

                menu = new Menu("File");

                menuItem = new MenuItem("New");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Open");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Save");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menu.getItems().add(new SeparatorMenuItem());

                menuItem = new MenuItem("Exit");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuBar.getMenus().add(menu);

                // Another menu for Edit

                menu = new Menu("Edit");

                menuItem = new MenuItem("Cut");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Copy");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Paste");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menu.getItems().add(new SeparatorMenuItem());
                menuItem = new MenuItem("Undo");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Redo");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuBar.getMenus().add(menu);

                menu = new Menu("Color");
                menuItem = new MenuItem("Black");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("White");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menu.getItems().add(new SeparatorMenuItem());

                menuItem = new MenuItem("Blue");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Red");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Green");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Yellow");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuBar.getMenus().add(menu);

                menu = new Menu("Fill style");
                menuItem = new MenuItem("Solid");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Outline");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuBar.getMenus().add(menu);

                // Add a new menu for line thickness
                menu = new Menu("Line Thickness");
                menuItem = new MenuItem("1");
                for(int i = 1; i < 11; i++){
                        menuItem = new MenuItem("" + i);
                        menuItem.setOnAction(this);
                        menu.getItems().add(menuItem);
                }
                menuBar.getMenus().add(menu);

                // Add a new menu for Background color
                menu = new Menu("Background Color");
                menuItem = new MenuItem("Black Background");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("White Background");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Blue Background");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Red Background");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Green Background");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Yellow Background");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuBar.getMenus().add(menu);
                return menuBar;
        }

        private HBox createModeBar() {
                HBox modeBar = new HBox(this.labelMode);
                modeBar.setPadding(new Insets(10));
                return modeBar;
        }


        @Override
        public void handle(ActionEvent event) {
                String command = ((MenuItem) event.getSource()).getText();
                if (command.equals("Exit")) {
                        Platform.exit();
                }
                if(command.equals("Undo")){
                        paintModel.Undo();
                }
                if(command.equals("Redo")){
                        paintModel.clearDrawables();
                }
                if(command.equals("Black")){
                        this.paintPanel.setColor(Color.BLACK);
                        System.out.println("Select color " + "Black");
                }
                if(command.equals("White")){
                        this.paintPanel.setColor(Color.WHITE);
                        System.out.println("Select color " + "White");
                }
                if(command.equals("Blue")){
                        this.paintPanel.setColor(Color.BLUE);
                        System.out.println("Select color " + "Blue");
                }
                if(command.equals("Red")){
                        this.paintPanel.setColor(Color.RED);
                        System.out.println("Select color " + "Red");
                }
                if(command.equals("Green")){
                        this.paintPanel.setColor(Color.GREEN);
                        System.out.println("Select color " + "Green");
                }
                if(command.equals("Yellow")){
                        this.paintPanel.setColor(Color.YELLOW);
                        System.out.println("Select color " + "Yellow");
                }
                if(command.equals("Black Background")){
                        this.paintPanel.setBackgroundColor(Color.BLACK);
                        System.out.println("Select background color " + "Black");
                }
                if(command.equals("White Background")){
                        this.paintPanel.setBackgroundColor(Color.WHITE);
                        System.out.println("Select background color " + "White");
                }
                if(command.equals("Blue Background")){
                        this.paintPanel.setBackgroundColor(Color.BLUE);
                        System.out.println("Select background color " + "Blue");
                }
                if(command.equals("Red Background")){
                        this.paintPanel.setBackgroundColor(Color.RED);
                        System.out.println("Select background color " + "Red");
                }
                if(command.equals("Green Background")){
                        this.paintPanel.setBackgroundColor(Color.GREEN);
                        System.out.println("Select background color " + "Green");
                }
                if(command.equals("Yellow Background")){
                        this.paintPanel.setBackgroundColor(Color.YELLOW);
                        System.out.println("Select background color " + "Yellow");
                }
                if(command.equals("Solid")){
                        this.paintPanel.setSolid();
                }
                if(command.equals("Outline")){
                        this.paintPanel.setOutline();

                }
                try{
                        double thickness = Integer.parseInt(command);
                        this.paintPanel.setThickness(thickness);
                } catch (NumberFormatException _){}
        }

        private void resizeCanvas() {
                try {
                        double newWidth = Double.parseDouble(widthField.getText());
                        double newHeight = Double.parseDouble(heightField.getText());

                        paintPanel.setWidth(newWidth);
                        paintPanel.setHeight(newHeight);

                        updateLabelCanvasSize();
                        paintPanel.update(null,null);
                } catch (NumberFormatException e) {
                        System.out.println("Invalid input for dimensions.");
                }
        }
        private void updateLabelCanvasSize() {
                lableCanvasSize.setText("Canvas Dimensions: " +
                        (int) paintPanel.getWidth() + " x " +
                        (int) paintPanel.getHeight());
        }

}
