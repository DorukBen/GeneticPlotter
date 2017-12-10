import data.Population;
import util.PlotUtils;
import util.evolution.Evolution;
import util.factory.PopulationFactory;

import java.io.File;

class GeneticPlotter {
    private MainView mView;

    private File mSource;
    private int[][] mSourceMatrix;
    private boolean[][] mSourceThreshold;


    GeneticPlotter(File source) {
        mSource = source;
        mSourceMatrix = PlotUtils.getPixelMatrix(mSource);
        mSourceThreshold = PlotUtils.thresholdFilter(mSourceMatrix);

        mView = new MainView(source.getName(), mSourceMatrix[0].length, mSourceMatrix.length);
    }

    void start(float threshold) {
        PopulationFactory populationFactory = new PopulationFactory();
        Population population = populationFactory.randomInstance(mSourceThreshold[0].length, mSourceThreshold.length);
        PlotUtils.calculateFitness(mSourceThreshold, population, mSourceThreshold[0].length, mSourceThreshold.length);

        do{
            System.out.println("Generation: " + population.getGeneration() + " Fitness: "+ Evolution.mostAdaptive(population).getFitness());
            population = Evolution.evolve(population, true, mSourceThreshold[0].length, mSourceThreshold.length);
            PlotUtils.calculateFitness(mSourceThreshold, population, mSourceThreshold[0].length, mSourceThreshold.length);

            mView.update(Evolution.mostAdaptive(population));
        } while(Evolution.mostAdaptive(population).getFitness() < threshold);
    }
}
