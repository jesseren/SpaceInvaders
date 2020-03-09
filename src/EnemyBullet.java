import java.awt.*;

public class EnemyBullet extends GameObject{
    public EnemyBullet (float x, float y, ID id, Game game, Image[] image, int width, int height, Handler handler){
        super(x,y,id, game, image, width, height, handler);
        this.setVelY(2);
        this.setVelX(0);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 8, 8);
    }

    public void tick(){
        x += velX;
        y += velY;
        if(y >= 480) handler.remove(this);
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player && this.getBounds().intersects(tempObject.getBounds())){
                handler.remove(this);
                HUD.health -= 2;
            }
        }
    }

    public void render(Graphics g){
        g.setColor(Color.white);
        g.fillRect((int)x,(int)y,8,8);
    }
}
