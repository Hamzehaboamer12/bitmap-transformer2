package bitmap.transformer2;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bitmap {

    BufferedImage image;
    String newFileName;

    public Bitmap(String filePath) {
        this.image = read(filePath);

    }
//     public Bitmap(String path , String newFileName){
//
//          try {
//
//
//              this.image = ImageIO.read(new File(path));
//          } catch (IOException r){
//               System.out.println(r.getMessage());
//          }
//
//          this.newFileName =newFileName;
//     }

    public void invert() {
        int rgb;

        for (int h = 1; h < image.getHeight(); h++) {
            for (int w = 1; w < image.getWidth(); w++) {
                rgb = 255 - this.image.getRGB(w, h);
                this.image.setRGB(w, h, rgb);
            }
        }
    }

    public void vflip() {
        int src = 0;
        int dst = 0;

        for (int h = 1; h < image.getHeight() / 2; h++) {
            for (int w = 1; w < image.getWidth(); w++) {
                src = this.image.getRGB(w, h);
                dst = this.image.getRGB(w, image.getHeight() - h);
                this.image.setRGB(w, h, dst);
                this.image.setRGB(w, image.getHeight() - h, src);
            }
        }
    }

    public void light() {
        int rgb;

        for (int h = 1; h < image.getHeight(); h++) {
            for (int w = 1; w < image.getWidth(); w++) {
                rgb = (this.image.getRGB(w, h) * 2) % 256;
                this.image.setRGB(w, h, rgb);
            }
        }
    }

    public BufferedImage read(String filePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.out.println("Failed to open image");
            System.out.println(e.getMessage());
        }
        return image;
    }

    public void save(String filePath) {
        try {
            ImageIO.write(this.image, "bmp", new File(filePath));
        } catch (IOException e) {
            System.out.println("ERROR SAVING!");
            System.out.println(e);
        }
    }
}




//     public BufferedImage read(String filePath) {
//          BufferedImage img = null;
//          try {
//               img = ImageIO.read(new File(filePath));
//          } catch (IOException e) {
//               System.out.println("Failed to open image");
//               System.out.println(e);
//          }
//          return img;
//     }
