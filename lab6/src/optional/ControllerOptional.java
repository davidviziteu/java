package optional;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import javafx.scene.image.*;
import javafx.stage.Window;

public class ControllerOptional implements Initializable {
    ArrayList<Shape> drawnShapes = new ArrayList<>();
    Color shapeColor;
    String selectedShape;
    @FXML
    ColorPicker colorPicker;
    @FXML
    Spinner<Integer> shapeSize;
    @FXML
    Spinner<Integer> polySides;
    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    AnchorPane drawingArea;
    @FXML
    Button exitButton;
    @FXML
    Label shapeSizeLabel;
    @FXML
    Label polySidesLabel;
    @FXML
    Canvas drawingCanvas;

    public void setColor(ActionEvent e){
        shapeColor = colorPicker.getValue();
    }

    public void setShape(ActionEvent e){

        SpinnerValueFactory<Integer> sizeValueSpinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 50);
        shapeSize.setValueFactory(sizeValueSpinner);
        if(choiceBox.getValue().equals("Circle")){
            shapeSizeLabel.setText("Cricle radius");
            shapeSize.setVisible(true);
            shapeSizeLabel.setVisible(true);
            polySidesLabel.setVisible(false);
            drawingCanvas.setVisible(false);
            polySides.setVisible(false);
            return;
        }
        if(choiceBox.getValue().equals("Polygon")){
            SpinnerValueFactory<Integer> sizeValueSpinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 100, 4);
            polySides.setValueFactory(sizeValueSpinner1);
            shapeSizeLabel.setText("Polygon size");
            shapeSize.setVisible(true);
            shapeSizeLabel.setVisible(true);
            polySidesLabel.setVisible(true);
            drawingCanvas.setVisible(false);
            polySides.setVisible(true);
        }
        if(choiceBox.getValue().equals("FreeDraw")){
            shapeSizeLabel.setText("Line size");
            drawingCanvas.setVisible(true);
            shapeSize.setVisible(true);
            shapeSizeLabel.setVisible(true);
            polySidesLabel.setVisible(false);
            polySides.setVisible(false);


            final GraphicsContext graphicsContext = drawingCanvas.getGraphicsContext2D();
            initDraw(graphicsContext);

            drawingCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>(){
                        @Override
                        public void handle(MouseEvent event) {
                            graphicsContext.beginPath();
                            graphicsContext.moveTo(event.getX(), event.getY());
                            graphicsContext.stroke();
                        }
                    });

            drawingCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                    new EventHandler<MouseEvent>(){
                        @Override
                        public void handle(MouseEvent event) {
                            graphicsContext.lineTo(event.getX(), event.getY());
                            graphicsContext.stroke();
                        }
                    });

            drawingCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                    new EventHandler<MouseEvent>(){
                        @Override
                        public void handle(MouseEvent event) {

                        }
                    });
        }

    }

    public void handleShape(MouseEvent event) {
        try{
            if(choiceBox.getValue().equals("FreeDrawing")){
                System.out.println("free draw");
                shapeSize.setVisible(false);
                shapeSizeLabel.setVisible(false);
                polySidesLabel.setVisible(false);
                polySides.setVisible(false);
                if(event.getEventType() == MouseEvent.MOUSE_DRAGGED){
                    var circle = new javafx.scene.shape.Circle();
                    circle.setRadius(3);
                    circle.setCenterX(event.getSceneX());
                    circle.setCenterY(event.getSceneY());
                    circle.setFill(shapeColor);
                    drawingArea.getChildren().add(circle);
                }
            }
            else
            if(event.getButton() == MouseButton.PRIMARY && choiceBox.getValue() != null) {
                var customShape = ShapeFactory.newShape(choiceBox.getValue(), (int) shapeSize.getValue(), event, polySides);
                assert customShape != null;
                customShape.setFill(shapeColor);
                if (event.getY() + (int) shapeSize.getValue() < drawingArea.getHeight()) {
                    drawingArea.getChildren().add(customShape);
                    drawnShapes.add(customShape);
                }
                customShape.setOnMouseClicked(evt -> {
                    if (evt.getButton() == MouseButton.SECONDARY) {
                        evt.consume();
                        drawingArea.getChildren().remove(customShape);
                        drawnShapes.remove(customShape);
                    }
                });
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void saveButtonOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:"));
        Window stage = drawingArea.getScene().getWindow();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (.png)", ".png");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(stage);

        WritableImage writableImage = new WritableImage((int) drawingArea.getWidth(), (int) drawingArea.getHeight());
        drawingArea.snapshot(null, writableImage);
        ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), ".png", file);
        System.out.println("snapshot saved: " + file.getAbsolutePath());

    }
    public void loadButtonOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        Window canvas;
        Window stage = drawingArea.getScene().getWindow();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files", ".png");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(stage);
        fileChooser.setInitialDirectory(new File("C:\""));
        ImageView imageView = new ImageView(String.valueOf(file.toURI()));
        drawingArea.getChildren().add(imageView);
    }
    public void exitButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    public void resetButton(ActionEvent event){
        drawingArea.getChildren().clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().add("Circle");
        choiceBox.getItems().add("Polygon");
        choiceBox.getItems().add("FreeDrawing");
        shapeSize.setVisible(false);
        shapeSizeLabel.setVisible(false);
        polySidesLabel.setVisible(false);
        polySides.setVisible(false);
        drawingCanvas.setVisible(false);
    }

    private void initDraw(GraphicsContext gc){
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        gc.setFill(Color.LIGHTGRAY);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);

        gc.fill();
        gc.strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                canvasWidth,    //width of the rectangle
                canvasHeight);  //height of the rectangle

        gc.setFill(Color.RED);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);

    }
}

