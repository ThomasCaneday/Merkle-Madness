public class MonitorThread implements Runnable {
    public String threadName;

    @Override
    public void run() {
        Util oUtil = new Util();

        while(true) {
            if(MerkleManager.sMerkleRoot != null) {
                if(MerkleManager.sMerkleRoot.equals(MerkleManager.sUserEnteredExpectedRoot)) {
                    System.out.println("You win: " + MerkleManager.sMerkleRoot);
                    System.exit(0);
                }
                else {
                    System.out.println("User lost!");
                    System.exit(0);
                }
            }
            else if(MerkleManager.iStrikes == 3) {
                System.out.println("3 strikes: you lost!");
                System.exit(0);
            }

            oUtil.sleep(1);
        }
    }
}
