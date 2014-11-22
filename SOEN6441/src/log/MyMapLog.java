package log;

import java.io.File;

public class MyMapLog {

	private static MyMapLog mml = new MyMapLog();
	private File mapLog;
	
	private MyMapLog()
	{ 
		
	};
	
	public static MyMapLog getMapLogInstance()
	{
		return mml;
	}

	
}
