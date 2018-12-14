package bear.game.Game.Figure;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;

public class Hach extends FigureSkeleton
{
    private Line firstLine;

    private Line secondLine;

    private static final float DEFAULT_SIZE = 20.0f;

    private float size = DEFAULT_SIZE;

    private float halfSize = size/2.0f;

    private void getHalfSize()
    {
        halfSize = size/2.0f;
    }

    @Override
    public FigureType getFigureType()
    {
        return FigureType.X;
    }

    @Override
    public void draw(Graphics graphics)
    {
        update();
        graphics.draw(firstLine);
        graphics.draw(secondLine);
    }

    @Override
    public void update()
    {
        getHalfSize();
        firstLine.set(centerX-halfSize, centerY-halfSize, centerX+halfSize, centerY+halfSize);
        secondLine.set(centerX+halfSize, centerY-halfSize, centerX-halfSize, centerY+halfSize);
    }

    @Override
    public float getDefaultSize()
    {
        return DEFAULT_SIZE;
    }

    public Hach()
    {
        this(20.0f);
    }

    public Hach(float size)
    {
        this(0.0f, 0.0f, size);
    }

    public Hach(float centerX, float centerY)
    {
        this(centerX, centerY, DEFAULT_SIZE);
    }

    public Hach(float centerX, float centerY, float size)
    {
        super(centerX, centerY);
        this.size = size;
        this.firstLine = new Line(0.0f, 0.0f, size, size);
        this.secondLine = new Line(size, size, 0.0f, 0.0f);
    }
}
