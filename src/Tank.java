import java.awt.*;
import java.util.Vector;

/*
所有坦克的父类
 */
public abstract class Tank {
    private int HP;
    private int type;

    private int speed;
    private int x;
    private int y;
    private int direct;

    Vector<Shot> shotVector = new Vector<>();
    Shot shot = null;

    public Tank(){
    }

    public Tank(int HP, int type, int x, int y, int direct,int speed) {
        this.HP = HP;
        this.type = type;
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.speed = speed;
        this.shotVector.add(new Shot(false,true));
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getSize() {
        return type;
    }

    public void setSize(int size) {
        this.type = size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void decreaseX(){
        x-=speed;
    }
    public void increaseX(){
        x+=speed;
    }
    public void decreaseY(){
        y-=speed;
    }
    public void increaseY(){
        y+=speed;
    }

    public void shotTank(){
        switch (getDirect()){
            case 0://up
                shot = new Shot(getX()+15,getY()-35,getDirect());
                System.out.println("UP now x= "+x+" y= "+y);
                break;
            case 1://down
                shot = new Shot(getX()+15,getY()+55,getDirect());
                System.out.println("DOWN now x= "+x+" y= "+y);
                break;
            case 2://left
                shot = new Shot(getX()-55,getY()+15,getDirect());
                System.out.println("LEFT now x= "+x+" y= "+y);
                break;
            case 3://right
                shot = new Shot(getX()+55,getY()+15,getDirect());
                System.out.println("RIGHT now x= "+x+" y= "+y);
                break;
        }
        shotVector.add(shot);
        new Thread(shot).start();
        if(!shot.isLive&&!shot.isFirst)shotVector.remove(shot);
    }

}
