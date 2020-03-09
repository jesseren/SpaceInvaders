import java.awt.*;

public abstract class GameObject {

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public void setGameWidth(int gameWidth) {
        this.gameWidth = gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public void setGameHeight(int gameHeight) {
        this.gameHeight = gameHeight;
    }

    public void tick(){

    }

    public void render(Graphics g){

    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y);
    }

    protected float x;
    protected float y;
    protected float velX, velY;
    protected Image[] image;
    protected ID id;
    protected Game game;
    protected int gameWidth;
    protected int gameHeight;
    protected Handler handler;
    protected int health = 0;

    public GameObject(float x, float y, ID id, Game game, Image[] image,  int gameWidth, int gameHeight, Handler handler){
        this.x = x;
        this.y = y;
        this.id = id;
        this.game = game;
        this.image = image;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.handler = handler;
    }
}
