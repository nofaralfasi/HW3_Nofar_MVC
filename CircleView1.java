
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleView1 extends StackPane implements MyListener {
	private CircleModel1 model;

	@Override
	public void actionPerformed(MyActionEvent e) {
		getChildren().clear();
		drawCircle();
	}

	/** Set a model */
	public void setModel(CircleModel1 newModel) {
		this.model = newModel;
		if (model != null)
			model.addListener(this);
		drawCircle();
	}

	public CircleModel1 getModel() {
		return model;
	}

	public void drawCircle() {
		if (model == null)
			return;
		double radius = model.getRadius();
		Circle c = new Circle(radius);
		c.setFill(Color.TRANSPARENT);
		if (model.isFilled())
			c.setFill(model.getColor());
		else
			c.setStroke(model.getColor());
		getChildren().add(c);
	}

}