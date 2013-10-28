package P4;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;

import javax.swing.JPanel;

public class Game extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public int[] numColonne;
	public char[][] grille;
	public static final int TAILLE_JETON = 75;
	public static final int NOMBRE_TOUR = 42;
	private Scanner colonne;
	private Scanner couleurJeton;
	
	int i,j,c;
	
	
	public Game(){
		initialise();
	}
	public void initialise(){
		this.grille = new char[7][6];
		this.numColonne = new int[7];
		int l = 1;
		for(c=0;c<7;c++){
			numColonne[c] = l;
			l++;
		}
		
		for(i=0;i<7;i++){
			for(j=0;j<6;j++){
				this.grille[i][j] = 'O';
			}
		}
	}
	public void affiche(){
		for(c=0;c<7;c++){
			System.out.print(this.numColonne[c]);
		}
		System.out.println();
		for(j=0;j<6;j++){
			for(i=0;i<7;i++){
				System.out.print(this.grille[i][j]);
			}
			System.out.println();
		}
	}
	public void paintComponent(Graphics g)
    {
    	g.setColor(Color.white);
    	g.fillRect(0, 0, 550, 525);
    	
    	for(c=0;c<7;c++){
			String colonne = "" + this.numColonne[c];
			g.setColor(Color.black);
			g.drawString(colonne, c*TAILLE_JETON + 35, 15);
    	}    	
    	for(i=0;i<7;i++){
    		for(j=0;j<6;j++){
    			switch (this.grille[i][j]){
    			case 'O' :
    				g.setColor(Color.blue);
    		    	g.fillOval(i*TAILLE_JETON, j*TAILLE_JETON +30, TAILLE_JETON, TAILLE_JETON);
    		    	break;
    			case 'R' :
    				g.setColor(Color.red);
    		    	g.fillOval(i*TAILLE_JETON, j*TAILLE_JETON +30, TAILLE_JETON, TAILLE_JETON);
    		    	break;
    			case 'J' :
    				g.setColor(Color.yellow);
    		    	g.fillOval(i*TAILLE_JETON, j*TAILLE_JETON +30, TAILLE_JETON, TAILLE_JETON);
    		    	break;
    			}
    		}
    	}
    }
	public void jouer(){
		int ligneJeu = 0;
		colonne = new Scanner(System.in);
		System.out.println("Veuillez saisir le numéro de colonne dans laquelle vous voulez jouer :");
		int num = colonne.nextInt();
		while (num < 1 || num > 7){
			System.out.println("Vous avez saisi : " + num);
			System.out.println("Veuillez redonner un numéro de colonne entre 1 et 7 :");
			num = colonne.nextInt();
		}
		couleurJeton = new Scanner(System.in);
		System.out.println("Veuillez indiquer la couleur de votre jeton (J pour jaune, R pour rouge) :");
		char couleurJouee = couleurJeton.nextLine().charAt(0);
		while(couleurJouee != 'J' && couleurJouee != 'R'){
			System.out.println("La couleur donnée n'est pas bonne.");
			System.out.println("Veuillez indiquer la couleur de votre jeton (J pour jaune, R pour rouge) :");
			couleurJouee = couleurJeton.nextLine().charAt(0);
		}
		if(this.grille[num-1][0] == 'O'){
			while(ligneJeu<6 && this.grille[num-1][ligneJeu] == 'O'){
				ligneJeu++;
			}
			this.grille[num-1][ligneJeu-1] = couleurJouee;
		}
		else{
			System.out.println("Le colonne est pleine.");
			jouer();
		}
		aligne(num-1,ligneJeu-1);
	}
	public void aligne(int colonne, int ligne){
		int nombreAligne = 0;
		char couleur;
		couleur = this.grille[colonne][ligne];
		if(this.grille[colonne-1][ligne] == couleur){
			nombreAligne = nombreAligne + 1;
		}
		
	}
	public void run()
	{
		affiche();
		grille[5][5]='R';
		affiche();
		repaint();
		while( this.grille[0][0] == 'O' || this.grille[1][0] == 'O' || this.grille[2][0] == 'O' || this.grille[3][0] == 'O' 
				|| this.grille[4][0] == 'O' || this.grille[5][0] == 'O' || this.grille[6][0] == 'O'){
			try{
				Thread.sleep(2000);
			} catch (InterruptedException e){
				e.printStackTrace();
		}
		jouer();
		affiche();
		repaint();
		}
		
	}
}
