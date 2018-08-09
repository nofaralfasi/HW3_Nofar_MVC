
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class CircleView2 extends BorderPane implements CircleEvents {
	class RadiusEvent implements MyListener {
		public void actionPerformed(MyActionEvent e) {
			eventTitle = eventType.RADIUS.toString();
			drawCircle();
		}
	}

	class ColorEvent implements MyListener {
		public void actionPerformed(MyActionEvent e) {
			eventTitle = eventType.COLOR.toString();
			drawCircle();
		}
	}

	class FilledEvent implements MyListener {
		public void actionPerformed(MyActionEvent e) {
			eventTitle = eventType.FILLED.toString();
			drawCircle();
		}
	}

	class AreaEvent implements MyListener {
		public void actionPerformed(MyActionEvent e) {
			eventTitle = eventType.AREA.toString();
			drawCircle();
		}
	}

	private CircleModel2 model;
	private int circleCounter;
	private String eventTitle = "DEFAULT";

	public CircleView2(int circleCounter) {
		this.circleCounter = circleCounter;
	}

	public int getcircleCounter() {
		return circleCounter;
	}

	public void setModel(CircleModel2 newModel) {
		this.model = newModel;
		if (model != null) {
			model.addListener(new RadiusEvent(), eventType.RADIUS);
			model.addListener(new FilledEvent(), eventType.FILLED);
			model.addListener(new ColorEvent(), eventType.COLOR);
			model.addListener(new AreaEvent(), eventType.AREA);
		}
		drawCircle();
	}

	public CircleModel2 getModel() {
		return model;
	}

	public void drawCircle() {
		if (model == null)
			return;
		getChildren().clear();
		int rowSpace = SPACE;
		int row = SPACE;
		int column = SPACE;
		int circleNumber = circleCounter + 1;
		double radius = model.getRadius();
		Text t1 = new Text(column, row, "Event Type: " + eventTitle);
		row = row + rowSpace;
		Text t2 = new Text(column, row, "Circle number " + circleNumber);
		row = row + rowSpace;
		Text t3 = new Text(column, row, "Circle raduis " + radius);
		row = row + rowSpace;
		if (model.calculateArea()) {
			double circleArea = radius * radius * Math.PI;
			Text t4 = new Text(column, row, "Circle Area " + circleArea);
			getChildren().add(t4);
		}
		getChildren().addAll(t1, t2, t3);
		Circle circle = new Circle(radius);
		circle.setFill(Color.TRANSPARENT);
		if (model.isFilled())
			circle.setFill(model.getColor());
		else
			circle.setStroke(model.getColor());
		setCenter(circle);	
	}
}
