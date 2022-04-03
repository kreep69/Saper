package sweper;

public class Game {


    Matrix mineMap;
    Mine mine;

    public Game(int cols, int rows, int mines) {
        Ranges.setSize(new Coord(cols, rows));
        mine=new Mine(mines);
    }

    public void start()
    {
        mine.start();
    }


    public Box getBox (Coord coord)
    {
        return mine.get(coord);
    }
}
