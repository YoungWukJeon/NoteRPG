import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.event.*;

public class ItemLabel extends CustomLabel
{
	private LinkedList<Item> itemList;
	
	public ItemLabel(LinkedList<Item> itemList)
	{
		this.itemList = itemList;
		this.init();
	}
	
	public ItemLabel()
	{
		super();
	}

	protected void init()
	{
		this.setLayout(new BorderLayout());
//		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		if( this.getItemList() != null )
			this.addMouseListener(new ItemMouseAdapter(this.getItem(0)));
		else
			this.addMouseListener(new NoItemMouseAdapter());
	}
	
	public LinkedList<Item> getItemList()
	{
		return this.itemList;
	}
	
	public Item getItem(int pos)
	{
		return this.getItemList().get(pos);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(ImageResource.itemBorder.getImage(), 0, 0, this.getWidth() - 1, this.getHeight() - 1, this);
		
		if( this.getItemList() != null )
			g.drawImage(this.getItem(0).getImageIcon().getImage(), (int) (this.getWidth() * 0.1), (int) (this.getHeight() * 0.1), this.getWidth() - (int) (this.getWidth() * 0.2), this.getHeight() - (int) (this.getHeight() * 0.2), this);
		
		if( this.getItemList() != null && !this.getItem(0).isRedundant() && this.getItem(0).getEnhancement() > 0 )
		{
			g.setColor(Color.RED);
			
			if( this.getItem(0).getEnhancement() > 9 )
				g.drawString("+" + Integer.toString(this.getItem(0).getEnhancement()), this.getWidth() - (int) (this.getWidth() * 0.45), this.getHeight() - (int) (this.getHeight() * 0.75));
			else
				g.drawString("+" + Integer.toString(this.getItem(0).getEnhancement()), this.getWidth() - (int) (this.getWidth() * 0.35), this.getHeight() - (int) (this.getHeight() * 0.75));
		}
		
		if( this.getItemList() != null && this.getItem(0).isRedundant() )
		{
			if( this.getItemList().size() > 99 )
				g.drawString("99+", this.getWidth() - (int) (this.getWidth() * 0.67), this.getHeight() - (int) (this.getHeight() * 0.08));
			else if( this.getItemList().size() > 9 )
				g.drawString(Integer.toString(this.getItemList().size()), this.getWidth() - (int) (this.getWidth() * 0.25), this.getHeight() - (int) (this.getHeight() * 0.08));
			else
				g.drawString(Integer.toString(this.getItemList().size()), this.getWidth() - (int) (this.getWidth() * 0.17), this.getHeight() - (int) (this.getHeight() * 0.08));
		}
	}
	
	class ItemMouseAdapter extends MouseAdapter
	{
		private Item item;
		
		ItemMouseAdapter(Item item)
		{
			this.item = item;
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0)
		{
			System.out.println(this.item.toString());
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			ItemDetailViewDialog.INSTANCE.startThread(this.item);
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// sleep이 종료된 후에 마우스를 이동했을 경우 다른 곳에서 Dialog가 나타나는 것을 방지
			ItemDetailViewDialog.INSTANCE.stopThread();
		}
	}
	
	class NoItemMouseAdapter extends MouseAdapter
	{
		@Override
		public void mouseEntered(MouseEvent e)
		{
			if( ItemDetailViewDialog.INSTANCE.isShowing() )
				ItemDetailViewDialog.INSTANCE.dismiss();
		}
	}
}
