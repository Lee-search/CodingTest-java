package subject;

import java.util.Arrays;

public class PCSTest {

    public static int N, R;
    public static int[] numbers;
    public static boolean[] isSelected;

    public static void main(String[] args) {

        N = 6;
        R = 2;  // nPr

        /* Permutation */
        System.out.println("--- Permutation ---");
        numbers = new int[R];
        isSelected = new boolean[N];
        permutation(0);

        /* Combination */
        System.out.println("--- Combination ---");
        numbers = new int[R];
        combination(0, 0);

        /* Subset */
        System.out.println("--- SubSet ---");
        isSelected = new boolean[N];
        generateSubSet(0);
    } // end of main

    public static void permutation(int cnt) {

        if(cnt == R) {
            // 기저조건
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for(int i = 0; i < N; i++) {
            if(isSelected[i]) continue;

            numbers[cnt] = i + 1;
            isSelected[i] = true;
            permutation(cnt + 1);
            isSelected[i] = false;
        }
    } // end of permutation

    public static void combination(int cnt, int start) {

        if(cnt == R) {
            // 기저조건
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for(int i = start; i < N; i++) {

            numbers[cnt] = i + 1;
            combination(cnt + 1, i + 1);
        }
    } // end of combination

    public static void generateSubSet(int cnt) {

        if(cnt == N) {
            // 기저조건
            for(int i = 0; i < N; i++) {
                if(isSelected[i]) System.out.print(i + 1 + "\t");
            }
            System.out.println();

            return;
        }

        isSelected[cnt] = true;
        generateSubSet(cnt + 1);
        isSelected[cnt] = false;
        generateSubSet(cnt + 1);
    } // end of subset
}

