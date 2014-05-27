package GameWatchdogs;

import Common.Interfaces.Event;
import Utility.Utility;
import Server.Interfaces.ServerGameDispenser;
import Server.Interfaces.GameManager;

import java.rmi.RemoteException;

/**
 * Creates thread, which deletes zombie games
 */
public class StandardGameWatchdog {
    private final ServerGameDispenser gameDispenser;
    private final Thread watchdogThread;

    private static final int WATCHDOG_TIMEOUT = 10000;

    public StandardGameWatchdog(ServerGameDispenser gameDispenser) {
        this.gameDispenser = gameDispenser;
        Utility.logInfo("Starting StandardGameWatchdog");
        watchdogThread = new Thread(new WatchdogThread());
        watchdogThread.start();
        gameDispenser.registerCloseEvent(new Event() {
            @Override
            public void call() {
                watchdogThread.interrupt();
            }
        });
    }

    private class WatchdogThread implements Runnable {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                Utility.logInfo("Running Watchdog Zombie Collector");
                for (GameManager gameManager : gameDispenser.getGameManagers()) {
                    try {
                        gameManager.checkForZombies();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(WATCHDOG_TIMEOUT);
                } catch (InterruptedException e) {
                    break;
                }
            }
            Utility.logInfo("StandardGameWatchdog stopped");
        }
    }
}
