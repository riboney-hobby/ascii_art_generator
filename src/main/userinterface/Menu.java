package main.userinterface;

import main.filehandling.File_IO;

public final class Menu {
    private Menu(){
        // throw instantation error
    }

    private static String menuText = "Image to ASCII Converter\n" +
                                     "------------------------\n\n" +
                                     "Choose Image to convert to ASCII: ";

    public static void displayMenu(){
        System.out.println(menuText);
        File_IO.displayFileNamesInFolder(File_IO.returnFileMapping("src/main/res/in"));
    }
}
