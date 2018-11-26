import javax.swing.ImageIcon;

public class Item
{
	private long generateId;
	private int itemId;
	private String name;
	private String generateDate;
	private String explanation;
	private int enhancement;
	private ImageIcon imageIcon;
	private boolean redundant;
	
	public Item()
	{
		this.setRedundant(false);
	}
	
	public void setGenerateId(long generateId)
	{
		this.generateId = generateId;
	}
	
	public void setItemId(int itemId)
	{
		this.itemId = itemId;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setEnhancement(int enhancement)
	{
		this.enhancement = enhancement;
	}
	
	public void setImageIcon(ImageIcon imageIcon)
	{
		this.imageIcon = imageIcon;
	}
	
	public void setRedundant(boolean redundant)
	{
		this.redundant = redundant;
	}
	
	public long getGenerateId()
	{
		return this.generateId;
	}
	
	public int getItemId()
	{
		return this.itemId;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getGenerateDate()
	{
		return this.generateDate;
	}
	
	public String getExplanation()
	{
		return this.explanation;
	}
	
	public int getEnhancement()
	{
		return this.enhancement;
	}
	
	public ImageIcon getImageIcon()
	{
		return this.imageIcon;
	}
	
	public boolean isRedundant()
	{
		return this.redundant;
	}
	
	@Override
	public String toString()
	{
		return
				"** Item Info **" + "\n" +
				"GenerateId : " + this.generateId + "\n" +
				"ItemId : " + this.itemId + "\n" +
				"Name : " + this.name + "\n" +
				"GenerateDate : " + this.generateDate + "\n" +
				"Explanation : " + this.explanation + "\n" +
				"Image : " + this.imageIcon.getDescription() + "\n" +
				"Redundant : " + this.redundant + "\n" +
				"***********" + "\n";
	}
}
