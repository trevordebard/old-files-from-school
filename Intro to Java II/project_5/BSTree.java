package bstreedemo;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * A binary search tree <br>
  * Requires JDK 1.8 for Function* 
  * @author Duncan
  * #author YOUR NAME
 * @param <E> the tree data type
 * @since DATE MODIFIED BY YOU
 * @see BSTreeAPI
 */
public class BSTree<E extends Comparable<E>> implements BSTreeAPI<E>
{
   /**
    * the root of this tree
    */
   private Node root;
   /**
    * the number of nodes in this tree
    */
   private int size;
   /**
    * A node of a tree stores a data item and references
    * to the child nodes to the left and to the right.
    */    
   private class Node
   {
      /**
       * the data in this node
       */
      public E data;
      /**
       * A reference to the left subtree rooted at this node.
       */
      public Node left;
      /**
       * A reference to the right subtree rooted at this node
       */
      public Node right;
   } 
   /**
    *   Constructs an empty tree
    */      
   public BSTree()
   {
      root = null;
      size = 0;
   }
   @Override
   public boolean isEmpty()
   {
      return size == 0;
   }
   
   @Override
   public void insert(E item)
   {
      Node newNode = new Node();
      newNode.data = item;
      if (size == 0)
      {
         root = newNode;
         size++;
      }
      else
      {
         Node tmp = root;
         while (true)
         {
            int d = tmp.data.compareTo(item);
            if (d==0)
            { /* Key already exists. (update) */
               tmp.data = item;
               return;
            }
            else if (d>0)
            {
               if (tmp.left == null)
               { /* If the key is less than tmp */
                  tmp.left = newNode;
                  size++;
                  return;
               }
               else
               { /* continue searching for insertion pt. */
                  tmp = tmp.left;
               }
            }
            else
            {
               if (tmp.right == null)
               {/* If the key is greater than tmp */
                  tmp.right = newNode;
                  size++;
                  return;
               }
               else
               { /* continue searching for insertion point*/
                  tmp = tmp.right;
               }
            }
         }
      }
   }

   @Override
   public boolean inTree(E item)
   {
      return search(item) != null;
   }

   @Override
   public void remove(E item)
   {      
      Node nodeptr = search(item);
      if (nodeptr != null)
      {
         remove(nodeptr);
         size--;
      }
   }

   @Override
   public void inorderTraverse(Function func)
   {
      inorderTraverse(root,func);
   }
   
   @Override
   public E retrieve(E key) throws NoSuchElementException
   {      
      if (size == 0)
         throw new NoSuchElementException("Non-empty tree expected on retrieve().");
      Node nodeptr = search(key);
      if (nodeptr == null)
         throw new NoSuchElementException("Existent key expected on retrieve().");
      return nodeptr.data;
   }
     
   @Override
   public int size()
   {
       return size;
   }   
  
   /**
    * A recursive auxiliary method for the inorderTraver method that
    * @param node a reference to a Node object
    * @param func a function that is applied to the data in each
    * node as the tree is traversed in order.
    */
   private void inorderTraverse(Node node, Function func)
   {
      if (node != null)
      {
         inorderTraverse(node.left,func); 
         func.apply(node.data);         
         inorderTraverse(node.right,func);
      }
   }

   /**
    * An auxiliary method that support the remove method
    * @param node a reference to a Node object in this tree
    */
   private void remove(Node node)
   {
      E theData;
      Node parent, replacement;
      parent = findParent(node);
      if ((node.left != null) && (node.right != null))
      {
         replacement = node.right;
         while (replacement.left != null)
            replacement = replacement.left;
         theData = replacement.data;
         remove(replacement);
         node.data = theData;
      }
      else
      {
         if ((node.left == null) && (node.right == null))
            replacement = null;
         else if (node.left == null)
            replacement = node.right;
         else
            replacement = node.left;
         if (parent==null)
            root = replacement;
         else if (parent.left == node)
            parent.left = replacement;
         else
            parent.right = replacement;
      }
   }

   /**
    * An auxiliary method that supports the search method
    * @param key a data key
    * @return a reference to the Node object whose data has the specified key.
    */
   private Node search(E key)
   {
      Node current = root;
      while (current != null)
      {
         int d = current.data.compareTo(key);
         if (d == 0)
            return current;
         else if (d > 0)
            current = current.left;
         else
            current = current.right;
      }
      return null;
   }

   /**
    * An auxiliary method that gives a Node reference to the parent node of
    * the specified node
    * @param node a reference to a Node object
    * @return a reference to the parent node of the specified node
    */
   private Node findParent(Node node)
   {
      Node tmp = root;
      if (tmp == node)
         return null;
      while(true)
      {
         assert tmp.data.compareTo(node.data) != 0;
         if (tmp.data.compareTo(node.data)>0)
         {
            /* this assert is not needed but just
               in case there is a bug         */
            assert tmp.left != null;
            if (tmp.left == node)
               return tmp;
            else
               tmp = tmp.left;
         }
         else
         {
            assert tmp.right != null;
            if (tmp.right == node)
               return tmp;
            else
               tmp = tmp.right;
         }
      }
   }

    @Override
    public String toString()
    {
        if (root == null)
        {
            return "{}";
        }
        if(root.left == null)
        {
             if(root.right == null)
             {
                 return "{" + "(" + root.data + ",*,*)}";
             }
             return "{" + "(" + root.data + ",*," + root.right.data+")" + toString(root.right) + "}";
        }
        else
        {
            if(root.right == null)
            {
                return "{" + "(" + root.data + "," + root.left.data + ",*)" +toString(root.left) + "}";
            }
            return "{" + "(" + root.data + "," + root.left.data + "," + root.right.data + ")" 
                    + toString(root.left) + toString(root.right) + "}";
        }
    }
    /**
    * An auxiliary method that recursively generates the string * representation of this tree as a set of triples
    * (node, leftChild, rightChild), in pre-order.
    * @param node a root of a subtree.
    */
    private String toString(Node node)
    {
         
         if(node.left == null)
         {
             if(node.right == null)
                 return ",(" + node.data + ",*,*)";
             return ",(" + node.data + ",*," + node.right.data + ")" + toString(node.right);
         }
         else
         {
             if(node.right == null)
                 return ",(" + node.data + "," + node.left.data + ",*)" + toString(node.left);
             return ",(" + node.data + "," + node.left.data + "," + node.right.data + ")" + toString(node.left) + toString(node.right);
         }
    }
   
   @Override
   public int height()
   {
       if(root == null)
           return -1;
       return height(root);
   }
   /**
    * An auxiliary method that recursively determines the height * of a subtree rooted at the specified node.
    * @param node a root of a subtree.
    */
   private int height(Node node)
   {
        if (node == null)
           return -1;
        return 1 + Math.max(height(node.left), height(node.right));
   }
   @Override
   public boolean isPerfect()
   {
       if(root == null)
           return true;
       return isPerfect(root);
   }
   /**
    * An auxiliary method that recursively determines whether
    * the subtree rooted at the specified node is perfect.
    * @param node a root of a subtree
    * @return true if the tree is perfect, false otherwise
    */
   private boolean isPerfect(Node node)
   {
        if (node == null || size == 1)
            return true;
        return isPerfect(node.left) && isPerfect(node.right) && height(node.left) == height(node.right);
   }
   @Override
   public boolean isComplete()
   {
       if(root == null || size == 1)
           return true;
       return isComplete(root);
   }
   /**
    * An auxiliary method that recursively determines whether the subtree 
    * rooted at the specified node is complete.
    * @param node a root of a subtree
    * @return true if the tree is complete, false otherwise
    */
   private boolean isComplete(Node node)
   {
       if(node == null)
           return true;
       if(height(node.left) - height(node.right) == 1)
           return isPerfect(node.right) && isComplete(node.left);
       return isPerfect(node);
   }
   @Override
   public int diameter()
   {
       if(root == null)
           return 0;
       return diameter(root);
   }
   /**
    * An auxiliary method that recursively computes the diameter
    * of the subtree rooted at the specified node.
    * @param node a root of a subtree
    * @return the diameter of the subtree rooted at the specified node 
    */
   private int diameter(Node node)
   {
       if (node == null)
            return 0;
       return Math.max(3 + height(node.left) + height(node.right), 
               Math.max(diameter(node.left), diameter(node.right)));
    }
}