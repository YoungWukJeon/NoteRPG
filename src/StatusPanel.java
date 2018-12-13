import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class PointLabel extends CustomLabel
{
//	enum PointType { HIT_POINT, MANA_POINT, EXPERIENCE_POINT }
	enum PointType { HP, MP, EXP }
	
	PointType pointType;
	int maxPoint;
	int currentPoint;
	Color startColor;
	Color endColor;
	Color backColor;
	
	PointLabel(PointType pointType, int maxPoint, int currentPoint)
	{
		this.pointType = pointType;
		this.maxPoint = maxPoint;
		this.currentPoint = currentPoint;
		this.init();
		this.changeText();
	}
	
	protected void init()
	{
//		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(255, 255, 255)));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setForeground(new Color(255, 255, 255));
		this.setFont(new Font("Verdana", Font.BOLD, 10));
		
		if( this.pointType != null )
		{
			if( this.pointType.ordinal() == 0 )
			{
				this.startColor = new Color(255, 128, 128);
				this.endColor = new Color(255, 0, 0);
				this.backColor = new Color(60, 0, 0);
			}
			else if( this.pointType.ordinal() == 1 )
			{
				this.startColor = new Color(128, 128, 255);
				this.endColor = new Color(0, 0, 255);
				this.backColor = new Color(0, 0, 60);
			}
			else
			{
				this.startColor = new Color(128, 255, 128);
				this.endColor = new Color(0, 255, 0);
				this.backColor = new Color(0, 60, 0);
				this.setToolTipText(String.format("%,d / %,d", this.currentPoint, this.maxPoint));
			}
		}
	}
	
	public JToolTip createToolTip()
	{
		JToolTip tip = super.createToolTip();
		ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
		
        tip.setBackground(new Color(0, 0, 0, 210));
        tip.setOpaque(true);
        tip.setFont(new Font("Verdana", Font.BOLD, 12));
        tip.setForeground(new Color(255, 255, 255));
        tip.setBorder(null);
        
        return tip;
	}
	
	public void changeMaxPoint(int point)
	{
		this.maxPoint = point;
		this.changeText();
	}
	
	public void changeCurrentPoint(int point)
	{
		this.currentPoint = point;
		this.changeText();
	}
	
	public void changeText()
	{	
		if( this.pointType != null )
		{
			if( this.pointType.ordinal() == 2 )
				this.setText(String.format("%s %,.1f%%", this.pointType.name(), (this.currentPoint * 100 / (double) this.maxPoint)));
			else
				this.setText(String.format("%s %,d / %,d", this.pointType.name(), this.currentPoint, this.maxPoint));
		}
		this.repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		int width = this.getWidth();
		int height = this.getHeight();
		int current = (int) (width * (this.currentPoint / (double) this.maxPoint));
		
		GradientPaint paint = new GradientPaint(0, 0, this.startColor, width, height, this.endColor, true);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(this.backColor);
		g2d.fillRect(0, 0, width, height);
		
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, current, height);
		
		super.paintComponent(g2d);
	}
}

class LevelLabel extends CustomLabel
{
	int level;
	
	LevelLabel(int level)
	{
		this.level = level;
		this.init();
	}
	
	protected void init()
	{
		this.setForeground(new Color(255, 255, 255));
		this.setFont(new Font("Verdana", Font.BOLD, 12));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setText(Integer.toString(this.level));
	}
	
	public void changeLevel(int level)
	{
		this.level = level;
		this.setText(Integer.toString(this.level));
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(ImageResource.levelBorder.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		
//		g.setColor(Color.WHITE);
//		g.drawArc(0, 0, getWidth(), getHeight(), 0, 360);
		
		super.paintComponent(g);
	}
}

class StatusLabel extends CustomLabel
{
	enum StatusType { JOB, ATK, DEF, STR, DEX, LUK, INT }
	
	StatusType statusType;
	Object value;
	
	StatusLabel(StatusType statusType, Object value)
	{
		this.statusType = statusType;
		this.value = value;
		this.init();
		this.changeText();
	}
	
	protected void init()
	{
		this.setForeground(new Color(255, 255, 255));
		this.setFont(new Font("", Font.BOLD, 12));
	}
	
	public void changeValue(Object value)
	{
		this.value = value;
		this.changeText();
	}
	
	public void changeText()
	{	
		if( this.statusType != null )
		{
			if( this.value instanceof String )
			{
				if( this.statusType.ordinal() == 0 )
					this.setText(String.format("%s : %s", "직업", this.value));	
				else
					this.setText(String.format("%s : %s", this.statusType.name(), this.value));
			}	
			else if( this.value instanceof Integer )
				this.setText(String.format("%s : %,d", this.statusType.name(), this.value));
		}
		this.repaint();
	}
}

class StatusInfoPanel extends CustomPanel
{
	PointLabel hpLabel, mpLabel, expLabel;
	LevelLabel levelLabel;
	StatusLabel jobLabel, atkLabel, defLabel, strLabel, dexLabel, lukLabel, intLabel;

	protected void init()
	{
		this.setLayout(null);
		this.setBackground(new Color(0, 0, 0, 240));
	}

	protected void addComponent()
	{
		this.hpLabel = new PointLabel(PointLabel.PointType.HP, Main.characterInfo.getMaxHP(), Main.characterInfo.getCurrentHP());
		this.mpLabel = new PointLabel(PointLabel.PointType.MP, Main.characterInfo.getMaxMP(), Main.characterInfo.getCurrentMP());
		this.expLabel = new PointLabel(PointLabel.PointType.EXP, Main.characterInfo.getMaxEXP(), Main.characterInfo.getCurrentEXP());
		this.levelLabel = new LevelLabel(Main.characterInfo.getLevel());
		this.jobLabel = new StatusLabel(StatusLabel.StatusType.JOB, Main.characterInfo.getJob());
		this.atkLabel = new StatusLabel(StatusLabel.StatusType.ATK, Main.characterInfo.getAtk());
		this.defLabel = new StatusLabel(StatusLabel.StatusType.DEF, Main.characterInfo.getDef());
		this.strLabel = new StatusLabel(StatusLabel.StatusType.STR, Main.characterInfo.getStatusStr());
		this.dexLabel = new StatusLabel(StatusLabel.StatusType.DEX, Main.characterInfo.getStatusDex());
		this.lukLabel = new StatusLabel(StatusLabel.StatusType.LUK, Main.characterInfo.getStatusLuk());
		this.intLabel = new StatusLabel(StatusLabel.StatusType.INT, Main.characterInfo.getStatusInt());
		
		StatusLabel l1 = new StatusLabel(StatusLabel.StatusType.ATK, 200);
		StatusLabel l2 = new StatusLabel(StatusLabel.StatusType.ATK, 200);
		StatusLabel l3 = new StatusLabel(StatusLabel.StatusType.ATK, 200);
		StatusLabel l4 = new StatusLabel(StatusLabel.StatusType.ATK, 200);
		StatusLabel l5 = new StatusLabel(StatusLabel.StatusType.ATK, 201);
		
		this.hpLabel.setBounds(10, 10, 150, 15);
		this.mpLabel.setBounds(10, 30, 150, 15);
		this.expLabel.setBounds(10, 50, 150, 15);
		this.levelLabel.setBounds(10, 75, 50, 50);
		this.jobLabel.setBounds(70, 75, 90, 20);
		this.atkLabel.setBounds(70, 100, 90, 20);
		this.defLabel.setBounds(10, 135, 150, 20);
		this.strLabel.setBounds(10, 160, 150, 20);
		this.dexLabel.setBounds(10, 185, 150, 20);
		this.lukLabel.setBounds(10, 210, 150, 20);
		this.intLabel.setBounds(10, 235, 150, 20);
		
		l1.setBounds(10, 260, 150, 20);
		l2.setBounds(10, 285, 150, 20);
		l3.setBounds(10, 310, 150, 20);
		l4.setBounds(10, 335, 150, 20);
		l5.setBounds(10, 360, 150, 20);
		
		this.add(hpLabel);
		this.add(mpLabel);
		this.add(expLabel);
		this.add(levelLabel);
		this.add(jobLabel);
		this.add(atkLabel);
		this.add(defLabel);
		this.add(strLabel);
		this.add(dexLabel);
		this.add(lukLabel);
		this.add(intLabel);
		
//		this.add(l1);
//		this.add(l2);
//		this.add(l3);
//		this.add(l4);
//		this.add(l5);
	}
}

public class StatusPanel extends CustomPanel
{
	JButton expandBtn;
	StatusInfoPanel statusInfoPanel;
	JScrollPane statusScrollPane;
	
	boolean isExpanded = false;

	protected void init()
	{
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(255, 255, 255));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}
	
	protected void addComponent()
	{
		this.statusInfoPanel = new StatusInfoPanel();
		this.expandBtn = new JButton("▼");
		
		JScrollBar statusScrollBar = new JScrollBar(JScrollBar.VERTICAL) {
			@Override
			public boolean isVisible() {
				return true;
			}
		};
		
		this.statusInfoPanel.setPreferredSize(new Dimension(this.getWidth() - 10, 135 + 25 * (this.statusInfoPanel.getComponentCount() - 6)));
		this.statusScrollPane = new JScrollPane(this.statusInfoPanel);
		this.statusScrollPane.setBorder(null);
		this.statusScrollPane.setVerticalScrollBar(statusScrollBar);
		this.statusScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		this.statusScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.statusScrollPane.getVerticalScrollBar().setUnitIncrement(12);
		this.expandBtn.setFont(new Font("", Font.BOLD, 9));
		this.expandBtn.setBackground(new Color(68, 114, 196));
		this.expandBtn.setForeground(new Color(255, 255, 255));
		this.expandBtn.setBorder(null);
		this.expandBtn.setFocusable(false);
		this.expandBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		this.expandBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int padding;
				int height;
				
				if( !isExpanded )
				{
					isExpanded = true;
					expandBtn.setText("▲");
					padding = 6;
					height = 300;
				}
				else
				{
					isExpanded = false;
					expandBtn.setText("▼");
					padding = 5;
					height = 150;
				}		
				
				setSize(getWidth(), height);
				setBorder(BorderFactory.createEmptyBorder(5, 5, padding, 5));
				revalidate();
				repaint();
			}
		});
		
		this.add(statusScrollPane);
		this.add(expandBtn, BorderLayout.SOUTH);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if( !isExpanded )
			g.drawImage(ImageResource.statusCollapsedBorder.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		else
			g.drawImage(ImageResource.statusExpandedBorder.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
