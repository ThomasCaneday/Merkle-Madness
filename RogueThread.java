public class RogueThread implements Runnable {
    public String threadName;

    @Override
    public void run() {
        Util oUtil = new Util();

        while(true) {
            oUtil.sleepRandomTime(threadName);
            String sNewWord = MerkleManager.grabWord();
            if(sNewWord != null) {
                MerkleManager.iStrikes++;
                System.out.println("Rogue grabbed a word... STRIKE!");
            }
        }
    }
}
