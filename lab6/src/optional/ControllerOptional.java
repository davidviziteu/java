package optional;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.shape.Circle;
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
    AnchorPane clickArea;
    @FXML
    Button exitButton;
    @FXML
    Label shapeSizeLabel;
    @FXML
    Label polySidesLabel;
    @FXML
    Canvas drawingCanvas;
    @FXML
    ToggleButton eraseToggle;

    public void setColor(ActionEvent e){
        shapeColor = colorPicker.getValue();
    }
    public void freeDrawing() { Shape point = new Circle();
        GraphicsContext g = drawingCanvas.getGraphicsContext2D();
        drawingCanvas.setOnMouseDragged(event -> {
            double size = shapeSize.getValue();
            double x = event.getX();
            double y = event.getY();
            if(eraseToggle.isSelected()){
                g.clearRect(x,y,size,size);
            }
            else{
                System.out.println("drawing");
                g.setFill(colorPicker.getValue());
                g.fillRect(x,y, size,size);
            }
        });

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
            eraseToggle.setVisible(false);
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
            eraseToggle.setVisible(false);
        }
        if(choiceBox.getValue().equals("FreeDrawing")) {
            drawingCanvas.setVisible(true);
            shapeSize.setVisible(true);
            eraseToggle.setVisible(true);
            System.out.println("free drawing selected");
            freeDrawing();
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
            }
            else
            if(event.getButton() == MouseButton.PRIMARY && choiceBox.getValue() != null) {
                var customShape = ShapeFactory.newShape(choiceBox.getValue(), (int) shapeSize.getValue(), event, polySides);
                customShape.setFill(shapeColor);
                if (event.getY() + (int) shapeSize.getValue() < clickArea.getHeight()) {
                    clickArea.getChildren().add(customShape);
                    drawnShapes.add(customShape);
                }
                customShape.setOnMouseClicked(evt -> {
                    if (evt.getButton() == MouseButton.SECONDARY) {
                        evt.consume();
                        clickArea.getChildren().remove(customShape);
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
        Window stage = clickArea.getScene().getWindow();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (.png)", ".png");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(stage);

            WritableImage writableImage = new WritableImage((int) clickArea.getWidth(), (int) clickArea.getHeight());
            clickArea.snapshot(null, writableImage);

            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
            System.out.println("snapshot saved: " + file.getAbsolutePath());

    }

    public void loadButtonOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        Window canvas;
        Window stage = clickArea.getScene().getWindow();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files", ".png");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(stage);
        fileChooser.setInitialDirectory(new File("C:\""));
        ImageView imageView = new ImageView(String.valueOf(file.toURI()));
        clickArea.getChildren().add(imageView);
    }

    public void exitButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void resetButton(ActionEvent event){
        clickArea.getChildren().clear();
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


}

