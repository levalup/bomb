public class TheTrue {       //��ֵ��
    private int tx,ty,tn;  //�������������
    private boolean[][] t;    //��ֵ��
	public TheTrue(int x,int y,int n) {
	    fresh(x,y,n);
	}
	public void fresh(int x,int y,int n) {     //�����µ���ֵ��
	    t = new boolean[x][y];
		for(boolean[] b1:t)         //����ֵ
		    for(boolean b2:b1)
			    b2 = false;
		int intSum;
		for(intSum=0;intSum<n;) {    //���ŵ���
		    tx = (int)(Math.random()*x);
			ty = (int)(Math.random()*y);
			if(get(tx,ty)) continue;
		    t[tx][ty] = true;
			intSum++;
		}
	    tx = x; ty = y; tn = n;     //������ֵ���״̬
	}
	public void fresh() {
	    fresh(tx,ty,tn);
	}
	public boolean get(int x,int y) {     //��ȡĳ���������ֵ
	    if(x>=t.length||y>=t[0].length||t[x][y]!=true) return false;
		else return true;
	}
}