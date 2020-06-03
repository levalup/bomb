import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class BombTable extends JFrame implements ActionListener {
    static int tx,ty,tn;   //方格数
    static JMenuBar menuBar;
	static JMenu game, system;
	static JMenuItem newGame, setGame, about, exit;
    static JPanel mainTable;
	static P[][] pane;
	private static TheTrue t;
	
	static ImageIcon bomb;
	static ImageIcon fal;
	
	public BombTable() {          //构造函数
		setTitle("扫雷");
	    setLayout(new FlowLayout());
	    mainTable = new JPanel();
		menuBar = new JMenuBar();
		game = new JMenu("游戏");
		system = new JMenu("系统");
		newGame = new JMenuItem("新游戏");
		setGame = new JMenuItem("设置");
		about = new JMenuItem("关于");
		exit = new JMenuItem("退出");
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
	public static P[] getNeighbor(int x,int y) {    //获得某个坐标的周边的方格对象
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
	public static void endGame() {     //游戏失败
	    for(P[] p1 : pane)
		    for(P p2 : p1)
			    if(p2.getV()||p2.getFace()==P.flag) {
  				    if(p2.getFace()==P.cover||p2.getFace()==P.cover_l) p2.setIcon(bomb);
					if(!p2.getV()&&p2.getFace()==P.flag) p2.setIcon(fal);
				}
		JOptionPane.showMessageDialog( null , // parent frame
		                               "请再接再厉！" ,   // Object to display
									   "你失败了！" , // title bar message
									   JOptionPane.INFORMATION_MESSAGE  );
		newGame.doClick();
	}
	public static void testV() {       //测试是否游戏成功
	    int sum = 0; 
	    for(P[] p1 : pane)
		    for(P p2 : p1)
			    if(p2.getFace()==P.cover||p2.getFace()==P.flag||
				   p2.getFace()==P.cover_l||p2.getFace()==P.flag_l) sum++;
		if(sum==tn) {
		    JOptionPane.showMessageDialog( null , // parent frame
		                                   "恭喜您成功！" ,   // Object to display
									       "继续努力！" , // title bar message
									       JOptionPane.INFORMATION_MESSAGE  );
		    //setTable(tx,ty,tn);
			newGame.doClick();
		}
	}
	public static void setTable(int x,int y,int n) {       //放置雷区x列y行n雷
	    tx = x; ty = y; tn = n;
	    mainTable.setSize(x*50,y*50);
		mainTable.setLayout(new GridLayout(x,y));
		pane = new P[x][y];
		t = new TheTrue(x,y,n);
		mainTable.removeAll();
		for(int i=0;i<x;i++)
		    for(int j=0;j<y;j++) {
			    pane[i][j] = new P(t,i,j);
				pane[i][j].setBorder(BorderFactory.createBevelBorder(     //绘制表格线
				    BevelBorder.RAISED,Color.blue,Color.white));
				mainTable.add(pane[i][j]);
			}
	}
	public void actionPerformed(ActionEvent e) {		//菜单处理
	    if(e.getSource() == newGame) {					//新游戏
		    //System.out.println("asdf");
		    setTable(tx,ty,tn);
			validate();
			pack();
		} else if(e.getSource() == setGame) {			//设置
			new Setting(this, ty, ty, tn).setVisible(true);
		} else if(e.getSource() == about) {				//关于
		    JOptionPane.showMessageDialog( null , // parent frame
		                                   "kier制作2011年7月31日23:37:05" ,   // Object to display
									       "欢迎使用！" , // title bar message
									       JOptionPane.INFORMATION_MESSAGE  );
		} else if(e.getSource() == exit) {				//退出
		    //System.out.println("sdfasdf");
		    System.exit(0);
		}
	}
}