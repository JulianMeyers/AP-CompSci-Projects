/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author harlan.howe
 */
public class BST {

    private TreeNode root;
    
    public BST()
    {
        ArrayList<String> dictionary = new ArrayList<String>();
        File inputFile = new File("word_list_moby_crossword.flat.txt");
        try
        {
            Scanner input = new Scanner(inputFile);
            int i = 0;
            while (input.hasNext())
            {
                dictionary.add(input.next());
                i++;
                if (i%1000 == 0)
                {
                    System.out.println("Loaded "+i+" words.");
                }
            }
            input.close();
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("File not found.");
        }
        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("How many words should I add to the tree? ");
        int numWords = keyboard.nextInt();
        
        for (int i=0; i<numWords; i++)
        {
            int which = (int)(Math.random()*dictionary.size());
            System.out.println(dictionary.get(which));
            add(dictionary.get(which));
        }
    
        System.out.println("------------------\n"+this.toString());

        generateReverseOrder(root);

        System.out.println(calculateTotalNumbers(root,0));

        keyboard.nextLine();
        String searchTerm = keyboard.nextLine();
        System.out.println(containsString(root, searchTerm));

        System.out.println(checkDepth(root, 0, 0));
    }
    
    public String toString()
    {
        
        return subString("",root);
    }
        
    public String subString(String prefix, TreeNode subroot)
    {
        if (subroot == null)
            return "";
        else 
        {
            String result = "";
            result+= subString(prefix+"\t",subroot.getLeft());
            result+= prefix+subroot.getValue()+"\n";
            result+= subString(prefix+"\t",subroot.getRight());
            return result; 
        }
    }
    public void add(String s)
    {
        if (root == null)
            root = new TreeNode(s);
        else
            addToSubTree(s,root);
    }
    
    public void addToSubTree(String s, TreeNode subroot)
    {
        int rel = s.compareTo(subroot.getValue());
        if (rel<0)
        {
            if (subroot.getLeft() == null)
                subroot.setLeft(new TreeNode(s));
            else
                addToSubTree(s,subroot.getLeft());
        }
        else
        {
            if (subroot.getRight() == null)
                subroot.setRight(new TreeNode(s));
            else
                addToSubTree(s,subroot.getRight());
        }
    }

    public void generateReverseOrder(TreeNode subroot) {
        //Print left stuff, then print self, then print right stuff
        if (subroot.getLeft() != null) {
            generateReverseOrder(subroot.getLeft());
        }
        System.out.println(subroot.getValue());
        if (subroot.getRight() != null) {
            generateReverseOrder(subroot.getRight());
        }
    }

    public int calculateTotalNumbers(TreeNode subroot, int currentSum) {
        //Add left stuff, then add self, then add right stuff
        int newSum = currentSum;
        if (subroot.getLeft() != null) {
            newSum = calculateTotalNumbers(subroot.getLeft(), newSum);
        }
        newSum += subroot.getValue().length();
        if (subroot.getRight() != null) {
            newSum = calculateTotalNumbers(subroot.getRight(), newSum);
        }
        return newSum;
    }

    public boolean containsString(TreeNode subroot, String searchString) {
        //Check Self, then check right, then check left
        if (subroot.getValue().equals(searchString)) {
            return true;
        }
        if (subroot.getRight() != null) {
            if (containsString(subroot.getRight(), searchString)) {
                return true;
            }
        }
        if (subroot.getLeft() != null) {
            if (containsString(subroot.getLeft(), searchString)) {
                return true;
            }
        }
        return false;
    }

    public int checkDepth (TreeNode subroot, int currentDepth, int greatestDepth) {
        //Check left, if current depth > greatest depth, it = greatest depth
        //Check right is same, but right
        //Check self current depth + 1
        int newMax = greatestDepth;
        if (subroot.getLeft() != null) {
            newMax = checkDepth(subroot.getLeft(), currentDepth + 1, newMax);
        }
        if (subroot.getRight() != null) {
            newMax = checkDepth(subroot.getRight(), currentDepth + 1, newMax);
        }
        if (currentDepth > newMax) {
            newMax = currentDepth;
        }
        return newMax;
    }


    
    
    
    
    
}
