package bear.game.Game.Figure;

import bear.game.Game.Exceptions.BlockIsNotEmptyException;
import bear.game.Game.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Block extends FigureSkeleton
{
    private float startX;

    private float endX;

    private float startY;

    private float endY;

    private boolean isNextToBound;

    private boolean isEmpty = true;

    private static final float DEFAULT_SIZE = 20.0f;

    private float size = DEFAULT_SIZE;

    private float sizeX = size;

    private float sizeY = size;

    private float halfSizeX;

    private float halfSizeY;

    private Figure figureInside;

    private List<Line> lines = new CopyOnWriteArrayList<>();

    public Block(float centerX, float centerY)
    {
        this(centerX, centerY, DEFAULT_SIZE);
    }

    public Block(float centerX, float centerY, float size)
    {
        this(centerX, centerY, DEFAULT_SIZE, DEFAULT_SIZE);
    }

    public Block(float centerX, float centerY, float sizeX, float sizeY)
    {
        this(centerX, centerY, sizeX, sizeY, false);
    }

    public Block(float centerX, float centerY, float sizeX, float sizeY, boolean isNextToBound)
    {
        super(centerX, centerY);
        this.sizeX = sizeX;
        this.sizeY= sizeY;
        this.isNextToBound = isNextToBound;
        update();
    }

    @Override
    public FigureType getFigureType()
    {
        return FigureType.Block;
    }

    @Override
    public float getDefaultSize()
    {
        return DEFAULT_SIZE;
    }

    @Override
    public void draw(Graphics graphics)
    {
        lines.forEach(graphics::draw);
        if(!isEmpty)figureInside.draw(graphics);
    }

    @Override
    public void update()
    {
        halfSizeX = sizeX/2.0f;
        halfSizeY = sizeY/2.0f;
        startX = centerX - halfSizeX;
        startY = centerY - halfSizeY;
        endX = centerX + halfSizeX;
        endY = centerY + halfSizeY;
        if(lines.size() != 0) lines.clear();
        if(startX > 0.0f)//Add left line
            lines.add(new Line(startX, startY, startX, endY));
        else
            isNextToBound = true;
        if(endX < GameContainer.getGameWidth())
            lines.add(new Line(endX, startY, endX, endY));//add right line
        else
            isNextToBound = true;
        if(startY > 0.0f)//add upper line
            lines.add(new Line(startX, endY, endX, endY));
        else
            isNextToBound = true;
        if(endY < GameContainer.getGameHeight())//add bottom line
            lines.add(new Line(startX, startY, endX, startY));
        else
            isNextToBound = true;
    }

    public boolean isEmpty()
    {
        return isEmpty;
    }

    public void setEmpty(boolean empty)
    {
        isEmpty = empty;
    }

    public void addFigure(Figure figure) throws Exception
    {
        if(isEmpty)
        {
            figure.setCenterX(centerX);
            figure.setCenterY(centerY);
            figureInside = figure;
            isEmpty = false;
        }
        else throw new BlockIsNotEmptyException();
    }

    public void clearFigure()
    {
        if(!isEmpty)
        {
            figureInside = null;
            isEmpty = true;
        }
    }

    public boolean isNextToBound()
    {
        return isNextToBound;
    }

    public void setNextToBound(boolean nextToBound)
    {
        isNextToBound = nextToBound;
    }

    public FigureType getFigureInsideType()
    {
        return isEmpty ? FigureType.NONE : figureInside.getFigureType();
    }

    public float[] getStartCoords()
    {
        return new float[] {startX, startY};
    }

    public float[] getEndCoords()
    {
        return new float[] {endX, endY};
    }

    public float[] getAllCoords()
    {
        return new float[] { startX, startY, endX, endY };
    }
}
