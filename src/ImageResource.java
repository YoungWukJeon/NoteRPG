import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageResource
{
	public static ImageIcon sword;
	public static ImageIcon bow;
	public static ImageIcon crossedSwords;
	public static ImageIcon darkRepulser;
	public static ImageIcon elucidator;
	public static ImageIcon guiltyThornNovel;
	public static ImageIcon twoHandsSwords;
	
	public static ImageIcon itemBorder;
	public static ImageIcon statusCollapsedBorder;
	public static ImageIcon statusExpandedBorder;
	public static ImageIcon levelBorder;
	
	public ImageResource()
	{
		sword = new ImageIcon("res/item/weapon/Excalibur.png");
		bow = new ImageIcon("res/item/weapon/Bow.png");
		crossedSwords = new ImageIcon("res/item/weapon/Crossed swords.png");
		darkRepulser = new ImageIcon("res/item/weapon/Dark repulser.png");
		elucidator = new ImageIcon("res/item/weapon/Elucidator.png");
		guiltyThornNovel = new ImageIcon("res/item/weapon/Guilty Thorn Novel.png");
		twoHandsSwords = new ImageIcon("res/item/weapon/Two hands swords.png");
		
		itemBorder = new ImageIcon("res/layout/item_border.png");
		statusCollapsedBorder = new ImageIcon("res/layout/status_collapsed_border.png");
		statusExpandedBorder = new ImageIcon("res/layout/status_expanded_border.png");
		levelBorder = new ImageIcon("res/layout/level_border.png");
	}
}
