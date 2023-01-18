package UI.Components;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

public class Background extends JPanel {
 
    public float getAnimate() {
        return animate;
    }

    public void setAnimate(float animate) {
        this.animate = animate;
        repaint();
    }
    private float animate;
   
      public Background() {
        setOpaque(false);
    }
      private float easeOutQuint(float x) {
        return (float) (1 - Math.pow(1 - x, 5));
    }
      private float easeInOutCirc(float x) {
        double v = x < 0.5 ? (1 - Math.sqrt(1 - Math.pow(2 * x, 2))) / 2 : (Math.sqrt(1 - Math.pow(-2 * x + 2, 2)) + 1) / 2;
        return (float) v;
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int height = (int) (getHeight() * (1f - easeOutQuint(animate)));
        g2.setColor(new Color(217,241,255));
        g2.fill(createShape(height, 50, 70, 50, 100));
        g2.setColor(new Color(3,138,255));
        g2.fill(createShape(height, 80, 50, 100, 50, 100, 50));
        g2.setColor(new Color(0,91,149));
        g2.fill(createShape(height, 70, 20, 60, 20, 70));
        g2.fill(createShape(height,70,20,60,20,70));
        int bgHeight = (int) (getHeight() * (1f - easeInOutCirc(animate)));
        g2.setColor(new Color(255, 255, 255));
        g2.fillRect(0, bgHeight, getWidth(), getHeight());
        g2.dispose();
        
        
    }


private Shape createShape(int location, int startInit, int... points) {
        int width = getWidth();
        int height = getHeight();
        int ss = width / points.length;
        int size = location;
        GeneralPath p = new GeneralPath();
        int space = 0;
        int xs = 0;
        int ys = location - startInit;
        for (int point : points) {
            point = size - point;
            int s = space + ss / 2;
            p.append(new CubicCurve2D.Double(xs, ys, s, ys, s, point, xs + ss, point), true);
            space += ss;
            xs += ss;
            ys = point;
        }
        p.lineTo(width, ys);
        p.lineTo(width, height);
        p.lineTo(0, height);
        return p;
    }
}