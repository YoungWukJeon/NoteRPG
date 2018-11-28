public class Main
{
	public static void main(String[] args)
	{
		System.out.println("Start!!");
		
		ThreadPool.getInstance();
		new ImageResource();
		new MainFrame();
	}
}
