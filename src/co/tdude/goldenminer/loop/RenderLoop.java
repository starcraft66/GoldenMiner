package co.tdude.goldenminer.loop;

import co.tdude.goldenminer.GoldenMiner;
import co.tdude.goldenminer.ImageUtils;
import co.tdude.goldenminer.sprites.Hook;
import co.tdude.goldenminer.sprites.SpriteManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by tristan on 2016-11-24.
 */
public class RenderLoop extends GameLoop {
    private GoldenMiner gm = GoldenMiner.getInstance();
    private SpriteManager sm = SpriteManager.getInstance();
    private GraphicsContext gc = gm.getGraphicsContext();

    Image top = new Image("file:assets/top.jpg");
    Image background = new Image("file:assets/bg.jpg");
    Image reel = new Image("file:assets/reel.png");

    Font font = Font.font("Trebuchet MS", FontWeight.NORMAL, 20);

    @Override
    public void run(double delta) {
        super.run(delta);
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void startup() {
        System.out.println("renderer started");
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void update(double delta) {
        sm.getSprites().forEach(sprite -> sprite.update(delta));
    }

    @Override
    public void draw() {
        //System.out.println("renderer drew");
        gc.clearRect(0, 0, GoldenMiner.WIDTH, GoldenMiner.HEIGHT);
        gc.drawImage(top, 0,0);
        gc.drawImage(background, 0, 115);
        gc.drawImage(reel, 364, 50);
        sm.getSprites().forEach(sprite -> sprite.render(gc));
        gc.setFont(font);
        gc.fillText("Money", 25, 35);
        gc.fillText("Goal", 25, 75);
        gc.fillText("Time", 650, 35);
    }
}
