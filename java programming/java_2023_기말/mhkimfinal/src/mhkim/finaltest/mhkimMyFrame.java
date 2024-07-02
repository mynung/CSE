package mhkim.finaltest;

import java.awt.Container;

import javax.swing.JFrame;

public class mhkimMyFrame extends JFrame {

	Container frame = getContentPane();

	public mhkimMyFrame(mhkimKuAuction mall) {
		this.setTitle("202311264 김민홍");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		init();
		this.setVisible(true);
	}

	private void init() {
		// TODO Auto-generated method stub
	}

}
