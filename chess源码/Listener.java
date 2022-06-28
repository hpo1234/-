package �й�����2;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Listener extends MouseAdapter implements ActionListener {
	Graphics g;
	String action;
	int x1, y1, x2, y2, index, beindex;
	int r = -1;
	int c = -1;
	int chessflag = 1;// �췽����Ϊ1
	DrawUI ui;
	int[][] lianbiao = new int[99999][6];// ���ӳ�ʼλ�ã����ڵ�λ�ã����ӵı�ţ�����ռ��λ���������ӵı��
	int[] curchess = new int[3];
	int[] beforechess = new int[3];
	int[][] flag = new int[10][9]; // ��ʼ������

	// ���������ݹ���
	public void setG(Graphics g) {
		this.g = g;
	}

	public void setUI(DrawUI ui) {
		this.ui = ui;
	}

	// �õ����ڵ����λ��
	public void getcr() {
		x2 = ((x1 - init.x0 + init.size / 2) / init.size) * init.size + init.x0;
		y2 = ((y1 - init.y0 + init.size / 2) / init.size) * init.size + init.y0;
		// ��ǰ�����λ��
		c = (x2 - init.x0) / init.size;
		r = (y2 - init.y0) / init.size;
	}

	// ���»����б�
	public void setLb() {
		lianbiao[index][0] = beforechess[0];
		lianbiao[index][1] = beforechess[1];
		lianbiao[index][2] = r;
		lianbiao[index][3] = c;
		lianbiao[index][4] = beforechess[2];
		lianbiao[index][5] = flag[r][c];
		index++;
	}

	// �������ڵ��е�����
	public void recurchess() {
		if (r != -1) {
			curchess[0] = r;
			curchess[1] = c;
			curchess[2] = flag[r][c];
		}
	}

	// ������һ�ε��е�����
	public void rebec() {
		//System.arraycopy(src, srcPos, dest, destPos, length);��������
		//%Arrays.copyOf(original, newLength);��������
		beforechess[0] = curchess[0];
		beforechess[1] = curchess[1];
		beforechess[2] = curchess[2];
	}

	// ���ºڷ��췽
	public void rechessflag() {
		if (chessflag == 1) {
			chessflag = 2;
		} else if (chessflag == 2) {
			chessflag = 1;
		}
	}
	
	public void walk(){
		setLb();// �Ѵ����ӵ�ǰ��λ�ñ�������
		flag[r][c] = beforechess[2];
		flag[beforechess[0]][beforechess[1]] = 0;
		ifwin();
		curchess = new int[3]; // ����һ����curchess��Ϊ0
		beforechess = new int[3];
		c = -1;
		r = -1;
		rechessflag();
		ui.repaint();
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("���");
		x1 = e.getX();
		y1 = e.getY();
		if (x1 > init.x0 - init.size / 2 && y1 > init.y0 - init.size / 2
				&& x1 < init.x0 + init.size / 2 + init.column * init.size
				&& y1 < init.y0 + init.row * init.size + init.size / 2) {
			x2 = ((x1 - init.x0 + init.size / 2) / init.size) * init.size + init.x0;
			y2 = ((y1 - init.y0 + init.size / 2) / init.size) * init.size + init.y0;
			// ��ǰ�����λ��
			getcr();// ��ô�ʱ�������λ��
			System.out.println("grtcr"+flag[r][c]);
			
			rebec();// ����ǰһ������
			ui.repaint();
			recurchess();
			if (r != -1) {
				if (curchess[2] == 0 & chessflag == 1 & beforechess[2] > 10 & ifwalk(beforechess[2]) == 1
						| curchess[2] == 0 & chessflag == 2 & beforechess[2] < 10 & ifwalk(beforechess[2]) == 1) {// �����ʱ��ĵط�û�����ӣ�ֱ���滻
					System.out.println("�߿�λ");
					walk();
				} else if (beforechess[2] > 10 & curchess[2] < 10 & chessflag == 1 & flag[r][c] < 10
						& ifwalk(beforechess[2]) == 1) {
					if (curchess[2] != 0) {// �������������
						System.out.println("����Ժ���");
						walk();
					}
				} else if (beforechess[2] < 10 & curchess[2] > 10 & beforechess[2] > 0 & chessflag == 2
						& flag[r][c] > 10 & ifwalk(beforechess[2]) == 1) {
					if (curchess[2] != 0) {// �������������
						System.out.println("����Ժ���");
						walk();
					}
				}
			}
		}
	}

	public int ifwalk(int who) {
		int ifflag = 0;
		// �����߷�
		if (who == 5) {
			if (r < 3 & c < 6 & c > 2) {
				if(beforechess[0] == curchess[0] & Math.abs(beforechess[1] - curchess[1]) == 1
						| beforechess[1] == curchess[1] & Math.abs(beforechess[0] - curchess[0]) == 1){
					ifflag = 1;
				}
			}
		}
		// ˧���߷�
		else if (who == 55) {
			if (r > 6 & c < 6 & c > 2) {
				if (beforechess[0] == curchess[0] & Math.abs(beforechess[1] - curchess[1]) == 1
						| beforechess[1] == curchess[1] & Math.abs(beforechess[0] - curchess[0]) == 1) {
					ifflag = 1;
				}
			}
		}
		// ܇���߷�
		else if (who == 1 | who == 11) {
			if (beforechess[0] == curchess[0] | beforechess[1] == curchess[1]) {
				if (findnumb(beforechess[0], beforechess[1], curchess[0], curchess[1]) == 0) {
					ifflag = 1;
				}
			}
		}
		// ����߷�
		else if (who == 2 | who == 22) {
			if(beforechess[0] > 0) {
				if (beforechess[0] - curchess[0] == 2 & Math.abs(beforechess[1] - curchess[1]) == 1
						& flag[beforechess[0] - 1][beforechess[1]] == 0) {
					ifflag = 1;// ��������
				}
			}
			if(beforechess[0] < 9) {
				if (beforechess[0] - curchess[0] == -2 & Math.abs(beforechess[1] - curchess[1]) == 1
						& flag[beforechess[0] + 1][beforechess[1]] == 0) {
					ifflag = 1;// ��������
				}
			}
			if(beforechess[1] < 8) {
				if (beforechess[1] - curchess[1] == -2 & Math.abs(beforechess[0] - curchess[0]) == 1
						& flag[beforechess[0]][beforechess[1] + 1] == 0) {
					ifflag = 1;// ��������
				}
			}
			if(beforechess[1] > 0) {
				if (beforechess[1] - curchess[1] == 2 & Math.abs(beforechess[0] - curchess[0]) == 1
						& flag[beforechess[0]][beforechess[1] - 1] == 0) {
					ifflag = 1;// ��������
				}
			}
		}
		// ����߷�
		else if (who == 3 | who == 33) {
			if(beforechess[0] > 0&beforechess[1] > 0) {
				if (beforechess[0] - curchess[0] == 2 & beforechess[1] - curchess[1] == 2
						& flag[beforechess[0] - 1][beforechess[1] - 1] == 0) {
					ifflag = 1;// �����Ͻ�����
				}
			}
			if(beforechess[0] < 9&beforechess[1]  > 0) {
				if (beforechess[0] - curchess[0] == -2 & beforechess[1] - curchess[1] == 2
						& flag[beforechess[0] + 1][beforechess[1] - 1] == 0) {
					ifflag = 1;// �����½�����
				}
			}
			if(beforechess[0] > 0&beforechess[1] < 8) {
				if (beforechess[0] - curchess[0] == 2 & beforechess[1] - curchess[1] == -2
						& flag[beforechess[0] - 1][beforechess[1] + 1] == 0) {
					ifflag = 1;// �����Ͻ�����
				}
			}
			if(beforechess[0] < 9&beforechess[1] < 8) {
				if (beforechess[0] - curchess[0] == -2 & beforechess[1] - curchess[1] == -2
						& flag[beforechess[0] + 1][beforechess[1] + 1] == 0) {
					ifflag = 1;// �����½�����
				}
			}
		}
		// ʿ���߷�
		else if (who == 4) {
			if (r < 3 & c < 6 & c > 2) {
				if(Math.abs(beforechess[1] - curchess[1])==1 & Math.abs(beforechess[0] - curchess[0])==1) {
					ifflag = 1;
				}
			}
		}
		// �˵��߷�
		else if(who == 44) {
			if (r > 6 & c < 6 & c > 2) {
				if(Math.abs(beforechess[1] - curchess[1])==1 & Math.abs(beforechess[0] - curchess[0])==1) {
					ifflag = 1;
				}
			}
		}
		// �ڵ��߷�
		else if(who == 6|who == 66) {
			if (beforechess[0] == curchess[0] | beforechess[1] == curchess[1]) {
				if (findnumb(beforechess[0], beforechess[1], curchess[0], curchess[1]) == 1&curchess[2]!=0|findnumb(beforechess[0], beforechess[1], curchess[0], curchess[1]) == 0&curchess[2]==0) {
					ifflag = 1;
				}
			}
		}
		// ����߷�
		else if(who == 7) {
			if(beforechess[0]<5&beforechess[1]==curchess[1]&beforechess[0]-curchess[0]==-1|beforechess[0]>4&beforechess[1]==curchess[1]&beforechess[0]-curchess[0]==-1|beforechess[0]>4&beforechess[0]==curchess[0]&Math.abs(beforechess[1]-curchess[1])==1) {
				ifflag = 1;	
			}
		}
		// �����߷�
		else if(who == 77) {
			if(beforechess[0]>4&beforechess[1]==curchess[1]&beforechess[0]-curchess[0]==1|beforechess[0]<5&beforechess[1]==curchess[1]&beforechess[0]-curchess[0]==1|beforechess[0]<5&beforechess[0]==curchess[0]&Math.abs(beforechess[1]-curchess[1])==1) {
				ifflag = 1;	
			}
		}
		System.out.println("ifflag="+ifflag);
		return ifflag;
	}

	// �ҵ�ĳһ��㵽�յ��к��е�������
	public int findnumb(int r1, int c1, int r2, int c2) {
		int numb = 0;
		if (r1 == r2) {
			for (int i = Math.min(c1, c2) + 1; i < Math.max(c1, c2); i++) {
				if (flag[r1][i] > 0) {
					numb++;
				}
			}
		} else if (c1 == c2) {
			for (int i = Math.min(r1, r2) + 1; i < Math.max(r1, r2); i++) {
				if (flag[i][c1] > 0) {
					numb++;
				}
			}
		}
		return numb;
	}
	
	public void ifwin() {
		if(chessflag == 1 & curchess[2] == 5) {
			System.out.println("�췽ʤ��");
			new RedWin().init();
		}else if(chessflag == 2 & curchess[2] == 55) {
			System.out.println("�ڷ�ʤ��");
			new BlackWin().init();
		}
	}
	
	public void renew() {
		flag = new int[][] { { 1, 2, 3, 4, 5, 4, 3, 2, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 6, 0, 0, 0, 0, 0, 6, 0 }, { 7, 0, 7, 0, 7, 0, 7, 0, 7 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 77, 0, 77, 0, 77, 0, 77, 0, 77 }, { 0, 66, 0, 0, 0, 0, 0, 66, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 11, 22, 33, 44, 55, 44, 33, 22, 11 } };
		chessflag = 1;
		r = -1;
		x1=0;y1=0;x2=0;y2=0;index=0;beindex=0;
		chessflag = 1;// �췽����Ϊ1
		lianbiao = new int[99999][6];// ���ӳ�ʼλ�ã����ڵ�λ�ã����ӵı�ţ�����ռ��λ���������ӵı��
		curchess = new int[3];
		beforechess = new int[3];
	}
	
	public void Regret_Chess(){
		r = -1;
		if (index > 0) {
			flag[lianbiao[index - 1][0]][lianbiao[index - 1][1]] = lianbiao[index - 1][4];
			flag[lianbiao[index - 1][2]][lianbiao[index - 1][3]] = lianbiao[index - 1][5];
			rechessflag();
			index--;
		}
	}

	public void actionPerformed(ActionEvent e) {
		// ��ȡ��ť�ϵ�����
		action = e.getActionCommand();

		if (action.equals("��ʼ��Ϸ")) {
			System.out.println("��ʼ��Ϸ");
			renew();
			ui.repaint();
		} else if (action.equals("���¿�ʼ")) {
			System.out.println("���¿�ʼ");
			renew();
			ui.repaint();
		} else if (action.equals("����")) {
			System.out.println("����");
			Regret_Chess();
			ui.repaint();
		}
	}

}
