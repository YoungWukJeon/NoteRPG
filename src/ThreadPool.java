
public enum ThreadPool
{
	INSTANCE;
	private final static int MAX_THREAD_COUNT = 5;
	private static Thread []th;
	
	public enum THREAD_TYPE { ITEM_DETAIL_VIEW };
	
	public static ThreadPool getInstance()
	{
		if( th == null )
			th = new Thread[MAX_THREAD_COUNT];
		
//		for( Thread t : th )
//			t = new Thread();
		
		th[THREAD_TYPE.ITEM_DETAIL_VIEW.ordinal()] = new ItemDetailViewThread();
		
		return INSTANCE;
	}
	
	private Thread getThread(THREAD_TYPE type)
	{
		return this.th[type.ordinal()];
	}
	
	public boolean isAlive(THREAD_TYPE type)
	{
		return this.th[type.ordinal()].isAlive();
	}
	
	private void newThread(THREAD_TYPE type, EventCallBackListener listener)
	{
		if( type == THREAD_TYPE.ITEM_DETAIL_VIEW )
			this.th[type.ordinal()] = new ItemDetailViewThread(listener);
	}
	
	public void startThread(THREAD_TYPE type, EventCallBackListener listener)
	{
		if( !isAlive(type) )
			newThread(type, listener);
		
		th[type.ordinal()].start();
	}
	
	public void stopThread(THREAD_TYPE type, EventCallBackListener listener)
	{
		if( isAlive(type) )
			th[type.ordinal()].interrupt();
		
		newThread(type, listener);
	}
}
