import java.util.LinkedList;


public class BFS {

    LinkedList<Node> frontier;
    LinkedList<Node> explored;
    Node checkNode;
    int num = 0;

    public BFS(){

        frontier = new LinkedList<>();
        explored = new LinkedList<>();
    }


    public boolean solve(Node root){

        GoalTest goalTest = new GoalTest();
        if (goalTest.nodeGoalCheck(root))
            return true;

        frontier.addLast(root);

        while (true){

            if (frontier.isEmpty())
                return false;

            checkNode = frontier.pop();

            System.out.println("Check: " + ++num + "    |   " + "Depth: " + checkNode.getDepth());
            checkNode.print();
            System.out.println();
            if (goalTest.nodeGoalCheck(checkNode))
                return true;

            explored.addLast(checkNode);

            checkNode.makeChildren(checkNode);
            for (Node newNode : checkNode.getChildren()){

                boolean passFlag = false;
                for (Node preNode : frontier)
                    if (newNode.checkSame(preNode))
                        passFlag = true;
                for (Node preNode : explored)
                    if (newNode.checkSame(preNode))
                        passFlag = true;

                if(!passFlag)
                  frontier.addLast(newNode);
            }
        }
    }
}