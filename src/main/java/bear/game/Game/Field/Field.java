package bear.game.Game.Field;

import bear.game.Game.Figure.FigureType;
import bear.game.Game.FigureContainer;
import bear.game.Game.Interface.Drawable;
import bear.game.Game.Exceptions.NotProperpFieldSizeException;
import bear.game.Game.Figure.Block;
import bear.game.Game.GameContainer;
import bear.game.Game.Interface.Updateable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Field implements Drawable, Updateable
{
    private static Field fieldInstance = new Field();

    private static final int DEFAULT_CELL_COUNT = 9;

    private int cellCount = DEFAULT_CELL_COUNT;

    private int cellsPerAxis = (int)Math.sqrt(cellCount);

    private List<List<Block>> blocks = new CopyOnWriteArrayList<>();

    protected Field()
    {
        update();
    }

    protected Field(int cellCount) throws NotProperpFieldSizeException
    {
        //if(!((cellCount != 0) && ((cellCount & (cellCount - 1)) == 0))) throw new NotProperpFieldSizeException();
        if(!(cellCount % 2 == 0)) throw new NotProperpFieldSizeException();
        this.cellCount = cellCount;
        cellsPerAxis = (int)Math.sqrt(cellCount);
        update();
    }

    public static Field getFieldInstance()
    {
        return fieldInstance;
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
        List<Block> currentLine;
        int blocksOnAxis = (int)Math.sqrt(cellCount);
        float blockPixelsX = GameContainer.getGameWidth()/blocksOnAxis;
        float blockPixelsY = GameContainer.getGameHeight()/blocksOnAxis;
        float centerOffsetX = blockPixelsX/2.0f;
        float centerOffsetY = blockPixelsY/2.0f;
        for (float j = 0; j < blockPixelsY * blocksOnAxis; j += blockPixelsY)
        {
            currentLine = new CopyOnWriteArrayList<>();
            for(float i = 0; i < blockPixelsX*blocksOnAxis; i += blockPixelsX)
                currentLine.add(new Block(i + centerOffsetX, j + centerOffsetY, blockPixelsX, blockPixelsY));
            blocks.add(currentLine);
        }
    }

    public void draw(Graphics grpaphics)
    {
        for(List<Block> blockLine : blocks)
            for(Block block : blockLine)
                block.draw(grpaphics);
    }

    public List<List<Block>> getBlocks()
    {
        return blocks;
    }

    public List<Block> getAllBlocks()
    {
        List<Block> allBlocks = new CopyOnWriteArrayList<>();
        for(List<Block> blocks : this.blocks)
            allBlocks.addAll(blocks);
        return allBlocks;
    }

    public List<Block> getBlockLine(int index)
    {
        return blocks.get(index);
    }

    /**
     * Check block on win
     * @return [line][index] of winner
     */
    private boolean checkBlocks(int[][] fieldMap, int i, int j, FigureType parentType)
    {
        if(parentType == FigureType.NONE) return false;
        boolean[] flag = new boolean[3];
        if(i >= cellsPerAxis || j >= cellsPerAxis) return true;
        if(i < 0 || j < 0) return false;
        FigureType type = blocks.get(i).get(j).getFigureInsideType();
        if(type == parentType)
        {
            fieldMap[i][j] = 1;
            flag[0] = checkBlocks(fieldMap, i+1, j, type);
            flag[1] = checkBlocks(fieldMap, i, j+1, type);
            flag[2] = checkBlocks(fieldMap, i+1, j+1, type);
        }
        return flag[0] || flag[1] || flag[2];
    }

    public void checkBlocks()
    {
        if(blocks.size() != 0)
        {
            int[][] fieldMap = new int[cellsPerAxis][cellsPerAxis];
            int[] firstIndex = {-1, -1}, lastIndex = {-1, -1};
            if (checkBlocks(fieldMap, 0, 0, blocks.get(0).get(0).getFigureInsideType()))
            {
                for(int i = 0; i < cellsPerAxis; i++)
                {
                    for (int j = 0; j < cellsPerAxis; j++)
                    {
                        if (fieldMap[i][j] == 1)
                        {
                            if (firstIndex[0] == -1)
                            {
                                firstIndex[0] = i;
                                firstIndex[1] = j;
                            }
                            lastIndex[0] = i;
                            lastIndex[1] = j;
                        }
                    }
                }
                Block startBlock = blocks.get(firstIndex[0]).get(firstIndex[1]),
                        endBlock = blocks.get(lastIndex[0]).get(lastIndex[1]);
                float[] startCoords = startBlock.getStartCoords();
                float[] endCoords = endBlock.getEndCoords();
                FigureContainer.addShapeFigure(new Line(startCoords[0], startBlock.getCenterY(), endCoords[0], endBlock.getCenterY()));
                System.out.println("We have a winner!");
            }
        }
    }
}
