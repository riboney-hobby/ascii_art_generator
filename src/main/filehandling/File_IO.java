
package main.filehandling;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class File_IO {
    public static String directoryString = "src/main/res/in";

    public static void addEntriesToFileMap(File[] filesFound, Map<String, File> fileMap) {
        // Loop through array to add contents to Map
        for(File f: filesFound) {
            fileMap.put(f.getName(), f);
        }
    }

    // Maps names of files in current directory to file objects
    public static Map<String, File> returnFileMapping(String directoryLocation) {

        // Variables
        File[] files;
        Map<String, File> filesMap = new HashMap<>();

        // Set file object to folder that contains images
        File directory = new File(directoryLocation);

        // Create array with files from res folder
        if(directory.isDirectory()){
            files = directory.listFiles();
            File_IO.addEntriesToFileMap(files, filesMap);
        }
        else{
            // Error if folder is not a directory
            System.out.println("Not a directory");
            return null;
        }

        return filesMap;
    }


    // Display file names in folder
    public static void displayFileNamesInFolder(Map<String,File> fileMap){

        // Get list of files from directory
        //File[] folder = directory.listFiles();

        // Directory variable should be checked if it is a directory, in another method
        //System.out.println("Images in " + directory.getName());

        // variable to get the index of the period in a filename to remove the .extention part of the file name
        int indexOfPeriod, counter = 1;

        for(File file: fileMap.values()){
            indexOfPeriod = file.getName().indexOf('.');
            // Print only the
            System.out.println(counter + ". " + file.getName().substring(0,indexOfPeriod));
            counter++;
        }
    }

//    // verify if file is a directory
//    public static void isItADirectory(File file){
//        if(!file.isDirectory()){
//            throw new IOException(file.getName() + " is not a directory!");
//    }

    // get image from url and save to folder
    // src: https://stackoverflow.com/a/5882039/12369650
    public static void saveImageFromURL(String urlString) throws IOException {

        URL url = new URL(urlString);
        String fileName = url.getFile();

        byte[] b = new byte[2048];
        int length;

        try(InputStream input = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            OutputStream out = new FileOutputStream(directoryString + fileName.substring(fileName.lastIndexOf("/")));
            ){
            while((length = input.read(b)) != -1){
                output.write(b, 0, length);
            }
            byte[] response = output.toByteArray();
            out.write(response);
            System.out.println("Done! File saved!");
        }
    }
}
