package P4;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;

import javax.swing.JPanel;

public class Game extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public int[] numColonne;
	public char[][] grille;
	public int[] nombreAligne;
	public static final int TAILLE_JETON = 75;
	private static final int NOMBRE_TOUR = 42;
	private static final char JAUNE = 'J';
	private static final char ROUGE = 'R';
	private static final char VIDE = 'O';
	private Scanner colonne;
	//private Scanner couleurJeton;
	
	int i,j,c, numeroTour;
	
	
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
				this.grille[i][j] = VIDE;
			}
		}
	}
	public void affiche(){
		System.out.println();
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
    			case VIDE :
    				g.setColor(Color.blue);
    		    	g.fillOval(i*TAILLE_JETON, j*TAILLE_JETON +30, TAILLE_JETON, TAILLE_JETON);
    		    	break;
    			case ROUGE :
    				g.setColor(Color.red);
    		    	g.fillOval(i*TAILLE_JETON, j*TAILLE_JETON +30, TAILLE_JETON, TAILLE_JETON);
    		    	break;
    			case JAUNE :
    				g.setColor(Color.yellow);
    		    	g.fillOval(i*TAILLE_JETON, j*TAILLE_JETON +30, TAILLE_JETON, TAILLE_JETON);
    		    	break;
    			}
    		}
    	}
    }
	public void jouer(){
		char couleurJouee;
		int ligneJeu = 0;
		if ((numeroTour %2) == 0){
			couleurJouee = ROUGE;
			System.out.println("Au tour de la couleur ROUGE");
		}
		else{
			couleurJouee = JAUNE;
			System.out.println("Au tour de la couleur JAUNE");
		}
		colonne = new Scanner(System.in);
		System.out.println("Veuillez saisir le numéro de colonne dans laquelle vous voulez jouer :");
		int num = colonne.nextInt();
		while (num < 1 || num > 7){
			System.out.println("Vous avez saisi : " + num);
			System.out.println("Veuillez redonner un numéro de colonne entre 1 et 7 :");
			num = colonne.nextInt();
		}
		/*couleurJeton = new Scanner(System.in);
		System.out.println("Veuillez indiquer la couleur de votre jeton (J pour jaune, R pour rouge) :");
		char couleurJouee = couleurJeton.nextLine().charAt(0);
		while(couleurJouee != JAUNE && couleurJouee != ROUGE){
			System.out.println("La couleur donnée n'est pas bonne.");
			System.out.println("Veuillez indiquer la couleur de votre jeton (J pour jaune, R pour rouge) :");
			couleurJouee = couleurJeton.nextLine().charAt(0);
		}*/
		if(this.grille[num-1][0] == VIDE){
			while(ligneJeu<6 && this.grille[num-1][ligneJeu] == VIDE){
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
		this.nombreAligne = new int[4];
		this.nombreAligne[0]=1; // nombreAligne sur une colonne
		this.nombreAligne[1]=1; // nombreAligne sur une ligne
		this.nombreAligne[2]=1; // nombreAligne sur la diagonale principale
		this.nombreAligne[3]=1; // nombreAligne sur la seconde diagonale
		char couleur;
		couleur = this.grille[colonne][ligne];
		// Test l'alignement sur une colonne
		if((ligne+1)<6 && this.grille[colonne][ligne+1] == couleur){
			nombreAligne[0] = nombreAligne[0] + 1;
			if((ligne+2)<6 && this.grille[colonne][ligne+2] == couleur){
				nombreAligne[0] = nombreAligne[0] + 1;
				if((ligne+3)<6 && this.grille[colonne][ligne+3] == couleur){
					nombreAligne[0] = nombreAligne[0] + 1;
				}
			}
		}
		// Test l'alignement sur une ligne 
		if((colonne-1)>=0 && this.grille[colonne-1][ligne] == couleur){
			nombreAligne[1] = nombreAligne[1] + 1;
			if((colonne-2)>=0 && this.grille[colonne-2][ligne] == couleur){
				nombreAligne[1] = nombreAligne[1] + 1;
				if((colonne-3)>=0 && this.grille[colonne-3][ligne] == couleur){
					nombreAligne[1] = nombreAligne[1] + 1;
				}
			}
		}
		if((colonne+1)<7 && this.grille[colonne+1][ligne] == couleur){
			nombreAligne[1] = nombreAligne[1] + 1;
			if((colonne+2)<7 && this.grille[colonne+2][ligne] == couleur){
				nombreAligne[1] = nombreAligne[1] + 1;
				if((colonne+3)<7 && this.grille[colonne+3][ligne] == couleur){
					nombreAligne[1] = nombreAligne[1] + 1;
				}
			}
		}
		// Test l'alignement sur la diagonale principale
		if((colonne+1)<7 && (ligne+1)<6 && this.grille[colonne+1][ligne+1] == couleur){
			nombreAligne[2] = nombreAligne[2] + 1;
			if((colonne+2)<7 && (ligne+2)<6 && this.grille[colonne+2][ligne+2] == couleur){
				nombreAligne[2] = nombreAligne[2] + 1;
				if((colonne+3)<7 && (ligne+3)<6 && this.grille[colonne+3][ligne+3] == couleur){
					nombreAligne[2] = nombreAligne[2] + 1;
				}
			}
		}
		if((colonne-1)>=0 && (ligne-1)>=0 && this.grille[colonne-1][ligne-1] == couleur){
			nombreAligne[2] = nombreAligne[2] + 1;
			if((colonne-2)>=0 && (ligne-2)>=0 && this.grille[colonne-2][ligne-2] == couleur){
				nombreAligne[2] = nombreAligne[2] + 1;
				if((colonne-3)>=0 && (ligne-3)>=0 && this.grille[colonne-3][ligne-3] == couleur){
					nombreAligne[2] = nombreAligne[2] + 1;
				}
			}
		}
		// Test l'alignement sur la seconde diagonale
		if((colonne-1)>=0 && (ligne+1)<6 && this.grille[colonne-1][ligne+1] == couleur){
			nombreAligne[3] = nombreAligne[3] + 1;
			if((colonne-2)>=0 && (ligne+2)<6 && this.grille[colonne-2][ligne+2] == couleur){
				nombreAligne[3] = nombreAligne[3] + 1;
				if((colonne-3)>=0 && (ligne+3)<6 && this.grille[colonne-3][ligne+3] == couleur){
					nombreAligne[3] = nombreAligne[3] + 1;
				}
			}
		}
		if((colonne+1)<7 && (ligne-1)>=0 && this.grille[colonne+1][ligne-1] == couleur){
			nombreAligne[3] = nombreAligne[3] + 1;
			if((colonne+2)<7 && (ligne-2)>=0 && this.grille[colonne+2][ligne-2] == couleur){
				nombreAligne[3] = nombreAligne[3] + 1;
				if((colonne+3)<7 && (ligne-3)>=0 && this.grille[colonne+3][ligne-3] == couleur){
					nombreAligne[3] = nombreAligne[3] + 1;
				}
			}
		}
		
		// Affichage du gagnant
		if(nombreAligne[0] == 4 || nombreAligne[1] == 4 || nombreAligne[2] == 4 || nombreAligne[3] == 4){
			if(couleur == ROUGE){
				System.out.println("La couleur ROUGE l'emporte.");
				numeroTour = NOMBRE_TOUR;
			}
			else{
				System.out.println("La couleur JAUNE l'emporte.");
				numeroTour = NOMBRE_TOUR;
			}
		}		
	}
	public void run()
	{
		numeroTour = 0;
		affiche();
		while( numeroTour <= NOMBRE_TOUR){
			try{
				Thread.sleep(1000);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		jouer();
		affiche();
		repaint();
		numeroTour++;
		}
		
	}
}
