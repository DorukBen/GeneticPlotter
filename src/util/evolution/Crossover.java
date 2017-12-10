package util.evolution;

import data.Individual;
import util.factory.IndividualFactory;

public class Crossover {
    private static double sDefaultUniformRate = 0.1;

    public Individual crossover(Individual first, Individual second) {
        // Create new individual instance from factory
        Individual result = IndividualFactory.newInstance();

        // Perform possible crossover over all genes
        for (int i = 0; i < result.size(); i++) {
            if (Math.random() <= sDefaultUniformRate) {
                result.getChromosome()[i].setLine(first.getChromosome()[i].getLine());
            } else {
                result.getChromosome()[i].setLine(second.getChromosome()[i].getLine());
            }
        }
        return result;
    }

    public static double getDefaultUniformRate() {
        return sDefaultUniformRate;
    }

    public static void setDefaultUniformRate(double uniformRate) {
        Crossover.sDefaultUniformRate = uniformRate;
    }
}
