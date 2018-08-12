package funny;

import java.util.Random;

/**
 * 通过蒙特卡洛算法求pi
 * Created by Administrator on 2018/8/12 0012.
 */
public class PiByMonteCarlo {
    public static double PiByMonteCarlo(long times) {
        Random random = new Random();
        double all=0;
        double circle=0;
        for(long i=0;i<times;i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if(Math.pow(x-0.5,2)+Math.pow(y-0.5,2)<=0.25){
                circle+=1;
            }
            all+=1;

        }
        return 4.0*circle/all;
    }

    public static void main(String[] args) {
        System.out.println(PiByMonteCarlo(10000000000L));
    }
}
