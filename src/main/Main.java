package main;

import main.asciiconverter.Convertor;
import main.filehandling.File_IO;
import main.userinterface.Menu;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import static main.filehandling.File_IO.*;

public class Main {
    public static void main(String[] arg) throws IOException {
        //Map<String, File> map = returnFileMapping("src/main/res/in");
        //Object[] fileList = map.keySet().toArray();

        //Convertor.imageToAscii(map.get((String) fileList[0]));
        //Convertor.imageToAscii(map.get("anime_feet_1.png"));

        //Menu.displayMenu();
        System.out.println("testing save image from url");
        //File_IO.saveImageFromURL("https://pm1.narvii.com/6329/b292549f784992e6da4aca85612077eb699c05a3_hq.jpg");
    }
}
