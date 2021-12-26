package knapsackProblem;

public class Thing {

    private final String name;
    private final int value;
    private final int weight;

    public Thing(String name, int value, int weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    public static Thing[] getThings() {

        // Total value : 780
        // Total weight: 3235

        return new Thing[]{
                new Thing("Laptop", 500, 2200),
                new Thing("Headphones", 150, 160),
                new Thing("Coffee Mug", 60, 350),
                new Thing("Notepad", 40, 333),
                new Thing("Water Bottle", 30, 192),
        };
    }

    public static Thing[] getMoreThings() {

        // Total value : 1410
        // Total weight: 3648

        return new Thing[]{
                new Thing("Laptop", 500, 2200),
                new Thing("Headphones", 150, 160),
                new Thing("Coffee Mug", 60, 350),
                new Thing("Notepad", 40, 333),
                new Thing("Water Bottle", 30, 192),
                new Thing("Mints", 5, 25),
                new Thing("Socks", 10, 38),
                new Thing("Tissues", 15, 80),
                new Thing("Phone", 500, 200),
                new Thing("Baseball Cap", 100, 70),
        };
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name;
    }
}
