package sweper;

class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;
    void start(){
        flagMap= new Matrix(Box.Closed);
        countOfClosedBoxes=Ranges.getSize().x*Ranges.getSize().y;
    }
    Box get (Coord coord)
    {
        return flagMap.get(coord);
    }
    public void setOpenedToBox(Coord coord)
    {
        flagMap.set(coord, Box.Opened);
        countOfClosedBoxes--;
    }
    public void setFlagToBox(Coord coord)
    {
        flagMap.set(coord, Box.Flag);
    }
    public void setClosedToBox(Coord coord)
    {
        flagMap.set(coord, Box.Closed);
    }
    public void toggleFlagToBox(Coord coord)
    {
        switch (flagMap.get(coord))
        {
            case Flag -> {setClosedToBox(coord); break;}
            case Closed -> {setFlagToBox(coord);break;}
            case Opened ->{break;}
        }

    }
    int getCountOfClosedBoxes()
    {
        return countOfClosedBoxes;
    }
    void setBombedToBox(Coord coord)
    {
        flagMap.set (coord, Box.MineR);
    }

    void setOpenedToCloseMineBox(Coord coord)
    {
        if (flagMap.get(coord)==Box.Closed)
        {
            flagMap.set(coord, Box.Opened);
        }
    }

    void setNoMineToFlagBox(Coord coord)
    {
        if (flagMap.get(coord)==Box.Flag)
        {
            flagMap.set(coord, Box.MineK);
        }
    }
}
