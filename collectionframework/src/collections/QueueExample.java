package collections;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueExample {
	
	public static void main(String[] args) {
		
		Deque<String> queue = new LinkedList<>();
		
		queue.offer("amit");
		queue.offer("harsh");
		queue.offer("javed");
		queue.offer("gourav");
		queue.offer("riya");
		queue.offer("prince");
		
//		for(var name:queue) {
//			System.out.println(name);
//		}
		
		while(!queue.isEmpty()) {
			System.out.println(queue.poll());
		}
		
	
		
		
	}

}
