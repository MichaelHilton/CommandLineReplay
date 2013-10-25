package edu.oregonstate.edu;

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
        //return null;  //To change body of created methods use File | Settings | File Templates.
        StringBuilder sb = new StringBuilder(initialString);
        sb.replace(start, start+length, insertedString);
        return sb.toString();
    }
}
