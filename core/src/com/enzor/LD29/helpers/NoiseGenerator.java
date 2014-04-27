package com.enzor.LD29.helpers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Followed tutorial at http://lodev.org/cgtutor/randomnoise.html

public class NoiseGenerator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8997468768517053471L;

	MersenneTwister mt;

	private double[][] noise;
	private int width, height;

	private static boolean createWindow = false;

	public static void main(String[] args) {
		createWindow = true;
		NoiseGenerator testingWindow = new NoiseGenerator(64, 64);
		testingWindow.setVisible(true);
	}

	public NoiseGenerator(int width, int height) {
		// Set up window stuff
		if (createWindow) {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setTitle("Noise Generator Testing Window");
			// I think we can set this to false now since we're setting the size
			// manually
			setResizable(false);
			setSize(640, 640);
			setLocationRelativeTo(null);
			add(new GraphicsPanel());
		}

		this.width = width;
		this.height = height;
		// Seed the random number generator with the hash code of the seed
		mt = new MersenneTwister();
		noise = new double[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				noise[x][y] = mt.nextDouble();
			}
		}
	}

	public NoiseGenerator(int width, int height, String seed) {
		// Set up window stuff
		if (createWindow) {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setTitle("Noise Generator Testing Window");
			// I think we can set this to false now since we're setting the size
			// manually
			setResizable(false);
			setSize(640, 620);
			setLocationRelativeTo(null);
			add(new GraphicsPanel());
		}

		this.width = width;
		this.height = height;
		// Seed the random number generator with the hash code of the seed
		mt = new MersenneTwister(seed.hashCode());
		noise = new double[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				noise[x][y] = mt.nextDouble();
				
			}
		}
	}

	class GraphicsPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5093657122060988440L;

		GraphicsPanel() {

		}

		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			for (int x = 0; x < width * 20; x++) {
				for (int y = 0; y < height * 20; y++) {
					float value = 0;
					if (Math.sqrt((x - 320) * (x - 320) + (y - 300) * (y - 300)) < 300) {
						value = (float) turbulence(x, y, 100);
						if (value < 0.29f) {
							g2.setColor(new Color(0, 0, 128));
						} else if (value < 0.43f) {
							g2.setColor(Color.blue);
						} else if (value < 0.45f) {
							g2.setColor(Color.yellow);
						} else if (value < 0.54f) {
							g2.setColor(Color.green);
						} else if (value < 0.6) {
							g2.setColor(new Color(0, 192, 0));
						} else if (value < 0.70f) {
							g2.setColor(new Color(0, 128, 0));
						} else if (value < 0.74f) {
							g2.setColor(new Color(139, 69, 19));
						} else {
							g2.setColor(Color.white);
						}
					} else {
						g2.setColor(Color.black);
					}
					// g2.setColor(new Color(value, value, value));
					g2.fillRect(x, y, 1, 1);
				}
			}
		}
	}

	private double smooth(double x, double y) {

		// Get fractional part of x and y
		double fractionX = x - Math.floor(x);
		double fractionY = y - Math.floor(y);

		// Wrap around terrain values
		int x1 = (int) Math.floor(x) % width;
		int y1 = (int) Math.floor(y) % height;

		// Get neighbor values
		int x2 = (x1 + width - 1) % width;
		int y2 = (y1 + height - 1) % height;

		// Smooth noise
		double value = 0;

		value += fractionX * fractionY * noise[x1][y1];
		value += fractionX * (1 - fractionY) * noise[x1][y2];
		value += (1 - fractionX) * fractionY * noise[x2][y1];
		value += (1 - fractionX) * (1 - fractionY) * noise[x2][y2];

		return value;
	}

	public double turbulence(double x, double y, double size) {
		double value = 0.0;
		double initialSize = size;

		while (size >= 1) {
			value += smooth(x / size, y / size) * size;
			size /= 2;
		}

		return (0.5d * value / initialSize);
	}

	/*
	 * Cosine Interpolation Function
	 * ratio - distance between first and second points
	 * a - first value
	 * b - second value
	 * mu - distance (from 0 to 1) between points a and b
	 */
	double cosineInterp(final double a, final double b, final double mu) {
		double mu2 = (1 - Math.cos(mu * Math.PI)) / 2.0d;
		return (a * (1 - mu2) + b * mu2);
	}

}
