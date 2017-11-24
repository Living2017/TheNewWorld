package TheNewWorld.util;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class FightingThread extends Thread{
	
	
	private GridPane gp;
	private Button F1;
	private Button F2;
	
	int riF1;
	int ciF1;
	int riF2;
	int ciF2;
	
	boolean a =false;

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		do {
			a();
			
		}while(!a);
		this.stop();
	}
	
	
	
	
	
	
	public FightingThread(GridPane gp, Button f1, Button f2, int riF1, int ciF1, int riF2, int ciF2) {
		super();
		this.gp = gp;
		this.F1 = f1;
		this.F2 = f2;
		this.riF1 = riF1;
		this.ciF1 = ciF1;
		this.riF2 = riF2;
		this.ciF2 = ciF2;
	}






	@SuppressWarnings("static-access")
	public  void a() {
		try {
			this.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		riF1 = gp.getRowIndex(F1);
		ciF1 = gp.getColumnIndex(F1);
		riF2 = gp.getRowIndex(F2);
		ciF2 = gp.getColumnIndex(F2);

		
		if((riF1==riF2&&Math.abs(ciF1-ciF2)==1)
				|| (ciF1==ciF2&&Math.abs(riF1-riF2)==1)
				||(riF1==riF2&&ciF1==ciF2)) {
			a=true;
			return;
		}
		
		
		
		
		if(riF1<12) {
			gp.setRowIndex(F1,riF1+1);
		}else if(riF1>12) {
			gp.setRowIndex(F1,riF1-1);
		}
		
		else if(ciF1<12) {
			gp.setColumnIndex(F1,ciF1+1);
		}
		else if(ciF1>12) {
			gp.setColumnIndex(F1,ciF1-1);
		}
		
		if(riF2<12) {
			gp.setRowIndex(F2,riF2+1);
		}else if(riF2>12) {
			gp.setRowIndex(F2,riF2-1);
		}
		
		else if(ciF2<12) {
			gp.setColumnIndex(F2,ciF2+1);
		}
		else if(ciF2>12) {
			gp.setColumnIndex(F2,ciF2-1);
		}
	}

	
}
