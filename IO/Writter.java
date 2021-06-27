package IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writter {
    public static void writeFile(String fileName, String content) {
        File directory = new File(fileName);
        String dirName = directory.getAbsolutePath();
        try {
            FileWriter writeFile = new FileWriter((dirName));
            writeFile.write(content);
            writeFile.close();
        } catch(IOException e) {
            System.out.println("Canot write file");
            e.printStackTrace();
        }
    }
}