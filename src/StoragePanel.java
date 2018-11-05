import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class StoragePanel extends CustomPanel
{
	int layoutX;
	int layoutY;
	ItemLabel [][]itemLabel;
	
	public StoragePanel()
	{
		super();
	}
	
	protected void init()
	{
		this.layoutX = 12;
		this.layoutX = 5;
		
		this.setLayout(new GridLayout(this.layoutY, this.layoutX));
		this.setBackground(Color.BLUE);
	}
	
	protected void addComponent()
	{
		this.itemLabel = new ItemLabel[this.layoutY][this.layoutX];
		
		for( int i = 0; i < this.layoutY; ++i )
			for( int j = 0; j < this.layoutX; ++j )
				this.add(this.itemLabel[i][j] = new ItemLabel());

//		((ItemLabel) this.il[0][1]).drawItem(ImageResource.sword, "Excalibur");
//		((ItemLabel) this.il[0][2]).drawItem(ImageResource.bow, "Bow");
//		((ItemLabel) this.il[2][1]).drawItem(ImageResource.crossedSwords, "Crossed Swords");
//		((ItemLabel) this.il[2][2]).drawItem(ImageResource.darkRepulser, "Dark Repulser");
//		((ItemLabel) this.il[2][3]).drawItem(ImageResource.elucidator, "Elucidator");
//		((ItemLabel) this.il[1][4]).drawItem(ImageResource.guiltyThornNovel, "Guilty Thorn Novel");
//		((ItemLabel) this.il[1][5]).drawItem(ImageResource.twoHandsSwords, "Two Hands Swords");
	
	}
}
