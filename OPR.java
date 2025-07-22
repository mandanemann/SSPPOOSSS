import java.util.*;

public class OPR{
public static void main(String[]args ){
int[]pages = {1,2,3,4,1,2,5,1,2,3,4,5};
int capacity = 3;
Set<Integer>memory = new HashSet<>();
int pageFaults = 0;
for (int i = 0; i < pages.length; i++){
int page = pages[i];

if (!memory.contains(page)){
if (memory.size() == capacity){
int pageToRemove = predict(pages,memory, i+1);
memory.remove(pageToRemove);
}
memory.add(page);
pageFaults++;
}
}

System.out.println("Total Page Fault(Optimal);"+pageFaults);
}
private static int predict(int[] pages, Set<Integer>memory, int startindex){
Map<Integer, Integer>nextUse = new HashMap<>();
for (int page:memory){
nextUse.put(page,Integer.MAX_VALUE);
}

for (int i = startindex; i< pages.length; i++){
if (nextUse.containsKey(pages[i]) && nextUse.get(pages[i]) == Integer.MAX_VALUE){
nextUse.put(pages[i], i);
}
}

int farthest = -1;
int pageToRemove = -1;
for (Map.Entry<Integer, Integer>entry : nextUse.entrySet()){
if (entry.getValue()>farthest){
farthest = entry.getValue();
pageToRemove = entry.getKey();
}
}

return pageToRemove;
}
}

