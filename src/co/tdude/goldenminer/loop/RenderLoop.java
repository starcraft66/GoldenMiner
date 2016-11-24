package co.tdude.goldenminer.loop;

import co.tdude.goldenminer.GoldenMiner;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by tristan on 2016-11-24.
 */
public class RenderLoop extends GameLoop {
    private GoldenMiner gm;
    Image background;

    @Override
    public void run(double delta) {
        System.out.println("renderer started");
        gm = GoldenMiner.getInstance();
        background = new Image("file:assets/bg.jpg");
        super.run(delta);
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void startup() {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        System.out.println("renderer drew");
        GraphicsContext gc = gm.getGraphicsContext();
        gc.drawImage(background, 0, 0);
    }
}
