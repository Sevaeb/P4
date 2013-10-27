package P4;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Game extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public char[][] grille;
	public static final int TAILLE_JETON = 75;
	
	int i,j;
	
	public Game(){
		initialise();
		affiche();

	}
	public void initialise(){
		this.grille = new char[8][7];
		
		this.grille[0][0] = ' ';
		
		this.grille[0][1] = 'A';
		this.grille[0][2] = 'B';
		this.grille[0][3] = 'C';
		this.grille[0][4] = 'D';
		this.grille[0][5] = 'E';
		this.grille[0][6] = 'F';
		this.grille[1][0] = '1';
		this.grille[2][0] = '2';
		this.grille[3][0] = '3';
		this.grille[4][0] = '4';
		this.grille[5][0] = '5';
		this.grille[6][0] = '6';
		this.grille[7][0] = '7';
		
		for(i=1;i<8;i++){
			for(j=1;j<7;j++){
				this.grille[i][j] = 'O';
			}
		}
	}
	public void affiche(){
		for(i=0;i<8;i++){
			for(j=0;j<7;j++){
				System.out.print(this.grille[i][j]);
			}
			System.out.println();
		}
	}
	public void paintComponent(Graphics g)
    {
    	g.setColor(Color.white);
    	g.fillRect(0, 0, 600, 525);
    	
    	for(i=0;i<8;i++){
    		for(j=0;j<7;j++){
    			switch (this.grille[i][j]){
    			case 'O' :
    				g.setColor(Color.blue);
    		    	g.fillOval(i*TAILLE_JETON, j*TAILLE_JETON, TAILLE_JETON, TAILLE_JETON);
    		    	break;
    			case 'R' :
    				g.setColor(Color.red);
    		    	g.fillOval(i*TAILLE_JETON, j*TAILLE_JETON, TAILLE_JETON, TAILLE_JETON);
    		    	break;
    			case 'J' :
    				g.setColor(Color.yellow);
    		    	g.fillOval(i*TAILLE_JETON, j*TAILLE_JETON, TAILLE_JETON, TAILLE_JETON);
    		    	break;
    			case 'A' : case 'B' : case 'C' : case 'D' : case 'E' : case 'F' :
    				String str = "" + this.grille[i][j];
    				g.setColor(Color.black);
    				g.drawString(str, 35, j*TAILLE_JETON + 40);
    				break;
    			case '1' : case '2' : case '3' : case '4' : case '5' : case '6' : case'7' :
    				str = "" + this.grille[i][j];
    				g.setColor(Color.black);
    				g.drawString(str, i*TAILLE_JETON + 35, 30);
    				break;
    			
    			}
    		}
    	}
    	
    	
    }
}
