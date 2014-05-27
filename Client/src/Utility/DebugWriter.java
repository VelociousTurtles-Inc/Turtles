package Utility;

public class DebugWriter {
    public static Boolean write(String message, Object... args){
        System.err.println(" ::: " + System.currentTimeMillis() + " : " + message);
        for (Object temp : args){
            System.out.println("    " + temp);
        }
        return true;
    }
}
