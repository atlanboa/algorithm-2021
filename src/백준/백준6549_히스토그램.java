package 백준;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준6549_히스토그램 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = null;

    public static void main(String[] args) throws Exception {

        while (true) {
            int n;
            long max;
            int[] histogram, left, right;

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            histogram = new int[n];
            if (n == 0)
                break;
            for (int i = 0; i < n; i++) {
                histogram[i] = Integer.parseInt(st.nextToken());
            }
            left = getLeft(histogram, n);
            right = getRight(histogram, n);
            max = getMaxArea(left, right, histogram, n);
            System.out.println(max);
        }

    }

    private static long getMaxArea(int[] left, int[] right, int[] histogram, int n) {
        long max = Integer.MIN_VALUE;
        long cur;
        for (int i = 0; i < n; i++) {
            cur = (long) (right[i] - left[i] - 1) * histogram[i];
            max = Math.max(max, cur);
        }
        return (max);
    }

    private static int[] getRight(int[] histogram, int n) {
        int[] right = new int[n];
        Stack<Bar> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            if (stack.isEmpty() || stack.peek().height < histogram[i]) {
                right[i] = i + 1;
                stack.push(new Bar(histogram[i], i));
            } else {
                while (true) {
                    if (!stack.isEmpty() && stack.peek().height >= histogram[i])
                        stack.pop();
                    else {
                        right[i] = stack.isEmpty() ? n : stack.peek().idx;
                        stack.push(new Bar(histogram[i], i));
                        break;
                    }
                }
            }
        }
        return (right);
    }

    private static int[] getLeft(int[] histogram, int n) {

        int[] left = new int[n];
        Stack<Bar> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (stack.isEmpty() || stack.peek().height < histogram[i]) {
                left[i] = i - 1;
                stack.push(new Bar(histogram[i], i));
            } else {
                while (true) {
                    if (!stack.isEmpty() && stack.peek().height >= histogram[i])
                        stack.pop();
                    else {
                        left[i] = stack.isEmpty() ? -1 : stack.peek().idx;
                        stack.push(new Bar(histogram[i], i));
                        break;
                    }
                }
            }
        }
        return (left);
    }


    public static class Bar {
        int height, idx;

        public Bar(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }
}
