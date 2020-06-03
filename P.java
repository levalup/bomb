import java.awt.event.*;
import javax.swing.*;
class P extends JLabel implements MouseListener {          //雷的单独方格
    public static final String[] num = {"image/empty.gif",   //图片
                                   "image/1.gif",
                                   "image/2.gif",
                                   "image/3.gif",
                                   "image/4.gif",
                                   "image/5.gif",
                                   "image/6.gif",
                                   "image/7.gif",
                                   "image/8.gif",};
    public static final String cover = "image/cover.gif",
                          cover_l = "image/cover_l.gif",
                          bomb = "image/bomb.gif",
                          fal = "image/false.gif",
                          flag =  "image/flag.gif",
                          flag_l = "image/flag_l.gif";
    private int x;          //坐标
    private int y;
    private boolean isBomb;    //是雷true，不是雷false
    private String face;
    public P(TheTrue t,int x,int y) {   //构造函数
        super(new ImageIcon(cover));
        this.setSize(50,50);
        face = cover;
        setIcon(new ImageIcon(this.getClass().getResource(face)));
        this.x = x;
        this.y = y;
        isBomb = t.get(x,y);
        addMouseListener(this);
    }
    public String getFace() {      //获得现在的对象
        return face;
    }
    public boolean getV() {        //检测是否是雷
        return isBomb;
    }
    public void pressed() {        //左键按下的动作
        if(!(face==cover||face==cover_l)) return;
        //System.out.println("sdfs");
        if(getV()) BombTable.endGame();   //如果踩雷，游戏结束
        P[] temp = BombTable.getNeighbor(x,y);   //获得周围方格的对象
        int index = 0;
        int sum = 0;
        for(index=0;index<temp.length&&temp[index]!=null;index++) {  //获取周围方格地雷数量
            if(temp[index].getV()) {
                sum++;
            }
            //temp[index].pressed();
        }
        face = num[sum];
        //setIcon(new ImageIcon(face));
        setIcon(new ImageIcon(this.getClass().getResource(face)));
        if(sum==0)    //若没有雷则全部打开
            for(index=0;index<temp.length&&temp[index]!=null;index++)
                temp[index].pressed();
    }
    public void aiPressed() {
        P[] temp = BombTable.getNeighbor(x,y);
        int index = 0;
        int sum = 0;
        for(index=0;index<temp.length&&temp[index]!=null;index++) {  //获取周围方格地雷数量
            if(temp[index].getFace()==flag||temp[index].getFace()==flag_l) {
                sum++;
            }
        }
        if(face==num[sum])   //如果地雷数等于数字
            for(index=0;index<temp.length&&temp[index]!=null;index++) {  //获取周围方格地雷数量
                if(temp[index].getFace()!=flag||temp[index].getFace()!=flag_l) {
                    temp[index].pressed();
                }
            }
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {    //加亮
        if(face==cover) {
            face = cover_l;
            setIcon(new ImageIcon(this.getClass().getResource(face)));
        } else if(face==flag) {
            face = flag_l;
            setIcon(new ImageIcon(this.getClass().getResource(face)));
        }
    }
    public void mouseExited(MouseEvent e) {     //去掉加亮
        if(face==cover_l) {
            face = cover;
            setIcon(new ImageIcon(this.getClass().getResource(face)));
        } else if(face==flag_l) {
            face = flag;
            setIcon(new ImageIcon(this.getClass().getResource(face)));
        }
    }
    public void mousePressed(MouseEvent e) {
        //System.out.println("sdfasdf");
        if(e.getModifiers()==InputEvent.BUTTON1_MASK) {           //按左键
            if(face==flag||face==flag_l) return;  //如果已经插上旗子返回
            pressed();   //在自己身上按左键
        } else if(e.getModifiers()==InputEvent.BUTTON2_MASK) {
            if(face==cover||face==cover_l||face==flag||face==flag_l) return;  //如果不是数字则返回
            aiPressed();
        } else if(e.getModifiers()==InputEvent.BUTTON3_MASK)      //按右键
            if(face==cover||face==cover_l) {
                face = flag_l; setIcon(new ImageIcon(this.getClass().getResource(face)));
            } else if(face==flag||face==flag_l) {
                face = cover_l; setIcon(new ImageIcon(this.getClass().getResource(face)));
            }
        BombTable.testV();  //测试是否游戏结束
    }
    public void mouseReleased(MouseEvent e) {
    }
}