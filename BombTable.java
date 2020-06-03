import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class BombTable extends JFrame implements ActionListener {
    static int tx,ty,tn;   //������
    static JMenuBar menuBar;
	static JMenu game, system;
	static JMenuItem newGame, setGame, about, exit;
    static JPanel mainTable;
	static P[][] pane;
	private static TheTrue t;
	
	static ImageIcon bomb;
	static ImageIcon fal;
	
	public BombTable() {          //���캯��
		setTitle("ɨ��");
	    setLayout(new FlowLayout());
	    mainTable = new JPanel();
		menuBar = new JMenuBar();
		game = new JMenu("��Ϸ");
		system = new JMenu("ϵͳ");
		newGame = new JMenuItem("����Ϸ");
		setGame = new JMenuItem("����");
		about = new JMenuItem("����");
		exit = new JMenuItem("�˳�");
		game.add(newGame);
		game.add(setGame);
		system.add(about);
		system.add(exit);
		menuBar.add(game);
		menuBar.add(system);
		//setTable(12,25,48); 
		setTable(10,10,15); 
		setJMenuBar(menuBar);
		add(mainTable);
		setVisible(true);
		validate();
		pack();
		setResizable(false);
		newGame.addActionListener(this);
		setGame.addActionListener(this);
		about.addActionListener(this);
		exit.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bomb = new ImageIcon(this.getClass().getResource(P.bomb));
		fal = new ImageIcon(this.getClass().getResource(P.fal));
		
 	}
	public static P[] getNeighbor(int x,int y) {    //���ĳ��������ܱߵķ������
	    P[] temp = new P[8];
		int index = 0;
		if(x+1<tx) temp[index++] = pane[x+1][y];
		if(x-1>=0) temp[index++] = pane[x-1][y];
        if(y+1<ty) temp[index++] = pane[x][y+1];
		if(y-1>=0) temp[index++] = pane[x][y-1];
		if(x+1<tx&&y+1<ty) temp[index++] = pane[x+1][y+1];
		if(x-1>=0&&y+1<ty) temp[index++] = pane[x-1][y+1];
		if(x+1<tx&&y-1>=0) temp[index++] = pane[x+1][y-1];
		if(x-1>=0&&y-1>=0) temp[index++] = pane[x-1][y-1];
		while(index<8) temp[index++] = null;
		return temp;
	}
	public static void endGame() {     //��Ϸʧ��
	    for(P[] p1 : pane)
		    for(P p2 : p1)
			    if(p2.getV()||p2.getFace()==P.flag) {
  				    if(p2.getFace()==P.cover||p2.getFace()==P.cover_l) p2.setIcon(bomb);
					if(!p2.getV()&&p2.getFace()==P.flag) p2.setIcon(fal);
				}
		JOptionPane.showMessageDialog( null , // parent frame
		                               "���ٽ�������" ,   // Object to display
									   "��ʧ���ˣ�" , // title bar message
									   JOptionPane.INFORMATION_MESSAGE  );
		newGame.doClick();
	}
	public static void testV() {       //�����Ƿ���Ϸ�ɹ�
	    int sum = 0; 
	    for(P[] p1 : pane)
		    for(P p2 : p1)
			    if(p2.getFace()==P.cover||p2.getFace()==P.flag||
				   p2.getFace()==P.cover_l||p2.getFace()==P.flag_l) sum++;
		if(sum==tn) {
		    JOptionPane.showMessageDialog( null , // parent frame
		                                   "��ϲ���ɹ���" ,   // Object to display
									       "����Ŭ����" , // title bar message
									       JOptionPane.INFORMATION_MESSAGE  );
		    //setTable(tx,ty,tn);
			newGame.doClick();
		}
	}
	public static void setTable(int x,int y,int n) {       //��������x��y��n��
	    tx = x; ty = y; tn = n;
	    mainTable.setSize(x*50,y*50);
		mainTable.setLayout(new GridLayout(x,y));
		pane = new P[x][y];
		t = new TheTrue(x,y,n);
		mainTable.removeAll();
		for(int i=0;i<x;i++)
		    for(int j=0;j<y;j++) {
			    pane[i][j] = new P(t,i,j);
				pane[i][j].setBorder(BorderFactory.createBevelBorder(     //���Ʊ����
				    BevelBorder.RAISED,Color.blue,Color.white));
				mainTable.add(pane[i][j]);
			}
	}
	public void actionPerformed(ActionEvent e) {		//�˵�����
	    if(e.getSource() == newGame) {					//����Ϸ
		    //System.out.println("asdf");
		    setTable(tx,ty,tn);
			validate();
			pack();
		} else if(e.getSource() == setGame) {			//����
			new Setting(this, ty, ty, tn).setVisible(true);
		} else if(e.getSource() == about) {				//����
		    JOptionPane.showMessageDialog( null , // parent frame
		                                   "kier����2011��7��31��23:37:05" ,   // Object to display
									       "��ӭʹ�ã�" , // title bar message
									       JOptionPane.INFORMATION_MESSAGE  );
		} else if(e.getSource() == exit) {				//�˳�
		    //System.out.println("sdfasdf");
		    System.exit(0);
		}
	}
}