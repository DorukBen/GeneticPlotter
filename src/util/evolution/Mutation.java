package util.evolution;

import data.Individual;
import data.Population;
import util.factory.GeneFactory;

public class Mutation {
    private static double sDefaultMutationRate = 0.2;

    public void mutate(Population population, int ... dimen) {
        if (sDefaultMutationRate >= Math.random()){
            for (int i = 0; i < population.size(); i++) {
                Individual individual = population.getIndividuals()[i];
                // Select point which is representing gene which will be mutate
                int point = (int) (Math.random() * individual.size());
                 individual.getChromosome()[point] = new GeneFactory().randomInstance(dimen);
            }
        }
    }

    public static double getDefaultMutationRate() {
        return sDefaultMutationRate;
    }

    public static void setDefaultMutationRate(double defaultMutationRate) {
        Mutation.sDefaultMutationRate = defaultMutationRate;
    }
}
