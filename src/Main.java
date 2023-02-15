import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int n;
    static int m;
    static int k;
    static int c;

    static class GraphNode {
        int color;
        boolean isChecked;
        int[] cost;
        List<GraphNode> children;

        GraphNode() {
            color = 0;
            cost = new int[k + 1];
            isChecked = false;
            children = new ArrayList<>();
        }
    }

    static class myQueue {
        GraphNode[] queue;
        int head;
        int tail;
        int size;

        myQueue(int size) {
            queue = new GraphNode[size];
            head = 0;
            tail = 0;
            this.size = size;
        }

        void push(GraphNode node) {
            queue[tail] = node;
            tail = (tail + 1) % size;
        }

        GraphNode pop() {
            GraphNode node = queue[head];
            head = (head + 1) % size;
            return node;
        }

        boolean isEmpty() {
            return head == tail;
        }
    }

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        c = in.nextInt();
        GraphNode[] graph = new GraphNode[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new GraphNode();
            graph[i].color = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            if (!graph[a].children.contains(graph[b])) {
                graph[a].children.add(graph[b]);
            }
            if (!graph[b].children.contains(graph[a])) {
                graph[b].children.add(graph[a]);
            }
        }
        BFS(graph);
        //print
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= k; j++) {
//                System.out.print(graph[i].cost[j] + " ");
//            }
//            System.out.println();
//        }
        //将cost数组排序
        for (int i = 1; i <= n; i++) {
            Arrays.sort(graph[i].cost);
        }
        for (int i = 1; i <= n; i++) {
            int temp = 0;
            for (int j = 1; j <= c; j++) {
                temp += graph[i].cost[j];
            }
            System.out.print(temp + " ");
        }
    }

    //多源BFS
    static void BFS(GraphNode[] graph) {
        myQueue queue = new myQueue(n);
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[j].color == i) {
                    queue.push(graph[j]);
                    graph[j].isChecked = true;
                }
            }
            while (!queue.isEmpty()) {
                GraphNode node = queue.pop();
                for (GraphNode child : node.children) {
                    if (!child.isChecked) {
                        child.isChecked = true;
                        child.cost[i] = node.cost[i] + 1;
                        queue.push(child);
                    }
                }
            }
            for (int j = 1; j <= n; j++) {
                graph[j].isChecked = false;
            }
        }
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
