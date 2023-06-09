
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This application uses JavaFX to draw out a recursive shape (circle in this case)
 * until it reaches a certain stopping point
 * Matt Covone and 2/6/2022
 */
public class circleRecursion extends Application {
    static final int START_CENTER_PT_X = 200;
    static final int START_CENTER_PT_Y = 200;
    static final int START_RADIUS = 100;
    static final int START_DEPTH = 6;
    private Canvas drawingArea;

    //Launches the application
    public static void main(String[] args) {

        launch(args);
    }

    //Sets up the JavaFX stage
    public void start(Stage stage) {
        stage.setTitle("My Circle Fractal");

        drawingArea = new Canvas(500, 500); //Drawing canvas is set to 500px by 500px. Anything drawn beyond those dimensions  will NOT show up

        //Sets up the border around the window
        GraphicsContext gc = drawingArea.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(0, 0, 500, 500); //Black border is set to 500px by 500px

        drawCircle(START_CENTER_PT_X, START_CENTER_PT_Y, START_RADIUS, START_DEPTH);

        Pane window = new Pane(drawingArea);

        Scene scene = new Scene(window, 500, 500); //The window is set to 500px by 500px

        stage.setScene(scene);

        stage.show();
    }

    /**
     * This method recursively draws a circle until it reaches a base case less than one
     * @param x The circle's x-position
     * @param y The circle's y-position
     * @param r The circle's radius
     * @param d The circle's depth
     */
    public void drawCircle(int x, int y, int r, int d) {
        GraphicsContext gc = drawingArea.getGraphicsContext2D();
        gc.setFill(Color.CHOCOLATE);
        gc.fillOval(x, y, r, r);

        if (d < 1) {
            return; //Base Case (Known as the stopping point if "depth" reaches less than one
        }

        if (d > 1) {
            drawCircle(x + r, y + (r / 4), r / 2, d - 1); //Without the other recursive syntax, this one shows a repeating sequence to the right
            drawCircle(x - (r / 2), y + (r / 4), r / 2, d - 1); //Without the other recursive syntax, this one shows a repeating sequence to the left
            drawCircle(x + (r / 4), y - (r / 2), r / 2, d - 1); //Without the other recursive syntax, this one shows a repeating sequence to the top
            drawCircle(x + (r / 4), y + r, r / 2, d - 1); //Without the other recursive syntax, this one shows a repeating sequence to the bottom
        }
    }
} 