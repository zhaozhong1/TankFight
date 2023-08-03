import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Iterator;

public class TankGame extends JFrame {

    public static final int RIGHT_BORDER = 1000;
    public static final int LEFT_BORDER = 0;
    public static final int DOWN_BORDER = 1000;
    public static final int UP_BORDER = 0;

    private MapPanel mp;

    public TankGame() {
        this.setSize(RIGHT_BORDER, DOWN_BORDER);
        mp = new MapPanel();
        this.add(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);
        Thread thread = new Thread(mp);
        thread.setDaemon(true);
        thread.start();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        TankGame tg = new TankGame();
    }

    class MapPanel extends JPanel implements KeyListener,Runnable{
        private Tank mytank = new MyTank(100, 1, 100, 100, 0, 5);

        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.repaint();
            }

        }

        private Tank[] enemytank = new EnemyTank[3];

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, TankGame.RIGHT_BORDER,TankGame.DOWN_BORDER );
            drawMyTank(mytank.getX(), mytank.getY(), g, mytank.getDirect(), 0);
            Iterator<Shot> iterator = mytank.shotVector.iterator();
            while (iterator.hasNext()) {
                Shot next =  iterator.next();
                if(next!=null&&!next.isFirst&&next.isLive){
                    g.setColor(Color.WHITE);
                    g.fillOval(next.x,next.y,10,10);
                }
            }
            g.setColor(Color.yellow);
            for (int i = 0; i < 3; i++) {
                enemytank[i] = new EnemyTank1();
                drawEnemyTank(enemytank[i].getX(), enemytank[i].getY() + i * 100, g, enemytank[i].getDirect(), enemytank[i].getType());
            }

        }

        public void drawMyTank(int x, int y, Graphics g, int direct, int type) {

            switch (type) {
                case 0: //myTank
                    g.setColor(Color.cyan);
                    break;
                case 1:
                    g.setColor(Color.yellow);
                    break;
            }
            switch (direct) {
                case 0://上
                    g.fill3DRect(x, y, 10, 40, false);//左轮
                    g.fill3DRect(x + 10, y + 5, 20, 30, false);//中间
                    g.fill3DRect(x + 30, y, 10, 40, false);//右轮
                    g.fillOval(x + 11, y + 11, 15, 15);//炮台
                    g.drawLine(x + 20, y + 20, x + 20, y - 20);//炮管
                    break;
                case 1://下
                    g.fill3DRect(x, y, 10, 40, false);//左轮
                    g.fill3DRect(x + 10, y + 5, 20, 30, false);//中间
                    g.fill3DRect(x + 30, y, 10, 40, false);//右轮
                    g.fillOval(x + 11, y + 11, 15, 15);//炮台
                    g.drawLine(x + 20, y + 20, x + 20, y + 60);//炮管
                    break;
                case 2://左
                    g.fill3DRect(x, y, 40, 10, false);//左轮
                    g.fill3DRect(x + 5, y + 10, 30, 20, false);//中间
                    g.fill3DRect(x, y + 30, 40, 10, false);//右轮
                    g.fillOval(x + 11, y + 11, 15, 15);//炮台
                    g.drawLine(x + 20, y + 20, x - 20, y + 20);//炮管
                    break;
                case 3://右
                    g.fill3DRect(x, y + 30, 40, 10, false);//左轮
                    g.fill3DRect(x + 5, y + 10, 30, 20, false);//中间
                    g.fill3DRect(x, y, 40, 10, false);//右轮
                    g.fillOval(x + 11, y + 11, 15, 15);//炮台
                    g.drawLine(x + 20, y + 20, x + 60, y + 20);//炮管
            }
        }

        public void drawEnemyTank(int x, int y, Graphics g, int direct, int type) {
            g.fill3DRect(x, y, 10, 40, false);//左轮
            g.fill3DRect(x + 10, y + 5, 20, 30, false);//中间
            g.fill3DRect(x + 30, y, 10, 40, false);//右轮
            g.fillOval(x + 11, y + 11, 15, 15);//炮台
            g.drawLine(x + 20, y + 20, x + 20, y + 60);//炮管
        }

        public void drawBullet(int x, int y, Graphics g, int direct) {

        }


        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_UP) {
                mytank.decreaseY();
                mytank.setDirect(0);
                System.out.println(mytank.getX()+" "+mytank.getY());
            } else if (keyCode == KeyEvent.VK_DOWN) {
                mytank.increaseY();
                mytank.setDirect(1);
                System.out.println(mytank.getX()+" "+mytank.getY());
            } else if (keyCode == KeyEvent.VK_LEFT) {
                mytank.decreaseX();
                mytank.setDirect(2);
                System.out.println(mytank.getX()+" "+mytank.getY());
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                mytank.increaseX();
                mytank.setDirect(3);
                System.out.println(mytank.getX()+" "+mytank.getY());
            }
            if(keyCode == KeyEvent.VK_SPACE){
                mytank.shotTank();
                System.out.println("SHOOOOOOOOT!");
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }


    }

    class TankMove implements Runnable {
        public void run() {

        }
    }

}
