package bear.game.Game;

import bear.game.Game.Field.Field;
import bear.game.Game.Figure.Figure;
import bear.game.Game.Figure.FigureType;
import bear.game.Game.Figure.Hach;
import bear.game.Game.Figure.Ou;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameContainer extends BasicGame
{
    private Field field = new Field();

    private Figure currentFigure = new Hach();

    private FigureContainer haches = new FigureContainer();

    private FigureContainer ous = new FigureContainer();

    private static float gameWidth;

    private static float gameHeight;

    private float[] mouse = new float[2];//X, Y

    private Input input;

    private static final int FPS_LIMIT = 60;

    public void init(org.newdawn.slick.GameContainer gameContainer) throws SlickException
    {
        gameWidth = gameContainer.getWidth();
        gameHeight = gameContainer.getHeight();
        field.update();
        currentFigure.update();
        gameContainer.setTargetFrameRate(FPS_LIMIT);
        input = gameContainer.getInput();
        FigureContainer.addDefaultFigure(currentFigure);

    }

    public void update(org.newdawn.slick.GameContainer gameContainer, int i) throws SlickException
    {
        gameWidth = gameContainer.getWidth();
        gameHeight = gameContainer.getHeight();
        mouse[0] = input.getMouseX();
        mouse[1] = input.getMouseY();
        try
        {
            if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
            {
                if(currentFigure.getFigureType() == FigureType.X)
                {
                    haches.addFigure((Figure)currentFigure.clone());
                    currentFigure = new Ou(mouse[0], mouse[1]);
                }
                else
                {
                    ous.addFigure((Figure)currentFigure.clone());
                    currentFigure = new Hach(mouse[0], mouse[1]);
                }
                FigureContainer.getDefFigures().set(0, currentFigure);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void render(org.newdawn.slick.GameContainer gameContainer, Graphics graphics) throws SlickException
    {
        field.draw(graphics);
        currentFigure.setCenterX(mouse[0]);
        currentFigure.setCenterY(mouse[1]);
        FigureContainer.drawAllFigures(graphics);

    }

    public GameContainer()
    {
        super("XO");
    }

    public GameContainer(String title)
    {
        super(title);
    }

    public static float getGameWidth()
    {
        return gameWidth;
    }

    public static void setGameWidth(float gameWidth)
    {
        GameContainer.gameWidth = gameWidth;
    }

    public static float getGameHeight()
    {
        return gameHeight;
    }

    public static void setGameHeight(float gameHeight)
    {
        GameContainer.gameHeight = gameHeight;
    }
}
