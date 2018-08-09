import javafx.collections.FXCollections;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class CircleController3 extends GridPane implements CircleEvents,MyListener {
	private CircleModel3 model;
	private int circleCounter;
	private TextField jtfRadius = new TextField();
	private ComboBox<Boolean> jcboFilled = new ComboBox<Boolean>(FXCollections.observableArrayList(true, false));
	private ComboBox<Boolean> jcboCalculateArea = new ComboBox<Boolean>(FXCollections.observableArrayList(true, false));
	private final ColorPicker colorPicker = new ColorPicker(Color.BLACK);
	final String inputError = "radis must be > 0.0";
	final String chooseAcolor = "Choose a Color";

	public CircleController3(int circleCounter) {
		this.circleCounter = circleCounter;
		setVgap(SPACE);
		setHgap(SPACE);

		add(new Label(eventType.RADIUS.toString()), 1, 1);
		add(new Label(eventType.FILLED.toString()), 1, 2);
		add(new Label(eventType.AREA.toString()), 1, 3);
		add(new Label(eventType.COLOR.toString()), 1, 4);
		
		jtfRadius.setMaxWidth(B_WIDTH);
		jcboFilled.setMinWidth(B_WIDTH);
		jcboCalculateArea.setMinWidth(B_WIDTH);
		jcboFilled.setValue(false);
		jcboCalculateArea.setValue(false);
		
		add(jtfRadius, 2, 1);
		add(jcboFilled, 2, 2);
		add(jcboCalculateArea, 2, 3);
		add(colorPicker, 2, 4);
		
		jtfRadius.setOnAction(e->actionPerformed(new MyActionEvent(jtfRadius, eventType.RADIUS.ordinal(), eventType.RADIUS.name())));
		jcboFilled.setOnAction(e->actionPerformed(new MyActionEvent(jcboFilled, eventType.FILLED.ordinal(), eventType.FILLED.name())));
		jcboCalculateArea.setOnAction(e->actionPerformed(new MyActionEvent(jcboCalculateArea, eventType.AREA.ordinal(), eventType.AREA.name())));
		colorPicker.setOnAction(e->actionPerformed(new MyActionEvent(colorPicker, eventType.COLOR.ordinal(), eventType.COLOR.name())));
	}
	
	public int getcircleCounter() {
		return circleCounter;
	}
	
	public int setcircleCounter(int circleCounter) {
		return this.circleCounter = circleCounter;
	}
	
	@Override
	public void actionPerformed(MyActionEvent e) {
		if (model == null)
			return;
		if (e.getSource() == jtfRadius) {
			try {
				double radius = new Double(jtfRadius.getText()).doubleValue();
				if (radius <= 0) {
					jtfRadius.setText(inputError);
					return;
				}
				model.setRadius(radius);
			} catch (Exception ex) {
				jtfRadius.setText(inputError);
				return;
			}
		} else if (e.getSource() == jcboFilled)
			model.setFilled(jcboFilled.getValue());
		else if (e.getSource() == colorPicker) {
			Color selectedColor = colorPicker.getValue();
			if (selectedColor != null) 
				model.setColor(selectedColor);
		} else if (e.getSource() == jcboCalculateArea)
			model.setCalculateArea(jcboCalculateArea.getValue());		
	}

	public void setModel(CircleModel3 newModel) {
		model = newModel;
	}

	public CircleModel3 getModel() {
		return model;
	}
}
