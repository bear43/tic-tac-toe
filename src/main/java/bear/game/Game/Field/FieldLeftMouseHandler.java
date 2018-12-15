package bear.game.Game.Field;

import bear.game.Game.Event.EventHandler;
import bear.game.Game.Event.Mouse.MouseButton;
import bear.game.Game.Exceptions.BlockIsNotEmptyException;
import bear.game.Game.Figure.*;
import bear.game.Game.FigureContainer;
import bear.game.Game.GameContainer;

import java.util.List;

public class FieldLeftMouseHandler implements EventHandler
{
    @Override
    public void handle(Object[] args)
    {
        if(args != null && args.length == 4 && args[0] == MouseButton.LEFT)
        {
            try
            {
                List<Block> blocks = Field.getFieldInstance().getAllBlocks();
                Block winner = blocks.get(0);
                for (Block block : blocks)
                {
                    if (Math.abs(winner.getCenterX() - (float) args[1]) > Math.abs(block.getCenterX() - (float) args[1]) ||
                            Math.abs(winner.getCenterY() - (float) args[2]) > Math.abs(block.getCenterY() - (float) args[2]))
                        winner = block;
                }
                if(winner.isEmpty())
                {
                    Figure currentFigure = (Figure) args[3];
                    currentFigure.setCenterX(winner.getCenterX());
                    currentFigure.setCenterY(winner.getCenterY());
                    if (currentFigure.getFigureType() == FigureType.X)
                    {
                        winner.addFigure(currentFigure);
                        currentFigure = new Ou(winner.getCenterX(), winner.getCenterY(),
                                GameContainer.getGameWidth() / Field.getFieldInstance().getCellCount());
                    }
                    else
                    {
                        winner.addFigure(currentFigure);
                        currentFigure = new Hach(winner.getCenterX(), winner.getCenterY(),
                                GameContainer.getGameWidth() / Field.getFieldInstance().getCellCount(),
                                GameContainer.getGameHeight() / Field.getFieldInstance().getCellCount());
                    }
                    FigureContainer.getDefFigures().set(0, currentFigure);
                    Field.getFieldInstance().checkBlocks();
                }
                else
                    throw new BlockIsNotEmptyException();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName();
    }
}
