package knn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static final String DELIMITER = ",";

    private static final Map<String, Double> genderEncoder = new HashMap<>() {{
        put("F", 1.0);
        put("M", 0.0);
    }};

    private static final Map<String, Integer> bpEncoder = new HashMap<>() {{
        put("HIGH", 2);
        put("NORMAL", 1);
        put("LOW", 0);
    }};

    private static final Map<String, Double> cholesterolEncoder = new HashMap<>() {{
        put("HIGH", 1.0);
        put("NORMAL", 0.0);
    }};

    public static void trainTestSplit(List<List<Double>> bps,
                                      List<String> y,
                                      List<List<Double>> X_train,
                                      List<List<Double>> X_test,
                                      List<String> y_train,
                                      List<String> y_test
    ) {
        int size = bps.size();
        int limit = (int) Math.round(size * 0.8);

        List<Integer> range = IntStream.range(0, size).boxed().collect(Collectors.toList());
        Collections.shuffle(range);

        for (int i = 0; i <= limit; i++) {
            X_train.add( bps.get(range.get(i)) );
            y_train.add( y.get(range.get(i)) );
        }

        for (int i = limit + 1; i < size; i++) {
            X_test.add( bps.get(range.get(i)) );
            y_test.add( y.get(range.get(i)) );
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<List<String>> X = new ArrayList<>();
        List<String> columns = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("drug200.csv"))) {
            String line;
            columns = Collections.singletonList(br.readLine());

            while ((line = br.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                X.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(columns);
        System.out.println(X);

        List<Double> ages = X.stream()
                .map(s -> Double.parseDouble(s.get(0)))
                .collect(Collectors.toList());

        List<Double> genders = X.stream()
                .map(s -> genderEncoder.get(s.get(1)))
                .collect(Collectors.toList());

        List<List<Double>> bps = X.stream()
                .map(s -> {
                    List<Double> zeros = new ArrayList<>(Collections.nCopies(3, 0.0));
                    zeros.set(bpEncoder.get(s.get(2)), 1.0);
                    return zeros;
                })
                .collect(Collectors.toList());

        List<Double> chols = X.stream()
                .map(s -> cholesterolEncoder.get(s.get(3)))
                .collect(Collectors.toList());

        List<Double> ntk = X.stream()
                .map(s -> Double.parseDouble(s.get(4)))
                .collect(Collectors.toList());

        List<String> y = X.stream()
                .map(s -> s.get(5))
                .collect(Collectors.toList());

        System.out.println(ages);
        System.out.println(genders);
        System.out.println(bps);
        System.out.println(chols);
        System.out.println(ntk);
        System.out.println(y);

        int size = bps.size();
        for (int i = 0; i < size; i++) {
            List<Double> d = bps.get(i);

            d.add(ages.get(i));
            d.add(genders.get(i));
            d.add(chols.get(i));
            d.add(ntk.get(i));

            bps.set(i, d);
        }

        System.out.println(bps);

        List<List<Double>> X_train = new ArrayList<>();
        List<List<Double>> X_test = new ArrayList<>();

        List<String> y_train = new ArrayList<>();
        List<String> y_test = new ArrayList<>();

        trainTestSplit(bps, y, X_train, X_test, y_train, y_test);

        KNN model = new KNN(1);
        model.fit(X_train, y_train);

        List<String> y_pred = model.predict(X_test);

        int correct = 0;
        size = y_pred.size();

        for (int i = 0; i < size; i++) {
            if(y_pred.get(i).equals(y_test.get(i))) {
                ++correct;
            }
        }

        System.out.printf("Predicted: %s\n", y_pred);
        System.out.printf("Actual: %s\n", y_test);

        System.out.println(correct);
        System.out.println(size);
        System.out.printf("Accuracy: %f", (double) correct / (double) size);
    }
}
