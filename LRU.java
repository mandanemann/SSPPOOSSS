import java.util.*;

public class LRU {
public static void main(String[]args){
int[]pages = {1,2,3,4,1,2,5,1,2,3,4,5};
int capacity = 3;
LinkedHashSet<Integer>cache = new LinkedHashSet<>();
int pageFaults = 0;

for (int page :pages){
if (!cache.contains(page)){
if(cache.size() == capacity){
Iterator<Integer>it = cache.iterator();
it.next();
it.remove();
}
cache.add(page);
pageFaults++;
}else{
cache.remove(page);
cache.add(page);
}
}
System.out.println("Total Page Faults(LRU):" + pageFaults);
}
}

