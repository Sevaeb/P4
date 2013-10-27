package P4;

import javax.swing.JFrame;

// https://github.com/Sevaeb/P4.git

public class P4 {

	public static void main(String[] args) {
		JFrame fen = new JFrame();
    	fen.setTitle("Puissance 4");
    	fen.setSize(640, 580);
    	fen.setLocationRelativeTo(null);
    	fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
        Game Partie = new Game();
        Partie.run();
        fen.setContentPane(Partie);
        fen.setVisible(true);

	}

}
