import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.CSS;
import javax.swing.text.html.HTMLEditorKit;

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
		this.enhancementLabel.setFont(new Font("Verdana", Font.BOLD, 15));
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
		
		if( this.item != null )
			g.drawImage(this.item.getImageIcon().getImage(), 15, 15, 170, 170, this);
	}
}

class ItemDetailViewInfoPanel extends CustomPanel
{
	private Item item;
	
	JPanel infoPanel, detailPanel;
	JLabel nameLabel, requiredLabel, optionLabel, detailLabel;
	
	public ItemDetailViewInfoPanel(Item item)
	{
		this.item = item;
		this.init();
		this.addComponent();
	}
	
	protected void init()
	{
		this.setLayout(null);
		this.setOpaque(false);
	}
	
	protected void addComponent()
	{
		this.infoPanel = new JPanel(null);
		this.detailPanel = new JPanel(null);
		
		this.nameLabel = new JLabel();
		this.requiredLabel = new JLabel();
		this.optionLabel = new JLabel();
		this.detailLabel = new JLabel();
		
		this.infoPanel.setBounds(0, 0, 200, 120);
		this.infoPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(250, 208, 50)));	// U L D R 순서로 Border
		this.infoPanel.setOpaque(false);
		this.detailPanel.setBounds(0, 120, 200, 80);
		this.detailPanel.setOpaque(false);
		
//		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		jsp.setBounds(0, 0, 200, 120);
//		jsp.getViewport().setOpaque(false);
//		jsp.setOpaque(false);
		
//		HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
//		htmlEditorKit.setStyleSheet(ItemToolTip.loadStyleSheet());
//		htmlEditorKit.getStyleSheet().addRule("div { color: white; }");
	
//		this.infoPane.setForeground(new Color(255, 255, 255));
//		this.infoPane.setFont(new Font("Consolas", Font.PLAIN, 12));
//		this.infoPane.setOpaque(false);
//		this.infoPane.setContentType("text/html");
//		this.infoPane.setEditable(false);
//		this.infoPane.setEditorKit(htmlEditorKit);
		
		JScrollPane infoScrollPane = new JScrollPane(this.infoPanel);
		JScrollPane detailScrollPane = new JScrollPane(this.detailPanel);
//		
		infoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		detailScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		
		infoScrollPane.setBounds(0, 0, 200, 120);
		infoScrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(250, 208, 50)));	// U L D R 순서로 Border
		detailScrollPane.setBounds(0, 120, 200, 80);
		infoScrollPane.getViewport().setOpaque(false);
		infoScrollPane.setOpaque(false);
		detailScrollPane.getViewport().setOpaque(false);
		detailScrollPane.setOpaque(false);
//		
////		this.infoPanel.setBounds(0, 0, 200, 120);
////		this.infoPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(250, 208, 50)));	// U L D R 순서로 Border
////		this.detailPanel.setBounds(0, 120, 200, 80);
		this.infoPanel.setOpaque(false);
		this.detailPanel.setOpaque(false);
//		
		this.nameLabel.setBounds(5, 5, 190, 20);
		this.nameLabel.setForeground(new Color(217, 153, 49));
		this.nameLabel.setFont(new Font("Consolas", Font.BOLD, 18));
//		this.nameLabel.setBackground(Color.RED);
//		this.nameLabel.setOpaque(true);
		this.requiredLabel.setBounds(5, 30, 190, 15);
		this.requiredLabel.setForeground(new Color(255, 0, 0));
		this.requiredLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.requiredLabel.setFont(new Font("Consolas", Font.BOLD, 12));
		this.optionLabel.setBounds(5, 50, 190, 70);
		this.optionLabel.setForeground(new Color(255, 255, 255));
		this.optionLabel.setFont(new Font("Consolas", Font.BOLD, 12));
		this.detailLabel.setBounds(3, 3, 194, 74);
		this.detailLabel.setForeground(new Color(255, 255, 255));
		this.detailLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.detailLabel.setPreferredSize(new Dimension(194, 200));
//		
		this.infoPanel.add(nameLabel);
		this.infoPanel.add(requiredLabel);
		this.infoPanel.add(optionLabel);
		this.detailPanel.add(detailLabel);
		
		this.add(infoScrollPane);
		this.add(detailScrollPane);
//		this.add(infoPanel);
//		this.add(detailPanel);
	}
	
	public void updateItem(Item item)
	{		
		this.item = item;
		
		this.nameLabel.setText("<html>" + item.getName() + "(+" + item.getEnhancement() + ")" + "</html>");
//		this.nameLabel.setText(item.getName() + "(+" + item.getEnhancement() + ")");
		this.requiredLabel.setText("Required " + 115 + "Lv");
		this.optionLabel.setText(
				"<html>" +
					"ATK 2150<br>" + 
					"STR 80<br>" + 
					"DEX 50<br>" + 
					"STR 80<br>" + 
					"DEX 50<br>" + 
					"STR 80<br>" + 
					"DEX 50<br>" + 
					"STR 80<br>" + 
					"DEX 50<br>" + 
				"</html>");
		this.detailLabel.setText(
				"<html>&nbsp고대의 마왕 벨제부브를 토벌하고 얻은 마검이다." + 
				"드롭할 수 있는 무기 중에서 최상위 클래스에 속한다.<br><br><br><br>ddd<br><br>dd" +
				"</html>");
		
//		this.detailPane.setText(
//				"<html><div scrolling id='item-detai'>&nbsp고대의 마왕 벨제부브를 토벌하고 얻은 마검이다." + 
//				"드롭할 수 있는 무기 중에서 최상위 클래스에 속한다.dddd<br>dddd<br>ddd<br><br>ddd<br><br>dd" +
//				"</div></html>");
		
//		this.revalidate();
//		this.repaint();
	}
}

public class ItemDetailViewPanel extends CustomPanel
{
	private Item item;
	
	ItemDetailViewImagePanel cp1;
	ItemDetailViewInfoPanel cp2;
	
	public ItemDetailViewPanel(Item item)
	{
		this.item = item;
		this.init();
		this.addComponent();
	}
	
	protected void init()
	{
		this.setLayout(null);
		this.setOpaque(false);
	}
	
	protected void addComponent()
	{
		this.cp1 = new ItemDetailViewImagePanel(item);
		this.cp2 = new ItemDetailViewInfoPanel(item);
		
		this.cp1.setBounds(0, 0, 200, 200);
		this.cp2.setBounds(200, 0, 200, 200);
		
		this.add(cp1);
		this.add(cp2);
	}
	
	public void updateItem(Item item)
	{
		this.item = item;
		this.cp1.updateItem(item);
		this.cp2.updateItem(item);
	}
}
