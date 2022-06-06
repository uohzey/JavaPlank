import java.util.TreeMap;

class MyCalendar {
    class Node {
        // 当前节点的左节点在线段树数组tr中的下标
        int ls;
        // 当前节点的右节点在线段树数组tr中的下表
        int rs;
        // 懒标记 lazy
        int lazy;
        // 当前区间的最大值
        int max;
    }

    int N = (int) 1e9, M = 5 * (int) 1e5, cnt = 1;
    Node[] tree = new Node[M];

    public MyCalendar() {

    }

    void update(int u, int lc, int rc, int l, int r, int value) {
        // if 当前区间被需要加的区间包裹，当前区间全加
        if (l <= lc && rc <= r) {
            tree[u].lazy += value;
            tree[u].max += value;
            return;
        }
        lazyCreate(u);
        pushdown(u);
        int mid = (lc + rc) / 2;
        if (l <= mid) {
            update(tree[u].ls, lc, mid, l, r, value);
        }
        if (r > mid) {
            update(tree[u].rs, mid + 1, rc, l, r, value);
        }
        pushup(u);
    }

    int query(int u, int lc, int rc, int l, int r) {
        // if 当前区间被需要查询的区间包裹，直接返回当前区间的最大值
        if (l <= lc && rc <= r) {
            return tree[u].max;
        }
        lazyCreate(u);
        pushdown(u);
        int mid = (lc + rc) / 2;
        int left = 0, right = 0;
        if (l <= mid) {
            left = query(tree[u].ls, lc, mid, l, r);
        }
        if (r > mid) {
            right = query(tree[u].rs, mid + 1, rc, l, r);
        }
        return Math.max(left, right);
    }

    // 动态开点
    void lazyCreate(int u) {
        if (tree[u] == null) {
            tree[u] = new Node();
        }
        if (tree[u].ls == 0) {
            tree[u].ls = ++cnt;
            tree[tree[u].ls] = new Node();
        }
        if (tree[u].rs == 0) {
            tree[u].rs = ++cnt;
            tree[tree[u].rs] = new Node();
        }
    }

    // 下载lazy
    void pushdown(int u) {
        if (tree[u].lazy != 0) {
            tree[tree[u].ls].lazy += tree[u].lazy;
            tree[tree[u].ls].max += tree[u].lazy;
            tree[tree[u].rs].lazy += tree[u].lazy;
            tree[tree[u].rs].max += tree[u].lazy;
            tree[u].lazy = 0;
        }
    }

    // 上传lazy
    void pushup(int u) {
        tree[u].max = Math.max(tree[tree[u].ls].max, tree[tree[u].rs].max);
    }

    public int book(int start, int end) {
        update(1, 1, N + 1, start, end - 1, 1);
        return query(1, 1, N + 1, 1, N + 1);
    }

    public static void main(String[] args) {
        // MyCalendar myCalendar = new MyCalendar();
        // System.out.println(myCalendar.book(10, 20));
        // System.out.println(myCalendar.book(50, 60));
        // System.out.println(myCalendar.book(10, 40));
        // System.out.println(myCalendar.book(5, 15));
        // System.out.println(myCalendar.book(5, 10));
        // System.out.println(myCalendar.book(25, 55));
        System.out.print(Math.sqrt(1e9) * 1000 * 4);
    }
}
