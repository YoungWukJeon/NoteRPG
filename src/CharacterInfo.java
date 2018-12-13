public class CharacterInfo
{
	private int maxHP = 725;
	private int currentHP = 512;
	private int maxMP = 125;
	private int currentMP = 125;
	private int maxEXP = 127;
	private int currentEXP = 35;
	private String job = "검사";
	private int level = 37;
	private int atk = 530;
	private int def = 300;
	private int statusStr = 50;
	private int statusDex = 127;
	private int statusLuk = 30;
	private int statusInt = 20;
	
	public int getMaxHP()
	{
		return this.maxHP;
	}
	
	public int getCurrentHP()
	{
		return this.currentHP;
	}
	
	public int getMaxMP()
	{
		return this.maxMP;
	}
	
	public int getCurrentMP()
	{
		return this.currentMP;
	}
	
	public int getMaxEXP()
	{
		return this.maxEXP;
	}
	
	public int getCurrentEXP()
	{
		return this.currentEXP;
	}
	
	public String getJob()
	{
		return this.job;
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	public int getAtk()
	{
		return this.atk;
	}
	
	public int getDef()
	{
		return this.def;
	}
	
	public int getStatusStr()
	{
		return this.statusStr;	
	}
	
	public int getStatusDex()
	{
		return this.statusDex;	
	}
	
	public int getStatusLuk()
	{
		return this.statusLuk;	
	}
	
	public int getStatusInt()
	{
		return this.statusInt;	
	}
}
