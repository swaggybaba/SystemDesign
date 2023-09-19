package SingletonDesignPattern;

public class Singleton {
	//volatile keyword , makes the threads which accessing the variable visible 
	private static volatile Singleton instance;
	
	private Singleton() {
		
	}
	
	public static Singleton getInstance() {
		if(instance==null) {
			//this block ensure that no two threads are initializing the instance at the same time
			synchronized(Singleton.class){
				if(instance==null) {
					instance=new Singleton();
				}
			}
		}
		return instance;
	}
}
