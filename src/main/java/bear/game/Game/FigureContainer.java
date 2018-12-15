package bear.game.Game;

import bear.game.Game.Exceptions.FigureLimitExceededException;
import bear.game.Game.Exceptions.FigureNotFoundException;
import bear.game.Game.Figure.Figure;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FigureContainer
{
    private List<Figure> figures;

    private static List<Figure> defFigures = new CopyOnWriteArrayList<>();

    private static List<List<Figure>> figuresList = new CopyOnWriteArrayList<>();

    private static List<Shape> shapeList = new CopyOnWriteArrayList<>();

    private static final int MAX_FIGURES = 9;

    private int maxFigures = MAX_FIGURES;

    static
    {
        figuresList.add(defFigures);
    }

    public FigureContainer()
    {
        this(new CopyOnWriteArrayList<>());
    }

    public FigureContainer(List<Figure> figures)
    {
        this.figures = figures;
        figuresList.add(figures);
    }

    public void addFigure(Figure figure) throws FigureLimitExceededException
    {
        if(figures.size() < maxFigures)
            figures.add(figure);
        else throw new FigureLimitExceededException();
    }

    public static void addDefaultFigure(Figure figure)
    {
        defFigures.add(figure);
    }

    public static void removeDefaultFigure(Figure figure)
    {
        defFigures.remove(figure);
    }

    public static void removeDefaultFigure(int index)
    {
        defFigures.remove(index);
    }

    public void removeFigure(Figure figure) throws FigureNotFoundException
    {
        if(!figures.remove(figure)) throw  new FigureNotFoundException();
    }

    public void removeFigure(int index)
    {
        if(figures.remove(index) == null) throw new IndexOutOfBoundsException();
    }

    public List<Figure> getFigures()
    {
        return figures;
    }

    public void setFigures(List<Figure> figures)
    {
        this.figures = figures;
    }

    public static List<List<Figure>> getFiguresList()
    {
        return figuresList;
    }

    public static void setFiguresList(List<List<Figure>> figuresList)
    {
        FigureContainer.figuresList = figuresList;
    }

    public static int getMaxFigures()
    {
        return MAX_FIGURES;
    }

    public void setMaxFigures(int maxFigures)
    {
        this.maxFigures = maxFigures;
    }

    public static void drawAllFigures(Graphics graphics)
    {
        figuresList.forEach((x) -> x.forEach((y) -> y.draw(graphics)));
        shapeList.forEach(graphics::draw);
    }

    public static List<Figure> getDefFigures()
    {
        return defFigures;
    }

    public static void setDefFigures(List<Figure> defFigures)
    {
        FigureContainer.defFigures = defFigures;
    }

    public static void addShapeFigure(Shape figure)
    {
        shapeList.add(figure);
    }

    public static boolean removeShapeFigure(Shape figure)
    {
        return shapeList.remove(figure);
    }

    public static boolean removeShapeFigure(int index)
    {
        return shapeList.remove(index) != null;
    }
}
