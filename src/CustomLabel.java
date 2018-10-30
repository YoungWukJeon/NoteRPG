import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public abstract class CustomLabel extends JLabel
{
	protected CustomLabel()
	{
		this.init();
		this.addComponent();
	}
	
	protected abstract void init();
	protected abstract void addComponent();
}
