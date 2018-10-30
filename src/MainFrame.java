import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MainFrame extends JFrame
{
	CustomPanel cp1, cp2, cp3, cp4, cp5;
	
	public MainFrame()
	{
		init();
		addComponent();
	}
	
	private void init()
	{
		this.setTitle("Note RPG");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.pack();
		
		this.setSize(720, 740);
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		this.setVisible(true);
		
		
	}
	
	private void addComponent()
	{
		this.cp1 = new StoragePanel();
		this.cp2 = new InventoryPanel();
		this.cp3 = new CostumePanel();
		this.cp4 = new StatusPanel();
		this.cp5 = new EquipPanel();
		
		this.cp1.setBounds(0, 0, 720, 300);
		this.cp2.setBounds(0, 300, 240, 420);
		this.cp3.setBounds(240, 300, 300, 420);
		this.cp4.setBounds(540, 300, 180, 150);
		this.cp5.setBounds(540, 450, 180, 270);
		
		this.add(cp1);
		this.add(cp2);
		this.add(cp3);
		this.add(cp4);
		this.add(cp5);
		
		
	}
}
