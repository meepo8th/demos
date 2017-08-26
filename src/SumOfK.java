import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SumOfK {

    public static Integer chooseBestSum(int t, int k, List<Integer> ls) {
        Integer rtn = null;
        if (null != ls && ls.size() >= k) {
            Collections.sort(ls);
            int sum=0;
            int lastSum=0;
            for (int i = 0; i < ls.size()-k; i++) {
                for(int j=0;j<k;j++){
                    sum+=ls.get(i+j);
                }
                if(sum>t){
                    break;
                }else{
                    lastSum=sum;
                }
            }
            rtn=lastSum;
        }
        return rtn;
    }

    /**
     * 找出与平均数最接近的位置
     *
     * @param ls
     * @param averageNum
     * @return
     */
    public static int findMidPos(List<Integer> ls, int averageNum) {
        int minPos = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < ls.size(); i++) {
            if (averageNum - ls.get(i) < min && averageNum - ls.get(i) >= 0) {
                min = averageNum - ls.get(i);
                minPos = i;
            } else {
                break;
            }
        }
        return minPos;
    }

    public static void main(String args[]) {
        System.out.println(chooseBestSum(9, 2, Arrays.asList(new Integer[]{3, 4, 6, 9, 8, 2, 0, 1})));
    }
}
