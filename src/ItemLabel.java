import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.*;

class ImageLabel extends JLabel
{
	public ImageLabel() {}
	
//	public void setImageIcon(ImageIcon icon)
//	{
//		this.repaint();
//	}
	
//	public void paintComponent(Graphics g)
//	{
//		super.paintComponent(g);
//		
//		g.drawImage(getScaledImage(ImageResource.icon.getImage(), this.getWidth(), this.getHeight()), 0, 0, this.getWidth(), this.getHeight(), this);
//	}
}

public class ItemLabel extends CustomLabel
{
	JLabel itemImageLabel, itemNameLabel;
	
	public ItemLabel()
	{
		super();
	}

	protected void init()
	{
		this.setLayout(new BorderLayout());
		this.setOpaque(true);
	}

	protected void addComponent()
	{
//		this.itemImageLabel = new JLabel(new ImageIcon("res/item/weapon/Excalibur.png"));
//		this.itemNameLabel = new JLabel("Excalibur");
//		this.itemNameLabel.setFont(new Font("Verdana", Font.BOLD, 14));
		
		this.itemImageLabel = new JLabel();
		this.itemNameLabel = new JLabel("Item Name");
		this.itemNameLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		this.itemImageLabel.setBackground(Color.BLACK);
		this.itemNameLabel.setBackground(Color.LIGHT_GRAY);
		this.itemNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.itemImageLabel.setOpaque(true);
		this.itemNameLabel.setOpaque(true);
		
		this.add(itemImageLabel);
		this.add(itemNameLabel, BorderLayout.SOUTH);
	}
	
	public void drawItem(ImageIcon icon, String name)
	{
		this.itemImageLabel.setOpaque(false);
		this.itemImageLabel.setIcon(icon);
		this.itemImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.itemNameLabel.setText(name);
	}
	
//	public Image getScaledImage(ImageIcon srcImg)
//	{
//		int w = 100;
//		int h = 100;
//		
//	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//	    Graphics2D g2 = resizedImg.createGraphics();
//
//	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//	    g2.drawImage(srcImg.getImage(), 0, 0, w, h, null);
//	    g2.dispose();
//
//	    return resizedImg;
//	}
}
