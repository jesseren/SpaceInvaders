import java.awt.*;

public class HUD {
    protected static int health = 100;
    protected static double score = 0;
    private Game game;

    public HUD(Game game){
        this.game = game;
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(game.getInsets().left,game.getInsets().top,200,20);
        g.setColor(Color.green);
        g.fillRect(game.getInsets().left,game.getInsets().top, health*2,20);
        g.drawString("Score: " + score, game.getInsets().left,game.getInsets().top+30);
    }
}
