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
	enum StatusType {}
	
	protected void init()
	{
		
	}
}

class StatusInfoPanel extends CustomPanel
{
	PointLabel hpLabel, mpLabel, expLabel;
	LevelLabel levelLabel;
	StatusLabel jobLabel, atkLabel;
	
	protected void init()
	{
		this.setLayout(null);
		this.setBackground(new Color(0, 0, 0, 240));
	}

	protected void addComponent()
	{
		// TODO Auto-generated method stub
		this.hpLabel = new PointLabel(PointLabel.PointType.HP, 725, 512);
		this.mpLabel = new PointLabel(PointLabel.PointType.MP, 125, 125);
		this.expLabel = new PointLabel(PointLabel.PointType.EXP, 127, 35);
		this.levelLabel = new LevelLabel(37);
		
		this.hpLabel.setBounds(10, 10, 150, 15);
		this.mpLabel.setBounds(10, 30, 150, 15);
		this.expLabel.setBounds(10, 50, 150, 15);
		this.levelLabel.setBounds(10, 70, 50, 50);
		
		this.add(hpLabel);
		this.add(mpLabel);
		this.add(expLabel);
		this.add(levelLabel);
	}
	
}

public class StatusPanel extends CustomPanel
{
	JScrollPane statusScrollPane;
	JButton expandBtn;
	StatusInfoPanel statusInfoPanel;
	
	boolean isExpanded = false;
	
	public StatusPanel()
	{
		super();
	}
	
	protected void init()
	{
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(255, 255, 255));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}
	
	protected void addComponent()
	{
		this.expandBtn = new JButton("▼");
		
		this.statusInfoPanel = new StatusInfoPanel();
		this.statusScrollPane = new JScrollPane(this.statusInfoPanel);
		
		JScrollBar statusScrollBar = new JScrollBar(JScrollBar.VERTICAL) {
			@Override
			public boolean isVisible() {
				return true;
			}
		};
		
		this.statusScrollPane.setBorder(null);
		this.statusScrollPane.setVerticalScrollBar(statusScrollBar);
		this.statusScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		this.statusScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
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
