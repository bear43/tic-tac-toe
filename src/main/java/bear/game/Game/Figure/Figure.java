package bear.game.Game.Figure;

import bear.game.Game.Drawable;
import bear.game.Game.Updateable;

public interface Figure extends Cloneable, Drawable, Updateable
{
    FigureType getFigureType();
    float getDefaultSize();
    float getCenterX();
    void setCenterX(float centerX);
    float getCenterY();
    void setCenterY(float centerY);
    Object clone();
}
