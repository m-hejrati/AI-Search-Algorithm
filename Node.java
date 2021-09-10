import java.util.LinkedList;

public class Node {

    private Node parent = null;
    private LinkedList<Node> children;
    private LinkedList<Card>[] lists;
    private int depth = 0;
    private int cost = 0; // depth + heuristic


    public Node(int k){
        lists = new LinkedList[k];
        children = new LinkedList<>();
    }


    // just first time
    public void initiateStacks(String line, int i) {

        lists[i] = new LinkedList<>();

        if (!line.equals("#")) {
            String[] cards = line.split("\\s+");

            for (String s : cards) {
                Card card = new Card(s.charAt(0) - 48, s.charAt(1));
                lists[i].push(card);
            }
        }
    }


    public LinkedList<Card>[] getLists() {
        return lists;
    }
    public LinkedList<Node> getChildren() {
        return children;
    }
    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }


    // print all cards of a node
    public void print(){
        for (LinkedList<Card> list : lists) {
            for (int i = 0; i < list.size(); i++) {
                Card card = list.get(list.size() - i - 1);
                System.out.print(" " + card.getNumber() + card.getColor());
            }
            System.out.print("   |   ");
        }
        System.out.println();
    }


    public boolean checkSame(Node preNode){

        for (int i = 0; i < preNode.getLists().length; i++){

            int size = preNode.getLists()[i].size();
            if (size != lists[i].size())
                return false;

            for(int j = 0 ; j < size; j++){
                if (preNode.getLists()[i].get(j).getNumber() != lists[i].get(j).getNumber())
                    return false;
                if (preNode.getLists()[i].get(j).getColor() != lists[i].get(j).getColor())
                    return false;
            }
        }
        return true;
    }


    public void makeChildren(Node preNode) {

        for (int i = preNode.getLists().length - 1; i >= 0; i--)
//        for (int i = 0; i < preNode.getLists().length; i++)
            for (int j = preNode.getLists().length - 1; j >= 0; j--)
//            for (int j = 0; j < preNode.getLists().length; j++)

                if (i != j) {

                    // make a copy of node
                    Node newNode = new Node(preNode.getLists().length);

                    for (int k = 0; k < lists.length; k++) {
                        newNode.lists[k] = new LinkedList<>();
                        for (int l = 0; l < lists[k].size(); l++) {
                            Card preCard = preNode.lists[k].get(lists[k].size() - l - 1);
                            Card card = new Card(preCard.getNumber(), preCard.getColor());
                            newNode.lists[k].push(card);
                        }
                    }

                    // move last card of each list
                    if (!newNode.getLists()[i].isEmpty()) {
                        Card moveCard = newNode.getLists()[i].pop();
                        Card baseCard = newNode.getLists()[j].peek();
                        if ((baseCard == null) || moveCard.getNumber() < baseCard.getNumber())
                            newNode.getLists()[j].push(moveCard);
                        else
                            newNode.getLists()[i].push(moveCard);


                        newNode.setDepth(preNode.getDepth() + 1);
                        newNode.setCost(newNode.getDepth() + newNode.heuristic());


                        // check if new, add
                        boolean newFlag = true;
                        if (preNode.checkSame(newNode))
                            newFlag = false;
                        for (Node checkNode : children)
                            if (newNode.checkSame(checkNode))
                                newFlag = false;
                        if (newFlag) {
                            preNode.children.push(newNode);
                            newNode.parent = preNode;
                        }
                    }
                }
    }

    private int heuristic() {
        int h1 = 0;
        int h2 = 0;
        int h = 0;
        char color;
        int number;

        for(LinkedList<Card> list : lists){
            if (list.size() > 1) {
                color = list.getFirst().getColor();
                number = list.getFirst().getNumber();

                for (Card card : list) {
                    if (color != card.getColor())
                        h1 ++;
                    color = card.getColor();

                    if (number > card.getNumber())
                        h1 ++;
                    number = card.getNumber();
                }

                h += Math.max(h1, h2);
            }
        }

        return h;
    }
}