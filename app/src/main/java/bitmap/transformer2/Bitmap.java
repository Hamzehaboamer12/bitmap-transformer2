package bitmap.transformer2;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.ColorModel;
public class Bitmap {


    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
    public static void invert(BufferedImage image,String outputPath) throws IOException {
        BufferedImage imageInvert = deepCopy(image) ;
        int imageHeight = imageInvert.getHeight();
        int imageWidth = imageInvert.getWidth();
        int[][] array2D = new int[imageWidth][imageHeight];
        int   xlength=imageInvert.getHeight()-1;//272
        int  ylength=imageInvert.getWidth()-1;//299
        for (int xPixel = 0; xPixel < array2D.length; xPixel++) {
            for (int yPixel = 0; yPixel < array2D[xPixel].length; yPixel++) {
                int color = imageInvert.getRGB(xPixel, yPixel);
                imageInvert.setRGB(xPixel , yPixel ,color ^ 0x00ffffff);

            }
            ImageIO.write(imageInvert, "png", new File(outputPath));

        }
    }
    public static void border(BufferedImage image,String outputPath) throws IOException {
        BufferedImage imageBoarder = deepCopy(image) ;
        int imageHeight = imageBoarder.getHeight();
        int imageWidth = imageBoarder.getWidth();
        int[][] array2D = new int[imageWidth][imageHeight];
        int   xlength=imageBoarder.getHeight()-1;//272
        int  ylength=imageBoarder.getWidth()-1;//299
        for (int xPixel = 0; xPixel < array2D.length; xPixel++) {
            for (int yPixel = 0; yPixel < array2D[xPixel].length; yPixel++) {
                if(xPixel<=10||xPixel>=(ylength-11) || yPixel<=10||yPixel>=(xlength-11)){
                    imageBoarder.setRGB(xPixel, yPixel, -16777216);
                }

            }
            ImageIO.write(imageBoarder, "png", new File(outputPath));

        }
    }
    public void randomize(BufferedImage image,String outputPath) throws IOException {
        BufferedImage imageRandomized = deepCopy(image) ;
        System.out.println("height " +  imageRandomized.getHeight());
        System.out.println("width " +  imageRandomized.getWidth());
        int imageHeight = imageRandomized.getHeight();
        int imageWidth = imageRandomized.getWidth();
        ArrayList<Integer> colorSet = new ArrayList();
        Integer randomValue = (int)Math.random();
        int[][] array2D = new int[imageWidth][imageHeight];
        int blackPixels = 0 ;
        int whitePixels = 0 ;
        for (int xPixel = 0; xPixel < array2D.length; xPixel++)
        {
            for (int yPixel = 0; yPixel < array2D[xPixel].length; yPixel++)
            {
                int color = imageRandomized.getRGB(xPixel, yPixel);
                colorSet.add(color);
                randomValue = (int)(Math.random()*(colorSet.size()-1));
                imageRandomized.setRGB(xPixel,yPixel,colorSet.get(randomValue));
                colorSet.remove(randomValue);
            }
        }
        ImageIO.write(imageRandomized , "png" , new File(outputPath));
    }


}