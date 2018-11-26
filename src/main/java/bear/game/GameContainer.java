package bear.game;

import bear.game.Figure.Ou;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class GameContainer extends BasicGame
{
    private Ou first = new Ou();

    private float gameWidth;

    private float gameHeight;

    private static final int FPS_LIMIT = 60;

    public void init(org.newdawn.slick.GameContainer gameContainer) throws SlickException
    {
        first.flush();
        gameContainer.setTargetFrameRate(FPS_LIMIT);
    }

    public void update(org.newdawn.slick.GameContainer gameContainer, int i) throws SlickException
    {
        gameWidth = gameContainer.getWidth();
        gameHeight = gameContainer.getHeight();
    }

    public void render(org.newdawn.slick.GameContainer gameContainer, Graphics graphics) throws SlickException
    {
        first.setCenterX(gameWidth/2);
        first.setCenterY(gameHeight/2);
        first.draw(graphics);
    }

    public GameContainer()
    {
        super("XO");
    }

    public GameContainer(String title)
    {
        super(title);
    }
}
