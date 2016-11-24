package co.tdude.goldenminer;

import co.tdude.goldenminer.loop.GameLoop;
import co.tdude.goldenminer.loop.RenderLoop;
import co.tdude.goldenminer.loop.UpdateLoop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.HashSet;

import static javafx.application.Application.launch;

/**
 * Created by tristan on 2016-11-21.
 */
public class GoldenMiner extends Application {
    static GoldenMiner INSTANCE;

    static Scene mainScene;
    static GraphicsContext graphicsContext;

    static int WIDTH = 800;
    static int HEIGHT = 600;

    static HashSet<String> currentlyActiveKeys;
    static Image background;

    static GameLoop updateLoop;
    static GameLoop renderLoop;

    private final double FRAMERATE = 60D;
    private final double TICKRATE = 20D;

    @Override
    public void start(Stage theStage) throws Exception{
        Group root = new Group();
        mainScene = new Scene( root );
        theStage.setScene( mainScene );

        INSTANCE = this;
        Canvas canvas = new Canvas( WIDTH, HEIGHT );
        graphicsContext = canvas.getGraphicsContext2D();


        updateLoop = new UpdateLoop();
        renderLoop = new RenderLoop();

        //Start logic and render threads
        Thread updateThread = new Updater();
        updateThread.start();
        Thread renderThread = new Renderer();
        renderThread.start();

        currentlyActiveKeys = new HashSet<String>();

        mainScene.setOnKeyPressed(e ->
        {
            String code = e.getCode().toString();

            // only add once... prevent duplicates
            if (!currentlyActiveKeys.contains(code))
                currentlyActiveKeys.add(code);
        });

        mainScene.setOnKeyReleased(e ->
        {
            String code = e.getCode().toString();
            currentlyActiveKeys.remove(code);
        });

        root.getChildren().add( canvas );
        theStage.setTitle("Golden Miner");
        theStage.show();
    }

    public static GameLoop getUpdateLoop() {
        return updateLoop;
    }

    public static GameLoop getRenderLoop() {
        return renderLoop;
    }

    public static GoldenMiner getInstance() {
        return INSTANCE;
    }

    public double getFramerate() {
        return FRAMERATE;
    }

    public double getTickrate() {
        return TICKRATE;
    }

    public static GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
