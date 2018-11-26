package bear.game.Figure;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class Ou extends FigureSkeleton
{
    private static final float DEFAULT_RADIUS = 10.0f;

    private float radius = DEFAULT_RADIUS;

    private Circle circle;

    public Ou(float radius)
    {
        this(0.0f, 0.0f, radius);
    }

    public Ou(float centerX, float centerY, float radius)
    {
        super(centerX, centerY);
        this.radius = radius;
        this.circle = new Circle(centerX, centerY, radius);
    }

    public Ou()
    {
        this(0.0f, 0.0f, DEFAULT_RADIUS);
    }

    @Override
    public FigureType getFigureType()
    {
        return FigureType.O;
    }

    @Override
    public void draw(Graphics graphics)
    {
        flush();
        graphics.draw(circle);
    }

    @Override
    public void flush()
    {
        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
        circle.setRadius(radius);
    }

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }
}
