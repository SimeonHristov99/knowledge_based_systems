package knapsackProblem;

public class Main {

    public static void test1(int numberOfInidividuals, int epochs) {
        int cullingLimit = numberOfInidividuals / 2;

        Thing[] things = Thing.getThings();
        int bestValue = 780;
        int weightLimit = 3235;

        Population p = new Population(numberOfInidividuals, things, weightLimit);

        for (int i = 0; i < epochs; i++) {
            System.out.println(p);
            p.nextGeneration(cullingLimit);

            if(p.stop(bestValue)) {
                System.out.printf("\n\n\nEpochs needed: %d\n", i);
                System.out.println("Generation:");
                System.out.println(p);
                System.out.printf("Answer: %s\n", p.getCurrentBest());
                return;
            }
        }

        System.out.printf("Value found: %s", p.getCurrentBest());
        System.out.printf("Couldn't reach an optimal value for %d epochs", epochs);
        System.out.println("Last generation:");
        System.out.println(p);
    }

    public static void test2(int numberOfInidividuals, int epochs) {
        int cullingLimit = numberOfInidividuals / 2;

        Thing[] things = Thing.getMoreThings();
        int bestValue = 1410;
        int weightLimit = 3648;

        Population p = new Population(numberOfInidividuals, things, weightLimit);

        for (int i = 0; i < epochs; i++) {
            System.out.println(p);
            p.nextGeneration(cullingLimit);

            if(p.stop(bestValue)) {
                System.out.printf("\n\n\nEpochs needed: %d\n", i);
                System.out.println("Generation:");
                System.out.println(p);
                System.out.printf("Answer: %s\n", p.getCurrentBest());
                return;
            }
        }

        System.out.printf("Value found: %s", p.getCurrentBest());
        System.out.printf("Couldn't reach an optimal value for %d epochs", epochs);
        System.out.println("Last generation:");
        System.out.println(p);
    }

    public static void main(String[] args) {

        System.out.println("START OF TEST 1");
        test1(15, 15); // should find only 1s
        System.out.println("END OF TEST 2");


        System.out.println("\n\n\nSTART OF TEST 2");
        test2(20, 50); // should find only 1s
        System.out.println("END OF TEST 2");
    }

}
