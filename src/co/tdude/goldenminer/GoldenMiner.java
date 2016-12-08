package co.tdude.goldenminer;

import co.tdude.goldenminer.loop.GameLoop;
import co.tdude.goldenminer.loop.RenderLoop;
import co.tdude.goldenminer.loop.UpdateLoop;
import co.tdude.goldenminer.sprites.GoldNugget;
import co.tdude.goldenminer.sprites.Hook;
import co.tdude.goldenminer.sprites.SpriteManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.HashSet;

import static javafx.application.Application.launch;

/**
 * Created by tristan on 2016-11-21.
 */
public class GoldenMiner extends Application {
    static GoldenMiner INSTANCE;

    static Scene mainScene;
    static GraphicsContext graphicsContext;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    static HashSet<String> currentlyActiveKeys;
    static Image background;

    static GameLoop updateLoop;
    static GameLoop renderLoop;

    private final double FRAMERATE = 60D;
    private final double TICKRATE = 20D;

    private MediaPlayer mediaPlayer;
    private File musicFile = new File("assets/music.mp3");

    private int score;

    @Override
    public void start(Stage theStage) throws Exception{
        Group root = new Group();
        mainScene = new Scene( root );
        theStage.setScene( mainScene );

        INSTANCE = this;
        Canvas canvas = new Canvas( WIDTH, HEIGHT );
        graphicsContext = canvas.getGraphicsContext2D();

        SpriteManager sm = new SpriteManager();
        Sprite hook = new Hook();

        sm.addSprite("hook", hook);
        sm.addSprite("nugget1", new GoldNugget());
        sm.addSprite("nugget2", new GoldNugget());
        sm.addSprite("nugget3", new GoldNugget());
        sm.addSprite("nugget4", new GoldNugget());
        sm.addSprite("nugget5", new GoldNugget());
        sm.addSprite("nugget6", new GoldNugget());
        sm.addSprite("nugget7", new GoldNugget());
        sm.addSprite("nugget8", new GoldNugget());

        Media sound = new Media(musicFile.toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();


        updateLoop = new UpdateLoop();
        renderLoop = new RenderLoop();

        //Start logic and render threads
        Thread updateThread = new Updater();
        updateThread.start();
        Thread renderThread = new Renderer();
        renderThread.start();

        InputManager im = InputManager.getInstance();

        mainScene.setOnKeyPressed(e ->
        {
            im.addKey(e.getCode());
        });

        mainScene.setOnKeyReleased(e ->
        {
            im.removeKey(e.getCode());
        });

        root.getChildren().add( canvas );
        theStage.setTitle("Golden Miner");
        theStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        updateLoop.stop();
        renderLoop.stop();
        Platform.exit();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
