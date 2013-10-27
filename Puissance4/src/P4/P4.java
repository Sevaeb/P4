package P4;

import javax.swing.JFrame;

public class P4 {

	public static void main(String[] args) {
		JFrame fen = new JFrame();
    	fen.setTitle("Puissance 4");
    	fen.setSize(640, 580);
    	fen.setLocationRelativeTo(null);
    	fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
        Game Partie1 = new Game();
        fen.setContentPane(Partie1);
        fen.setVisible(true);

	}

}
