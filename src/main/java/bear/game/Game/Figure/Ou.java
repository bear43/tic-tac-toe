package bear.game.Game.Figure;

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

    public Ou(float centerX, float centerY)
    {
        this(centerX, centerY, DEFAULT_RADIUS);
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
        update();
        graphics.draw(circle);
    }

    @Override
    public void update()
    {
        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
        circle.setRadius(radius);
    }

    @Override
    public float getDefaultSize()
    {
        return DEFAULT_RADIUS;
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
