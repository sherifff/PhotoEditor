package photoeditor;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.media.jai.PlanarImage;
import javax.swing.JScrollPane;

/**
 *
 * @author OmarElfaroukAhmed
 */
public class TotalObject {

    private PlanarImage image;
    private Point prePoint;
    private Point point;
    private int x, y;
    private int rotate;
    private int oriRotate;
    private float oriscaleX;
    private float oriscaleY;
    private PlanarImage orignalImage;
    private float scale;
    private JScrollPane scrollPane;
    private boolean flag ;
    private int scrollX, scrollY;
    private ArrayList<bufferedImageCustom> images;
    private int indexOfMoving;
    private int xIntersect;
    private int yIntersect;
    private Rectangle selectedRect;

    public TotalObject(PlanarImage image, Point prePoint, Point point, int x, int y, int rotate, int oriRotate, float oriscaleX, float oriscaleY, PlanarImage orignalImage, float scale, JScrollPane scrollPane, boolean flag, int scrollX, int scrollY, ArrayList<bufferedImageCustom> images, int indexOfMoving, int xIntersect, int yIntersect, Rectangle selectedRect) {
        this.image = image;
        this.prePoint = prePoint;
        this.point = point;
        this.x = x;
        this.y = y;
        this.rotate = rotate;
        this.oriRotate = oriRotate;
        this.oriscaleX = oriscaleX;
        this.oriscaleY = oriscaleY;
        this.orignalImage = orignalImage;
        this.scale = scale;
        this.scrollPane = scrollPane;
        this.flag = flag;
        this.scrollX = scrollX;
        this.scrollY = scrollY;
        this.images = images;
        this.indexOfMoving = indexOfMoving;
        this.xIntersect = xIntersect;
        this.yIntersect = yIntersect;
        this.selectedRect = selectedRect;
    }

    public PlanarImage getImage() {
        return image;
    }

    public void setImage(PlanarImage image) {
        this.image = image;
    }

    public Point getPrePoint() {
        return prePoint;
    }

    public void setPrePoint(Point prePoint) {
        this.prePoint = prePoint;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public int getOriRotate() {
        return oriRotate;
    }

    public void setOriRotate(int oriRotate) {
        this.oriRotate = oriRotate;
    }

    public float getOriscaleX() {
        return oriscaleX;
    }

    public void setOriscaleX(float oriscaleX) {
        this.oriscaleX = oriscaleX;
    }

    public float getOriscaleY() {
        return oriscaleY;
    }

    public void setOriscaleY(float oriscaleY) {
        this.oriscaleY = oriscaleY;
    }

    public PlanarImage getOrignalImage() {
        return orignalImage;
    }

    public void setOrignalImage(PlanarImage orignalImage) {
        this.orignalImage = orignalImage;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getScrollX() {
        return scrollX;
    }

    public void setScrollX(int scrollX) {
        this.scrollX = scrollX;
    }

    public int getScrollY() {
        return scrollY;
    }

    public void setScrollY(int scrollY) {
        this.scrollY = scrollY;
    }

    public ArrayList<bufferedImageCustom> getImages() {
        return images;
    }

    public void setImages(ArrayList<bufferedImageCustom> images) {
        this.images = images;
    }

    public int getIndexOfMoving() {
        return indexOfMoving;
    }

    public void setIndexOfMoving(int indexOfMoving) {
        this.indexOfMoving = indexOfMoving;
    }

    public int getxIntersect() {
        return xIntersect;
    }

    public void setxIntersect(int xIntersect) {
        this.xIntersect = xIntersect;
    }

    public int getyIntersect() {
        return yIntersect;
    }

    public void setyIntersect(int yIntersect) {
        this.yIntersect = yIntersect;
    }

    public Rectangle getSelectedRect() {
        return selectedRect;
    }

    public void setSelectedRect(Rectangle selectedRect) {
        this.selectedRect = selectedRect;
    }
    public void setScales( float oriscaleX,float oriscaleY,float scale){
            this.oriscaleX=oriscaleX;
            this.oriscaleY=oriscaleY;
            this.scale=scale;
    }

    
}
