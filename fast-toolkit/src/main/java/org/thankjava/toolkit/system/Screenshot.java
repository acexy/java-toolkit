package org.thankjava.toolkit.system;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * 截屏
* <p>Function: Screenshot</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年7月7日 下午1:41:03
* @version 1.0
 */
public class Screenshot {

	private static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	private static final String PNG = "png";
	
	public static void screenshot(String path,String fileName){
		try {
			BufferedImage bufferedImage = (new Robot()).createScreenCapture(
					new Rectangle(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight())
					);
			File f = new File(path + File.separator + fileName +".png");
			ImageIO.write(bufferedImage, PNG, f);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
