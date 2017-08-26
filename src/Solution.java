public class Solution {
//    public ArrayList<Integer> printListFromTailToHead(i listNode) {
//        ArrayList<Integer> tmpList= new ArrayList();
//        ListNode cur=listNode;
//        while(null!=cur&&null!=cur.next){
//            tmpList.add(cur.val);
//        }
//        int halfLength=(tmpList.size()+1)/2;
//        int tmp;
//        for(int i=0;i<halfLength;i++){
//            tmp=tmpList.get(i);
//            tmpList.set(i,tmpList.get(tmpList.size()-i));
//            tmpList.set(tmpList.size()-i,tmp);
//        }
//        return tmpList;
//
//    }
//    public static void main(String[] args){
//        System.out.println(new Solution().replaceSpace(new StringBuffer("a bbb c")));
//    }
}