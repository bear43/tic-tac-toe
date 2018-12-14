package bear.game.Game.Figure;

public abstract class FigureSkeleton implements Figure
{
    protected float centerX;

    protected float centerY;

    public float getCenterX()
    {
        return centerX;
    }

    public void setCenterX(float centerX)
    {
        this.centerX = centerX;
    }

    public float getCenterY()
    {
        return centerY;
    }

    public void setCenterY(float centerY)
    {
        this.centerY = centerY;
    }

    public FigureSkeleton(float centerX, float centerY)
    {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    @Override
    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
