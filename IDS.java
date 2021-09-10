import java.util.LinkedList;

public class IDS {

    int startDepth = 0;
    int maxDepth = 5;
//    int maxDepth = (int) Double.POSITIVE_INFINITY;

    LinkedList<Node> frontier;
    LinkedList<Node> explored;
    Node checkNode;
    int num = 0;


    public IDS() {
        frontier = new LinkedList<>();
        explored = new LinkedList<>();
    }


    public boolean solve(Node root){

        boolean result = false;
        for (int i = startDepth ; i <= maxDepth; i ++){
            System.out.println("Start DLS with limit " + i + " ...");
            System.out.println();
            result = DLS(root, i);
            if(result)
                break;
        }
        return result;
    }


    public boolean DLS(Node root, int maxDepth) {

        explored.clear();

        GoalTest goalTest = new GoalTest();
        if (goalTest.nodeGoalCheck(root))
            return true;

        frontier.addFirst(root);

        while (true) {

            if (frontier.isEmpty())
                return false;

            checkNode = frontier.pop();

            System.out.println("Check: " + ++num + "    |   " + "Depth: " + checkNode.getDepth());
            checkNode.print();
            System.out.println();
            if (goalTest.nodeGoalCheck(checkNode))
                return true;

            explored.addLast(checkNode);

            if (checkNode.getDepth() < maxDepth)
                checkNode.makeChildren(checkNode);

            for (Node newNode : checkNode.getChildren()) {

                boolean passFlag = false;
                for (Node preNode : frontier)
                    if (newNode.checkSame(preNode))
                        passFlag = true;
                for (Node preNode : explored)
                    if (newNode.checkSame(preNode))
                        passFlag = true;

                if (!passFlag)
                    frontier.addFirst(newNode);
            }
        }
    }
}
