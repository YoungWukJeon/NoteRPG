import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class StatusPanel extends CustomPanel
{
	public StatusPanel()
	{
		super();
	}
	
	protected void init()
	{
		this.setLayout(new GridLayout(5, 2));
		this.setBackground(Color.red);
	}
	
	protected void addComponent()
	{
		
	}
}
