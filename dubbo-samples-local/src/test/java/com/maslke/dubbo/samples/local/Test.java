package com.maslke.dubbo.samples.local;

import java.util.ArrayList;
import java.util.List;

class Test {

    public void hanota(int[] A, int[] B, int[] C) {
        List<Integer> la = new ArrayList<>();
        List<Integer> lb = new ArrayList<>();
        List<Integer> lc = new ArrayList<>();
        for (int value : A) {
            la.add(value);
        }
        move(la.size(), la, lb, lc);
        C = new int[la.size()];
        for (int i = 0; i < lc.size(); i++) {
            C[i] = lc.get(i);
        }
    }

    private void move(int m, List<Integer> la, List<Integer> lb, List<Integer> lc) {
        if (m == 1) {
            int v = la.get(la.size() - 1);
            lc.add(v);
            la.remove(la.size() - 1);
        } else {
            move(m - 1, la, lc, lb);
            int v = la.get(la.size() - 1);
            lc.add(v);
            la.remove(la.size() - 1);
            move(m - 1, lb, la, lc);
        }
    }

    private void move(int m, String a, String b, String c) {
        if (m == 1) {
            System.out.println("moving "  + " from " + a + " to " + c);
        } else {
            move(m - 1, a, c, b);
            System.out.println("moving from "+ a + " to " + c);
            move(m - 1, b,a, c);
        }
    }

    public static void main(String[] args) {
//        int[] A = new int[3];
//        A[0] = 2;
//        A[1] = 1;
//        A[2] = 0;
//        int[] B = new int[3];
//        int[] C = new int[3];
//        Test tst = new Test();
//        tst.hanota(A, B, C);
        Test test = new Test();
        test.move(15, "A", "B", "C");
    }
}