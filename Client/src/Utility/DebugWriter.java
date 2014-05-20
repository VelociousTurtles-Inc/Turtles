package Utility;

/**
 * Created by larhard on 13.05.14.
 */
public class DebugWriter {
    public static Boolean write(String message, Object... args){
        System.out.println(" ::: " + System.currentTimeMillis() + " : " + message);
        for (Object temp : args){
            System.out.println("    " + temp);
        }
        return true;
    }
}
