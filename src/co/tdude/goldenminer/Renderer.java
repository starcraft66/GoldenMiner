package co.tdude.goldenminer;

import co.tdude.goldenminer.loop.GameLoop;

/**
 * Created by tristan on 2016-11-24.
 */
public class Renderer extends Thread {
    @Override
    public void run() {
        super.run();
        GoldenMiner goldenMiner = GoldenMiner.getInstance();
        double framerate = goldenMiner.getFramerate();
        GameLoop renderLoop = GoldenMiner.getRenderLoop();
        renderLoop.run(framerate);
    }
}
