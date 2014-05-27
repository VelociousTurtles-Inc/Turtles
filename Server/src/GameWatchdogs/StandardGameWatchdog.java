package GameWatchdogs;

import Events.Event;
import Utility.Utility;
import Server.Interfaces.ServerGameDispenser;
import Server.Interfaces.GameManager;

import java.rmi.RemoteException;

/**
 * Creates thread, which deletes zombie games
 * Created by larhard on 25.05.14.
 */
public class StandardGameWatchdog {
    private ServerGameDispenser gameDispenser;
    private Thread watchdogThread;

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
