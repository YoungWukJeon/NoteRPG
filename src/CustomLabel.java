import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public abstract class CustomLabel extends JLabel
{
	protected CustomLabel()
	{
		this.init();
	}
	
	protected abstract void init();
}
