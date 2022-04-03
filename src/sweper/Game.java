package sweper;

public class Game {


    private Matrix mineMap;
    private Mine mine;
    private Flag flag;
    private  GameState state;
    public GameState getState() {
        return state;
    }




    public Game(int cols, int rows, int mines) {
        Ranges.setSize(new Coord(cols, rows));
        mine=new Mine(mines);
        flag = new Flag();
    }

    public void start()
    {
        mine.start();
        flag.start();
        state = GameState.Played;
    }


    public Box getBox (Coord coord)
    {
        if (flag.get(coord)==Box.Opened)
        {
            return mine.get(coord);
        }
        else return flag.get(coord);

    }
    public void pressLeftButton(Coord coord)
    {
        if (gameOver()) return;
        openBox (coord);
        checkWinner();
    }
    public void pressRightButton(Coord coord)
    {
        if (gameOver()) return;
        flag.toggleFlagToBox(coord);
    }
    private boolean gameOver()
    {
        if (state==GameState.Played) return false;
        start();
        return true;
    }

    private void checkWinner()
    {
        if (state== GameState.Played){
            if (flag.getCountOfClosedBoxes()==mine.getTotalMines())
            {
                state= GameState.Winner;
            }
        }
    }
    private void openBox (Coord coord)
    {
        switch (flag.get(coord))
        {
            case Opened ->{

            }
            case Flag ->{
                return;
            }
            case Closed -> {
                switch (mine.get(coord))
                {
                    case Zero -> {openBoxesAround(coord);return;}
                    case Mine -> {openMines(coord); return;}
                    default -> {flag.setOpenedToBox(coord);return;}
                }
            }
        }
    }

    private void openMines(Coord mined) {
        state= GameState.Bombed;
        flag.setBombedToBox (mined);
        for (Coord coord: Ranges.getAllCoords())
        {
            if (mine.get(coord)==Box.Mine)
            {
                flag.setOpenedToCloseMineBox(coord);
            }
            else
            {
                flag.setNoMineToFlagBox(coord);
            }
        }
    }

    private void openBoxesAround(Coord coord)
    {
        flag.setOpenedToBox(coord);
        for (Coord around: Ranges.getCoordsAround(coord))
        {
            openBox(around);
        }
    }


}
