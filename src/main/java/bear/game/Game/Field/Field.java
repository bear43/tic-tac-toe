package bear.game.Game.Field;

import bear.game.Game.Drawable;
import bear.game.Game.Exceptions.NotProperpFieldSizeException;
import bear.game.Game.Figure.Block;
import bear.game.Game.GameContainer;
import bear.game.Game.Updateable;
import org.newdawn.slick.Graphics;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Field implements Drawable, Updateable
{
    private static final int DEFAULT_CELL_COUNT = 9;

    private int cellCount = DEFAULT_CELL_COUNT;

    private List<Block> blocks = new CopyOnWriteArrayList<>();

    public Field()
    {
        update();
    }

    public Field(int cellCount) throws NotProperpFieldSizeException
    {
        //if(!((cellCount != 0) && ((cellCount & (cellCount - 1)) == 0))) throw new NotProperpFieldSizeException();
        if(!(cellCount % 2 == 0)) throw new NotProperpFieldSizeException();
        this.cellCount = cellCount;
        update();
    }

    public static int getDefaultCellCount()
    {
        return DEFAULT_CELL_COUNT;
    }

    public int getCellCount()
    {
        return cellCount;
    }

    public void setCellCount(int cellCount)
    {
        this.cellCount = cellCount;
    }

    public void update()
    {
        if(blocks.size() != 0) blocks.clear();
        float blockPixels = GameContainer.getGameWidth()*GameContainer.getGameHeight()/cellCount;
        int blocksOnAxis = (int)Math.sqrt(cellCount);
        float blockSideSize = (float)Math.sqrt(blockPixels);
        float centerOffsetX = GameContainer.getGameWidth()/2.0f;
        float centerOffsetY = GameContainer.getGameHeight()/2.0f;
        for(float x = 0; x < blockSideSize*blocksOnAxis; x += blockSideSize)
            for(float y = 0; y < blockSideSize*blocksOnAxis; y+= blockSideSize)
                blocks.add(new Block(x+centerOffsetX, y+centerOffsetY));
    }

    public void draw(Graphics grpaphics)
    {
        for(Block block : blocks)
            block.draw(grpaphics);
    }
}
