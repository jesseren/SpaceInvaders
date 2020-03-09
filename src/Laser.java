import java.awt.*;

public class Laser extends GameObject{

    public Laser (float x, float y, ID id, Game game, Image[] image, int width, int height, Handler handler){
        super(x,y,id, game, image, width, height, handler);
        this.setVelY(-5);
        this.setVelX(0);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x + 52*24/128, (int) y, (int) Math.floor(24*24/128), 24);
    }

    public void tick(){
        x += velX;
        y += velY;
        if(y <= 0) handler.remove(this);
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.BasicEnemy && this.getBounds().intersects(tempObject.getBounds())
                    || tempObject.getId() == ID.StrongEnemy && this.getBounds().intersects(tempObject.getBounds())
                    || tempObject.getId() == ID.LaserEnemy && this.getBounds().intersects(tempObject.getBounds())){
                handler.remove(this);
                HUD.score += 10;
                tempObject.health--;
            }
        }
    }

    public void render(Graphics g){
        g.drawImage(image[0], (int) x, (int) y, 24, 24, null);
    }
}
