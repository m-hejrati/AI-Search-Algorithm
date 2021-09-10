import java.util.LinkedList;

public class AStar {


    LinkedList<Node> frontier;
    LinkedList<Node> explored;
    Node checkNode;
    int num = 0;


    public AStar(){

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

            // find best choice
            int cost = (int) Double.POSITIVE_INFINITY;
            int index = 0;
            for (int i = 0; i < frontier.size(); i++) {
                Node node = frontier.get(i);
                int newCost = node.getCost();
                if (newCost < cost) {
                    cost = newCost;
                    index = i;
                }
            }

            checkNode = frontier.remove(index);

            System.out.println("Check: " + ++num + "    |   " + "Depth: " + checkNode.getDepth());
            checkNode.print();
            System.out.println();
            if (goalTest.nodeGoalCheck(checkNode))
                return true;

            explored.addLast(checkNode);

            checkNode.makeChildren(checkNode);
            for (Node newNode : checkNode.getChildren()) {

                boolean exploredFlag = false;
                for (Node preNode : explored)
                    if (newNode.checkSame(preNode))
                        exploredFlag = true;

                boolean frontFlag = false;
                int removeIndex = 0;
                if (!exploredFlag)
                    for (Node preNode : frontier)
                        if (newNode.checkSame(preNode))
                            if (newNode.getCost() < preNode.getCost())
                                for (int i = 0; i < frontier.size(); i++) {
                                    Node tmpNode = frontier.get(i);
                                    if (tmpNode.checkSame(preNode)) {
                                        removeIndex = i;
                                        frontFlag = true;
                                    }
                                }

                if (frontFlag){
                    frontier.remove(removeIndex);
                    frontier.addLast(newNode);
                }else if(!exploredFlag)
                    frontier.addLast(newNode);
            }
        }
    }

}
