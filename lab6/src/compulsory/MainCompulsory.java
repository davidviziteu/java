//package compulsory;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.SubScene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextInputDialog;
//import javafx.scene.effect.DropShadow;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.TilePane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//
//import static compulsory.Shapes.CIRCLE;
//import static compulsory.Shapes.NO_SHAPE;
//
//public class Main extends Application {
//    Label userInfo;
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        var root = new Pane();
//
////        Sta drawingStage = new SubScene(root, 300, 300);
////        drawingStage.setFill(Color.WHITE);
//        TilePane getSize = new TilePane();
//        userInfo = new Label("");
//        this.loadObjects();
//        circleButton.setLayoutX(50);
//        circleButton.setLayoutY(50);
//        root.getChildren().add(circleButton);
//        root.getChildren().add(userInfo);
//        root.getChildren().add(drawingArea);
//        root.getChildren().add(drawingArea);
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 500, 500));
//        primaryStage.show();
//    }
//    Shapes shapeSelected = null;
//
//    Button circleButton;
//    Rectangle drawingArea;
//    int shapeSize = 0;
//
//    void loadObjects(){
//        drawingArea = new Rectangle(100, 100, 500, 500);
//        drawingArea.setFill(Color.WHITE);
//        drawingArea.setStroke(Color.BLACK);
//        circleButton = new Button("Circle");
//
//        DropShadow shadow = new DropShadow();
//        //Adding the shadow when the mouse cursor is on
//        circleButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
//                new EventHandler<MouseEvent>() {
//                    @Override public void handle(MouseEvent e) {
//                        circleButton.setEffect(shadow);
//                    }
//                });
//        //Removing the shadow when the mouse cursor is off
//        circleButton.addEventHandler(MouseEvent.MOUSE_EXITED,
//                new EventHandler<MouseEvent>() {
//                    @Override public void handle(MouseEvent e) {
//                        circleButton.setEffect(null);
//                    }
//                });
//        circleButton.setOnAction(
//                new EventHandler<ActionEvent>() {
//                    @Override public void handle(ActionEvent e) {
//                        int input = 0;
//                        TextInputDialog td = new TextInputDialog();
//                        System.out.println("circle pressed");
//                        shapeSelected = null;
//                        td.setHeaderText("pls give circle radius as a positive integer");
//                        while (shapeSelected == null) {
//                            try {
//                                do{
//                                    var rawInput = td.showAndWait();
//                                    if (rawInput.isEmpty()) {
//                                        userInfo.setText("no shape selected");
//                                        shapeSelected = NO_SHAPE;
//                                        break;
//                                    }
//                                    input = Integer.parseInt(rawInput.get());
//                                    System.out.println("Got: " + rawInput + " " + input);
//                                } while(input <= 0);
//                                userInfo.setText("Selected circle of size "+ input + "px");
//                                shapeSize = input;
//                                shapeSelected = CIRCLE;
//                                break;
//                            } catch (NumberFormatException ex) {
//                                System.out.println(ex.getMessage());
//                                System.out.println("input err");
//                                td.setContentText("no number given as input. pls try again");
//                            }
//                        }
//                    }
//                });
//
//        drawingArea.addEventHandler(MouseEvent.MOUSE_CLICKED,
//                new EventHandler<MouseEvent>() {
//                    @Override public void handle(MouseEvent e) {
//                        switch (shapeSelected){
//                            case NO_SHAPE:
//
//                                break;
//                            case CIRCLE:
//
//                                break;
//                        }
//                    }
//                });
//
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//}
