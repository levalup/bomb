public class TheTrue {       //真值阵
    private int tx,ty,tn;  //长宽个数，雷数
    private boolean[][] t;    //真值阵
	public TheTrue(int x,int y,int n) {
	    fresh(x,y,n);
	}
	public void fresh(int x,int y,int n) {     //构造新的真值阵
	    t = new boolean[x][y];
		for(boolean[] b1:t)         //赋初值
		    for(boolean b2:b1)
			    b2 = false;
		int intSum;
		for(intSum=0;intSum<n;) {    //安放地雷
		    tx = (int)(Math.random()*x);
			ty = (int)(Math.random()*y);
			if(get(tx,ty)) continue;
		    t[tx][ty] = true;
			intSum++;
		}
	    tx = x; ty = y; tn = n;     //更新真值阵的状态
	}
	public void fresh() {
	    fresh(tx,ty,tn);
	}
	public boolean get(int x,int y) {     //获取某个坐标的真值
	    if(x>=t.length||y>=t[0].length||t[x][y]!=true) return false;
		else return true;
	}
}