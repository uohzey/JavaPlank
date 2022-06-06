class SegmentTree {
    // 线段树！！ 原版
    static void build(int[] arr, int[] tree, int start, int end, int node) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        // 将数进行分割，然后左右递归建树
        build(arr, tree, start, mid, node * 2);
        build(arr, tree, mid + 1, end, node * 2 + 1);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static int query(int[] tree, int start, int end, int node, int l, int r) {
        if (start > end || start > r || end < l) {
            return 0;
        }
        if (start >= l && end <= r) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        int left = query(tree, start, mid, node * 2, l, r);
        int right = query(tree, mid + 1, end, node * 2 + 1, l, r);
        return left + right;
    }

    static void update(int[] tree, int start, int end, int node, int index, int value) {
        if (start == end) {
            tree[node] = value;
            return;
        }
        int mid = (start + end) / 2;
        if (index <= mid) {
            update(tree, start, mid, node * 2, index, value);
        } else {
            update(tree, mid + 1, end, node * 2 + 1, index, value);
        }
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 93, 90, 50, 50, 1 };
        int n = arr.length;
        int[] tree = new int[4 * n];
        build(arr, tree, 0, n - 1, 1);
        for (int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println(query(tree, 0, n - 1, 1, 0, 2));
        update(tree, 0, n - 1, 1, 4, 2);
        for (int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + " ");
        }
    }
}