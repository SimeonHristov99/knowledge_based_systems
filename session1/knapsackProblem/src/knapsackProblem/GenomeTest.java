package knapsackProblem;

import java.util.ArrayList;

public class GenomeTest {

    public static void testRandomInit() {
        System.out.println(new Genome(5));
    }

    public static void testCrossover() {
        Genome p1 = new Genome(new ArrayList<>() {{
            add(0);
            add(0);
            add(0);
            add(1);
            add(1);
        }});

        Genome p2 = new Genome(new ArrayList<>() {{
            add(1);
            add(1);
            add(1);
            add(0);
            add(0);
        }});

        System.out.println(p1);
        System.out.println(p2);

        Genome[] children = p1.singlePointCrossover(p2);

        System.out.println(children[0]);
        System.out.println(children[1]);
    }

    public static void testGetFitness(Thing[] things) {
        Genome p1 = new Genome(new ArrayList<>() {{
            add(1);
            add(1);
            add(1);
            add(1);
            add(1);
        }});

        Genome p2 = new Genome(new ArrayList<>() {{
            add(0);
            add(1);
            add(1);
            add(1);
            add(1);
        }});

        System.out.println(p1.getFitness(things, 3235)); // 780
        System.out.println(p1.getFitness(things, 3000)); // 0

        System.out.println(p2.getFitness(things, 3235)); //  280
        System.out.println(p2.getFitness(things, 3235 - 2200)); // 280
        System.out.println(p2.getFitness(things, 3235 - 2201)); // 0
    }

    public static void testMutation() {
        Genome g = new Genome(5);
        System.out.printf("BEFORE: %s\n", g);

        g.mutate();
        System.out.printf("AFTER: %s\n", g);
    }

    public static void main(String[] args) {

        testRandomInit();
        testCrossover();
        testGetFitness(Thing.getThings());
        testMutation();

    }

}
