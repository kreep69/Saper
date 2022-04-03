package sweper;

public class Mine {
    private Matrix mineMap;
    private int totalMine;

    public Mine(int totalMine) {
        this.totalMine = totalMine;
        fixMineCount();
    }
    void start ()
    {

        mineMap=new Matrix(Box.Zero);
        for (int j  = 0; j<totalMine;j++) {
            placeMine();
        }
    }
    Box get (Coord coord)
    {
        return mineMap.get(coord);
    }

    private void fixMineCount()
    {
        int maxMines=Ranges.getSize().x*Ranges.getSize().y/2;
        if (totalMine>maxMines)
        {
            totalMine=maxMines;
        }
    }
    private void placeMine()
    {
        while (true)
        {
            Coord coord = Ranges.getRandomCoord();
            if (Box.Mine==mineMap.get(coord))
            {
                continue;
            }

            mineMap.set (coord , Box.Mine);
            incNumbersAroundMine(coord);
            break;

        }


    }
    private void incNumbersAroundMine (Coord coord)
    {
        for (Coord around: Ranges.getCoordsAround(coord))
        {
            if (Box.Mine!= mineMap.get(around))
            {
                mineMap.set (around, mineMap.get(around).getNextNumberBox());

            }

        }
    }
    int getTotalMines()
    {
        return totalMine;
    }
}
