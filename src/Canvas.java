import raster.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Line;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Canvas {
    private JFrame frame;
    private JPanel panel;
    private BufferedImage img;
    private Raster raster;
    private LineRasterizer lineRasterizer;

    public Canvas(int width, int height) {
        frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        raster = new RasterBufferedImage(new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB));
        //lineRasterizer = new LineRasterizerTrivial(raster);
        lineRasterizer = new LineRasterizerGraphics(raster);

        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };

        panel.setPreferredSize(new Dimension(width, height));

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public void clear() {
        //Graphics gr = raster.getGraphics();
        //gr.setColor(new Color(0x2f2f2f));
        //gr.fillRect(0, 0, raster.getWidth(), raster.getHeight());
        raster.setBackgroundColor(0x445566);
        raster.clear();
    }

    public void present(Graphics graphics) {
        graphics.drawImage(((RasterBufferedImage)raster).getImg(), 0, 0, null);
    }

    public void draw() {
        clear();
        lineRasterizer.rasterize(10,10,100,50);
    }

    private void drawLine(int x1, int y1, int x2, int y2){
        float k = (float)(y2-y1)/(x2-x1);
        float q = y2-(k*x2);
        for (int x = 10; x <= 100; x++) {
            int y = (int)(k*x+q);
            img.setRGB(x, y, 0xffff00);
        }
    }

    public void start() {
        draw();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Canvas(800, 600).start());
    }
}
