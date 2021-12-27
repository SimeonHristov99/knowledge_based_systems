package knn;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class KNN {

    private final int k;
    private List<List<Double>> X;
    private List<String> y;

    public KNN(int k) {
        this.k = Math.max(k, 1);
    }

    public static Double euclideanDistance(List<Double> s1, List<Double> s2) {
        int size = s1.size();
        double s = 0;

        for (int i = 0; i < size; i++) {
            s += Math.pow(s1.get(i) - s2.get(i), 2);
        }

        return Math.sqrt(s);
    }

    public void fit(List<List<Double>> X, List<String> y) {
        this.X = X;
        this.y = y;
    }

    private String predictHelper(List<Double> x) {
        // Get k nearest samples.
        List<String> nearestN = X.stream()
                .sorted(Comparator.comparingDouble(s -> euclideanDistance(s, x)))
                .limit(k)
                .map(s -> y.get(X.indexOf(s)))
                .collect(Collectors.toList());

//        System.out.printf("Nearest Neighbous: %s\n", nearestN);

        // Majority vote
        List<String> distinct = nearestN.stream()
                .distinct()
                .sorted(Comparator.comparingInt(s -> Collections.frequency(nearestN, s)))
                .collect(Collectors.toList());

        return distinct.get(distinct.size() - 1);
    }

    public List<String> predict(List<List<Double>> X) {
        return X.stream().map(this::predictHelper).collect(Collectors.toList());
    }
}
