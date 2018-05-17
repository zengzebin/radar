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

public class ImageZip {
	/**
	 * 采用指定宽度、高度或压缩比例 的方式对图片进行压缩
	 * 
	 * @param imgsrc
	 *            源图片地址
	 * @param imgdist
	 *            目标图片地址
	 * @param widthdist
	 *            压缩后图片宽度（当rate==null时，必传）
	 * @param heightdist
	 *            压缩后图片高度（当rate==null时，必传）
	 * @param rate
	 *            压缩比例
	 */
	public static void reduceImg(String imgsrc, String imgdist, int widthdist, int heightdist, Float rate) {
		try {
			File srcfile = new File(imgsrc);
			// 检查文件是否存在
			if (!srcfile.exists()) {
				return;
			}
			// 如果rate不为空说明是按比例压缩
			if (rate != null && rate > 0) {
				// 获取文件高度和宽度
				int[] results = getImgWidth(srcfile);
				if (results == null || results[0] == 0 || results[1] == 0) {
					return;
				} else {
					widthdist = (int) (results[0] * rate);
					heightdist = (int) (results[1] * rate);
				}
			}
			// 开始读取文件并进行压缩
			Image src = javax.imageio.ImageIO.read(srcfile);
			BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);

			tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);

			FileOutputStream out = new FileOutputStream(imgdist);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(tag);
			out.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 获取图片宽度
	 * 
	 * @param file
	 *            图片文件
	 * @return 宽度
	 */
	public static int[] getImgWidth(File file) {
		InputStream is = null;
		BufferedImage src = null;
		int result[] = { 0, 0 };
		try {
			is = new FileInputStream(file);
			src = javax.imageio.ImageIO.read(is);
			result[0] = src.getWidth(null); // 得到源图宽
			result[1] = src.getHeight(null); // 得到源图高
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean compressPic(String srcFilePath, String descFilePath) throws IOException {
		File file = null;
		BufferedImage src = null;
		FileOutputStream out = null;
		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams;

		// 指定写图片的方式为 jpg
		imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
		imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
		// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
		imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
		// 这里指定压缩的程度，参数qality是取值0~1范围内，
		imgWriteParams.setCompressionQuality((float) 1);
		imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
		ColorModel colorModel = ImageIO.read(new File(srcFilePath)).getColorModel();// ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		// imgWriteParams.setDestinationType(new
		// javax.imageio.ImageTypeSpecifier(
		// colorModel, colorModel.createCompatibleSampleModel(16, 16)));
		imgWriteParams.setDestinationType(
				new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));

		try {
			if (isBlank(srcFilePath)) {
				return false;
			} else {
				file = new File(srcFilePath);
				System.out.println(file.length());
				src = ImageIO.read(file);
				out = new FileOutputStream(descFilePath);

				imgWrier.reset();
				// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
				// OutputStream构造
				imgWrier.setOutput(ImageIO.createImageOutputStream(out));
				// 调用write方法，就可以向输入流写图片
				imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean isBlank(String string) {
		if (string == null || string.length() == 0 || string.trim().equals("")) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("压缩图片开始...");
		File srcfile = new File("F:\\20170703000002400.JPG");
		System.out.println("压缩前srcfile size:" + srcfile.length());
		reduceImg("F:\\20170703000002400.JPG", "F://31.jpg", 860, 697,0.98f);
		File distfile = new File("F://31.jpg");
		System.out.println("压缩后distfile size:" + distfile.length());
		
//		try {
//			compressPic("F:\\20170703000002400.JPG","F://31.jpg");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
