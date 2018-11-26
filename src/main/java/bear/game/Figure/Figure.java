package bear.game.Figure;

import org.newdawn.slick.Graphics;

public interface Figure
{
    FigureType getFigureType();
    void draw(Graphics graphics);
    void flush();
}
