package bear.game.Game.Figure;

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

    private static final float DEFAULT_SIZE = 20.0f;

    private float size = DEFAULT_SIZE;

    private float halfSize;

    private List<Line> lines = new CopyOnWriteArrayList<>();

    public Block(float centerX, float centerY)
    {
        super(centerX, centerY);
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
    }

    @Override
    public void update()
    {
        halfSize = size/2.0f;
        if(lines.size() != 0) lines.clear();
        if(centerX-halfSize > 0.0f)//Add left line
            lines.add(new Line(centerX-halfSize, centerY-halfSize, centerX-halfSize, centerY+halfSize));
        if(centerX+halfSize < GameContainer.getGameWidth())
            lines.add(new Line(centerX+halfSize, centerY-halfSize, centerX+halfSize, centerY+halfSize));//add right line
        if(centerY-halfSize > 0.0f)//add upper line
            lines.add(new Line(centerX-halfSize, centerY+halfSize, centerX+halfSize, centerY+halfSize));
        if(centerY+halfSize < GameContainer.getGameHeight())//add bottom line
            lines.add(new Line(centerX-halfSize, centerY-halfSize, centerX+halfSize, centerY-halfSize));
    }
}
