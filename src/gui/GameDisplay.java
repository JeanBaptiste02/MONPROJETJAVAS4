package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import configuration.GameConfiguration;

import motor.map.Map;
import motor.mobile.Joueur;
import motor.process.MobileElementManager;



/**
 * 
 * @author D.JB E.SRI Z.VIC
 *
 */
@SuppressWarnings("serial")
public class GameDisplay extends JPanel {
	
	private boolean debugGrid = true; // permet de montrer la grille pour débuger facilement
	
	private Map map;
	private MobileElementManager manager;
	private PaintStrategy paintStrategy = new PaintStrategy();
	
	public GameDisplay(Map map, MobileElementManager manager) {
		this.map = map;
		this.manager = manager;
	}
	
	
	@Override
	public void paintComponent(Graphics g) { // comme si on avait un crayon pour dessiner
		super.paintComponent(g);
		
		if (debugGrid) {
			drawDebugGrid(g);
		}
		
		Graphics2D g2 = (Graphics2D)g; // permet d'avoir plus de fonctionalité graphique
		
		
		paintStrategy.paint(g2, map);
		Joueur player = manager.getPlayer();
		paintStrategy.paint(player,g2);
		
	}
	
	private void drawDebugGrid(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		g.setColor(Color.GREEN);

		for (int i = GameConfiguration.BLOCK_SIZE; i <= width; i += GameConfiguration.BLOCK_SIZE) {
			g.drawLine(i, 1, i, height);
		}

		for (int i = GameConfiguration.BLOCK_SIZE; i <= height; i += GameConfiguration.BLOCK_SIZE) {
			g.drawLine(1, i, width, i);
		}
	}
	

	
}
