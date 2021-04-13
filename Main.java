public class Main {
    public static void main(String[] args) {
    var tree = new AVLTree();
    tree.insert(10);
    tree.insert(5);
    tree.insert(8);
    tree.insert(9);
    tree.insert(19);
    System.out.println(tree.isBalanced());//This may return true always cause AVL tree is a self-balancing tree
    }
}
