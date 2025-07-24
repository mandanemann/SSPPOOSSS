import java.io.*;
import java.util.*;

class Symbol {
    String label;
    int address;

    Symbol(String label, int address) {
        this.label = label;
        this.address = address;
    }
}

class IntermediateCode {
    int location;
    String mnemonic;
    String operand;

    IntermediateCode(int location, String mnemonic, String operand) {
        this.location = location;
        this.mnemonic = mnemonic;
        this.operand = operand;
    }
}

public class PassOneAssembler {
    static Map<String, String> opcodeTable = Map.of(
        "LDA", "01", "STA", "02", "ADD", "03", "SUB", "04", "JMP", "05", "HLT", "00"
    );

    static List<Symbol> symbolTable = new ArrayList<>();
    static List<IntermediateCode> intermediateCodes = new ArrayList<>();
    static int locationCounter = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.asm"));
        BufferedWriter icWriter = new BufferedWriter(new FileWriter("intermediate.txt"));
        BufferedWriter symWriter = new BufferedWriter(new FileWriter("Symbol_table.txt"));

        String line;
        boolean started = false;

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.trim().split("\\s+");

            if (tokens.length == 0 || tokens[0].startsWith(";")) continue; // Skip empty or comment lines

            if (tokens[0].equalsIgnoreCase("START")) {
                locationCounter = Integer.parseInt(tokens[1]);
                started = true;
                continue;
            }

            if (tokens[0].equalsIgnoreCase("END")) {
                break;
            }

            String label = null, mnemonic, operand = null;
            int idx = 0;

            if (!opcodeTable.containsKey(tokens[0].toUpperCase()) && !tokens[0].equalsIgnoreCase("DS") && !tokens[0].equalsIgnoreCase("DC")) {
                label = tokens[idx++];
            }

            mnemonic = tokens[idx++];

            if (idx < tokens.length) {
                operand = tokens[idx];
            }

            if (label != null) {
                symbolTable.add(new Symbol(label, locationCounter));
            }

            if (opcodeTable.containsKey(mnemonic.toUpperCase())) {
                intermediateCodes.add(new IntermediateCode(locationCounter, mnemonic.toUpperCase(), operand));
                locationCounter++;
            } else if (mnemonic.equalsIgnoreCase("DC")) {
                intermediateCodes.add(new IntermediateCode(locationCounter, "DC", operand));
                locationCounter++;
            } else if (mnemonic.equalsIgnoreCase("DS")) {
                int size = Integer.parseInt(operand);
                intermediateCodes.add(new IntermediateCode(locationCounter, "DS", operand));
                locationCounter += size;
            }
        }

        // Write symbol table
        for (Symbol sym : symbolTable) {
            symWriter.write(sym.label + " " + sym.address + "\n");
        }

        // Write intermediate code
        for (IntermediateCode ic : intermediateCodes) {
            icWriter.write(ic.location + " " + ic.mnemonic + " " + (ic.operand != null ? ic.operand : "") + "\n");
        }

        reader.close();
        icWriter.close();
        symWriter.close();

        System.out.println("Pass-I completed. Intermediate code and symbol table generated.");
    }
}

