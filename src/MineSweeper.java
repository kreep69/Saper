import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.Objects;



import sweper.Box;
import sweper.Coord;
import sweper.Game;
import sweper.Ranges;

public class MineSweeper extends JFrame {
    private Game game;
    private JPanel panel;

    public static void main(String[] args) {
        new MineSweeper();

    }
    private MineSweeper(){
        game= new Game(Constants.COLS, Constants.ROWS, Constants.MINES);
        game.start();
        setImages();
        initPanel();
        initFrame();
    }
    private void initPanel(){
        panel= new JPanel()
        {
            @Override
            protected void paintComponent (Graphics g)
            {
                super.paintComponent(g);
                for (Coord coord: Ranges.getAllCoords())
                {

                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x*Constants.IMAGE_SIZE,coord.y*Constants.IMAGE_SIZE,this );
                }


            }
        };
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x*Constants.IMAGE_SIZE,
                Ranges.getSize().y*Constants.IMAGE_SIZE));
        add (panel);
    }
    private void initFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Сапёр");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("Icon"));
        pack();
    }
    private void setImages(){
        for (Box box: Box.values())
        {
            box.image = getImage(box.name());
        }
    }
    private Image getImage(String name)
    {
        String filename ="image/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}
