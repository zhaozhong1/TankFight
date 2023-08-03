import java.util.Vector;

public class TankVector {

    public static TankGame tg;
    public static Vector<Tank> tankVector = new Vector<>();

    public static void getAllTank(TankGame tg){
        tankVector.add(tg.getMp().getMytank());
        tankVector.addAll(tg.getMp().getEnemyTankVector());
    }

    public static Vector<Tank> getTankVector(){
        return tankVector;
    }

}
