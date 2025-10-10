public class AVLNodeString {
    private String content;
    private byte balance;
    private AVLNodeString left, right;
    public AVLNodeString(String content) {
        this.content = content;
        this.balance = 0;
        this.left = null;
        this.right = null;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public byte getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = (byte) balance;
    }
    public AVLNodeString getLeft() {
        return left;
    }
    public void setLeft(AVLNodeString left) {
        this.left = left;
    }
    public AVLNodeString getRight() {
        return right;
    }
    public void setRight(AVLNodeString right) {
        this.right = right;
    }
}
