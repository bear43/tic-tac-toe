package bear.game.Game;

import bear.game.Game.Event.Event;
import bear.game.Game.Event.EventHandler;
import bear.game.Game.Event.Mouse.EventContainer;
import bear.game.Game.Event.Mouse.MouseButton;
import bear.game.Game.Event.Mouse.MousePressed;
import bear.game.Game.Field.Field;
import bear.game.Game.Field.FieldLeftMouseHandler;
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
    private Field field;

    private Figure currentFigure;

    private static float gameWidth;

    private static float gameHeight;

    private float[] mouse = new float[2];//X, Y

    private Input input;

    private static final int FPS_LIMIT = 60;

    private EventContainer mousePressedEvent = new MousePressed();

    public void init(org.newdawn.slick.GameContainer gameContainer) throws SlickException
    {
        field = Field.getFieldInstance();
        gameWidth = gameContainer.getWidth();
        gameHeight = gameContainer.getHeight();
        field.update();
        currentFigure = new Hach(0, 0, gameWidth/Field.getFieldInstance().getCellCount(), gameHeight/Field.getFieldInstance().getCellCount());
        currentFigure.update();
        gameContainer.setTargetFrameRate(FPS_LIMIT);
        input = gameContainer.getInput();
        FigureContainer.addDefaultFigure(currentFigure);
        EventHandler handler = new FieldLeftMouseHandler();
        mousePressedEvent.addEvent(handler.toString(), handler);

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
                mousePressedEvent.startEvent(this, new Object[]{MouseButton.LEFT, mouse[0], mouse[1], currentFigure});
                currentFigure = FigureContainer.getDefFigures().get(0);
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
