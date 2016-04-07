package photoeditor;

import java.awt.Point;
import java.awt.image.ColorModel;
import javax.media.jai.PlanarImage;

public class bufferedImageCustom {
    PlanarImage image;
    PlanarImage curImage;
    Point point;
    double rotate = 0;
    float scale=1;
    
    
    public bufferedImageCustom(PlanarImage image,Point point){

        this.image = image;
        this.point = point;
        this.curImage = image;
    }
        public bufferedImageCustom(PlanarImage image,Point point,PlanarImage cuImage,double rotate){
        this.curImage=cuImage;
        this.rotate=rotate;
        this.image = image;
        this.point = point;
    }
       

   
    
}