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
	JLabel itemImageLabel;
	
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
		this.itemImageLabel = new JLabel(new ImageIcon("res/item/weapon/Excalibur.png"));		
		this.itemImageLabel = new JLabel();
		this.itemImageLabel.setBackground(Color.WHITE);
		this.itemImageLabel.setOpaque(true);
		this.add(itemImageLabel);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawString("12", 45, 55);
	}
	
	public void drawItem(ImageIcon icon, String name)
	{
		this.itemImageLabel.setOpaque(false);
		this.itemImageLabel.setIcon(icon);
		this.itemImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
