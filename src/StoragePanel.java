import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.event.*;

public class StoragePanel extends CustomPanel
{
	int layoutX;
	int layoutY;
	ItemLabel [][]itemLabel;
	LinkedList<Item> []storageList;
	
	public StoragePanel()
	{
		super();
	}
	
	protected void init()
	{
		this.layoutX = 12;
		this.layoutY = 5;
		
		this.setLayout(new GridLayout(this.layoutY, this.layoutX));
		this.setBackground(new Color(210, 210, 210));
		
		// data //
		this.storageList = new LinkedList[this.layoutX * this.layoutY];
		
		for( int i = 0; i < this.storageList.length; ++i )
			this.storageList[i] = new LinkedList<> ();
		
		Item item1 = new Item();
		Item item2 = new Item();
		Item item3 = new Item();
		Item item4 = new Item();
		Item item5 = new Item();
		Item item6 = new Item();
		Item item7 = new Item();
		
		item1.setImageIcon(ImageResource.sword);
		item1.setGenerateId(1);
		item1.setItemId(1);
		item1.setName("Excalibur");
		item2.setImageIcon(ImageResource.bow);
		item2.setGenerateId(2);
		item2.setItemId(2);
		item2.setName("Bow");
		item3.setImageIcon(ImageResource.crossedSwords);
		item3.setGenerateId(3);
		item3.setItemId(3);
		item3.setName("Crossed swords");
		item4.setImageIcon(ImageResource.darkRepulser);
		item4.setGenerateId(4);
		item4.setItemId(4);
		item4.setName("Dark repulser");
		item4.setEnhancement(6);
		item5.setImageIcon(ImageResource.elucidator);
		item5.setGenerateId(5);
		item5.setItemId(5);
		item5.setName("Elucidator");
		item5.setEnhancement(12);
		item6.setImageIcon(ImageResource.guiltyThornNovel);
		item6.setGenerateId(6);
		item6.setItemId(6);
		item6.setName("Guilty Thorn Novel");
		item7.setImageIcon(ImageResource.twoHandsSwords);
		item7.setGenerateId(7);
		item7.setItemId(7);
		item7.setName("Two hands swords");
	
		this.storageList[1].add(item1);
		this.storageList[2].add(item2);
		this.storageList[25].add(item3);
		this.storageList[26].add(item4);
		this.storageList[27].add(item5);
		this.storageList[15].add(item6);
		this.storageList[16].add(item7);
		
		// End //
	}
	
	protected void addComponent()
	{
		this.itemLabel = new ItemLabel[this.layoutY][this.layoutX];
		
		for( int i = 0; i < this.layoutY; ++i )
		{
			for( int j = 0; j < this.layoutX; ++j )
			{				
				if( this.storageList[i * this.layoutX + j].size() > 0 )					
					this.add(this.itemLabel[i][j] = new ItemLabel(this.storageList[i * this.layoutX + j]));
				else
					this.add(this.itemLabel[i][j] = new ItemLabel());
			}
		}
	}
}
