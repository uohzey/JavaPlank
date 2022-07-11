```java
class MyCalendar {
    class Node {
        Node ls;
        Node rs;
        int lazy;
        // 当前节点所包含的点的数量
        int sum;
    }
    Node root = new Node(); 
    int N = (int)(1e9);
    public MyCalendar() {

    }
    void pushup(Node node) {
        node.sum = node.ls.sum + node.rs.sum;
    }
    void pushdown(Node node, int len) {
        node.ls.lazy += node.lazy;
        node.rs.lazy += node.lazy;
        node.ls.sum += (len - len / 2) * node.lazy;
        node.rs.sum += (len / 2) * node.lazy;
        node.lazy = 0;
    }
    void lazyCreate(Node node) {
        if(node == null){
            node = new Node();
        }
        if(node.ls == null){
            node.ls = new Node();
        }
        if(node.rs == null){
            node.rs = new Node();
        }
    }
    void update(Node node, int lc, int rc, int l, int r, int value) {
        int len = rc - lc + 1;
        if(l <= lc && rc <= r){
            node.sum += len * value;
            node.lazy += value;
            return ;
        }
        lazyCreate(node);
        pushdown(node, len);
        int mid = (lc + rc) >> 1;
        if(l <= mid){
            update(node.ls, lc, mid, l, r, value);
        }
        if(r > mid){
            update(node.rs, mid + 1, rc, l , r, value);
        }
        pushup(node);
    }
    int query(Node node, int lc, int rc, int l, int r) {
        int len = rc - lc + 1;
        if(l <= lc && rc <= r){
            return node.sum;
        }
        lazyCreate(node);
        pushdown(node, len);
        int mid = (lc + rc) >> 1;
        int ans = 0;
        if(l <= mid){
            ans = query(node.ls, lc, mid, l, r);
        }
        if(r > mid){
            ans += query(node.rs, mid + 1, rc, l, r);
        }
        return ans;
    }
    
    public boolean book(int start, int end) {
        if(query(root, 1, N + 1, start + 1, end) > 0){
            return false;
        }
        update(root, 1, N + 1, start + 1, end, 1);
        return true;
    }
    
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
```

