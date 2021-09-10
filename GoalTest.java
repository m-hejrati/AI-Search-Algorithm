import java.util.LinkedList;

public class GoalTest {

    public GoalTest(){

    }


    // get a node and check if it is goal or not, yes return true, no return false
    public boolean nodeGoalCheck(Node node) {

        for (int i = 0; i < node.getLists().length; i++)
            if (!listGoalCheck(node.getLists()[i]))
                return false;

        return true;
    }


    // check a list to be descending and have the same color
    public boolean listGoalCheck(LinkedList<Card> list){

        if (list.isEmpty())
            return true;

        if (list.size() == 1)
            return true;

        Card card = (Card) list.peek();
        char color = card.getColor();
        int number = card.getNumber();

        for(Card newCard : list)

            if (color != newCard.getColor())
                return false;
            else if (number > newCard.getNumber())
                return false;
            else
                number = newCard.getNumber();

        return true;
    }
}