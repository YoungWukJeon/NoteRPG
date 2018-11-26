import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.event.*;

public class ItemLabel extends CustomLabel implements MouseListener, Runnable
{
	private LinkedList<Item> itemList;
	private Thread itemDetailViewThread;
	
	public ItemLabel(LinkedList<Item> itemList)
	{
		this();
		this.itemList = itemList;
	}
	
	public ItemLabel()
	{
		super();
	}

	protected void init()
	{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.addMouseListener(this);
	}

	protected void addComponent()
	{
	}
	
	public LinkedList<Item> getItemList()
	{
		return this.itemList;
	}
	
	public Item getItem(int pos)
	{
		return this.getItemList().get(pos);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if( this.getItemList() != null )
			g.drawImage(this.getItem(0).getImageIcon().getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		
		if( this.getItemList() != null && !this.getItem(0).isRedundant() && this.getItem(0).getEnhancement() > 0 )
		{
			g.setColor(Color.RED);
			
			if( this.getItem(0).getEnhancement() > 9 )
				g.drawString("+" + Integer.toString(this.getItem(0).getEnhancement()), this.getWidth() - 23, this.getHeight() - 45);
			else
				g.drawString("+" + Integer.toString(this.getItem(0).getEnhancement()), this.getWidth() - 17, this.getHeight() - 45);
		}
		
		if( this.getItemList() != null && this.getItem(0).isRedundant() )
		{
			if( this.getItemList().size() > 99 )
				g.drawString("99+", this.getWidth() - 20, this.getHeight() - 5);
			else if( this.getItemList().size() > 9 )
				g.drawString(Integer.toString(this.getItemList().size()), this.getWidth() - 15, this.getHeight() - 5);
			else
				g.drawString(Integer.toString(this.getItemList().size()), this.getWidth() - 10, this.getHeight() - 5);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		if( this.getItemList() != null )
			System.out.println(this.getItem(0).toString());
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		if( this.itemDetailViewThread == null || !this.itemDetailViewThread.isAlive() )
		{
			itemDetailViewThread = new Thread(this);
			this.itemDetailViewThread.start();
		}
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		if( this.itemDetailViewThread == null || !this.itemDetailViewThread.isAlive() )
			this.itemDetailViewThread.interrupt();
		
		// Show Panel Visible
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void run()
	{
		try
		{
			Thread.sleep(1000);
			
			// Show Panel Visible
		}
		catch(InterruptedException ie)
		{
			System.out.println("MouseExited");
		}
	}
	
//	public void drawItem(ImageIcon icon, String name)
//	{	
//	}
	
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
