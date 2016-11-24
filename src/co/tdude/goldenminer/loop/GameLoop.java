package co.tdude.goldenminer.loop;

/**
 * Created by tristan on 2016-11-24.
 */
public abstract class GameLoop
{
    private boolean runFlag = false;

    /**
     * Begin the game loop
     * @param delta times the logic updates per second;
     */
    public void run(double delta) //60
    {
        delta = 1 / delta;
        runFlag = true;

        startup();
        // convert the time to seconds
        double nextTime = (double)System.nanoTime() / 1000000000.0;
        while(runFlag)
        {
            // convert the time to seconds
            double currTime = (double)System.nanoTime() / 1000000000.0;
            if(currTime >= nextTime)
            {
                // assign the time for the next update
                nextTime += delta;
                update();
                draw();
            }
            else
            {
                // calculate the time to sleep
                int sleepTime = (int)(1000.0 * (nextTime - currTime));
                // sanity check
                if(sleepTime > 0)
                {
                    // sleep until the next update
                    try
                    {
                        Thread.sleep(sleepTime);
                    }
                    catch(InterruptedException e)
                    {
                        // do nothing
                    }
                }
            }
        }
        shutdown();
    }

    public void stop()
    {
        runFlag = false;
    }

    public abstract void startup();
    public abstract void shutdown();
    public abstract void update();
    public abstract void draw();
}
