public class EnemyTank extends Tank implements Runnable{
    public EnemyTank(int HP, int type, int x, int y, int direct, int speed) {
        super(HP, type, x, y, direct, speed);
    }

    @Override
    public void run() {
        while(this.isLive()){
            if(!(getX()>TankGame.LEFT_BORDER))setDirect(3);
            else if(!(getX()<TankGame.RIGHT_BORDER))setDirect(2);

            move();
            try {
                shotTank();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void move(){
        if(getDirect() == 2){
            decreaseX();
        }else if(getDirect() == 3){
            increaseX();
        }else if(getDirect() == 1){
            increaseY();
        }else if(getDirect() == 0){
            decreaseY();
        }
    }



}

class EnemyTank1 extends EnemyTank{
    public EnemyTank1(){
        super(1,1,300,300,2,10);
    }
}
class EnemyTank2 extends EnemyTank{
    public EnemyTank2(){
        super(2,2,300,200,2,20);
    }
}
class EnemyTank3 extends EnemyTank{
    public EnemyTank3(){
        super(3,3,300,100,2,5);
    }
}


