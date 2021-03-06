package util.factory;

import data.Gene;
import data.Individual;

public class IndividualFactory implements RandomizedInstance<Individual> {
    private static int sChromosomeLength = 64;

    public static Individual newInstance() {
        Gene[] chromosome = new Gene[sChromosomeLength];
        for (int i = 0; i < sChromosomeLength; i++) {
            chromosome[i] = GeneFactory.newInstance();
        }
        return new Individual(chromosome);
    }

    @Override
    public Individual randomInstance(int ... dimen) {
        Gene[] chromosome = new Gene[sChromosomeLength];
        GeneFactory geneFactory = new GeneFactory();
        for (int i = 0; i < sChromosomeLength; i++) {
            chromosome[i] = geneFactory.randomInstance(dimen);
        }
        return new Individual(chromosome);
    }

    public static int getChromosomeLength() {
        return sChromosomeLength;
    }

    public static void setChromosomeLength(int chromosomeLength) {
        sChromosomeLength = chromosomeLength;
    }
}
