package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	Tile[] tiles;
	int mapTileNum[][];

	public TileManager(GamePanel gp) {

		this.gp = gp;
		tiles = new Tile[16];
		mapTileNum = new int[gp.maxColumns][gp.maxRows];


		getTileImage();
		loadMap("/maps/map00.txt");
	}

	public void getTileImage() {
		try {
			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/Applying_tile01.png"));
			tiles[1].collsion = true;

			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/Applying_tile02.png"));
			tiles[2].collsion = true;

			tiles[3] = new Tile();
			tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/Applying_tile03.png"));
			tiles[3].collsion = true;
			
			tiles[4] = new Tile();
			tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/Applying_tile04.png"));
			tiles[4].collsion = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String map) {
		try {
			InputStream is = getClass().getResourceAsStream(map);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			int row = 0;

			while (row < gp.maxRows) {
				String line = br.readLine();
				if (line == null)
					break;

				String[] numbers = line.split(" ");

				while (col < gp.maxColumns) {
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}

				col = 0;
				row++;

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {

		int x = 0;
		int y = 0;

		for (int row = 0; row < gp.maxRows; row++) {
			for (int col = 0; col < gp.maxColumns; col++) {
				int tileNum = mapTileNum[col][row];
				if (tileNum > 0 && tileNum < tiles.length && tiles[tileNum] != null) {
					g2.drawImage(tiles[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
				}
				x += gp.tileSize;
			}
			x = 0;
			y += gp.tileSize;
		}

	}
}
