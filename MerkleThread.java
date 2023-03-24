import java.util.ArrayList;

public class MerkleThread implements Runnable {
    public static volatile ArrayList<String> listWords;
    private int iMerkleTreeInputs = 4;
    public String threadName;

    @Override
    public void run() {
        Util oUtil = new Util();
        ArrayList<String> listWords = new ArrayList<>();

        while(true) {
            oUtil.sleepRandomTime(threadName);
            String sNewWord = MerkleManager.grabWord();

            if(sNewWord != null) {
                System.out.println("Merkle grabbed a word!");
                listWords.add(sNewWord);

                if(listWords.size() == iMerkleTreeInputs) {
                    MerkleManager.sMerkleRoot = oUtil.getMerkleRoot(listWords);
                }
            }
        }
    }
}
