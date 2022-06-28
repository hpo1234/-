package �й�����2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BlackWin extends JFrame {

	private static final long serialVersionUID = 1L;
	private Listener ls;

	public void init() {
		// ���ô�������
		this.setSize(400, 100);
		this.setDefaultCloseOperation(3);
		this.setLayout(new BorderLayout());
		this.setLocationByPlatform(true);
		this.setResizable(false);

		// ���ô��ھ�����ʾ
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - getSize().width / 2, screenSize.height / 2 - getSize().height / 2);

		// ���JPanel
		JPanel jp = new JPanel();

		// ��Ӿ�̬�ı�������������
		JLabel jl = new JLabel("��Ϸ�������ڷ�ʤ��");
		jl.setSize(200, 30);
		jl.setFont(new Font("΢���ź�", Font.BOLD, 16));
		jl.setHorizontalAlignment(SwingConstants.CENTER);

		// ��Ӱ�ť
		JButton jbt1 = new JButton("�˳�");
		JButton jbt2 = new JButton("����");
		Listener re = new Listener();
		// ���õ�����˳�ʱ�˳���Ϸ
		jbt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// ���õ����ȷ��ʱ���¿�ʼ��Ϸ
		jbt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				re.renew();
				setVisible(false); //�رմ���
				dispose(); //��������
			}
		});

		// ��JPanel���ı���ӵ������ϣ����Ѱ�ť��ӵ�JPanel��
		this.add(jl, BorderLayout.CENTER);
		this.add(jp, BorderLayout.SOUTH);
		jp.add(jbt1);
		jp.add(jbt2);

		// ��������������
		ls = new Listener();

		// ��Ӱ�ť������
		jbt1.addActionListener(ls);
		jbt2.addActionListener(ls);

		// ���ô���ɼ�
		this.setVisible(true);

		// ��ȡ��������Ϊg
		Graphics g = this.getGraphics();

		// ���ü����������ĵĻ�����g����
		ls.setG(g);
	}

	public static void mian(String[] args) {
		BlackWin ot = new BlackWin();
		ot.init();
	}
}