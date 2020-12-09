package 백준;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준1655_가운데를말해요 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {
        int n;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.comparingInt(o -> -o));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (maxHeap.size() == minHeap.size())
                maxHeap.add(num);
            else
                minHeap.add(num);
            if (!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek())
            {
                int a = minHeap.poll();
                int b = maxHeap.poll();
                minHeap.add(b);
                maxHeap.add(a);
            }
            bw.write(maxHeap.peek()+"\n");
        }
        bw.flush();
        bw.close();
    }
}
