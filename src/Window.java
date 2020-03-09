import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static final long serialVersionUID = -240840600533728354L;
    private JFrame frame;

    public Window(float width, float height, String title, Game game) {
        this.frame = new JFrame(title);

        frame.setPreferredSize(new Dimension((int) width, (int) height));
        frame.setMaximumSize(new Dimension((int) width, (int) height));
        frame.setMinimumSize(new Dimension((int) width, (int) height));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }

    public Graphics returnGraphics(){
        return frame.getGraphics();
    }
}
