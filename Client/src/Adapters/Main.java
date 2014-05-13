package Adapters;

import ModelHelpers.DebugWriter;

/**
 * Created by michaziobro on 01.05.2014.
 */
public class Main {

    public static void main(String[] args) {
        assert DebugWriter.write("Starting application", args);
        StandardMenuController myMenuController = new StandardMenuController();

    }
}
