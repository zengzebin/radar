package test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class PicUtil {
	/**
	 * 压缩照片
	 * 
	 * @return
	 * @throws IOException
	 */
	public static void compressPhoto(String newFullPath) throws IOException {

		// 压缩处理
		File oldfile = new File(newFullPath);
		BufferedImage image = ImageIO.read(oldfile);
		int srcWidth = image.getWidth(null);// 得到文件原始宽度
		int srcHeight = image.getHeight(null);// 得到文件原始高度

		int newWidth = 1000;
		double scale_w = (double) newWidth / srcWidth;
		int newHeight = (int) (srcHeight * scale_w);

		BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		newImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);

		ImageIO.write(newImage, "jpg", new File(newFullPath));
	}
	public static void main(String[] args) {
		try {
			compressPhoto("F:\\20170703000002400.JPG");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
