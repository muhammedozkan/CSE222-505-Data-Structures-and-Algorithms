import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReader {

    public static Integer[][][] read(String name) throws IOException {
        BufferedImage img;
        File f;
        int color;
        // read image

        f = new File(name);
        img = ImageIO.read(f);

        // get image's width and height
        int width = img.getWidth();
        int height = img.getHeight();

        //x y pixel color rgb
        Integer[][][] image = new Integer[width][height][3];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                color = img.getRGB(x, y);
                //shifting and bitwise and operation
                image[x][y][0] = (color >> 16) & 0xff; // r
                image[x][y][1] = (color >> 8) & 0xff; // g
                image[x][y][2] = color & 0xff; // b
            }
        }
        return image;
    }
}
