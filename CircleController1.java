
import javafx.collections.FXCollections;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class CircleController1 extends GridPane implements CircleEvents, MyListener {
	private CircleModel1 model;
	private TextField jtfRadius = new TextField();
	private ComboBox<Boolean> jcboFilled = new ComboBox<Boolean>(FXCollections.observableArrayList(true, false));
	private final ColorPicker colorPicker = new ColorPicker(Color.BLACK);

	/** Creates new form CircleController */
	public CircleController1() {
		setVgap(SPACE);
		setHgap(SPACE);
		add(new Label("Radius"), 1, 1);
		add(jtfRadius, 2, 1);
		add(new Label("Filled"), 1, 2);
		add(jcboFilled, 2, 2);
		add(new Label("Color"), 1, 3);
		jcboFilled.setValue(false);
		add(colorPicker, 2, 3);

		// jtfRadius.setOnAction(this);
		jtfRadius.setOnAction(e -> actionPerformed(
				new MyActionEvent(jtfRadius, eventType.RADIUS.ordinal(), eventType.RADIUS.name())));
		jcboFilled.setOnAction(e -> actionPerformed(
				new MyActionEvent(jcboFilled, eventType.FILLED.ordinal(), eventType.FILLED.name())));
		colorPicker.setOnAction(e -> actionPerformed(
				new MyActionEvent(colorPicker, eventType.COLOR.ordinal(), eventType.COLOR.name())));
	}

	@Override
	public void actionPerformed(MyActionEvent e) {
		if (model == null)
			return;
		if (e.getSource() == jtfRadius)
			model.setRadius(new Double(jtfRadius.getText()).doubleValue());
		else if (e.getSource() == jcboFilled)
			model.setFilled((jcboFilled.getValue()));
		else if (e.getSource() == colorPicker) {
			Color selectedColor = colorPicker.getValue();
			if (selectedColor != null)
				model.setColor(selectedColor);
		}
	}

	public void setModel(CircleModel1 newModel) {
		model = newModel;
	}

	public CircleModel1 getModel() {
		return model;
	}
}
