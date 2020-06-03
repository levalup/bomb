import java.awt.event.*;
import javax.swing.*;
class P extends JLabel implements MouseListener {          //�׵ĵ�������
    public static final String[] num = {"image/empty.gif",   //ͼƬ
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
    private int x;          //����
    private int y;
    private boolean isBomb;    //����true��������false
    private String face;
    public P(TheTrue t,int x,int y) {   //���캯��
        super(new ImageIcon(cover));
        this.setSize(50,50);
        face = cover;
        setIcon(new ImageIcon(this.getClass().getResource(face)));
        this.x = x;
        this.y = y;
        isBomb = t.get(x,y);
        addMouseListener(this);
    }
    public String getFace() {      //������ڵĶ���
        return face;
    }
    public boolean getV() {        //����Ƿ�����
        return isBomb;
    }
    public void pressed() {        //������µĶ���
        if(!(face==cover||face==cover_l)) return;
        //System.out.println("sdfs");
        if(getV()) BombTable.endGame();   //������ף���Ϸ����
        P[] temp = BombTable.getNeighbor(x,y);   //�����Χ����Ķ���
        int index = 0;
        int sum = 0;
        for(index=0;index<temp.length&&temp[index]!=null;index++) {  //��ȡ��Χ�����������
            if(temp[index].getV()) {
                sum++;
            }
            //temp[index].pressed();
        }
        face = num[sum];
        //setIcon(new ImageIcon(face));
        setIcon(new ImageIcon(this.getClass().getResource(face)));
        if(sum==0)    //��û������ȫ����
            for(index=0;index<temp.length&&temp[index]!=null;index++)
                temp[index].pressed();
    }
    public void aiPressed() {
        P[] temp = BombTable.getNeighbor(x,y);
        int index = 0;
        int sum = 0;
        for(index=0;index<temp.length&&temp[index]!=null;index++) {  //��ȡ��Χ�����������
            if(temp[index].getFace()==flag||temp[index].getFace()==flag_l) {
                sum++;
            }
        }
        if(face==num[sum])   //�����������������
            for(index=0;index<temp.length&&temp[index]!=null;index++) {  //��ȡ��Χ�����������
                if(temp[index].getFace()!=flag||temp[index].getFace()!=flag_l) {
                    temp[index].pressed();
                }
            }
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {    //����
        if(face==cover) {
            face = cover_l;
            setIcon(new ImageIcon(this.getClass().getResource(face)));
        } else if(face==flag) {
            face = flag_l;
            setIcon(new ImageIcon(this.getClass().getResource(face)));
        }
    }
    public void mouseExited(MouseEvent e) {     //ȥ������
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
        if(e.getModifiers()==InputEvent.BUTTON1_MASK) {           //�����
            if(face==flag||face==flag_l) return;  //����Ѿ��������ӷ���
            pressed();   //���Լ����ϰ����
        } else if(e.getModifiers()==InputEvent.BUTTON2_MASK) {
            if(face==cover||face==cover_l||face==flag||face==flag_l) return;  //������������򷵻�
            aiPressed();
        } else if(e.getModifiers()==InputEvent.BUTTON3_MASK)      //���Ҽ�
            if(face==cover||face==cover_l) {
                face = flag_l; setIcon(new ImageIcon(this.getClass().getResource(face)));
            } else if(face==flag||face==flag_l) {
                face = cover_l; setIcon(new ImageIcon(this.getClass().getResource(face)));
            }
        BombTable.testV();  //�����Ƿ���Ϸ����
    }
    public void mouseReleased(MouseEvent e) {
    }
}