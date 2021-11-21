package knapsackProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Genome {

    private ArrayList<Integer> dna;

    public Genome(int length) {
        setDna(null);
        Random r = new Random();

        while (length > 0) {
            dna.add(r.nextBoolean() ? 1 : 0);
            --length;
        }
    }

    public Genome(ArrayList<Integer> dna) {
        setDna(dna);
    }

    public Genome(List<Integer> dnaP1, List<Integer> dnaP2) {
        setDna(null);
        Stream.of(dnaP1, dnaP2).forEach(this.dna::addAll);
    }

    public ArrayList<Integer> getDna() {
        return dna;
    }

    public void setDna(ArrayList<Integer> dna) {
        this.dna = (dna == null ? new ArrayList<>() : dna);
    }

    public int getFitness(Thing[] things, int weightLimit) {
        if (things.length != dna.size()) {
            System.out.println("ERROR: Things and dna must have equal lengths.");
            return -1;
        }

        int value = 0;
        int weight = 0;

        for (int i = 0; i < things.length; i++) {
            if (dna.get(i) == 1) {
                weight += things[i].getWeight();
                value += things[i].getValue();

                if (weight > weightLimit) {
                    return 0;
                }
            }
        }

        return value;
    }

    public Genome[] singlePointCrossover(Genome other) {
        if (other == null) {
            System.out.println("ERROR: Other genome must not be null");
            return null;
        }

        Genome[] result = new Genome[]{this, other};
        Random r = new Random();

        if(r.nextBoolean()) {
            return result;
        }

        int crossOverIdx = r.nextInt(this.dna.size());

        result[0] = new Genome(this.dna.subList(0, crossOverIdx), other.dna.subList(crossOverIdx, other.dna.size()));
        result[1] = new Genome(other.dna.subList(0, crossOverIdx), this.dna.subList(crossOverIdx, this.dna.size()));

        return result;
    }

    public void mutate() {
        Random r = new Random();

        if(r.nextInt(100) > 94) {
            return;
        }

        for (int i = 0; i < dna.size(); i++) {
            if(r.nextBoolean()) {
                dna.set(i, (dna.get(i) == 0 ? 1 : 0));
            }
        }
    }

    @Override
    public String toString() {
        return dna.toString();
    }
}
