package com.enzor.LD29.helpers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Followed tutorial at http://lodev.org/cgtutor/randomnoise.html

public class NoiseGenerator {

	MersenneTwister mt;

	private double[][] noise;
	private int width, height;

	private static boolean createWindow = false;

	public NoiseGenerator(int width, int height) {
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
