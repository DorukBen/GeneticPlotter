import data.Individual;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlotCanvas extends JPanel {
    private int mWidth;
    private int mHeight;
    private BufferedImage plotImage;

    PlotCanvas(int width, int height) {
        mWidth = width;
        mHeight = height;
        plotImage = new BufferedImage(mWidth, mHeight, BufferedImage.TYPE_3BYTE_BGR);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(mWidth, mHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(plotImage, 0, 0, null);
    }

    // draw painting
    void updatePaint(Individual individual){
        Graphics g = plotImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, mWidth, mHeight);

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
        // repaint panel with new modified paint
        repaint();
    }
}
