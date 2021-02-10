package sample;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;


import static java.lang.Double.*;

public class Main extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(600);
        primaryStage.setTitle("Расчет площади закрашенной области");
        Label label1 = new Label("Радиус окружностей (см) : ");
        label1.setLayoutX(10);
        label1.setLayoutY(10);
        TextField txtRadius = new TextField();
        txtRadius.setLayoutX(10);
        txtRadius.setLayoutY(30);
        Button button = new Button("Построить");
        button.setLayoutX(10);
        button.setLayoutY(70);

        Group root = new Group();
        Scene scene = new Scene(root, 500, 600);
        root.getChildren().addAll(label1, txtRadius, button);
        
        button.setOnAction(event -> {

            Rectangle cleaner1 = new Rectangle(0, 110, scene.getWidth(), scene.getHeight());
            cleaner1.setStroke(Color.WHITE);
            cleaner1.setFill(Color.WHITE);
            Rectangle cleaner2 = new Rectangle(190, 0, 200, 50);
            cleaner2.setStroke(Color.WHITE);
            cleaner2.setFill(Color.WHITE);
            root.getChildren().addAll(cleaner1, cleaner2);

            double radius=0;
            try {
                 radius = parseDouble(txtRadius.getText()) * 40;
            }
            catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Радиус не соответвует формату. Введите вещественное число (используя точку для разделения целой части и дробной) ");
                alert.showAndWait();
            }

            if (4*radius <= scene.getWidth() && 4*radius + 100 <= scene.getHeight()) {
                double[] Coordinates = getCenters(radius,scene.getHeight(),scene.getWidth());

                Rectangle rectangle = new Rectangle(Coordinates[2], Coordinates[3], 2* radius, 2* radius);

                rectangle.setFill(Color.RED);

                Circle circle1 = new Circle(Coordinates[0], Coordinates[1], radius);
                circle1.setStroke(Color.BLACK);
                circle1.setFill(Color.WHITE);

                Circle circle2 = new Circle(Coordinates[2], Coordinates[3], radius);
                circle2.setStroke(Color.BLACK);
                circle2.setFill(Color.WHITE);

                Circle circle3 = new Circle(Coordinates[4], Coordinates[5], radius);
                circle3.setStroke(Color.BLACK);
                circle3.setFill(Color.WHITE);

                Circle circle4 = new Circle(Coordinates[6], Coordinates[7], radius);
                circle4.setStroke(Color.BLACK);
                circle4.setFill(Color.WHITE);


                Label label2 = new Label("Площадь закрашенной фигуры:");
                label2.setLayoutX(200);
                label2.setLayoutY(10);

                root.getChildren().addAll(rectangle,circle1,circle2,circle3,circle4,label2);

                double s = Math.pow(radius/40,2) * (4- Math.PI);
                Label label3 = new Label(Double.toString(s));
                label3.setLayoutX(200);
                label3.setLayoutY(30);
                root.getChildren().add(label3);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Рисунок с введенным радиусом не поместиться в окне. Увеличьте окно или уменьшите радиус! ");
                alert.showAndWait();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public double[] getCenters(double r,double h,double w) { //для расчета координат центров окружностей
        double CenterX =w/2;
        double CenterY =(h+100)/2;

        double centXel1 = CenterX + Math.sqrt((r*r));
        double centYel1 = CenterY - Math.sqrt((r*r));
        double centXel2 = CenterX - Math.sqrt((r*r));
        double centYel3 = CenterY + Math.sqrt((r*r));

        return new double[]{centXel1, centYel1, centXel2, centYel1, centXel2, centYel3, centXel1, centYel3};

    }
}
