public class MerkleManager {
    public static volatile String sUserWord;
    public static String sUserEnteredExpectedRoot;
    public static String sMerkleRoot = null;
    public static int iStrikes = 0;

    public void manage() {
        Util oUtil = new Util();
        sUserEnteredExpectedRoot = oUtil.promptUser("Enter the expected merkle root: ");

        MerkleThread oMerkle = new MerkleThread();
        oMerkle.threadName = "Merkle";
        Thread oMerkleThread = new Thread(oMerkle);
        oMerkleThread.start();

        RogueThread oRogue = new RogueThread();
        oRogue.threadName = "Rogue";
        Thread oRogueThread = new Thread(oRogue);
        oRogueThread.start();

        MonitorThread oMonitor = new MonitorThread();
        oMonitor.threadName = "Monitor";
        Thread oMonitorThread = new Thread(oMonitor);
        oMonitorThread.start();

        while(true) {
            sUserWord = oUtil.promptUser("Enter a word: ");
        }
    }

    public static synchronized String grabWord() {
        String tempWord = sUserWord;
        sUserWord = null;

        return tempWord;
    }
}
