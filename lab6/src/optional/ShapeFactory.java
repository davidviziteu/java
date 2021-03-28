package optional;

import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

public class ShapeFactory {
    static public Shape newShape(String what, int shapeSize, MouseEvent ev, Spinner sides) throws RuntimeException{
        if(what.equals("Circle")){
            var circle = new javafx.scene.shape.Circle();
            circle.setRadius(shapeSize);
            circle.setCenterX(ev.getSceneX());
            circle.setCenterY(ev.getSceneY());
            return circle;
        }
        else if(what.equals("Polygon")){
            var polygon = new javafx.scene.shape.Polygon();
            polygon.getPoints().clear();
            final double angleStep = Math.PI * 2 / (int) sides.getValue();
            double angle = 0; // assumes one point is located directly beneat the center point
            for (int i = 0; i < (int) sides.getValue(); i++, angle += angleStep) {
                polygon.getPoints().addAll(
                        Math.sin(angle) * shapeSize + ev.getX() , // x coordinate of the corner
                        Math.cos(angle) * shapeSize + ev.getY() // y coordinate of the corner
                );
            }
            return polygon;
        }
        throw new RuntimeException("no match for shape "+ what + " in shape factory");
    }
}
