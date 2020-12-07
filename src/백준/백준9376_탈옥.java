package 백준;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준9376_탈옥 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = null;
    //빈 공간은 '.', 지나갈 수 없는 벽은 '*', 문은 '#', 죄수의 위치는 '$'이다.

    static final char EMPTY = '.';
    static final char WALL = '*';
    static final char DOOR = '#';
    static final char PRISONER = '$';

    public static void main(String[] args)  throws IOException {
        int t;
        char[][] map;

        t = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < t; testCase++)
        {
            int h, w, prisonerIdx, minimumOpenDoor;
            int[][] prisonerOne, prisonerTwo, sanggeun;

            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            // h + 2 , w + 2 외부 출입라인 생성
            map = new char[h + 2][w + 2];
            Prisoner[] prisoners = new Prisoner[2];
            prisonerIdx = 0;

            String line = null;
            for (int i = 0; i < h; i++)
            {
                line = br.readLine();
                for (int j = 0; j < w; j++)
                {
                    map[i + 1][j + 1] = line.charAt(j);
                    if (line.charAt(j) == PRISONER)
                    {
                        prisoners[prisonerIdx++] = new Prisoner(i + 1, j + 1);
                    }
                }
            }

            prisonerOne = bfs(map, prisoners[0], h, w);
            prisonerTwo = bfs(map, prisoners[1], h, w);
            sanggeun = bfs(map, new Prisoner(0, 0), h, w);

            System.out.println();
            printMap(prisonerOne);
            printMap(prisonerTwo);
            printMap(sanggeun);

            minimumOpenDoor = getMinimumSum(prisonerOne, prisonerTwo, sanggeun, map);
            System.out.println(minimumOpenDoor);
        }
    }

    private static void printMap(int[][] arr)
    {
        for (int[] a: arr)
        {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }

    private static int getMinimumSum(int[][] prisonerOne, int[][] prisonerTwo, int[][] sanggeun, char[][] map) {
        int minSum;

        minSum = Integer.MAX_VALUE;

        for (int i = 0; i < prisonerOne.length; i++)
        {
            for (int j = 0; j < prisonerOne[i].length; j++)
            {
                if (map[i][j] == '*')
                    continue;

                int sum = prisonerOne[i][j] + prisonerTwo[i][j] + sanggeun[i][j];
                if (map[i][j] == '#')
                {
                    sum -= 2;
                }
                if (minSum > sum)
                {
                    minSum = sum;
                }
            }
        }


        return (minSum);
    }

    private static int[][] bfs(char[][] map, Prisoner prisoner, int h, int w) {
        PriorityQueue<Prisoner> queue = new PriorityQueue<>();
        boolean[][] visited = new boolean[h + 2][w + 2];
        int[][] doorHistory = new int[h + 2][w + 2];

        queue.add(prisoner);
        visited[prisoner.x][prisoner.y] = true;

        while (!queue.isEmpty())
        {
            Prisoner temp = queue.poll();

//            if (map[temp.x][temp.y] == '#')
//            {
//                temp.openDoor += 1;
//            }

            doorHistory[temp.x][temp.y] = temp.openDoor;

            for (int i = 0; i < 4; i++)
            {
                int nx, ny;

                nx = temp.x + dx[i];
                ny = temp.y + dy[i];
                if (0 <= nx && nx < h + 2 && 0 <= ny && ny < w + 2 && !visited[nx][ny]
                && map[nx][ny] != '*')
                {
                    visited[nx][ny] = true;
                    queue.add(new Prisoner(nx, ny, map[nx][ny] == '#' ? temp.openDoor + 1 : temp.openDoor));
                }
            }
        }
        return (doorHistory);
    }

    static int[] dx = {0, 0 ,1, -1};
    static int[] dy = {1, -1 ,0, 0};


    public static class Prisoner implements Comparable<Prisoner>{
        int x, y, openDoor;

        public Prisoner(int x, int y) {
            this.x = x;
            this.y = y;
            this.openDoor = 0;
        }

        public Prisoner(int x, int y, int openDoor) {
            this.x = x;
            this.y = y;
            this.openDoor = openDoor;
        }

        @Override
        public int compareTo(Prisoner o) {
            return Integer.compare(this.openDoor, o.openDoor);
        }
    }
}
