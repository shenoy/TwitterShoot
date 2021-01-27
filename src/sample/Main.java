package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

import java.util.Random;

public class Main extends Application {
    final static int WIDTH = 900;
    final static int HEIGHT = 600;
    final static Image BACKGROUND_IMG = new Image("fores.jpeg");
    final static Image TREE_1_IMG = new Image("tree1.png");
    final static Image TREE_2_IMG = new Image("tree2.png");
    final static Image TREE_3_IMG = new Image("tree3.png");
    final static Image BIRD_1_IMG = new Image("smallbird1.png");
    final static Image BIRD_2_IMG = new Image("smallbird2.png");
    final static Image BIRD_3_IMG = new Image("smallbird3.png");
    final static Image BIRD_4_IMG = new Image("smallbird4.png");
    final static Image BIRD_5_IMG = new Image("smallbird5.png");
    final static Image BIRD_6_IMG = new Image("smallbird6.png");
    final static Image BIRD_7_IMG = new Image("smallbird7.png");
    final static Image BIRD_8_IMG = new Image("smallbird8.png");
    private final static Random random = new Random();

    private Animation current;
    private Group bird;
    private IntegerProperty hitCounter = new SimpleIntegerProperty(this, "hitCounter");
    private IntegerProperty shotCounter = new SimpleIntegerProperty(this, "shotCounter");
    private IntegerProperty accuracy = new SimpleIntegerProperty(this, "accuracy");

    @Override
    public void start(Stage primaryStage) throws Exception {
        final ImageView tree1 = new ImageView(TREE_1_IMG);
        tree1.setTranslateX(400);
        tree1.setTranslateY(100);
        final ImageView tree2 = new ImageView(TREE_2_IMG);
        tree2.setTranslateX(-300);
        tree2.setTranslateY(100);
        final ImageView tree3 = new ImageView(TREE_3_IMG);
        tree3.setTranslateY(100);
        tree3.setTranslateX(100);
        final Group foreground = new Group(tree1, tree2, tree3);
        foreground.setEffect(new DropShadow());
        final ImageView background = new ImageView(BACKGROUND_IMG);
        background.setEffect(new BoxBlur());
        background.setOpacity(0.5);
        final ImageView bird1 = new ImageView(BIRD_1_IMG);
        final ImageView bird2 = new ImageView(BIRD_2_IMG);
        final ImageView bird3 = new ImageView(BIRD_3_IMG);
        final ImageView bird4 = new ImageView(BIRD_4_IMG);
        final ImageView bird5 = new ImageView(BIRD_5_IMG);
        final ImageView bird6 = new ImageView(BIRD_6_IMG);
        final ImageView bird7 = new ImageView(BIRD_7_IMG);
        final ImageView bird8 = new ImageView(BIRD_8_IMG);
        bird1.setTranslateX(-300);
        bird2.setTranslateX(-300);
        bird3.setTranslateX(-300);
        bird4.setTranslateX(-300);
        bird5.setTranslateX(-300);
        bird6.setTranslateX(-300);
        bird7.setTranslateX(-300);
        bird8.setTranslateX(-300);
        bird1.setTranslateY(-300);
        bird2.setTranslateY(-300);
        bird3.setTranslateY(-300);
        bird4.setTranslateY(-300);
        bird5.setTranslateY(-300);
        bird6.setTranslateY(-300);
        bird7.setTranslateY(-300);
        bird8.setTranslateY(-300);

        bird= new Group(bird1);
        KeyFrame KeyFrame1 = new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bird.getChildren().setAll(bird2);
            }
        });

        KeyFrame KeyFrame2 = new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bird.getChildren().setAll(bird3);
            }
        });
        KeyFrame KeyFrame3 = new KeyFrame(Duration.millis(300), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bird.getChildren().setAll(bird4);
            }
        });
        KeyFrame KeyFrame4 = new KeyFrame(Duration.millis(400), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bird.getChildren().setAll(bird5);
            }
        });
        KeyFrame KeyFrame5 = new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bird.getChildren().setAll(bird6);
            }
        });
        KeyFrame KeyFrame6 = new KeyFrame(Duration.millis(600), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bird.getChildren().setAll(bird7);
            }
        });
        KeyFrame KeyFrame7 = new KeyFrame(Duration.millis(700), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bird.getChildren().setAll(bird8);
            }
        });
        KeyFrame KeyFrame8 = new KeyFrame(Duration.millis(800), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bird.getChildren().setAll(bird1);
            }
        });

        Timeline t = new Timeline(KeyFrame1, KeyFrame2, KeyFrame3, KeyFrame4, KeyFrame5, KeyFrame6, KeyFrame7, KeyFrame8);
        t.setCycleCount(Animation.INDEFINITE);
        t.play();

        final Animation hitAnimation;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(720);
        rotateTransition.setDuration(Duration.millis(800));
        TranslateTransition tt = new TranslateTransition();
        tt.setByY(1000);
        tt.setDuration(Duration.millis(800));
        tt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startAnimation();
            }
        });
        hitAnimation= new SequentialTransition(bird, rotateTransition,tt);
        bird.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                current.stop();
                hitAnimation.play();
                hitCounter.set(hitCounter.get()+1);
            }
        });

        final Text hitLabel = new Text();
        hitLabel.textProperty().bind(Bindings.concat("HITS : ", hitCounter));
        final Text shotLabel = new Text();
        shotLabel.textProperty().bind(Bindings.concat("SHOTS : ", shotCounter));
        final Text accuracyLabel = new Text();
        accuracyLabel.textProperty().bind((Bindings.concat("ACCURACY % : ", accuracy )));
        final VBox hud = new VBox(hitLabel,shotLabel, accuracyLabel);
        hud.setTranslateX(20);
        hud.setTranslateY(20);

        final Group root = new Group(background, bird, foreground, hud);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                shotCounter.set(shotCounter.get()+1);
                accuracy.set(hitCounter.get()*100/shotCounter.get());

            }
        });



        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        startAnimation();
    }
    public static void main(String[] args) {
        launch(args);
    }
    private void startAnimation() {
        if (current != null) {
            current.stop();
        }
        final int y0 = random.nextInt(HEIGHT/2 ) + HEIGHT / 4;
        final int y1 = random.nextInt(HEIGHT/2 ) + HEIGHT / 4;
        bird.setRotate(0);
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(bird);
        translateTransition.setFromX(-100);
        translateTransition.setToX(WIDTH);
        translateTransition.setFromY(y0);
        translateTransition.setToY(y1);
        translateTransition.setDuration(Duration.seconds(3));
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startAnimation();
            }
        });
        current = translateTransition;
        current.play();

    }
}
