```java
class LRUCache {
    class ListNode {
        int key;
        int value;
        //为了在双向链表中添加和删除节点,需要创建两个哨兵节点,即head和tail,
        //位于双向链表的头部和尾部.函数put所添加的节点将位于这两个节点之间.
        ListNode next;
        ListNode prev;
        public ListNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    Map<Integer, ListNode> map;
    ListNode head;
    ListNode tail;
    int capacity;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        ListNode node = map.get(key);
        // 将节点移到链表尾部
        moveToTail(node, node.value);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            // 将节点移到链表尾部,并更新
            moveToTail(map.get(key), value);
        }
        else{
            if(map.size() == capacity){
                // 缓存上限,删除最久未使用的数据值
                ListNode toBeDeleted = head.next;
                // 链表中删除数据
                deleteNode(toBeDeleted);
                // hash表中删除
                map.remove(toBeDeleted.key);
            }
            var node = new ListNode(key, value);
            // 往尾部插入node
            insertToTail(node);
            map.put(key, node);
        }
    }

    private void moveToTail(ListNode node, int newValue){
        deleteNode(node);
        node.value = newValue;
        insertToTail(node);
    }

    private void deleteNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertToTail(ListNode node){
        tail.prev.next = node;
        node.prev = tail.prev;
        tail.prev = node;
        node.next = tail;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

