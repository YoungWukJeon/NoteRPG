import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public enum ItemDetailViewDialog
{
	INSTANCE;
	private static int width = 400;
	private static int height = 200;
	private int delay = 100;
	private Thread thread;
	
	private static JDialog itemDetailViewDialog;
	private static ItemDetailViewPanel itemDetailViewPanel;
	
	public static ItemDetailViewDialog getInstance()
	{
		if( itemDetailViewDialog == null )
			itemDetailViewDialog = new JDialog();
		
		init();
		addComponent();
		
		return INSTANCE;
	}
	
	public static void init()
	{
		itemDetailViewDialog.setUndecorated(true);
		itemDetailViewDialog.setBackground(new Color(0, 0, 0, 225));
		itemDetailViewDialog.setSize(width, height);
		itemDetailViewDialog.setResizable(false);
		itemDetailViewDialog.setAutoRequestFocus(false);
	}
	
	public static void addComponent()
	{
		itemDetailViewPanel = new ItemDetailViewPanel();
		
		itemDetailViewDialog.add(itemDetailViewPanel);
	}
	
	public void setItem(Item item)
	{
		itemDetailViewPanel.updateItem(item);
		
		Point p = MouseInfo.getPointerInfo().getLocation();
		p.x -= 20;
		p.y += 20;
		
		itemDetailViewDialog.setLocation(p);
		this.show();
//		this.itemDetailViewPanel.repaint();
	}
	
	public void startThread(Item item)
	{
		if( this.isShowing() )
			this.setItem(item);
		else if( this.thread == null || !this.thread.isAlive() )
		{
			this.thread = new Thread() {
				@Override
				public void run()
				{
					try
					{
						Thread.sleep(delay);
						setItem(item);
					}
					catch(InterruptedException ie)
					{
					}
				}
			};
			this.thread.start();
		}
	}
	
	public void stopThread()
	{
		this.thread.interrupt();
	}
	
	public boolean isShowing()
	{
		return itemDetailViewDialog.isShowing();
	}
	
	public void show()
	{
		itemDetailViewDialog.setVisible(true);
	}
	
	public void dismiss()
	{
		itemDetailViewDialog.setVisible(false);
	}
}
