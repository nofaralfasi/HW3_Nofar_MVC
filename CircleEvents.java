public interface CircleEvents {
	public final int S_WIDTH = 400, S_HEIGHT = 400; // scene 
	public final int SPACE = 20;
	public final int B_HEIGHT = 30, B_WIDTH = 150; // buttons
	public final int P_WIDTH = 350, P_HEIGHT = 350; // panels
	public final int X = 200, Y = 100; // location
	public final double RADIUS = 20.0;
	
	enum eventType {
		RADIUS, AREA, COLOR, FILLED
	}
}
