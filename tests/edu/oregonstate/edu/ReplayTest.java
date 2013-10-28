package edu.oregonstate.edu;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: michaelhilton
 * Date: 10/24/13
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReplayTest {

    Replay r;

    @Before
    public void setUp() throws Exception {
        r = new Replay();
        if(!Files.exists(Paths.get("./testIO.txt"))){
            Charset charset = Charset.forName("US-ASCII");
            String s = "SampleFile";
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("./testIO.txt"), charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }

        if(!Files.exists(Paths.get("./same1.txt"))){
            Charset charset = Charset.forName("US-ASCII");
            String s = "This is a sample file used for comparison.";
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("./same1.txt"), charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
        if(!Files.exists(Paths.get("./same2.txt"))){
            Charset charset = Charset.forName("US-ASCII");
            String s = "This is a sample file used for comparison.";
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("./same2.txt"), charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
        if(!Files.exists(Paths.get("./different1.txt"))){
            Charset charset = Charset.forName("US-ASCII");
            String s = "This is a different sample file used for comparison, with some diffs.";
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("./different1.txt"), charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }

    }

    @Test
    public void testAddChar() throws Exception {
        String initialString = "abcd";
        String afterString = r.insertString(initialString, "x", 0);
        assertEquals("xabcd",afterString);
    }

    @Test
    public void testAddCharEnd() throws Exception {
        String initialString = "abcd";
        String afterString = r.insertString(initialString, "x", 4);
        assertEquals("abcdx",afterString);
    }

    @Test
    public void testAddCharMiddle() throws Exception {
        String initialString = "abcd";
        String afterString = r.insertString(initialString, "x", 2);
        assertEquals("abxcd",afterString);
    }

    @Test
    public void testAddStringStart() throws Exception {
        String initialString = "abcd";
        String afterString = r.insertString(initialString, "xxx", 0);
        assertEquals("xxxabcd",afterString);
    }

    @Test
    public void testAddStringEnd() throws Exception {
        String initialString = "abcd";
        String afterString = r.insertString(initialString, "xxx", 2);
        assertEquals("abxxxcd",afterString);
    }

    @Test
    public void testAddStringMiddle() throws Exception {
        String initialString = "abcd";
        String afterString = r.insertString(initialString, "xxx", 4);
        assertEquals("abcdxxx",afterString);
    }

    @Test
    public void testRemoveStringStart() throws Exception {
        String initialString = "xxxabcd";
        String afterString = r.removeSubString(initialString, 0, 3);
        assertEquals("abcd",afterString);
    }
    @Test
    public void testRemoveStringMiddle() throws Exception {
        String initialString = "abxxcd";
        String afterString = r.removeSubString(initialString, 2, 2);
        assertEquals("abcd",afterString);
    }
    @Test
    public void testRemoveStringEnd() throws Exception {
        String initialString = "abcdxxx";
        String afterString = r.removeSubString(initialString, 4, 7);
        assertEquals("abcd",afterString);
    }

    @Test
    public void testRemoveCharStart() throws Exception {
        String initialString = "xabcd";
        String afterString = r.removeSubString(initialString, 0, 1);
        assertEquals("abcd",afterString);
    }
    @Test
    public void testRemoveCharMiddle() throws Exception {
        String initialString = "abxcd";
        String afterString = r.removeSubString(initialString, 2, 1);
        assertEquals("abcd",afterString);
    }
    @Test
    public void testRemoveCharEnd() throws Exception {
        String initialString = "abcdx";
        String afterString = r.removeSubString(initialString, 4, 1);
        assertEquals("abcd",afterString);
    }

    @Test
    public void testReplaceCharEnd() throws Exception {
        String initialString = "abcdx";
        String afterString = r.replaceSubString(initialString, "y", 4, 1);
        assertEquals("abcdy",afterString);
    }

    @Test
    public void testReplaceCharStart() throws Exception {
        String initialString = "xabcd";
        String afterString = r.replaceSubString(initialString, "y", 0, 1);
        assertEquals("yabcd",afterString);
    }

    @Test
    public void testReplaceCharMiddle() throws Exception {
        String initialString = "abxcd";
        String afterString = r.replaceSubString(initialString, "y", 2, 1);
        assertEquals("abycd",afterString);
    }

    @Test
    public void testReplaceStringEnd() throws Exception {
        String initialString = "abcdx";
        String afterString = r.replaceSubString(initialString, "yyyy", 4, 1);
        assertEquals("abcdyyyy",afterString);
    }

    @Test
    public void testReplaceStringStart() throws Exception {
        String initialString = "xxxabcd";
        String afterString = r.replaceSubString(initialString, "yyyy", 0, 3);
        assertEquals("yyyyabcd",afterString);
    }

    @Test
    public void testOpenFile() throws Exception {
        Path file = Paths.get("./testIO.txt");
        List<String> allLines = Files.readAllLines(file, Charset.defaultCharset());
        assertEquals("SampleFile",allLines.get(0));
    }

    @Test
    public void testReplaceStringMiddle() throws Exception {
        String initialString = "abxxxxxcd";
        String afterString = r.replaceSubString(initialString, "y", 2, 5);
        assertEquals("abycd",afterString);
    }

    @Test
    public void testSameFiles() throws Exception {
        Boolean isSame = r.areFilesIdentical("./same1.txt","./same2.txt");
        assertEquals((boolean)isSame,true);
    }
    @Test
    public void testDifferentFiles() throws Exception {
        Boolean isSame = r.areFilesIdentical("./same1.txt","./different1.txt");
        assertEquals((boolean)isSame,false);
    }



}
