package edu.oregonstate.edu;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Iterator;
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

    private List<String> getFileContents(String filename)  {
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
        List<String> file1 = getFileContents(fileName1);
        List<String> file2 = getFileContents(fileName2);
        if(file1.equals(file2)){
            return true;
        }
        return false;
    }

    public void replayFile(String fileName) {
        List<String> replayFileContents = getFileContents(fileName);
        // iterator loop
        System.out.println("#1 iterator");
        Iterator<String> iterator = replayFileContents.iterator();
        while (iterator.hasNext()) {
            String currLine = iterator.next();
            currLine = currLine.replace("$@$","");
            JSONObject curjObj = parseJSONString(currLine);

            System.out.println(curjObj.toString());

        }

    }

    public JSONObject parseJSONString(String jsonString) {
        Object obj = JSONValue.parse(jsonString);
        JSONObject jObj = (JSONObject)obj;
        return jObj;
    }



    public String dispatchJSON(JSONObject jObj) {
        String eventDispatched ="Unknown eventType";
        switch (jObj.get("eventType").toString()) {
            case "fileOpen":
                openFile(jObj);
                eventDispatched = "fileOpen";
                break;
            case "fileClose":
                eventDispatched = "fileClose";
                break;
            case "textChange":
                eventDispatched = "textChange";
                break;
            default: throw new RuntimeException("Unknown eventType");

        }

        return eventDispatched;  //To change body of created methods use File | Settings | File Templates.
    }

    public void openFile(JSONObject jObj) {
        String fileName = jObj.get("fullyQualifiedMain").toString();
        System.out.println(fileName);
        try {
           // File file = new File("C:\\user\\Desktop\\dir1\\dir2\\filename.txt");
            Files.createDirectories(Paths.get(fileName));
           // file.getParentFile().mkdirs();
            BufferedWriter writer =
                    Files.newBufferedWriter(
                            FileSystems.getDefault().getPath(".", fileName),
                            Charset.forName("US-ASCII"),
                            StandardOpenOption.CREATE);

            //writer.write(content, 0, content.length());
        }
        catch ( IOException ioe ) {
            ioe.printStackTrace();
        }
        //To change body of created methods use File | Settings | File Templates.
    }
}
