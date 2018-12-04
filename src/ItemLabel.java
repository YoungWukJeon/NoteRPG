import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
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
				
				Enumeration<?> rules = styleSheet.getStyleNames();
		        while (rules.hasMoreElements()) 
		        {
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
					"<div id='item-tooltip-area'>" + 
						"<div style='background: blue;' id='item-image-area'>" + 
							"<div id='item-enhancement-area'>+" + item.getEnhancement() + "</div>" + 
							"<div id='item-image'>" +
								"<img width=170 height=170 src='file:" + item.getImageIcon().getDescription() + "' />" +
							"</div>" +
							"<div id='item-rating-area'>★ ★ ★</div>" + 
						"</div>" + 
						"<div id='item-info-area'>" + 
							"<div id='item-info' style='background: green; background-attachment: scroll;'>" + 
								"<div id='item-info-name'>" + item.getName() + "(+" + item.getEnhancement() + ")</div>" +
								"<div id='item-info-required'>Required 115Lv</div>" + 
								"<div id='item-info-option'>" + 
									"ATK 2150<br>" + 
									"STR 80<br>" + 
									"DEX 50<br>" + 
									"STR 80<br>" + 
									"DEX 50<br>" + 
									"STR 80<br>" + 
									"DEX 50<br>" + 
									"STR 80<br>" + 
									"DEX 50<br>" + 
								"</div>" + 
							"</div>" +
							"<div id='item-detail' style='background-attachment: scroll;'>" + 
								"&nbsp고대의 마왕 벨제부브를 토벌하고 얻은 마검이다." + 
								"드롭할 수 있는 무기 중에서 최상위 클래스에 속한다.<br><br><br><br>ddd<br><br>dd" + 
							"</div>" + 
						"</div>" + 
					"</div>" +
				"</html>";
		
//		System.out.println(html);
	
		return html;
	}
}

public class ItemLabel extends CustomLabel
{
	private LinkedList<Item> itemList;
	
	public ItemLabel(LinkedList<Item> itemList)
	{
		this.itemList = itemList;
		this.init();
	}
	
	public ItemLabel()
	{
		super();
	}

	protected void init()
	{
		this.setLayout(new BorderLayout());
//		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
//		if( this.getItemList() != null )
//		{
//			HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
//			htmlEditorKit.setStyleSheet(ItemToolTip.loadStyleSheet());
////			htmlEditorKit.getStyleSheet().addRule("div { position: inherited; }");			
//			this.setToolTipText(ItemToolTip.getToolTipTextByHTML(this.getItem(0)));
//			ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
//		}
		
		if( this.getItemList() != null )
			this.addMouseListener(new ItemMouseAdapter(this.getItem(0)));
		else
			this.addMouseListener(new NoItemMouseAdapter());
	}
	
//	public JToolTip createToolTip()
//	{
//		JToolTip tip = super.createToolTip();
//        tip.setBackground(new Color(0, 0, 0, 210));
//        tip.setOpaque(true);
//        this.repaint();
//        
//        return tip;
//	}
	
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
	
	class ItemMouseAdapter extends MouseAdapter
	{
		private Item item;
		
		ItemMouseAdapter(Item item)
		{
			this.item = item;
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0)
		{
			System.out.println(this.item.toString());
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			ItemDetailViewDialog.INSTANCE.startThread(this.item);
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// sleep이 종료된 후에 마우스를 이동했을 경우 다른 곳에서 Dialog가 나타나는 것을 방지
			ItemDetailViewDialog.INSTANCE.stopThread();
		}
	}
	
	class NoItemMouseAdapter extends MouseAdapter
	{
		@Override
		public void mouseEntered(MouseEvent e)
		{
			if( ItemDetailViewDialog.INSTANCE.isShowing() )
				ItemDetailViewDialog.INSTANCE.dismiss();
		}
	}
}
