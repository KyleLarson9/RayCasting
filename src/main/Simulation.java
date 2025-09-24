package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

import inputs.MouseInputs;
import objects.Source;
import objects.Wall;

public class Simulation implements Runnable {

	private SimFrame frame;
	private SimPanel panel;
	
	private MouseInputs mouseInputs;
	
	private Thread thread;
	
	private Source source;

	
	private final int FPS = 120;
	private final int UPS = 200;
	
	private final static int TILES_DEFAULT_SIZE = 16;
	private final static float SCALE = 1.5f;
	private final static int TILES_IN_WIDTH = 60;
	private final static int TILES_IN_HEIGHT = 36;
	private final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	private final static int SIM_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	private final static int SIM_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;	
	
	private double sourceRadius = 20 * SCALE;
	
	private double boundaryWallOffset = 40 * SCALE;
	
	private ArrayList<Wall> walls = new ArrayList<>();
	
	public Simulation() {
		initializeClasses();
		
		startSimLoop();
	}
	
	private void update() {
		source.update();
	}
	
	public void render(Graphics2D g2d) {
		source.render(g2d);
		
		
		for(Wall wall : walls) {
			wall.draw(g2d);
		}
		
	}
	
	private void initializeClasses() {
		
		panel = new SimPanel(this);
		frame = new SimFrame(panel);
		panel.setFocusable(true);
		panel.requestFocus();
				
		source = new Source(SIM_WIDTH/2 - sourceRadius/2, SIM_HEIGHT/2 - sourceRadius/2, sourceRadius);
		
		mouseInputs = new MouseInputs(source);
		panel.addMouseListener(mouseInputs);
		panel.addMouseMotionListener(mouseInputs);
		
		initializeBoundaryWalls();
	}
	
	private void initializeBoundaryWalls() {
		
		// top wall		
		walls.add(new Wall(0 + boundaryWallOffset, 0 + boundaryWallOffset, SIM_WIDTH - boundaryWallOffset, 0 + boundaryWallOffset));
		
		// bottom wall
		walls.add(new Wall(0 + boundaryWallOffset, SIM_HEIGHT - boundaryWallOffset, SIM_WIDTH - boundaryWallOffset, SIM_HEIGHT - boundaryWallOffset));
		
		// left wall
		walls.add(new Wall(0 + boundaryWallOffset, 0 + boundaryWallOffset, 0 + boundaryWallOffset, SIM_HEIGHT - boundaryWallOffset));
		
		// right wall
		walls.add(new Wall(SIM_WIDTH - boundaryWallOffset, 0 + boundaryWallOffset, SIM_WIDTH - boundaryWallOffset, SIM_HEIGHT - boundaryWallOffset));
		
	}
	
	private void startSimLoop() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / FPS;
		double timePerUpdate = 1000000000.0 / UPS;
 
		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while(true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if(deltaF >= 1) {
				panel.repaint();
				frames++;
				deltaF--;
			}

			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
//			    System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;

			}
		}
	}
	
	public int getSimWidth() {
		return SIM_WIDTH;
	}
	
	public int getSimHeight() {
		return SIM_HEIGHT;
	}

}
