package IO;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Reader {
    public static String readFile(String fileName) {
        String sourceText = "";
        File directory = new File(fileName);
        String dirName = directory.getAbsolutePath();
        try {
            File sourceFile = new File((dirName));
            Scanner sourceReader = new Scanner(sourceFile);
            Integer lIndex = 0;
            while(sourceReader.hasNextLine()) {
                sourceText += (lIndex != 0 ? "\n": "") + sourceReader.nextLine();
                lIndex++;
            }
        } catch(IOException e) {
            System.out.println("Canot read file");
            e.printStackTrace();
        }

        return sourceText;
    }
}