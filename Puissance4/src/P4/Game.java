package P4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import javax.swing.JPanel;

public class Game extends JPanel implements MouseListener, MouseMotionListener{
	
	private static final long serialVersionUID = 1L;
	public int[] numColonne;
	public char[][] grille;
	public int[] nombreAligne;
	public char[] jeton;
	public char[] jeu;
	private static final int TAILLE_JETON = 75;
	private static final int NOMBRE_TOUR = 42;
	private static final char JAUNE = 'J';
	private static final char ROUGE = 'R';
	private static final char VIDE = 'O';
	private boolean victoireRouge = false;
	private boolean victoireJaune = false;
	private boolean clic = true;
	
	int i,j,c, numeroTour, jouee;
	int numColonneJouee = 1;
	int posSouris;
	
	
	public Game(){
		addMouseListener(this);
		addMouseMotionListener(this);
		initialise();
		numeroTour = 0;
	}
	public void initialise(){
		this.grille = new char[7][6];
		this.numColonne = new int[7];
		this.jeton = new char[2];
		this.jeu = new char[7];
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
		for(c=0;c<7; c++){
			this.jeu[c] = ' ';
		}
		this.jeton[0] = ROUGE;
		this.jeton[1] = JAUNE;
	}
	public void paintComponent(Graphics g)
    {
    	g.setColor(Color.white);
    	g.fillRect(0, 0, 660, 600);
    	
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
    		    	g.fillOval(i*TAILLE_JETON, (j+1)*TAILLE_JETON +30, TAILLE_JETON, TAILLE_JETON);
    		    	break;
    			case ROUGE :
    				g.setColor(Color.red);
    		    	g.fillOval(i*TAILLE_JETON, (j+1)*TAILLE_JETON +30, TAILLE_JETON, TAILLE_JETON);
    		    	break;
    			case JAUNE :
    				g.setColor(Color.yellow);
    		    	g.fillOval(i*TAILLE_JETON, (j+1)*TAILLE_JETON +30, TAILLE_JETON, TAILLE_JETON);
    		    	break;
    			}
    		}
    	}
    	jouee = numeroTour %2;
		switch (this.jeton[jouee]){
		case ROUGE :
			g.setColor(Color.red);
	    	g.fillOval(560, 200, TAILLE_JETON, TAILLE_JETON);
	    	if(posSouris != 1){
	    		g.fillOval(posSouris, 20, TAILLE_JETON, TAILLE_JETON);
	    	}
	    	else{
	    		g.setColor(Color.white);
		    	g.fillOval(20, 20, TAILLE_JETON, TAILLE_JETON);
	    	}
	    	break;
		case JAUNE :
			g.setColor(Color.yellow);
	    	g.fillOval(560, 200, TAILLE_JETON, TAILLE_JETON);
	    	if(posSouris != 1){
	    		g.fillOval(posSouris, 20, TAILLE_JETON, TAILLE_JETON);
	    	}
	    	else{
	    		g.setColor(Color.white);
		    	g.fillOval(20, 20, TAILLE_JETON, TAILLE_JETON);
	    	}
	    	break;
		}
    	if(victoireRouge){
    		g.setColor(Color.red);
    	    Font font1 = new Font("Book Antiqua", Font.PLAIN, 30);
    	    AttributedString attributedString = new AttributedString("La couleur ROUGE l'emporte !!!");
    	    attributedString.addAttribute(TextAttribute.FONT, font1);
    	    g.drawString(attributedString.getIterator(), 90, 90);
    	}
    	if(victoireJaune){
    		g.setColor(Color.yellow);
    	    Font font1 = new Font("Book Antiqua", Font.PLAIN, 30);
    	    AttributedString attributedString = new AttributedString("La couleur JAUNE l'emporte !!!");
    	    attributedString.addAttribute(TextAttribute.FONT, font1);
    	    g.drawString(attributedString.getIterator(), 90, 90);
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
		if(this.grille[numColonneJouee-1][0] == VIDE){
			while(ligneJeu<6 && this.grille[numColonneJouee-1][ligneJeu] == VIDE){
				ligneJeu++;
			}
			this.grille[numColonneJouee-1][ligneJeu-1] = couleurJouee;
			aligne(couleurJouee, numColonneJouee-1,ligneJeu-1);
		}
		else{
			System.out.println("Le colonne est pleine.");
			jouer();
		}
	}
	public void aligne(char couleur, int colonne, int ligne){
		this.nombreAligne = new int[4];
		this.nombreAligne[0]=1; // nombreAligne sur une colonne
		this.nombreAligne[1]=1; // nombreAligne sur une ligne
		this.nombreAligne[2]=1; // nombreAligne sur la diagonale principale
		this.nombreAligne[3]=1; // nombreAligne sur la seconde diagonale
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
				victoireRouge = true;
				numeroTour = NOMBRE_TOUR+1;
			}
			else{
				System.out.println("La couleur JAUNE l'emporte.");
				victoireJaune = true;
				numeroTour = NOMBRE_TOUR;
			}
		}		
	}
	public void run()
	{
		while( numeroTour <= NOMBRE_TOUR){
			while (this.clic){
				try{
					Thread.sleep(500);
					} catch (InterruptedException e){
					e.printStackTrace();
					}
			}
			jouer();
			repaint();
			this.clic = true;
			numeroTour++;
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.clic = false;
		switch((int)(arg0.getPoint().getX())/75){
		case 0 :
			numColonneJouee = 1;
			break;
		case 1 :
			numColonneJouee = 2;
			break;
		case 2 :
			numColonneJouee = 3;
			break;
		case 3 :
			numColonneJouee = 4;
			break;
		case 4 :
			numColonneJouee = 5;
			break;
		case 5 :
			numColonneJouee = 6;
			break;
		case 6 :
			numColonneJouee = 7;
			break;
		default :
			this.clic=true;
			break;
				
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		switch((int)(e.getPoint().getX())/75){
		case 0 :
			posSouris = 0;
			break;
		case 1 :
			posSouris = 75;
			break;
		case 2 :
			posSouris = 150;
			break;
		case 3 :
			posSouris = 225;
			break;
		case 4 :
			posSouris = 300;
			break;
		case 5 :
			posSouris = 375;
			break;
		case 6 :
			posSouris = 450;
			break;
		default :
			posSouris = 1;
			break;
		}
		repaint();
	}
}
	
