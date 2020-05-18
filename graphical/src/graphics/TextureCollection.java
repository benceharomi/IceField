package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TextureCollection {

	private static TextureCollection single_instance = null;
	private static HashMap<Textures, ArrayList<ImageIcon>> textures = new HashMap<Textures, ArrayList<ImageIcon>>();

	public static TextureCollection instance() {
		if (single_instance == null) {
			single_instance = new TextureCollection();
			loadTextures();
		}
		return single_instance;
	}

	private static void loadTextures() {

		setSingleTextures();
		setUpStableField();
		setUpUnStableField();
		setUpHoleField();
		setGunPart();

	}

	private static void setSingleTextures() {

		ArrayList<ImageIcon> eskimo = new ArrayList<ImageIcon>();
		eskimo.add(new ImageIcon("images/eskimo1.png"));
		eskimo.add(new ImageIcon("images/eskimo2.png"));
		eskimo.add(new ImageIcon("images/eskimo3.png"));
		eskimo.add(new ImageIcon("images/eskimo4.png"));
		eskimo.add(new ImageIcon("images/eskimo5.png"));
		eskimo.add(new ImageIcon("images/eskimo6.png"));
		textures.put(Textures.ESKIMO, eskimo);

		ArrayList<ImageIcon> explorer = new ArrayList<ImageIcon>();
		explorer.add(new ImageIcon("images/explorer1.png"));
		explorer.add(new ImageIcon("images/explorer2.png"));
		explorer.add(new ImageIcon("images/explorer3.png"));
		explorer.add(new ImageIcon("images/explorer4.png"));
		explorer.add(new ImageIcon("images/explorer5.png"));
		explorer.add(new ImageIcon("images/explorer6.png"));
		textures.put(Textures.EXPLORER, explorer);

		ArrayList<ImageIcon> polarbear = new ArrayList<ImageIcon>();
		polarbear.add(new ImageIcon("images/polarbear.png"));
		textures.put(Textures.BEAR, polarbear);

		ArrayList<ImageIcon> igloo = new ArrayList<ImageIcon>();
		igloo.add(new ImageIcon("images/igloo.png"));
		textures.put(Textures.IGLOO, igloo);

		ArrayList<ImageIcon> tent = new ArrayList<ImageIcon>();
		tent.add(new ImageIcon("images/tent.png"));
		textures.put(Textures.TENT, tent);

		ArrayList<ImageIcon> rope = new ArrayList<ImageIcon>();
		rope.add(new ImageIcon("images/rope.png"));
		textures.put(Textures.ROPE, rope);

		ArrayList<ImageIcon> scuba = new ArrayList<ImageIcon>();
		scuba.add(new ImageIcon("images/scuba.png"));
		textures.put(Textures.SCUBA, scuba);

		ArrayList<ImageIcon> food = new ArrayList<ImageIcon>();
		food.add(new ImageIcon("images/food.png"));
		textures.put(Textures.FOOD, food);

		ArrayList<ImageIcon> shovel = new ArrayList<ImageIcon>();
		shovel.add(new ImageIcon("images/shovel.png"));
		textures.put(Textures.SHOVEL, shovel);

	}

	private static void setUpStableField() {
		ArrayList<ImageIcon> stableIcons = new ArrayList<ImageIcon>();

		stableIcons.add(new ImageIcon("images/stablefield1.png"));
		stableIcons.add(new ImageIcon("images/stablefield2.png"));
		stableIcons.add(new ImageIcon("images/stablefield3.png"));
		stableIcons.add(new ImageIcon("images/stablefield4.png"));
		stableIcons.add(new ImageIcon("images/stablefield5.png"));
		stableIcons.add(new ImageIcon("images/stablefield6.png"));

		textures.put(Textures.STABLE, stableIcons);
	}

	private static void setUpUnStableField() {
		ArrayList<ImageIcon> unstableIcons = new ArrayList<ImageIcon>();

		unstableIcons.add(new ImageIcon("images/unstablefield1.png"));
		unstableIcons.add(new ImageIcon("images/unstablefield2.png"));
		unstableIcons.add(new ImageIcon("images/unstablefield3.png"));
		unstableIcons.add(new ImageIcon("images/unstablefield4.png"));
		unstableIcons.add(new ImageIcon("images/unstablefield5.png"));
		unstableIcons.add(new ImageIcon("images/unstablefield6.png"));

		textures.put(Textures.UNSTABLE, unstableIcons);
	}

	private static void setUpHoleField() {
		ArrayList<ImageIcon> holeIcons = new ArrayList<ImageIcon>();

		holeIcons.add(new ImageIcon("images/holefield1.png"));
		holeIcons.add(new ImageIcon("images/holefield2.png"));
		holeIcons.add(new ImageIcon("images/holefield3.png"));
		holeIcons.add(new ImageIcon("images/holefield4.png"));
		holeIcons.add(new ImageIcon("images/holefield5.png"));

		textures.put(Textures.HOLE, holeIcons);
	}

	private static void setGunPart() {
		ArrayList<ImageIcon> gunIcon = new ArrayList<ImageIcon>();
		gunIcon.add(new ImageIcon("images/gunpart1.png"));
		gunIcon.add(new ImageIcon("images/gunpart2.png"));
		gunIcon.add(new ImageIcon("images/gunpart3.png"));
		textures.put(Textures.GUNPART, gunIcon);
	}

	public static ArrayList<ImageIcon> getTexture(Textures t) {
		return textures.get(t);
	}
}
