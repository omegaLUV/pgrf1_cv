
import clip.Clipper;
import model.Line;
import model.Point;
import model.Polyline;
import raster.LineRasterizer;
import raster.LineRasterizerGraphics;
import raster.Raster;
import raster.RasterBufferedImage;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * trida pro kresleni na platno: zobrazeni pixelu, ovladani mysi
 *
 * @author PGRF FIM UHK
 * @version 2020
 */
public class CanvasMouse {

    private JPanel panel;
    private Raster raster;
    private LineRasterizer lineRasterizer;
    private List<Line> lines = new ArrayList<>();
    private int x1,y1;
    private Polyline sourcePoly;
    private Polyline clipPoly;

    public CanvasMouse(int width, int height) {
        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        raster = new RasterBufferedImage(width, height);
        lineRasterizer = new LineRasterizerGraphics(raster);

        sourcePoly = new Polyline();
        sourcePoly.getPoints().add(new Point(0,0));
        sourcePoly.getPoints().add(new Point(0,100));
        sourcePoly.getPoints().add(new Point(0,100));

        clipPoly = new Polyline();
        clipPoly.getPoints().add(new Point(0,0));
        clipPoly.getPoints().add(new Point(120,100));
        clipPoly.getPoints().add(new Point(0,0));


        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    x1 = e.getX();
                    y1 = e.getY();
                    raster.setPixel(e.getX(), e.getY(), 0xffff00);
                }
                if (e.getButton() == MouseEvent.BUTTON2)
                    raster.setPixel(e.getX(), e.getY(), 0xff00ff);
                if (e.getButton() == MouseEvent.BUTTON3)
                    raster.setPixel(e.getX(), e.getY(), 0xffffff);
                panel.repaint();
            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    lineRasterizer.rasterize(x1,y1,e.getX(),e.getY());
                    lines.add(new Line(x1,y1,e.getX(),e.getY()));
                }
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                clear();
                redraw();
                lineRasterizer.rasterize(x1,y1,e.getX(),e.getY());
                panel.repaint();
            }
        });
    }

    public void clear() {
        raster.clear();
    }

    public void redraw() {
//        for(int i = 0; i < lines.size(); i++) {
//            lineRasterizer.rasterize(lines.get(i));
//        }
//        for(Line line:lines) {
//            lineRasterizer.rasterize(line);
//        }
        Polyline result = Clipper.clip(sourcePoly, clipPoly);
        lineRasterizer.rasterize(result); //implementovat polyline
        panel.repaint();
    }

    public void present(Graphics graphics) {
        graphics.drawImage(((RasterBufferedImage)raster).getImg(), 0, 0, null);
    }

    public void start() {
        clear();
        //img.getGraphics().drawString("Use mouse buttons", 5, img.getHeight() - 5);
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CanvasMouse(800, 600).start());
    }

}