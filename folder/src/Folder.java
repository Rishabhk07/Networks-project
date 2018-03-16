
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
/**
 * Created by rishabhkhanna on 03/03/18.
 */
public class Folder {

    static ArrayList<Integer> ids = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    ArrayList<Integer> shareCowId = new ArrayList<>();
    ArrayList<Integer> confCowId = new ArrayList<>();
    int Q = sc.nextInt();
    int M = sc.nextInt();
    int N = sc.nextInt();
    static ArrayList<Integer> thisSharedID = null;
    ArrayList<Integer> thisConfiID = null;
    ArrayList<Integer> folders = new ArrayList<>();
    Node<Integer> folderTree = null;
    public static void main(String[] args) {



        for(int i =0;i < M;i ++ ){

            thisSharedID.add(sc.nextInt());

            int thisK = sc.nextInt();
            for(int j = 0 ; j < thisK ;j++){
                shareCowId.add(sc.nextInt());
            }
        }
        for(int i =0;i < N;i ++ ){

            thisConfiID.add(sc.nextInt());
            int thisK = sc.nextInt();
            for(int j = 0 ; j < thisK ;j++){
                confCowId.add(sc.nextInt());
            }
        }

        int G = sc.nextInt();

        for (int i = 0 ;i < G ;i++){
            int u =  sc.nextInt();
            int v = sc.nextInt();
            if(i == 0){
                folderTree = new Node<Integer>(u);
            }
            Node<Integer> thisParent = new Node<Integer>(u);
            Node<Integer> thisChild = new Node<Integer>(v,thisParent);

        }

        List<Node<Integer>> tree = folderTree.getChildren();

        ArrayList<Integer> geti = checkChild(tree);

        for(int i = 0; i < geti.size() ;i++){
            System.out.print(geti.get(i));
        }

    }

    public static ArrayList<Integer> checkChild(List<Node<Integer>> tree){
        for (int i =0 ; i < tree.size() ;i ++ ){
            int thisId = tree.get(i).getData();
            List<Node<Integer>> child = tree.get(i).getChildren();
            for(int j = 0 ; j < child.size() ;j++){
                int thisChild = child.get(j).getData();

                boolean ifShared = thisSharedID.contains(thisChild);

                if(!ifShared){
                    List<Node<Integer>> childOfChild = child.get(i).getChildren();
                    for(int k = 0; k < childOfChild.size(); k++){
                        if(childOfChild.get(i).isLeaf()){
                            ids.add(thisId);
                            return null;
                        }else{
                            checkChild(child.get(i).getChildren());
                        }
                    }
                }
            }

        }
        return ids;
    }




}




class Tree<T> {
    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>();
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
    }

    public static class Node<T> {
        private T data;
        private Node<T> parent;
        private List<Node<T>> children;
    }
}



class Node<T> {



    private List<Node<T>> children = new ArrayList<Node<T>>();
    private Node<T> parent = null;
    private T data = null;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setParent(Node<T> parent) {
        parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(T data) {
        Node<T> child = new Node<T>(data);
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        if(this.children.size() == 0)
            return true;
        else
            return false;
    }

    public void removeParent() {
        this.parent = null;
    }
}