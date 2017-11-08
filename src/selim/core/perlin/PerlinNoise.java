package selim.core.perlin;

import java.util.Random;

public class PerlinNoise {

	private final Random rand;
	private final int width;
	private final int height;
	private final int octaves;
	private float[][] noise;

	public PerlinNoise(int width, int height) {
		this(new Random(), width, height, 4);
	}

	public PerlinNoise(int width, int height, int octaves) {
		this(new Random(), width, height, octaves);
	}

	public PerlinNoise(Random rand, int width, int height) {
		this(rand, width, height, 4);
	}

	public PerlinNoise(Random rand, int width, int height, int octaves) {
		this.rand = rand;
		this.width = width;
		this.height = height;
		this.octaves = octaves;
	}

	public float noise(int x, int y) {
		if (this.noise == null)
			this.noise = genPerlinNoise(genWhiteNoise(width, height), octaves);
		return x < width && x >= 0 && y < height && y >= 0 ? this.noise[x][y] : 0.0f;
	}

	private float[][] genWhiteNoise(int width, int height) {
		float[][] noise = new float[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < width; j++)
				noise[i][j] = (float) rand.nextDouble() % 1;
		return noise;
	}

	private float[][] genSmoothNoise(float[][] noise, int octaves) {
		int width = noise.length;
		int height = noise[0].length;
		float[][] smoothNoise = new float[width][height];
		int per = 1 << octaves;
		float freq = 1.0f / per;
		for (int i = 0; i < width; i++) {
			int sampleI0 = (i / per) * per;
			int sampleI1 = (sampleI0 + per) % width;
			float horzBlend = (i - sampleI0) * freq;
			for (int j = 0; j < height; j++) {
				int sampleJ0 = (j / per) * per;
				int sampleJ1 = (sampleJ0 + per) % height;
				float vertBlend = (j - sampleJ0) * freq;
				float top = interpolate(noise[sampleI0][sampleJ0], noise[sampleI1][sampleJ0], horzBlend);
				float bottom = interpolate(noise[sampleI0][sampleJ1], noise[sampleI1][sampleJ1],
						horzBlend);
				smoothNoise[i][j] = interpolate(top, bottom, vertBlend);
			}
		}
		return smoothNoise;
	}

	private float interpolate(float x0, float x1, float alpha) {
		return x0 * (1 - alpha) + alpha * x1;
	}

	private float[][] genPerlinNoise(float[][] noise, int octaves) {
		int width = noise.length;
		int height = noise[0].length;
		float[][][] smoothNoise = new float[octaves][][];
		float persistance = 0.5f;
		for (int i = 0; i < octaves; i++)
			smoothNoise[i] = genSmoothNoise(noise, i);
		float[][] perlinNoise = new float[width][height];
		float amp = 1.0f;
		float totalAmp = 0.0f;
		for (int octave = octaves - 1; octave > 0; octave--) {
			amp *= persistance;
			totalAmp += amp;
			for (int i = 0; i < width; i++)
				for (int j = 0; j < height; j++)
					perlinNoise[i][j] += smoothNoise[octave][i][j] * amp;
		}
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				perlinNoise[i][j] /= totalAmp;
		return perlinNoise;
	}

}
