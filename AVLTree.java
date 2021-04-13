public class AVLTree {
    private class AVlNode{
      private int height;
      private int value;
      private AVlNode leftchild;
      private AVlNode rightchild;
      public AVlNode(int value){
          this.value=value;
      }
      @Override
      public String toString(){
          return "Value= "+this.value;
      }
    }
    private AVlNode root;
    public void insert(int value){
        root = insert(root,value);
    }
    public AVlNode insert(AVlNode root,int value){
        if(root==null)
            return new AVlNode(value);
        if(value<root.value)
            root.leftchild=insert(root.leftchild,value);
        else
            root.rightchild=insert(root.rightchild,value);
        setHeight(root);

        return balance(root);
    }
    private AVlNode balance(AVlNode root){
        if(isLeftHeavy(root)){//left child,right-subtree te balance harale left rotate on root.leftchild then right rotate on root
            if(balancefactor(root.leftchild)<0){
               root.leftchild = rotateLeft(root.leftchild);//same explanation but opposite of rightheavy
            }
            return rotateRight(root);
        }
        if(isRightHeavy(root)){//right child ,left sub-tree te balance harale hole right rotation on root.rightchild, then left rotate on root
           if(balancefactor(root.rightchild)>0){
               root.rightchild=rotateRight(root.rightchild);
               /*Here is the very extreme tricky part of this whole class
               10(root)
                 30(root.rightchild)
               20
               if we perform rotateRight(30) then it will look like below:-
               10
                 20(newRoot)
                   30(previously root.rightchild but now newRoot will be root.rightchild er leftchild)
                   that means 20 should be set to rightCHild of root 10 , for this logic will be root.rightChild = rotateRight(root.rightchild);
               * */
           }
            return rotateLeft(root);
        }
        return root;
    }
    private AVlNode rotateLeft(AVlNode root){
        var newRoot = root.rightchild;//setting newroot
        root.rightchild = newRoot.leftchild;
        newRoot.leftchild = root;
        //now we have to te reset these two roots height
        /*
        *  root.height = Math.max(height(root.leftchild),height(root.rightchild))+1;
        * This logic will be used in multiple places. So we have to write e helper method of calculating the new roots height
        * */
        setHeight(root);
        setHeight(newRoot);
        return newRoot;// returning the new root
    }
    private AVlNode rotateRight(AVlNode root){
        var newRoot = root.leftchild;//setting newroot
        root.leftchild = newRoot.rightchild;
        newRoot.rightchild = root;
        //now we have to te reset these two roots height
        /*
         *  root.height = Math.max(height(root.leftchild),height(root.rightchild))+1;
         * This logic will be used in multiple places. So we have to write e helper method of calculating the new roots height
         * */
        setHeight(root);
        setHeight(newRoot);
        return newRoot;// returning the new root
    }
    private void setHeight(AVlNode node){
        node.height = Math.max(height(node.leftchild),height(node.rightchild))+1;
    }
    private boolean isLeftHeavy(AVlNode node){
        return balancefactor(node)>1;
    }
    private boolean isRightHeavy(AVlNode node){
        return balancefactor(node)<-1;
    }
    private int balancefactor(AVlNode node){
        // for empty tree it is balanced already
        // otherwise calculate the differences of left and right subtree and return
        return (node==null)? 0:height(node.leftchild)-height(node.rightchild);
    }
    private int height(AVlNode node){
    return (node==null) ? -1: node.height;
    }
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(AVlNode root) {
        if (root == null)
            return true;

        var balanceFactor = height(root.leftchild) - height(root.rightchild);

        return Math.abs(balanceFactor) <= 1 &&
                isBalanced(root.leftchild) &&
                isBalanced(root.rightchild);
    }
    public boolean isPerfect() {
        return size() == (Math.pow(2, height(root) + 1) - 1);
    }
    public int size() {
        return size(root);
    }

    private int size(AVlNode root) {
        if (root == null)
            return 0;

        if (isLeaf(root))
            return 1;

        return 1 + size(root.leftchild) + size(root.rightchild);
    }
    private boolean isLeaf(AVlNode node) {
        return node.leftchild == null && node.rightchild == null;
    }
}