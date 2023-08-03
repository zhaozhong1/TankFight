import java.util.Vector;

public class Shot implements Runnable {

    int x, y;
    int direct;
    boolean isFirst = false;
    int speed = 20;
    boolean isLive = true;

    Vector<Tank> allTank = new Vector<>();

    public Shot(boolean isLive,boolean isFirst){
        this.isLive = isLive;
        this.isFirst = isFirst;
    }

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        allTank  = TankVector.getTankVector();
    }

    public void getAllTank(TankGame.MapPanel mp){
        allTank.add(mp.getMytank());
        allTank.addAll(mp.getEnemyTankVector());
    }

    public void run() {
        while (true) {
            switch (direct) {
                case 0 ->//up
                        y -= speed;
                case 1 ->//down
                        y += speed;
                case 2 ->//left
                        x -= speed;
                case 3 ->//right
                        x += speed;
            }
            if(!(x+speed>=TankGame.LEFT_BORDER&&x+speed<=TankGame.RIGHT_BORDER&&y+speed>=TankGame.UP_BORDER&&y+speed<=TankGame.DOWN_BORDER)){
                isLive = false;
                break;
            }

            for(Tank tank : allTank){
                if(hitTank(tank)){
                    isLive = false;
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean hitTank(Tank tank){
        HitBox hitBox = tank.getHitBox();
        int shotX = x+5,shotY = y+5;
        if(shotX <= hitBox.getX2()&&shotY<= hitBox.getY2()&&shotX>= hitBox.getX1()&&shotY>= hitBox.getY1()){
            tank.shotted();
            return true;
        }
        return false;
    }
}
