package util.evolution;

import data.Individual;
import data.Population;
import util.factory.IndividualFactory;
import util.factory.PopulationFactory;

public class Evolution {
    public static Population evolve(Population population, boolean elitism,int ... dimen) {
        Population newPopulation = PopulationFactory.newInstance();

        if (elitism) {
            newPopulation.getIndividuals()[0] = mostAdaptive(population);
        }

        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }

        for (int i = elitismOffset; i < population.size(); i++) {
            Individual first = new Selection().select(population);
            Individual second = new Selection().select(population);
            Individual newIndividual = new Crossover().crossover(first, second);
            newPopulation.getIndividuals()[i] =  newIndividual.clone();
        }

        new Mutation().mutate(newPopulation, dimen);

        newPopulation.setGeneration(population.getGeneration() + 1);

        return newPopulation;
    }

    public static Individual mostAdaptive(Population population) {
        double fitness = 0;
        Individual adaptive = IndividualFactory.newInstance();
        for (Individual individual : population.getIndividuals()) {
            if (individual.getFitness() > fitness) {
                adaptive = individual.clone();
            }
        }
        return adaptive;
    }
}
