import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Style;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

class ItemToolTip
{
	private static final String ITEM_TOOLTIP_CSS = "res/etc/item_tooltip.css"; 
	public static StyleSheet styleSheet;
	
	public static StyleSheet loadStyleSheet()
	{	
		if( styleSheet == null )
		{				
			try
			{
				InputStream is = new FileInputStream(ITEM_TOOLTIP_CSS);
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				styleSheet = new StyleSheet();
				styleSheet.loadRules(br, null);
				
				Enumeration rules = styleSheet.getStyleNames();
		         while (rules.hasMoreElements()) {
		             String name = (String) rules.nextElement();
		             Style rule = styleSheet.getStyle(name);
		             System.out.println(rule.toString());
		         }
				
				br.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
				styleSheet = null;
			}
		}
		
		return styleSheet;
	}
	
	public static String getToolTipTextByHTML(Item item)
	{
//		URL url = ImageResource.class.getResource(this.itemLabel[i][j].getItem(0).getImageIcon().getDescription());
		
		String html = 
				"<html>" +
						"<div>" +
							"<img width=150 height=150 src='file:" + item.getImageIcon().getDescription() + "' />" +
						"</div>" +
				"</html>";
		
		System.out.println(html);
	
		return html;
	}
}

public class ItemLabel extends CustomLabel implements MouseListener, EventCallBackListener
{
	private LinkedList<Item> itemList;
	
	public ItemLabel(LinkedList<Item> itemList)
	{
		this.itemList = itemList;
		this.init();
		this.addComponent();
	}
	
	public ItemLabel()
	{
		super();
	}

	protected void init()
	{
		this.setLayout(new BorderLayout());
//		this.setBackground(Color.WHITE);
//		this.setOpaque(true);
//		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.addMouseListener(this);
	}

	protected void addComponent()
	{
		if( this.getItemList() != null )
		{
			HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
			htmlEditorKit.setStyleSheet(ItemToolTip.loadStyleSheet());
			this.setToolTipText(ItemToolTip.getToolTipTextByHTML(this.getItem(0)));
		}
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
		
		g.drawImage(ImageResource.itemBorder.getImage(), 0, 0, this.getWidth() - 1, this.getHeight() - 1, this);
		
		if( this.getItemList() != null )
			g.drawImage(this.getItem(0).getImageIcon().getImage(), (int) (this.getWidth() * 0.1), (int) (this.getHeight() * 0.1), this.getWidth() - (int) (this.getWidth() * 0.2), this.getHeight() - (int) (this.getHeight() * 0.2), this);
		
		if( this.getItemList() != null && !this.getItem(0).isRedundant() && this.getItem(0).getEnhancement() > 0 )
		{
			g.setColor(Color.RED);
			
			if( this.getItem(0).getEnhancement() > 9 )
				g.drawString("+" + Integer.toString(this.getItem(0).getEnhancement()), this.getWidth() - (int) (this.getWidth() * 0.45), this.getHeight() - (int) (this.getHeight() * 0.75));
			else
				g.drawString("+" + Integer.toString(this.getItem(0).getEnhancement()), this.getWidth() - (int) (this.getWidth() * 0.35), this.getHeight() - (int) (this.getHeight() * 0.75));
		}
		
		if( this.getItemList() != null && this.getItem(0).isRedundant() )
		{
			if( this.getItemList().size() > 99 )
				g.drawString("99+", this.getWidth() - (int) (this.getWidth() * 0.67), this.getHeight() - (int) (this.getHeight() * 0.08));
			else if( this.getItemList().size() > 9 )
				g.drawString(Integer.toString(this.getItemList().size()), this.getWidth() - (int) (this.getWidth() * 0.25), this.getHeight() - (int) (this.getHeight() * 0.08));
			else
				g.drawString(Integer.toString(this.getItemList().size()), this.getWidth() - (int) (this.getWidth() * 0.17), this.getHeight() - (int) (this.getHeight() * 0.08));
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
//		if( !ThreadPool.INSTANCE.isAlive(ThreadPool.THREAD_TYPE.ITEM_DETAIL_VIEW) )
//			ThreadPool.INSTANCE.stopThread(ThreadPool.THREAD_TYPE.ITEM_DETAIL_VIEW, this);
//		
//		ThreadPool.INSTANCE.startThread(ThreadPool.THREAD_TYPE.ITEM_DETAIL_VIEW, this);
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
//		ThreadPool.INSTANCE.stopThread(ThreadPool.THREAD_TYPE.ITEM_DETAIL_VIEW, this);
		// Show Panel Visible
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void nextWork(boolean isSuccessful, Object result)
	{
		if( isSuccessful )
		{
			System.out.println("Work!!");
			
			if( this.getItemList() != null )
				System.out.println(this.getItem(0));
			else
				System.out.println("No item");
		}
		else
			System.out.println("MouseExited");
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
