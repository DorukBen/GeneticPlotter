package util;

import com.sun.istack.internal.Nullable;
import data.Individual;
import data.Population;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlotUtils {

    @Nullable
    public static int[][] getPixelMatrix(File file) {
        try {
            BufferedImage image = ImageIO.read(file);

            int width = image.getWidth();
            int height = image.getHeight();
            int[][] result = new int[height][width];

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    result[row][col] = image.getRGB(col, row);
                }
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean[][] thresholdFilter(int[][] image) {
        boolean[][] result = new boolean[image.length][image[0].length];
        float average = 0;
        for (int[] row : image) {
            for (int column : row) {
                average += column;
            }
        }

        average = average / (image.length * image[0].length);

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                result[i][j] = image[i][j] > average;
            }
        }

        return result;
    }

    public static float calculateSimilarity(boolean[][] source, boolean[][] plot) {
        float similarity = 0;
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[0].length; j++) {
                if (source[i][j] == plot[i][j]) {
                    similarity++;
                } else {
                    similarity -= 2;
                }
            }
        }

        if (similarity > 0) {
            return (similarity / (source.length * source[0].length)) * 100;
        } else {
            return 0;
        }
    }

    private static void plotIndividual(Individual individual, int ... dimen) {
        BufferedImage plotImage = new BufferedImage(dimen[0], dimen[1], BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = plotImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, dimen[0], dimen[1]);

        g.setColor(Color.BLACK);
        for (int i = 0; i < individual.size(); i++) {
            g.drawLine(
                    ((int)individual.getChromosome()[i].getLine().getX1()),
                    ((int)individual.getChromosome()[i].getLine().getY2()),
                    ((int)individual.getChromosome()[i].getLine().getX2()),
                    ((int)individual.getChromosome()[i].getLine().getY1())
            );
        }

        g.dispose();

        File imageFile = new File("template.png");
        try {
            ImageIO.write(plotImage, "PNG", imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void calculateFitness(boolean[][] source, Population population, int ... dimen) {
        for (int i = 0; i < population.size(); i++) {
            plotIndividual(population.getIndividuals()[i], dimen);

            int[][] plotMatrix = getPixelMatrix(new File("template.png"));
            boolean[][] plotThreshold = thresholdFilter(plotMatrix);
            population.getIndividuals()[i].setFitness(calculateSimilarity(source, plotThreshold));
        }
    }
}
