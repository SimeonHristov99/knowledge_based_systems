package knapsackProblem;

public class PopulationTest {

    public static void testRandomInit() {
        Population p = new Population(10, Thing.getThings(), 3000);
        System.out.println(p);
    }

    public static void testCulling() {
        System.out.println("Before culling");
        Population p = new Population(10, Thing.getThings(), 3000);
        System.out.println(p);

        System.out.println("\nAfter culling");
        p.selection(5);
        System.out.println(p);
    }

    public static void testBreeding() {
        System.out.println("Old generation");
        Population p = new Population(10, Thing.getThings(), 3000);
        System.out.println(p);

        System.out.println("\nAfter culling");
        p.selection(5);
        System.out.println(p);

        System.out.println("\nNew generation");
        p.nextGeneration(5);
        System.out.println(p);
    }

    public static void main(String[] args) {

        testRandomInit();
        testCulling();
        testBreeding();

    }

}
