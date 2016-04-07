/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photoeditor;

import com.sun.media.jai.widget.DisplayJAI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.renderable.ParameterBlock;
import java.util.ArrayList;
import javax.media.jai.InterpolationBilinear;
import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JScrollPane;
import static photoeditor.MainUI.undoMenu;

/**
 *
 * @author OmarElfaroukAhmed
 *
 *
 * List<RenderedImage> images = ......; // List of all my images for
 * (RenderedImage image : images) { paramBlock.addSource(image); }
 * paramBlock.set("mosaicType", MosaicDescriptor.MOSAIC_TYPE_BLEND);
 * JAI.create("mosaic", paramBlock);
 *
 *
 *
 */
public class AjiDisplay extends DisplayJAI implements MouseMotionListener, MouseListener {

    protected ArrayList annotations;
    PlanarImage image;
    Point prePoint = new Point(0, 0);
    Point point = new Point(0, 0);
    int x, y;
    int rotate = 0;
    int oriRotate = 0;
    float oriscaleX = 1;
    float oriscaleY = 1;
    PlanarImage orignalImage;
    float scale = 1;
    JScrollPane scrollPane;
    boolean flag = false;
    int scrollX, scrollY;
    ArrayList<bufferedImageCustom> images;
    int indexOfMoving;
    int xIntersect = -1;
    int yIntersect = -1;
    Rectangle selectedRect = null;
    ArrayList<TotalObject> undo = new ArrayList<>();
    boolean disable = false;
    boolean moveStore = false;
    int sliderValue = 50;

    public AjiDisplay(PlanarImage image) {
        // the application.
//        super(image);

        addMouseMotionListener(this);
        addMouseListener(this);
        setBackground(Color.BLACK);
        this.image = image;
        this.orignalImage = image;
        images = new ArrayList<>();
//        ArrayList<bufferedImageCustom> e = new ArrayList<>();
//        undo.add(new TotalObject(image, prePoint, point, x, y, rotate, oriRotate, 0, 0, orignalImage, 0, scrollPane, flag, scrollX, scrollY, e, indexOfMoving, xIntersect, yIntersect, selectedRect));

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        x = (getWidth() - image.getWidth()) / 2;
        y = (getHeight() - image.getHeight()) / 2;
        ColorModel cm = PlanarImage.createColorModel( image.getSampleModel() );
        try {
            BufferedImage temp = image.getAsBufferedImage(null,cm);
            g2d.drawImage(temp, null, x, y);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        if (scrollPane != null) {
            scrollPane.getViewport().validate();
            scrollPane.getViewport().revalidate();
            scrollPane.validate();
            scrollPane.revalidate();
            scrollPane.repaint();
            if (flag) {

                scrollPane.getVerticalScrollBar().setValue(scrollY);
                scrollPane.getHorizontalScrollBar().setValue(scrollX);
                flag = false;
            }
        }
        if (MainUI.state.equals("mark")) {
            drawSelectedArea(g2d);
        }
        float dash1[] = {10.0f};
        BasicStroke dashed
                = new BasicStroke(1.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
        g2d.setStroke(dashed);
        g2d.setColor(Color.RED);
        for (int i = 0; i < images.size(); i++) {
            g2d.drawImage(images.get(i).curImage.getAsBufferedImage(), null, images.get(i).point.x + x, images.get(i).point.y + y);
            if (i == indexOfMoving && indexOfMoving != -1) {
                g2d.draw(new Rectangle(images.get(i).point.x + x - 2, images.get(i).point.y + y - 2, images.get(i).curImage.getWidth() + 4, images.get(i).curImage.getHeight() + 4));
            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        point = new Point(e.getX(), e.getY());

        if (moveStore) {
            ArrayList<bufferedImageCustom> r = new ArrayList<>();
            for (int i = 0; i < images.size(); i++) {
                r.add(new bufferedImageCustom(images.get(i).image, images.get(i).point, images.get(i).curImage, images.get(i).rotate));
            }
            undo.add(new TotalObject(image, prePoint, point, x, y, rotate, oriRotate, oriscaleX, oriscaleY, orignalImage, scale, scrollPane, flag, scrollX, scrollY, r, indexOfMoving, xIntersect, yIntersect, selectedRect));
            moveStore = false;
        }

        if (MainUI.state.equals("rotate")) {
            if (indexOfMoving == -1) {
                rotate += (point.y - prePoint.y) % 360;
                scaleImage(oriscaleX, oriscaleY, orignalImage);
                rotateImage(rotate, image);
            } else {
                images.get(indexOfMoving).rotate += (point.y - prePoint.y) % 360;
                scaleImageSub(images.get(indexOfMoving).scale, indexOfMoving, true);
                rotateImageSub((int) images.get(indexOfMoving).rotate, indexOfMoving, false);
            }

            prePoint = new Point(point);
        }
        if (MainUI.state.equals("move") && indexOfMoving != -1) {
            images.get(indexOfMoving).point = new Point(e.getX() + xIntersect, e.getY() + yIntersect);
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MainUI.axis.setText(e.getX() + ":" + e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        MainUI.slider.setValue(sliderValue);

        if (MainUI.wait) {
            MainUI.apply.setVisible(true);
            MainUI.undoMenu.setEnabled(false);
            ArrayList<bufferedImageCustom> r = new ArrayList<>();
            for (int i = 0; i < images.size(); i++) {
                r.add(new bufferedImageCustom(images.get(i).image, images.get(i).point, images.get(i).curImage, images.get(i).rotate));
            }
            undo.add(new TotalObject(image, prePoint, point, x, y, rotate, oriRotate, oriscaleX, oriscaleY, orignalImage, scale, scrollPane, flag, scrollX, scrollY, r, indexOfMoving, xIntersect, yIntersect, selectedRect));
            bufferedImageCustom custom = new bufferedImageCustom(MainUI.clipboard, new Point(e.getX() - x, e.getY() - y));////\\
            images.add(custom);
            repaint();
        }
        MainUI.wait = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        prePoint = new Point(e.getX(), e.getY());
        System.out.println("prePoint: " + prePoint.toString());
        if (MainUI.state.equals("rotate")) {
            ArrayList<bufferedImageCustom> r = new ArrayList<>();
            for (int i = 0; i < images.size(); i++) {
                r.add(new bufferedImageCustom(images.get(i).image, images.get(i).point, images.get(i).curImage, images.get(i).rotate));
            }
            undo.add(new TotalObject(image, prePoint, point, x, y, rotate, oriRotate, oriscaleX, oriscaleY, orignalImage, scale, scrollPane, flag, scrollX, scrollY, r, indexOfMoving, xIntersect, yIntersect, selectedRect));
        }

        if (MainUI.state.equals("move")) {
            moveStore = true;
        }

//        if (MainUI.state.equals("move")) {
        indexOfMoving = -1;
        Rectangle rec = new Rectangle(e.getX(), e.getY(), 10, 10);
        for (int i = 0; i < images.size(); i++) {
            Rectangle imageRec = new Rectangle(
                    images.get(i).point.x + x,////\\
                    images.get(i).point.y + y,////\\
                    images.get(i).image.getHeight(),
                    images.get(i).image.getWidth());
            if (rec.intersects(imageRec)) {
                indexOfMoving = i;
                xIntersect = images.get(i).point.x - e.getX();
                yIntersect = images.get(i).point.y - e.getY();
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        point.x = e.getX();
        point.y = e.getY();
        System.out.println("point: " + point.toString());
        if (MainUI.state.equals("rotate")) {
            oriRotate = rotate;
        }
        repaint();
//        indexOfMoving = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Enter");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("exit");
        MainUI.axis.setText("0:0");
    }

    private void drawSelectedArea(Graphics2D g2d) {

        if (getSelectedArea()[1].x < 0.000001 || getSelectedArea()[1].y < 0.000001) {
            return;
        }
        float dash1[] = {10.0f};
        BasicStroke dashed
                = new BasicStroke(1.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
        g2d.setStroke(dashed);
        g2d.setColor(Color.RED);
        Polygon polygon = new Polygon();
        polygon.addPoint(prePoint.x, prePoint.y);
        polygon.addPoint(point.x, prePoint.y);
        polygon.addPoint(point.x, point.y);
        polygon.addPoint(prePoint.x, point.y);
        g2d.draw(polygon);
        g2d.setColor(Color.CYAN);
        g2d.fillOval(prePoint.x - 3, prePoint.y - 3, 6, 6);
        g2d.fillOval(-3 + point.x, -3 + point.y, 6, 6);
        g2d.fillOval(-3 + prePoint.x, -3 + point.y, 6, 6);
        g2d.fillOval(-3 + point.x, -3 + prePoint.y, 6, 6);
        g2d.fillOval(((prePoint.x + point.x) / 2) - 3, prePoint.y - 3, 6, 6);
        g2d.fillOval(prePoint.x - 3, ((prePoint.y + point.y) / 2) - 3, 6, 6);
        g2d.fillOval(((prePoint.x + point.x) / 2) - 3, point.y - 3, 6, 6);
        g2d.fillOval(point.x - 3, ((prePoint.y + point.y) / 2) - 3, 6, 6);
    }

    public Point[] getSelectedArea() {
        Point[] points = new Point[2];
        int preX = 0;
        int preY = 0;
        int X = 0;
        int Y = 0;

        if (prePoint.x - point.x > 0) {
            preX = point.x;
            X = prePoint.x;
        } else {
            X = point.x;
            preX = prePoint.x;
        }
        if (prePoint.y - point.y > 0) {
            preY = point.y;
            Y = prePoint.y;
        } else {
            Y = point.y;
            preY = prePoint.y;
        }
        points[0] = new Point(preX - x, preY - y);
        points[1] = new Point(X - preX, Y - preY);
        return points;
    }

    public void scaleChange(float scale) {
        float scale1 = scale;
        if (indexOfMoving == -1) {
            rotateImage(oriRotate, orignalImage);
            scaleImage(scale, scale, image);
            oriscaleX = scale;
            oriscaleY = scale;
            this.scale = scale;
        } else {
            if (images.isEmpty()) {
                return;
            }
            rotateImageSub((int) images.get(indexOfMoving).rotate, indexOfMoving, true);
            scaleImageSub(scale, indexOfMoving, false);
            images.get(indexOfMoving).scale = scale;

        }
        scrollPane.getViewport().validate();
        scrollPane.getViewport().revalidate();
        scrollPane.validate();
        scrollPane.revalidate();
        scrollPane.repaint();///////////////////////////////////////////////////////////////////////
        repaint();
    }

    public void scaleImage(float sclx, float scly, PlanarImage img) {
        try {
            ParameterBlock pb = new ParameterBlock();
            pb.addSource(img);
            pb.add(sclx);
            pb.add(scly);
            pb.add(0.0f);
            pb.add(0.0f);
            pb.add(new InterpolationNearest());
            this.image = JAI.create("scale", pb);
            this.scale = scale;
        } catch (Exception e) {
        }
        repaint();
    }

    public void scaleImageSub(float s, int i, boolean o) {
        try {
            ParameterBlock pb = new ParameterBlock();

            if (o) {
                pb.addSource(images.get(i).image);
            } else {
                pb.addSource(images.get(i).curImage);
            }
            pb.add(s);
            pb.add(s);
            pb.add(0.0f);
            pb.add(0.0f);
            pb.add(new InterpolationNearest());
            images.get(i).curImage = JAI.create("scale", pb);
        } catch (Exception e) {
        }
        repaint();
    }

    public void rotateImageSub(int rot, int i, boolean o) {
        float angle = (float) Math.toRadians(rot);
        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;
        ParameterBlock pb = new ParameterBlock();
        if (o) {
            pb.addSource(images.get(i).image);
        } else {
            pb.addSource(images.get(i).curImage);
        }
        pb.add(centerX);
        pb.add(centerY);
        pb.add(angle);
        pb.add(new InterpolationBilinear());
        double[] d = {0xFFFFFF, 0xFFFFFF, 0xFFFFFF};
        pb.add(d);
        images.get(i).curImage = JAI.create("rotate", pb);

    }

    public void rotateImage(int rot, PlanarImage img) {
        float angle = (float) Math.toRadians(rot);
        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;
        ParameterBlock pb = new ParameterBlock();
        pb.addSource(img);
        pb.add(centerX);
        pb.add(centerY);
        pb.add(angle);
        pb.add(new InterpolationBilinear());
        double[] d = {0xFFFFFF, 0xFFFFFF, 0xFFFFFF};
        pb.add(d);
        image = JAI.create("rotate", pb);
    }

    public void zoom() {
        Point[] points = getSelectedArea();
        if (points[1].x == 0 || points[1].y == 0) {
            return;
        }
        if( (((orignalImage.getWidth() * oriscaleX) / (points[1].x) / 1.3f) > 10) || ((orignalImage.getHeight() * oriscaleY) / (points[1].y) / 1.3f) >10 ){
            MainUI.message.setText("Too Large Scale");
            return;
        }
        rotateImage(oriRotate, orignalImage);
        scaleImage((orignalImage.getWidth() * oriscaleX) / (points[1].x) / 1.3f, (orignalImage.getHeight() * oriscaleY) / (points[1].y) / 1.3f, image);
        float orscTempX = oriscaleX;
        float orscTempY = oriscaleY;
        oriscaleX = (orignalImage.getWidth() * oriscaleX) / (points[1].x) / 1.3f;
        oriscaleY = (orignalImage.getHeight() * oriscaleY) / (points[1].y) / 1.3f;
        scale = Math.max(oriscaleX, oriscaleY);
        MainUI.slider.setValue((int) scale * 50);
        scrollX = (int) (oriscaleX * (points[0].x / orscTempX));
        scrollY = (int) (oriscaleY * (points[0].y / orscTempY));
        flag = true;
        repaint();
    }

    public void setScrollPane(JScrollPane sp) {
        this.scrollPane = sp;
    }

    public void apply() {
        ArrayList<bufferedImageCustom> r = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            r.add(new bufferedImageCustom(images.get(i).image, images.get(i).point, images.get(i).curImage, images.get(i).rotate));

        }
        undo.add(new TotalObject(image, prePoint, point, x, y, rotate, oriRotate, oriscaleX, oriscaleY, orignalImage, scale, scrollPane, flag, scrollX, scrollY, r, indexOfMoving, xIntersect, yIntersect, selectedRect));

        BufferedImage combined = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_4BYTE_ABGR);

        Graphics g = combined.getGraphics();
//        scaleImage(oriscaleX, oriscaleY, orignalImage);
//        rotateImage(rotate, image);
        g.drawImage(image.getAsBufferedImage(), 0, 0, null);
        for (int i = 0; i < images.size(); i++) {
            // Undo
            g.drawImage(images.get(i).curImage.getAsBufferedImage(), images.get(i).point.x, images.get(i).point.y, null);

        }
        images.clear();
        g.dispose();
        orignalImage = PlanarImage.wrapRenderedImage(combined);
        image = orignalImage;
        repaint();
    }

    public void undoo() {
        if (undo.isEmpty()) {
            repaint();
            return;
        }
        TotalObject total = undo.remove(undo.size() - 1);

        prePoint = total.getPrePoint();
        point = total.getPoint();
        x = total.getX();
        y = total.getY();
        rotate = total.getRotate();
        oriRotate = total.getOriRotate();
        // oriscaleX = total.getOriscaleX();
        //oriscaleY = total.getOriscaleY();
        orignalImage = total.getOrignalImage();
//        scaleImage(scale, scale, orignalImage);
//        rotateImage(rotate, image);
        image = total.getImage();
        scaleImage(scale, scale, image);
        // scale = total.getScale();
        scrollPane = total.getScrollPane();
        flag = total.isFlag();
        scrollX = total.getScrollX();
        scrollY = total.getScrollY();
        images = total.getImages();
        if( !images.isEmpty() ){
            MainUI.apply.setVisible(true);
        }
        for (int i = 0; i < images.size(); i++) {
            scaleImageSub(images.get(i).scale, i, true);
        }
        indexOfMoving = total.getIndexOfMoving();
        xIntersect = total.getxIntersect();
        yIntersect = total.getyIntersect();
        selectedRect = total.getSelectedRect();
        repaint();
    }

    public void setSlider(int s) {
        this.sliderValue = s;
    }

    public void rotate(int angle) {
        ArrayList<bufferedImageCustom> r = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            r.add(new bufferedImageCustom(images.get(i).image, images.get(i).point, images.get(i).curImage, images.get(i).rotate));
        }
        undo.add(new TotalObject(image, prePoint, point, x, y, rotate, oriRotate, oriscaleX, oriscaleY, orignalImage, scale, scrollPane, flag, scrollX, scrollY, r, indexOfMoving, xIntersect, yIntersect, selectedRect));

        scaleImage(oriscaleX, oriscaleY, orignalImage);
        rotateImage(angle, image);
        oriRotate = angle;
    }

    public void reset() {
        this.image = orignalImage;
        scale = 1;
        oriscaleX = 1;
        oriscaleY = 1;
        rotate = 0;

        prePoint = new Point(0, 0);
        point = new Point(0, 0);
        oriRotate = 0;
        oriscaleX = 1;
        oriscaleY = 1;
        flag = false;
        images.clear();
        images = new ArrayList<>();
        indexOfMoving = -1;
        xIntersect = -1;
        yIntersect = -1;
        selectedRect = null;
        undo = new ArrayList<>();
        disable = false;
        moveStore = false;
        sliderValue = 50;

    }
}
