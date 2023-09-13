package ChainOfResponsibility;

public class Main {

	public static void main(String[] args) {
		LogProcessor logProcessor=new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));
		logProcessor.log(1, "This is level 1 Info");
		logProcessor.log(2, "This is level 2 Debug");
		logProcessor.log(3, "This is level 3 Error");
	}

}
