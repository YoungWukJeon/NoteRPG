public class Main
{
	static CharacterInfo characterInfo;
	
	public static void main(String[] args)
	{
		System.out.println("Start!!");
		
//		ThreadPool.getInstance();
		ItemDetailViewDialog.getInstance();

		characterInfo = new CharacterInfo();
		new ImageResource();
		new MainFrame();
	}
}
