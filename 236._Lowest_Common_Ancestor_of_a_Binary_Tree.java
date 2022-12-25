/*236. Lowest Common Ancestor of a Binary Tree*/
/*

***Lowest Common Ancestor in a Binary Tree By Storing paths from root to n1 and root to n2: The idea of this approach is to store the path from the root to n1 and root to n2 in two separate data structures. Then look simultaneously into the values stored in the data structure, and look for the first mismatch.

***Illustration:
Path from root to 5 = { 1, 2, 5 }
Path from root to 6 = { 1, 3, 6 }

1.We start checking from 0 index. As both of the value matches( pathA[0] = pathB[0] ), we move to the next index.
2.pathA[1] not equals to pathB[1], there’s a mismatch so we consider the previous value. 
3.Therefore the LCA of (5,6) = 1

***Follow the steps below to solve the problem:

i.Find a path from the root to n1 and store it in a vector or array. 
ii.Find a path from the root to n2 and store it in another vector or array. 
iii.Traverse both paths till the values in arrays are the same. Return the common element just before the mismatch. 



*/ 

class Solution
{
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        //Find a path from the root to n1 and store it in a vector or array. 
        ArrayList<TreeNode> path1 = new ArrayList<>();
        searchBT(root, p, path1);
       //Find a path from the root to n2 and store it in another vector or array. 
        ArrayList<TreeNode> path2 = new ArrayList<>();
        searchBT(root, q, path2);
        
        // path1 and path2 are reverse paths
        
        int i = path1.size() - 1;
        int j = path2.size() - 1;
        TreeNode ans = null;
        
       //Traverse both paths till the values in arrays are the same. Return the common element just before the mismatch. 
        while(i >= 0 && j >= 0)
        {
            if(path1.get(i) == path2.get(j)) ans = path1.get(i);
            else break;
            i--;
            j--;
        }
        
        return ans;
    }
     // Finds the path from root node to given root of the
    // tree, Stores the path in a vector path[], returns
   // true if path exists otherwise false
    boolean searchBT(TreeNode root, TreeNode key, ArrayList<TreeNode> path)
    {
         // base case
        if(root == null)
            return false;
   
        if(root == key) 
        {
            // Store this node . The node will be removed if
           // not in path from root to n.
            path.add(root);
            return true;
        }
        
         // If not present in subtree rooted with root,
        // remove root from path[] and return false
        boolean res = searchBT(root.left, key, path) || searchBT(root.right, key, path);
        if(res == true)
            path.add(root);
        return res;
    }
    
    
}
// TC : O(N) as the method does a simple tree traversal in a bottom-up fashion. 
// and 
// SC : O(H) where H is the height of the tree.
// (Recursive space and path array) 
