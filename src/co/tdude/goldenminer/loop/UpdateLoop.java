package co.tdude.goldenminer.loop;

import co.tdude.goldenminer.GoldenMiner;
import co.tdude.goldenminer.InputManager;
import co.tdude.goldenminer.sprites.Hook;
import co.tdude.goldenminer.sprites.SpriteManager;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by tristan on 2016-11-24.
 */
public class UpdateLoop extends GameLoop {
    private SpriteManager sm;
    private GraphicsContext gc = GoldenMiner.getInstance().getGraphicsContext();

    public UpdateLoop() {
        sm = SpriteManager.getInstance();
    }

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

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void update(double delta) {
        if (InputManager.isKeyPressed("SPACE")) {

            ((Hook) SpriteManager.getInstance().getSprite("hook")).reel();
        }
    }

    @Override
    public void draw() {

    }
}
