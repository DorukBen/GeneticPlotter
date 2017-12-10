package util.factory;

import data.Gene;

import java.awt.geom.Line2D;

public class GeneFactory implements RandomizedInstance<Gene> {
    public static Gene newInstance() {
        return new Gene(new Line2D.Double());
    }

    @Override
    public Gene randomInstance(int ... dimen) {
        return new Gene(new Line2D.Double(
                (int) (Math.random() * dimen[0]),
                (int) (Math.random() * dimen[1]),
                (int) (Math.random() * dimen[0]),
                (int) (Math.random() * dimen[1])
        ));
    }
}
