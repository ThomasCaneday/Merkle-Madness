import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.security.SecureRandom;
public class Util {
    public String getMerkleRoot(ArrayList<String> listItems) {

//        Hard coding for 4 items coming in
        MerkleNode oNode1 = new MerkleNode();
        MerkleNode oNode2 = new MerkleNode();
        MerkleNode oNode3 = new MerkleNode();
        MerkleNode oNode4 = new MerkleNode();
        MerkleNode oNode5 = new MerkleNode();
        MerkleNode oNode6 = new MerkleNode();
        MerkleNode oNode7 = new MerkleNode();

//        Build out the Merkle tree
        oNode1.sHash = generateHash(listItems.get(0));
        oNode2.sHash = generateHash(listItems.get(1));
        oNode3.sHash = generateHash(listItems.get(2));
        oNode4.sHash = generateHash(listItems.get(3));

        populateMerkleNode(oNode5, oNode1, oNode2);
        populateMerkleNode(oNode6, oNode3, oNode4);
        populateMerkleNode(oNode7, oNode5, oNode6);

//        Return Merkle Root
        return oNode7.sHash;
    }

    private void populateMerkleNode(MerkleNode oNode, MerkleNode oLeftNode, MerkleNode oRightNode) {
        oNode.oLeft = oLeftNode;
        oNode.oRight = oRightNode;
        oNode.sHash = generateHash(oNode.oLeft.sHash + oNode.oRight.sHash);
    }

    public synchronized String generateHash(String sOriginal){

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] btEncodedhash = digest.digest(
                    sOriginal.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < btEncodedhash.length; i++) {
                sb.append(Integer.toString((btEncodedhash[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch(Exception ex){

            System.out.println("Error generating hash: " + ex.getMessage());
            return null;
        }
    }

    public String promptUser(String sQuestion) {
        JOptionPane oQuestion = new JOptionPane();
        String sAnswer = oQuestion.showInputDialog(sQuestion);
        return sAnswer;
    }

    public void sleepRandomTime(String sThreadName) {
//        Get random number between 3 and 8
        int iSleepTime = new SecureRandom().nextInt(5) + 3;
        System.out.println(sThreadName + " sleeping for " + iSleepTime + " seconds.");
        sleep(iSleepTime);
    }

    public void sleep(int iSeconds) {
        try {
            Thread.sleep(iSeconds * 1000);
        }
        catch (Exception e) {
        }
    }
}
