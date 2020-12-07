package 백준;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준10830_행렬제곱 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {
        int n;
        long b;
        long[][] matrix;
        long[][] result;
        Stack<Integer> stack = new Stack<>();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        b = Long.parseLong(st.nextToken());
        matrix = new long[n][n];
        result = new long[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Long.parseLong(st.nextToken());
                result[i][j] = matrix[i][j];
            }
        }

        while (b > 0)
        {
            if (b % 2 == 1)
                stack.add(1);
            else
                stack.add(0);
            b /= 2;
        }

        stack.pop();

        while (!stack.isEmpty()) {
            long bit = stack.pop();
            result = mul(result, result, n);
            if (bit != 0) {
                result = mul(result, matrix, n);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(result[i][j] % 1000 + " ");
            }
            System.out.println();
        }
    }

    private static long[][] mul(long[][] matrix1, long[][] matrix2, int n) {
        long[][] n_matrix;

        n_matrix = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long result = 0;
                for (int k = 0; k < n; k++) {
                    result += matrix1[i][k] * matrix2[k][j];
                    result %= 1000;
                }
                n_matrix[i][j] = result;
            }
        }
        return (n_matrix);
    }
}