package 中国象棋2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class DrawUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	Listener ls = new Listener();
	public void initui() throws IOException {
		// 创建面板
		JFrame jf = new JFrame();
		// 设置面板属性
		jf.setSize(1240, 860);
		jf.setTitle("中国象棋");
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.getContentPane().setBackground(Color.WHITE);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);// 设置窗体不可放缩
		// 添加JPanel
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(450, 1));
		jp.setBackground(Color.white);
		jp.setLayout(null);
		jf.add(jp, BorderLayout.EAST);
		// 添加JLabel
		JLabel jl = new JLabel("中国象棋") {
			private static final long serialVersionUID = 1L;
			//BufferedImage img=ImageIO.read(new File("image\\"+"中国象棋.png"));
			Image jli = new ImageIcon(ImageIO.read(new File("image\\"+"中国象棋.png"))).getImage();
			public void paint(Graphics g) {
				g.drawImage(jli, 0, 0,400, 204, null);
			}
		};
		jl.setBounds(0,0, 400, 204);
		jp.add(jl);
		// 把this添加到JFrame中
		this.setBackground(Color.white);
		jf.add(this);
		// 添加按钮
		String[] ShapeBtn = { "开始游戏", "重新开始", "悔棋" };
		for (int i = 0; i < ShapeBtn.length; i++) {
			String name = ShapeBtn[i];
			JButton jbt = new JButton(name) {
				private static final long serialVersionUID = 1L;
				Image jbti = new ImageIcon(ImageIO.read(new File("image\\"+name+".png"))).getImage();
				public void paint(Graphics g) {
					g.drawImage(jbti, 0, 0,250, 100, null);
				}
			};
			jbt.setBounds(100, 260+150*i, 250, 100);
			jbt.addActionListener(ls);
			jp.add(jbt);
		}
		// 给画板添加监听器
		jf.addMouseListener(ls);
		jf.setVisible(true);
		Graphics g = jf.getGraphics();
		ls.setG(g);
		ls.setUI(this);
	}

	// 重绘
	public void paint(Graphics g) {
		super.paint(g);
		
		try {
			g.drawImage(new ImageIcon(ImageIO.read(new File("image\\"+"棋盘.jpg"))).getImage(), 90, 60, 625, 700, this);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// 根据flag画棋子
		for (int i = 0; i < init.row; i++) {
			for (int j = 0; j < init.column; j++) {
				if (ls.flag[i][j] > 0) {
					try {
						g.drawImage(new ImageIcon(ImageIO.read(new File("image\\"+(Integer.toString(ls.flag[i][j])) + ".png"))).getImage(), init.y0 + j * init.size - init.chesssize / 2,init.x00 + i * init.size - init.chesssize / 2,init.chesssize, init.chesssize, this);
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		}
		
		if(ls.r != -1) {
			if(ls.flag[ls.r][ls.c] > 0) {
				if(ls.chessflag == 1&ls.flag[ls.r][ls.c] > 10 | ls.chessflag == 2&ls.flag[ls.r][ls.c] < 10) {
					int newexsize = 8;
					
					try {
						g.drawImage(new ImageIcon(ImageIO.read(new File("image\\"+(Integer.toString(ls.flag[ls.r][ls.c])) + ".png"))).getImage(), init.y0 + ls.c * init.size - (init.chesssize+newexsize) / 2,init.x00 + ls.r * init.size - (init.chesssize+newexsize) / 2,init.chesssize+newexsize, init.chesssize+newexsize, this);
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}				
			}
		}
	}

	public static void main(String args[]) throws IOException {
		DrawUI ui = new DrawUI();
		ui.initui();
	}
}
