
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;



public class RandomFileContentGenerator
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
       		
		Set<String> set;
        set = new TreeSet<>();
                String s; 
                int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Public\\temp_words_1.txt")))) {
            s = br.readLine();
            while( s != null ) {
                set.add(s);
                
                if(set.size() == 12000000) {
                    try (FileWriter fw = new FileWriter(new File("C:\\Users\\Public\\temp-" +i+".txt"))) {
                        for (String x: set) {
                            fw.write("\n"+x);
                           
                        }
                    }
                   set = new TreeSet<>();  i++;
                }
                s = br.readLine();
            }
        }
		
        try (FileWriter fw = new FileWriter(new File("C:\\Users\\Public\\temp-" +i+".txt"))) {
            for (String x: set) {
                fw.write(x);
                fw.write('\n');
            }
        }
		
		Map<String, Integer> map = new TreeMap<String, Integer>();
		
		BufferedReader[] brArr = new BufferedReader[i+1];
		for(int j=0; j<=i; j++) {
			brArr[j] = new BufferedReader(new FileReader(new File("C:\\Users\\Public\\temp-" +j+".txt")));			
			map.put(brArr[j].readLine(), j);
		}
		

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\Users\\Public\\largesortedfile.txt")))) {
            while(!map.isEmpty()) {
                s = map.keySet().iterator().next();
                i = map.get(s);
                map.remove(s);
                bw.write(s);
                bw.write("\n");
                s = brArr[i].readLine();
                if(s != null ) {
                    map.put(s, i);
                }
            }
        }

		for(int j=0; j<brArr.length; j++) {
			brArr[j].close();
			new File("C:\\Users\\Public\\temp-" +j+".txt").delete();
		}

    }
}