public class EnemyTank extends Tank{
    public EnemyTank(int HP, int type, int x, int y, int direct, int speed) {
        super(HP, type, x, y, direct, speed);
    }
}

class EnemyTank1 extends EnemyTank{
    public EnemyTank1(){
        super(1,1,300,300,0,1);
    }
}
class EnemyTank2 extends EnemyTank{
    public EnemyTank2(){
        super(2,2,300,200,0,1);
    }
}
class EnemyTank3 extends EnemyTank{
    public EnemyTank3(){
        super(3,3,300,100,0,1);
    }
}


