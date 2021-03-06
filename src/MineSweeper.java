import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.Objects;



import sweper.Box;
import sweper.Coord;
import sweper.Game;
import sweper.Ranges;

public class MineSweeper extends JFrame {
    private Game game;
    private JPanel panel;
    private JLabel label;


    public static void main(String[] args) {
        new MineSweeper();

    }
    private MineSweeper(){
        game= new Game(Constants.COLS, Constants.ROWS, Constants.MINES);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();

    }
    private void initLabel()
    {
        label = new JLabel("Welcome");
        add (label, BorderLayout.SOUTH);
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
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX()/Constants.IMAGE_SIZE;
                int y = e.getY()/Constants.IMAGE_SIZE;
                Coord coord = new Coord(x,y);
                if (e.getButton()== MouseEvent.BUTTON1)
                {
                    game.pressLeftButton(coord);
                }
                if (e.getButton()== MouseEvent.BUTTON3)
                {
                    game.pressRightButton(coord);
                }
                if (e.getButton()== MouseEvent.BUTTON2)
                {
                    game.start();
                }
                label.setText(getMessage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x*Constants.IMAGE_SIZE,
                Ranges.getSize().y*Constants.IMAGE_SIZE));
        add (panel);
    }

    private String getMessage()
    {
        switch (game.getState())
        {
            case Played -> {return "Think twice";}
            case Winner -> {return "Congratulations";}
            case Bombed -> {return "Nice try";}
            default -> {return "Welcome";}
        }
    }

    private void initFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("??????????");
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("Icon"));
        pack();
        setLocationRelativeTo(null);
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
