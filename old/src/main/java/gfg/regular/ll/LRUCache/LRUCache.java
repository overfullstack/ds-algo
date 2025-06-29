package gfg.regular.ll.LRUCache;

import gfg.ds.DLLNode;
import gfg.ds.Utils;
import java.util.HashMap;
import java.util.Map;

/** Created by gakshintala on 6/15/16. */
public class LRUCache {
	private static final LRUQueue lruQueue = new LRUQueue();

	public static void main(String[] args) {
		var lruCache = new LRUCache();
		lruCache.referPage(1);
		lruCache.referPage(2);
		lruCache.referPage(3);
		lruCache.referPage(4);
		lruCache.referPage(5);
		Utils.printDLL(lruQueue.front);
		lruCache.referPage(6);
		Utils.printDLL(lruQueue.front);
		lruCache.referPage(3);
		Utils.printDLL(lruQueue.front);
	}

	private void referPage(int pageNo) {
		// HashMap provide O(1) insertion and lookup.
		// But HashMap does not have a mechanism of tracking which entry is queried recently.
		// So DLL is used.
		// DLL is used over SLL because you can remove or move a node with its own
		// pointer, unlike SLL where you need a previous pointer
		var node = lruQueue.getNodeForPage(pageNo);
		if (node == null) { // New page
			node = new DLLNode(pageNo);
			lruQueue.enqueue(pageNo, node);
		} else if (node != lruQueue.front) { // No need to rearrange if the node is already front.
			// Remove from current position
			node.prev.next = node.next;
			if (node == lruQueue.rear) { // If node is rear, adjust the queue rear
				lruQueue.rear = node.prev;
			} else { // If not, cut it by connecting its nextRight to prev
				node.next.prev = node.prev;
			}

			// Add to front
			node.next = lruQueue.front; // Adjust front
			lruQueue.front.prev = node;
			lruQueue.front = node;
			node.prev = null;
		}
	}
}

class LRUQueue {
	private static final int MAX_SIZE = 5;
	private final Map<Integer, DLLNode> pageToNodeMap = new HashMap<>();
	DLLNode front, rear;
	private int size = 0;

	void enqueue(int pageNo, DLLNode node) {
		// If queue is full, dequeue rear.
		if (isQueueFull()) {
			dequeue();
		}
		// Initial state with front and rear are not initialized.
		if (front == null) {
			front = rear = node;
		} else { // Enqueue in front. The linked list grows in reverse direction.
			node.next = front;
			front.prev = node;
			front = node;
		}
		// Put node in Hashmap for quick access.
		pageToNodeMap.put(pageNo, node);
		size++;
	}

	private void dequeue() {
		if (rear != null) {
			rear = rear.prev;
			rear.next = null;
			size--;
		}
	}

	public DLLNode getNodeForPage(int pageNo) {
		return pageToNodeMap.get(pageNo);
	}

	public boolean isQueueFull() {
		return size >= MAX_SIZE;
	}
}
