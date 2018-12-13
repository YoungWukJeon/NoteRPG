import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.AttributedCharacterIterator;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.CSS;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

class ItemInfoHTML
{
	private static final String ITEM_INFO_CSS = "res/etc/item_info.css"; 
	public static StyleSheet styleSheet;
	
	public static StyleSheet loadStyleSheet()
	{	
		if( styleSheet == null )
		{				
			try
			{
				InputStream is = new FileInputStream(ITEM_INFO_CSS);
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				styleSheet = new StyleSheet();
				styleSheet.loadRules(br, null);
				
				Enumeration<?> rules = styleSheet.getStyleNames();
		        while (rules.hasMoreElements()) 
		        {
		        	String name = (String) rules.nextElement();
		        	Style rule = styleSheet.getStyle(name);
//		        	System.out.println(rule.toString());
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
	
	public static String getInfoHTMLByItem(Item item)
	{
//		URL url = ImageResource.class.getResource(this.itemLabel[i][j].getItem(0).getImageIcon().getDescription());
	
		return 
				"<div id='item-info'>" +
					"<div id='item-info-name'>" + item.getName() + "(+" + item.getEnhancement() + ")</div>" +
					"<div id='item-info-required'>" + "Required " + 115 + "Lv" + "</div>" + 
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
				"</div>";
	}
	
	public static String getDetailHTMLByItem(Item item)
	{
//		URL url = ImageResource.class.getResource(this.itemLabel[i][j].getItem(0).getImageIcon().getDescription());
	
		return 
				"<div id='item-detail'>" + 
					"&nbsp고대의 마왕 벨제부브를 토벌하고 얻은 마검이다." + 
					"드롭할 수 있는 무기 중에서 최상위 클래스에 속한다." +
					"<br><br><br><br>ddd<br><br>dd" + 
				"</div>";
	}
}

class ItemDetailViewImagePanel extends CustomPanel
{
	private Item item;
	
	JLabel enhancementLabel;
	JLabel ratingLabel;
	
	public ItemDetailViewImagePanel(Item item)
	{
		this.item = item;
		this.init();
		this.addComponent();
	}
	
	protected void init()
	{
		this.setLayout(null);
		this.setOpaque(false);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(250, 208, 50)));	// U L D R 순서로 Border
	}
	
	protected void addComponent() 
	{
		this.enhancementLabel = new JLabel();
		this.ratingLabel = new JLabel();
		
		this.enhancementLabel.setBounds(0, 5, 195, 15);
		this.enhancementLabel.setForeground(new Color(255, 0, 0));
		this.enhancementLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.enhancementLabel.setFont(new Font("", Font.BOLD, 15));
		this.ratingLabel.setBounds(0, 180, 195, 15);
		this.ratingLabel.setForeground(new Color(250, 208, 50));
		this.ratingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.ratingLabel.setFont(new Font("", Font.PLAIN, 15));
		
		this.add(enhancementLabel);
		this.add(ratingLabel);
	}
	
	public void updateItem(Item item)
	{
		this.item = item;
		this.enhancementLabel.setText("+" + item.getEnhancement());
		this.ratingLabel.setText("★ ★ ★");
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
//		g.drawImage(ImageResource.itemBorder.getImage(), 0, 0, this.getWidth() - 1, this.getHeight() - 1, this);
		
		if( this.item != null )
			g.drawImage(this.item.getImageIcon().getImage(), 15, 15, 170, 170, this);
	}
}

class ItemDetailViewInfoPanel extends CustomPanel
{	
	JTextPane infoTextPane, detailTextPane;
	JScrollPane infoScrollPane, detailScrollPane;
	
	protected void init()
	{
		this.setLayout(null);
		this.setOpaque(false);
	}
	
	protected void addComponent()
	{	
		this.infoTextPane = new JTextPane();
		this.detailTextPane = new JTextPane();
		
		// Invisible Vertical Scroll
		JScrollBar infoScrollBar = new JScrollBar(JScrollBar.VERTICAL) {
			@Override
			public boolean isVisible() {
				return true;
			}
		};
		JScrollBar detailScrollBar = new JScrollBar(JScrollBar.VERTICAL) {
			@Override
			public boolean isVisible() {
				return true;
			}
		};
		
		this.infoScrollPane = new JScrollPane(this.infoTextPane);
		this.detailScrollPane = new JScrollPane(this.detailTextPane);
		
		this.infoScrollPane.setBounds(0, 0, 200, 120);
		this.infoScrollPane.setOpaque(false);
		this.infoScrollPane.setBorder(null);
		this.infoScrollPane.setVerticalScrollBar(infoScrollBar);
		this.infoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		this.infoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.infoScrollPane.getVerticalScrollBar().setUnitIncrement(12);
		this.infoScrollPane.getViewport().setOpaque(false);
		this.infoScrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(250, 208, 50)));	// U L D R 순서로 Border
		this.detailScrollPane.setBounds(0, 120, 200, 80);
		this.detailScrollPane.setOpaque(false);
		this.detailScrollPane.setBorder(null);
		this.detailScrollPane.setVerticalScrollBar(detailScrollBar);
		this.detailScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		this.detailScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.detailScrollPane.getVerticalScrollBar().setUnitIncrement(12);
		this.detailScrollPane.getViewport().setOpaque(false);
		
		HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
		htmlEditorKit.setStyleSheet(ItemInfoHTML.loadStyleSheet());

		this.infoTextPane.setOpaque(false);
		this.infoTextPane.setEditable(false);
		this.infoTextPane.setEditorKit(htmlEditorKit);
		this.detailTextPane.setOpaque(false);
		this.detailTextPane.setEditable(false);
		this.detailTextPane.setEditorKit(htmlEditorKit);
		
		this.add(infoScrollPane);
		this.add(detailScrollPane);
	}
	
	public void updateItem(Item item)
	{		
		this.infoTextPane.setText(ItemInfoHTML.getInfoHTMLByItem(item));
		this.detailTextPane.setText(ItemInfoHTML.getDetailHTMLByItem(item));
		
		this.infoScrollPane.getVerticalScrollBar().setValue(0);
		this.detailScrollPane.getVerticalScrollBar().setValue(0);
	}	
}

public class ItemDetailViewPanel extends CustomPanel
{	
	ItemDetailViewImagePanel itemDetailViewImagePanel;
	ItemDetailViewInfoPanel itemDetailViewInfoPanel;
	
	protected void init()
	{
		this.setLayout(null);
		this.setOpaque(false);
	}
	
	protected void addComponent()
	{
		this.itemDetailViewImagePanel = new ItemDetailViewImagePanel(null);
		this.itemDetailViewInfoPanel = new ItemDetailViewInfoPanel();
		
		this.itemDetailViewImagePanel.setBounds(0, 0, 200, 200);
		this.itemDetailViewInfoPanel.setBounds(200, 0, 200, 200);
		
		this.add(itemDetailViewImagePanel);
		this.add(itemDetailViewInfoPanel);
	}
	
	public void updateItem(Item item)
	{
		this.itemDetailViewImagePanel.updateItem(item);
		this.itemDetailViewInfoPanel.updateItem(item);
	}
}
