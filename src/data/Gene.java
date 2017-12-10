package data;

import java.awt.geom.Line2D;

public class Gene {
    private Line2D mLine;

    public Gene(Line2D line) {
        this.mLine = line;
    }

    public Line2D getLine() {
        return mLine;
    }

    public void setLine(Line2D line) {
        this.mLine = line;
    }

    @Override
    public Gene clone(){
        return new Gene(this.mLine);
    }

    @Override
    public String toString() {
        return mLine.toString();
    }
}
