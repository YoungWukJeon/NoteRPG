public class ItemDetailViewThread extends Thread
{
	EventCallBackListener listener;
	
	public ItemDetailViewThread() {}
	
	public ItemDetailViewThread(EventCallBackListener listener)
	{
		this.setEventCallBackListener(listener);
	}
	
	public void setEventCallBackListener(EventCallBackListener listener)
	{
		this.listener = listener;
	}
	
	@Override
	public void run()
	{
		try
		{
			Thread.sleep(300);
			// Show Panel Visible
			this.listener.nextWork(true, null);
		}
		catch(InterruptedException ie)
		{
			this.listener.nextWork(false, null);
		}
	}
}
