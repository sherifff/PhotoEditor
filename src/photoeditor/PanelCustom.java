//package photoeditor;
//
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Point;
//import java.awt.Polygon;
//import java.awt.Rectangle;
//import java.awt.RenderingHints;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//import java.awt.geom.AffineTransform;
//import java.awt.image.AffineTransformOp;
//import java.awt.image.BufferedImage;
//import java.util.ArrayList;
//import javax.swing.JPanel;
//import javax.swing.JPopupMenu;
//
//public class PanelCustom extends JPanel implements MouseMotionListener, MouseListener {
//
//    BufferedImage image;
//    double rotate = 0;
//    Point prePoint = new Point(0, 0);
//    Point point = new Point(0, 0);
//    ArrayList<bufferedImageCustom> images;
//    int x;
//    int y;
//    int xIntersect = -1;
//    int yIntersect = -1;
//    Rectangle rec = null;
//    int x1,y1;
//    double scale;
//
//    /////////////////////////////////////////////////
//    BufferedImage image2;
//    BufferedImage imgRun;
//    ArrayList<BufferedImage>list=new ArrayList<BufferedImage>();
//    ArrayList<Double>zoomNum=new ArrayList<Double>();
//    ArrayList<Double>ratiox=new ArrayList<Double>();
//    ArrayList<Double>ratioy=new ArrayList<Double>();
//    private Point startPoint = new Point(0, 0);
//    private Point rectLocale = new Point();
//    private Dimension rectSize = new Dimension();
//    private BufferedImage capture = null;
//    private BufferedImage raw;
//    int flag;
//    double scale2;
//    
//    /////////////////////////////////////////////////
//    
//    public PanelCustom(BufferedImage image) {
//        this.image = image;
//        scale = 1.0;
//        scale2=1.0;
//        flag=0;
//        images = new ArrayList<>();
//        repaint();
//        addMouseMotionListener(this);
//        addMouseListener(this);
//
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
//        Graphics2D g2d = (Graphics2D) g;
//        
//        
//        
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        AffineTransform origXform;
//        origXform = g2d.getTransform();
//        AffineTransform newXform = (AffineTransform) (origXform.clone());
//        int xRot = this.getWidth() / 2;
//        int yRot = this.getHeight() / 2;
//        newXform.rotate(Math.toRadians(rotate), xRot, yRot);
//        g2d.setTransform(newXform);
//        x = (getWidth() - image.getWidth(this)) / 2;
//        y = (getHeight() - image.getHeight(this)) / 2;
//        g2d.drawImage(image, x, y, this);
//
//        for (int i = 0; i < images.size(); i++) {
//            newXform = (AffineTransform) (origXform.clone());
//            newXform.rotate(Math.toRadians(images.get(i).rotate), xRot, yRot);
//            g2d.setTransform(newXform);
////            g2d.drawImage(images.get(i).image, null, images.get(i).point.x + x, images.get(i).point.y + y);/////
//        }
//        g2d.setTransform(origXform);
//        if (MainUI.state.equals("mark")) {
//            drawSelectedArea(g2d);
//        }
//        if (rec != null) {
//            g2d.fill(rec);
//        }
//        g2d.fillRect(x1, y1, 20, 20);
//    }
//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        point = new Point(e.getX(), e.getY());
//        if (MainUI.state.equals("rotate")) {
//            rotate += (point.y - prePoint.y) ;
//
//            for (int i = 0; i < images.size(); i++) {
//                images.get(i).rotate += (point.y - prePoint.y) ;
//            }
//            prePoint = new Point(point);
//        }
//        
//        if (MainUI.state.equals("move") && indexOfMoving != -1) {
//            images.get(indexOfMoving).point = new Point(e.getX() + xIntersect, e.getY() + yIntersect);
//        }
//
//        repaint();
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        if (MainUI.wait) {
////            bufferedImageCustom custom = new bufferedImageCustom(MainUI.clipboard, new Point(e.getX() - x, e.getY() - y));////\\
////            images.add(custom);
//            repaint();
//            MainUI.wait = false;
//        }
//    }
//    int indexOfMoving = -1;
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        prePoint = new Point(e.getX(), e.getY());
//
////        if (MainUI.state.equals("rotate")) {
////
////            BufferedImage bi = new BufferedImage(getWidth(),
////                    getHeight(), BufferedImage.TYPE_INT_ARGB);
////            Graphics2D g2d = bi.createGraphics();
////            paint(g2d);
////            g2d.dispose();
////            // Scale dimension size of BufferedImage and return it
////            AffineTransform at = new AffineTransform();
////            at.scale(1, 1);
////            AffineTransformOp scaleOp = new AffineTransformOp(at,
////                    AffineTransformOp.TYPE_BILINEAR);
////            image = scaleOp.filter(bi, null);
////            images.clear();
////
////        }
//        if (MainUI.state.equals("move")) {
//            rec = new Rectangle(e.getX(), e.getY(), 10, 10);
//            for (int i = 0; i < images.size(); i++) {
//                Rectangle imageRec = new Rectangle(
//                        images.get(i).point.x + x,////\\
//                        images.get(i).point.y + y,////\\
//                        images.get(i).image.getHeight(),
//                        images.get(i).image.getWidth());
//                
//                x1 = (int) (this.getWidth()/2 + Math.cos(-images.get(i).rotate)*(images.get(i).point.x +x-this.getWidth()/2)-Math.sin(-images.get(i).rotate)*(images.get(i).point.y+y-this.getHeight()/2));
//                y1 = (int) (this.getHeight()/2 + Math.sin(-images.get(i).rotate) * (images.get(i).point.x + x - this.getWidth()/2) + Math.cos(-images.get(i).rotate) * (images.get(i).point.y + y - this.getHeight()/2));
//                System.out.println(x1+" ---- "+y1);
//                if (rec.intersects(imageRec)) {
//                    indexOfMoving = i;
//                    xIntersect = images.get(i).point.x - e.getX();
//                    yIntersect = images.get(i).point.y - e.getY();
//                    System.out.println("intersect:" + i);
//                }
//            }
//        }
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//        point.x = e.getX();
//        point.y = e.getY();
//        System.out.println("released:" + indexOfMoving);
//        indexOfMoving = -1;
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//    }
//
//    public Point[] getSelectedArea() {
//        Point[] points = new Point[2];
//        int preX = 0;
//        int preY = 0;
//        int X = 0;
//        int Y = 0;
//
//        if (prePoint.x - point.x > 0) {
//            preX = point.x;
//            X = prePoint.x;
//        } else {
//            X = point.x;
//            preX = prePoint.x;
//        }
//        if (prePoint.y - point.y > 0) {
//            preY = point.y;
//            Y = prePoint.y;
//        } else {
//            Y = point.y;
//            preY = prePoint.y;
//        }
//        points[0] = new Point(preX - x, preY - y);
//        points[1] = new Point(X - x, Y - y);
//        return points;
//    }
//
////    
////    private BufferedImage getScreenShot(JPanel panel){
////        BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
////        panel.paint(bi.getGraphics());
////        return bi;
////    }
//    private BufferedImage getScreenShot(JPanel panel) {
//        // Create BufferedImage of JPanel
//        BufferedImage bi = new BufferedImage(panel.getWidth(),
//                panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2d = bi.createGraphics();
//        panel.paint(g2d);
//        g2d.dispose();
//
//        // Scale dimension size of BufferedImage and return it
//        AffineTransform at = new AffineTransform();
//        at.scale(0.7, 0.7);
//        AffineTransformOp scaleOp = new AffineTransformOp(at,
//                AffineTransformOp.TYPE_BILINEAR);
//        return scaleOp.filter(bi, null);
//    }
//
//    private void drawSelectedArea(Graphics2D g2d) {
//        float dash1[] = {10.0f};
//        BasicStroke dashed
//                = new BasicStroke(1.0f,
//                        BasicStroke.CAP_BUTT,
//                        BasicStroke.JOIN_MITER,
//                        10.0f, dash1, 0.0f);
//        g2d.setStroke(dashed);
//        g2d.setColor(Color.RED);
////            g2d.drawRect(prePoint.x, prePoint.y, point.x - prePoint.x, point.y - prePoint.y);
//
//        Polygon polygon = new Polygon();
//        polygon.addPoint(prePoint.x, prePoint.y);
//        polygon.addPoint(point.x, prePoint.y);
//        polygon.addPoint(point.x, point.y);
//        polygon.addPoint(prePoint.x, point.y);
//
//        g2d.draw(polygon);
//
//        g2d.setColor(Color.CYAN);
//        g2d.fillOval(prePoint.x - 3, prePoint.y - 3, 6, 6);
//        g2d.fillOval(-3 + point.x, -3 + point.y, 6, 6);
//        g2d.fillOval(-3 + prePoint.x, -3 + point.y, 6, 6);
//        g2d.fillOval(-3 + point.x, -3 + prePoint.y, 6, 6);
//        g2d.fillOval(((prePoint.x + point.x) / 2) - 3, prePoint.y - 3, 6, 6);
//        g2d.fillOval(prePoint.x - 3, ((prePoint.y + point.y) / 2) - 3, 6, 6);
//        g2d.fillOval(((prePoint.x + point.x) / 2) - 3, point.y - 3, 6, 6);
//        g2d.fillOval(point.x - 3, ((prePoint.y + point.y) / 2) - 3, 6, 6);
//    }
//    
//    
//    public void zoom(){
////        MainUI.spinner.setValue(scale);
//    }
//    public void scaleChange(float scale){
//        this.scale = scale;
//    }
//}
