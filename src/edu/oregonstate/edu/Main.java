package edu.oregonstate.edu;

public class Main {

    public static void main(String[] args) {
	// write your code here
        if(args.length < 1){
            System.out.println("Usage: java commandLineReplay FILEtoReplay");
            return;
        }
        Replay r = new Replay();
        r.replayFile(args[0]);

    }
}
