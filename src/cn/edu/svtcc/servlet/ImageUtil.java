package cn.edu.svtcc.servlet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.http.HttpSession;
/**
 * 生成验证码图片的类
 * @author Shixuanming
 *	
 */
public class ImageUtil {
	//创建一个随机数对象
	private static Random rand = new Random();
	//设置验证码的宽度为120，高度为50
	private static int width = 120;
	private static int height = 50;
	//验证码中的4个字符都是从这个字符中随机的
	private static char[] randChar = {'a','b','c','d','e','f','g',
			'h','i','j','k','l','m','n','o','p','q','r','s','t',
			'u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
	/**
	 * 创建二维码图像的方法
	 * @param session servlet中的session对象
	 * @param length 验证码的字符个数
	 * @return
	 */
	public static BufferedImage createImage(HttpSession session,int length) {
		//创建一个BufferedIamge对象，设置该图片对象的宽高，（第三个参数可能是颜色模块，不确定。。。）
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		//创建并得到画笔对象
		Graphics g = bi.getGraphics();
		//设置验证码背景
		setBackground(g);
		//设置噪点
		drawDot(g);
		//设置验证码中的干扰线
		drawRandomLine((Graphics2D)g);
		//创建一个StringBuilder对象
		StringBuilder sb = new StringBuilder();
		//for循环，次数为字符数
		for(int i=0;i<length;i++) {
			//随机一个索引数字，范围为0-随机字符数组的长度
			int index = rand.nextInt(randChar.length);
			//从随机字符数组中随机一个字符，并添加到StringBuilder末尾
			sb.append(randChar[index]);
			//设置画笔颜色，每个字符都有一个随机的颜色
			g.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
			//设置字体样式，倾斜，字体大小在40-50随机
			g.setFont(new Font("微软雅黑",Font.ITALIC,rand.nextInt(10)+40));
			//将随机的字符添加到图片中，由于随机的是一个字符，drawString写入的是一个字符串，所以要将字符强转为一个字符串
			//横坐标为i*25+10，纵坐标在30-50随机(这个位置自己试出来的，主要是好看)
			g.drawString(Character.toString(randChar[index]), i*25+10, rand.nextInt(20)+30);
		}
		//结束循环中，StringBuilder中应该有4个随机的字符
		//将该对象转换为String类型，并放到session域中
		/**
		 * 注：由于String的值是不可变的，所以每次为String赋值都会创建一个新的String对象
		 * StringBuilder是将字符依次在末尾添加，不会创建新的对象 
		 * String和StringBuilder之间可以相互转换
		 * StringBuilder sb = new StringBuilder();
		 * 将String转换为StringBuilder  StringBuilder str = new StringBuilder(sb);
		 * 将StringBuilder转为String  sb.toString();
		 */
		session.setAttribute("sb", sb.toString());
		//关闭画笔
		g.dispose();
		//返回生成好的验证码图像
		return bi;
	}
	/**
	 * 设置背景的方法
	 * @param g
	 */
	private static void setBackground(Graphics g) {
		//设置画笔颜色
		g.setColor(Color.white);
		//画一个矩形，坐标为0,0  长度和宽度为验证码的长度和宽度
		//该矩形就相当于一个背景
		g.fillRect(0, 0, width, height);
	}
	/**
	 * 画随机线的方法
	 * @param g
	 */
	private static void drawRandomLine(Graphics2D g) {
		//设置画笔粗细
		g.setStroke(new BasicStroke(0.3F));
		//循环几次就是画几根线
		for(int i=0;i<5;i++) {
			//设置画笔颜色，实际就是线的颜色
			g.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
			//初始横纵坐标
			int x1=rand.nextInt(width);
			int y1=rand.nextInt(height);
			//结束横纵坐标
			int x2=rand.nextInt(width);
			int y2=rand.nextInt(height);
			//画线
			g.drawLine(x1, y1, x2, y2);
		}
	}
	/**
	 * 生成噪点的方法
	 * @param g
	 */
	public static void drawDot(Graphics g) {
		//通过一个循环的嵌套，实现横向每隔5生成一个长度为1的矩形，纵向每隔5生成一个长度为1的矩形，这些矩形看起来像是一个点
		for(int i=1;i<height;i+=5) {
			//设置噪点颜色
			g.setColor(Color.BLACK);
			for(int j=1;j<width;j+=5) {
				g.fillRect(j, i, 1, 1);
			}
		}
	}
}
