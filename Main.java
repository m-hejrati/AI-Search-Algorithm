import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        int k;
        int m;
        int n;

        Scanner scanner = new Scanner(System.in);

        k = scanner.nextInt();
        m = scanner.nextInt();
        n = scanner.nextInt();
        scanner.nextLine();

        Node root = new Node(k);

        for (int i = 0; i < k; i++){
            String line = scanner.nextLine();
            root.initiateStacks(line, i);
        }

//        BFS bfs = new BFS();
//        System.out.println(bfs.solve(root));

//        IDS ids = new IDS();
//        System.out.println(ids.solve(root));

//        AStar aStar = new AStar();
//        System.out.println(aStar.solve(root));

    }
}
