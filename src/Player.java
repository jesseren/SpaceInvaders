import java.awt.*;

public class Player extends GameObject{

    public Player (float x, float y, ID id, Game game, Image[] image, int width, int height, Handler handler){
        super(x,y,id, game, image, width, height, handler);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,22,32);
    }

    public void tick(){
        x += velX;
        y += velY;
        x = Game.clamp(x, game.getInsets().left, gameWidth-22);
        y = Game.clamp(y, game.getInsets().top, gameHeight-32);
    }

    public void render(Graphics g){
        g.drawImage(image[0], (int) x, (int) y, 22, 32, null);
    }
}
