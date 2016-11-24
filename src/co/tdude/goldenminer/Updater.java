package co.tdude.goldenminer;

import co.tdude.goldenminer.loop.GameLoop;

/**
 * Created by tristan on 2016-11-24.
 */
public class Updater extends Thread {
    @Override
    public void run() {
        super.run();
        GoldenMiner goldenMiner = GoldenMiner.getInstance();
        double tickrate = goldenMiner.getTickrate();
        GameLoop updateLoop = GoldenMiner.getUpdateLoop();
        updateLoop.run(tickrate);
    }
}
