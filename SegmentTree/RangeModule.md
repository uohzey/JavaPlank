```java
//715.Range模块

class RangeModule {
    class Node {
        // 当前节点的左节点在线段树数组tr中的下标
        Node ls;
        // 当前节点的右节点在线段树数组tr中的下表
        Node rs;
        // 懒标记 lazy
        int lazy;
        // 当前区间多少整数被追踪
        int sum;
    }

    int N = (int) 1e9;
    Node root = new Node();

    public RangeModule() {

    }
    
    public void addRange(int left, int right) {
        update(root, 1, N - 1, left, right - 1, 1);
    }
    
    public boolean queryRange(int left, int right) {
        return query(root, 1, N - 1, left, right - 1) == right - left;
    }
    
    public void removeRange(int left, int right) {
        update(root, 1, N - 1, left, right - 1, -1);
    }

    void update(Node node, int lc, int rc, int l, int r, int value) {
        int len = rc - lc + 1;
        // if 当前区间被需要加的区间包裹，当前区间全加
        if (l <= lc && rc <= r) {
            node.lazy = value;
            node.sum = value == 1 ? len : 0;
            return;
        }
        lazyCreate(node);
        pushdown(node, len);
        int mid = (lc + rc) / 2;
        if (l <= mid) {
            update(node.ls, lc, mid, l, r, value);
        }
        if (r > mid) {
            update(node.rs, mid + 1, rc, l, r, value);
        }
        pushup(node);
    }

    int query(Node node, int lc, int rc, int l, int r) {
        // if 当前区间被需要查询的区间包裹，直接跟踪的长度
        if (l <= lc && rc <= r) {
            return node.sum;
        }
        lazyCreate(node);
        pushdown(node, rc - lc + 1);
        int mid = (lc + rc) / 2;
        int ans = 0;
        if (l <= mid) {
            ans = query(node.ls, lc, mid, l, r);
        }
        if (r > mid) {
            ans += query(node.rs, mid + 1, rc, l, r);
        }
        return ans;
    }

    // 动态开点
    void lazyCreate(Node node) {
        if (node == null) {
            node = new Node();
        }
        if (node.ls == null) {
            node.ls = new Node();
        }
        if (node.rs == null) {
            node.rs = new Node();
        }
    }

    // 下载lazy
    void pushdown(Node node, int len) {
        if(node.lazy == 0){
            return ;
        }
        if(node.lazy == -1){
            node.ls.sum = 0;
            node.rs.sum = 0;
        }
        else{
            node.ls.sum = len - len / 2;
            node.rs.sum = len / 2;
        }
        node.ls.lazy = node.lazy;
        node.rs.lazy = node.lazy;
        node.lazy = 0;
    }

    // 上传lazy
    void pushup(Node node) {
        node.sum = node.ls.sum + node.rs.sum;
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
```

