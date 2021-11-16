
import clip.Clipper;
import model.Line;
import model.Point;
import model.Polyline;
import raster.LineRasterizer;
import raster.LineRasterizerGraphics;
import raster.Raster;
import raster.RasterBufferedImage;
import transforms.Mat3;
import transforms.Mat3Identity;
import transforms.Mat3Transl2D;
import transforms.Point2D;

import java.awt.*;
import java.awt.event.*;
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
    private Mat3 trans = new Mat3Identity();

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

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyChar() == 'a')
                    trans = trans.mul(new Mat3Transl2D(-10, 0));

                if(e.getKeyChar() == 'w')
                    trans = trans.mul(new Mat3Transl2D(0, -10));

                clear();
                redraw();
            }
        });
        panel.requestFocus();
        panel.requestFocusInWindow();
    }

    public void clear() {
        raster.clear();
    }

    public void redraw() {
        for(Line line:lines) {
            Point2D a = new Point2D(line.getX1(), line.getY1());
            Point2D b = new Point2D(line.getX2(), line.getY2());
            a = a.mul(trans);
            b = b.mul(trans);
            //
            Line lineTransformed = new Line((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
            lineRasterizer.rasterize(line);
            lineRasterizer.rasterize(lineTransformed, 0xff0000);
        }

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