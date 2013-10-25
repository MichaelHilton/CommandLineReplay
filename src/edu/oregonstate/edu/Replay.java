package edu.oregonstate.edu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: michaelhilton
 * Date: 10/24/13
 * Time: 3:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Replay {
    String insertString(String s, String c, int i){
        StringBuilder str = new StringBuilder(s);
        str.insert(i,c);
        return str.toString();
    }

    public String removeSubString(String initialString, int start, int length) {
        StringBuilder sb = new StringBuilder(initialString);
        sb.delete(start,start+length);
        return sb.toString();
    }

    public String replaceSubString(String initialString, String insertedString, int start, int length) {
        StringBuilder sb = new StringBuilder(initialString);
        sb.replace(start, start+length, insertedString);
        return sb.toString();
    }

    private List<String> FileContents(String filename)  {
        Path filePath = Paths.get(filename);
        List<String> allLines = null;
        try{
        allLines = Files.readAllLines(filePath, Charset.defaultCharset());
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return allLines;
    }

    public Boolean areFilesIdentical(String fileName1, String fileName2) {
        List<String> file1 = FileContents(fileName1);
        return false;
    }
}
