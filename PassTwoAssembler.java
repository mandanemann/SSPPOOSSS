import java.io.*;
import java.util.*;

public class PassTwoAssembler {
    static Map<String, Integer> symbolMap = new HashMap<>();
    static Map<String, String> opcodeTable = Map.of(
        "LDA", "01", "STA", "02", "ADD", "03", "SUB", "04",
        "JMP", "05", "HLT", "00"
    );

    public static void main(String[] args) throws IOException {
        BufferedReader symReader = new BufferedReader(new FileReader("symbol_table.txt"));
        BufferedReader icReader = new BufferedReader(new FileReader("intermediate.txt"));
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter("machine_code.txt"));

        String line;


        while ((line = symReader.readLine()) != null) {
            String[] tokens = line.trim().split("\\s+");
            if (tokens.length == 2) {
                symbolMap.put(tokens[0], Integer.parseInt(tokens[1]));
            }
        }


        while ((line = icReader.readLine()) != null) {
            String[] tokens = line.trim().split("\\s+");
            if (tokens.length < 2) continue;

            int loc = Integer.parseInt(tokens[0]);
            String mnemonic = tokens[1];
            String operand = tokens.length == 3 ? tokens[2] : "";

            if (opcodeTable.containsKey(mnemonic)) {
                String opcode = opcodeTable.get(mnemonic);
                int addr = operand.isEmpty() ? 0 : symbolMap.getOrDefault(operand, 0);
                outputWriter.write(String.format("%02d %s %02d\n", loc, opcode, addr));
            } else if (mnemonic.equals("DC")) {
                outputWriter.write(String.format("%02d %s\n", loc, operand));
            } else if (mnemonic.equals("DS")) {
                int size = Integer.parseInt(operand);
                for (int i = 0; i < size; i++) {
                    outputWriter.write(String.format("%02d 00\n", loc + i));
                }
            }
        }

        symReader.close();
        icReader.close();
        outputWriter.close();

        System.out.println("Pass-II completed. Machine code generated.");
    }
}

