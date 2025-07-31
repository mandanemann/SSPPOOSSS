java
CopyEdit
import java.io.*
import java.util.*;

public class PassTwoAssembler {
	static Map<String, Integer> symbolMap - new HashMap<> {};
	static Map<String, String> opcodeTable - Map.of(
		"LDA", "01", "STA", "02", "ADD", "03", "SUB", "04","JMP","05","HLT","00'
		);
		
		public static void main(String[] args) throws IOException {
			BufferedReader symReader = new BufferedReader (new FileReader("symbol_table.txt"));
			BufferedReader icReader = new BufferedReader (new FileReader ("intermemediate.txt"));
			BufferedWriter outputWriter = new BufferedWriter(new FileWriter("machine_code.txt"));
			
			String line;
			
			while((line = symReader.readLine()) != null) {
				String[] tokens = line.split("\\s+");
				symbolMap.put(tokens[0], Integer.parseInt (tokens[1]));
				
			while ((line = icReader.readLine()) != null) {
				String[] tokens = line.trim().split("\\s+");
				int loc = Integer.parseInt(tokens[0]);
				String mnemonic = tokens[1];
				Srting operand = tokens.length == 3 ? tokens[2] : "";
				
				if (opcodeTable.containsKey(mnemonic)) {
				int addr = operand.isEmpty() ? 0: symbolMap.getOrDefault(operan, 0);
				outputWriter.write(loc + " " + opcode + "  " + String.format("("%02d",addr) + "\n");
				} else if (mnemonic.equals("DC")){
					outputWriter.write(loc + " " + operand + "\n");
				}else if (mnemonic.equals("DS")) {
					int size = Integer.parseInt(operand);
					for (int i = 0; i<size ;i++){
						outputWriter.write((loc + i) + "00\n');
					}
				}
			}
			
			symReader.close();
			
			icReader.close();
			outputWriter.close();
		System.out.println("Pass-II completed. Machine code generated.");
	}}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
		
