package knapsackProblem;

import java.util.*;
import java.util.stream.Collectors;

public class Population {

    private ArrayList<Genome> genomes;
    private Thing[] things;
    private int weightLimit;

    public Population(int numberOfIndividuals, Thing[] things, int weightLimit) {
        setGenomes(null);
        setThings(things);
        setWeightLimit(weightLimit);

        while (numberOfIndividuals > 0) {
            genomes.add(new Genome(things.length));
            --numberOfIndividuals;
        }
    }

    public ArrayList<Genome> getGenomes() {
        return genomes;
    }

    public void setGenomes(List<Genome> genomes) {
        this.genomes = new ArrayList<>();

        if (genomes == null) {
            return;
        }

        this.genomes.addAll(genomes);
    }

    public Thing[] getThings() {
        return things;
    }

    public void setThings(Thing[] things) {
        this.things = things;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public void selection(int cullingLimit) {
        genomes.sort(Comparator.comparingInt(o -> o.getFitness(things, weightLimit) * -1));

        List<Genome> l = genomes.subList(0, cullingLimit);

        setGenomes(null);

        genomes.addAll(l);
    }

    public void nextGeneration(int cullingLimit) {
        if (cullingLimit > genomes.size()) {
            System.out.println("ERROR: Culling limit must be smaller than to the number of individuals");
            return;
        }

        List<Genome> nextGenomes = new ArrayList<>();
        selection(cullingLimit);

        Random r = new Random();

        while (cullingLimit * 2 > 0) {
            int p1Idx = 0;
            int p2Idx = 0;

            while (p1Idx == p2Idx) {
                int numIndividuals = genomes.size();
                p1Idx = r.nextInt(numIndividuals);
                p2Idx = r.nextInt(numIndividuals);
            }

            nextGenomes.addAll(Arrays.asList(genomes.get(p1Idx).singlePointCrossover(genomes.get(p2Idx))));

            --cullingLimit;
        }

        setGenomes(nextGenomes);
    }

    public Genome getCurrentBest() {
        genomes.sort(Comparator.comparingInt(o -> o.getFitness(things, weightLimit) * -1));
        return genomes.get(0);
    }

    public boolean stop(Integer bestFitness) {
        if (bestFitness != null) {
            List<Genome> bestGenes = genomes.stream().filter((g) -> g.getFitness(things, weightLimit) == bestFitness).collect(Collectors.toList());

            if (bestGenes.size() != 0) {
                return true;
            }
        }

        int currentFitness = genomes.get(0).getFitness(things, weightLimit);
        for (int i = 1; i < genomes.size(); i++) {
            if (currentFitness != genomes.get(i).getFitness(things, weightLimit)) {
                return false;
            }
        }

        System.out.printf("All individuals have the same fitness of %d\n", currentFitness);

        return true;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s\n",
                genomes,
                Arrays.toString(genomes.stream().map((g) -> g.getFitness(things, weightLimit)).toArray())
        );
    }
}
