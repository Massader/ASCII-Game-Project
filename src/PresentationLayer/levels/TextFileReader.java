package PresentationLayer.levels;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TextFileReader {

    public static List<String> readAllLines(String path){
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String next;
            while ((next = reader.readLine())!=null){
                lines.add(next);
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found" + path);
        }catch (IOException e){
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
        return lines;
    }
}
