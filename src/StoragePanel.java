import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class StoragePanel extends CustomPanel
{
	CustomLabel [][]il;
	
	public StoragePanel()
	{
		super();
	}
	
	protected void init()
	{
		this.setLayout(new GridLayout(5, 12));
		this.setBackground(Color.BLUE);
	}
	
	protected void addComponent()
	{
		this.il = new ItemLabel[5][12];
		
		for( int i = 0; i < il.length; ++i )
			for( int j = 0; j < il[i].length; ++j )
				this.add(this.il[i][j] = new ItemLabel());
		
		((ItemLabel) this.il[0][1]).drawItem(ImageResource.sword, "Excalibur");
		((ItemLabel) this.il[0][2]).drawItem(ImageResource.bow, "Bow");
		((ItemLabel) this.il[2][1]).drawItem(ImageResource.crossedSwords, "Crossed Swords");
		((ItemLabel) this.il[2][2]).drawItem(ImageResource.darkRepulser, "Dark Repulser");
		((ItemLabel) this.il[2][3]).drawItem(ImageResource.elucidator, "Elucidator");
		((ItemLabel) this.il[1][4]).drawItem(ImageResource.guiltyThornNovel, "Guilty Thorn Novel");
		((ItemLabel) this.il[1][5]).drawItem(ImageResource.twoHandsSwords, "Two Hands Swords");
	
	}
}
