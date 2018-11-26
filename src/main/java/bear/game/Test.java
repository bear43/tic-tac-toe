package bear.game;

import org.newdawn.slick.*;

public class Test
{
    private static final int DEFAULT_WIDTH = 640;

    private static final int DEFAULT_HEIGHT = 480;

    private static final boolean DEFAULT_FULLSCREEN = false;

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer app = new AppGameContainer(new GameContainer());
            app.setDisplayMode(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FULLSCREEN);
            app.start();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
