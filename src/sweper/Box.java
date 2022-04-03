package sweper;

public enum Box {
    Zero,
    One,
    Two,
    Three,
    Four,
    Five,
    Six,
    Seven,
    Eight,
    Mine,
    Opened,
    Closed,
    Flag,
    MineR,
    MineK;

    public Object image;

    Box getNextNumberBox ()
    {
        return  Box.values()[this.ordinal() +1];
    }
}
