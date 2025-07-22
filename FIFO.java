import java.util. *;

public class FIFO{
public static void main(String[]args){
int[]pages = {1,2,3,4,1,2,5,1,2,3,4,5};
int capacity = 3;
Set<Integer>memory = new HashSet<>();
Queue<Integer>queue = new LinkedList<>();
int pageFaults = 0;

for(int page:pages){
if (!memory.contains(page)){
if(memory.size() == capacity){
int removed = queue.poll();
memory.remove(removed);
}
memory.add(page);
queue.add(page);
pageFaults++;
}
}

System.out.println("Total Page Faults (FIFO):"+ pageFaults);
}
}
