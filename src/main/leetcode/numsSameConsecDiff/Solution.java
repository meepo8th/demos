package leetcode.numsSameConsecDiff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {


    public int[] numsSameConsecDiff(int N, int K) {

        List<Node<Integer, Integer>>[] lists = new ArrayList[N];
        lists[0] = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lists[0].add(new Node<>(i, i));
        }
        for (int i = 1; i < N; i++) {
            lists[i] = new ArrayList<>();
            for (Node<Integer, Integer> now : lists[i - 1]) {
                if (now.getValue() > 0) {
                    if (now.getKey() + K < 10) {
                        lists[i].add(new Node(now.getKey() + K, now.getValue() * 10 + now.getKey() + K));
                    }
                    if (K > 0 && now.getKey() - K >= 0) {
                        lists[i].add(new Node(now.getKey() - K, now.getValue() * 10 + now.getKey() - K));
                    }
                }
            }
        }
        int i = 0;
        int[] rtnArray = new int[lists[N - 1].size()];
        for (Node<Integer, Integer> node : lists[N - 1]) {
            rtnArray[i++] = node.getValue();
        }
        return rtnArray;
    }

    private class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().numsSameConsecDiff(3, 7)));
        System.out.println(Arrays.toString(new Solution().numsSameConsecDiff(2, 1)));
        System.out.println(Arrays.toString(new Solution().numsSameConsecDiff(1, 6)));
        System.out.println(Arrays.toString(new Solution().numsSameConsecDiff(3, 1)));
    }


}