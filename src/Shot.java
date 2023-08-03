public class Shot implements Runnable {

    int x, y;
    int direct;
    boolean isFirst = false;
    int speed = 10;
    boolean isLive = true;
    public Shot(boolean isLive,boolean isFirst){
        this.isLive = isLive;
        this.isFirst = isFirst;
    }

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public void run() {
        System.out.println("创建一颗子弹");
        while (true) {
            System.out.println("x = "+x+" y = "+y);
            switch (direct) {
                case 0://up
                    y -= speed;
                    break;
                case 1://down
                    y += speed;
                    break;
                case 2://left
                    x -= speed;
                    break;
                case 3://right
                    x += speed;
                    break;
            }
            if(!(x+speed>=TankGame.LEFT_BORDER&&x+speed<=TankGame.RIGHT_BORDER&&y+speed>=TankGame.UP_BORDER&&y+speed<=TankGame.DOWN_BORDER)){
                isLive = false;
                break;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
