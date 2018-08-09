
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;

public class CircleModel3 implements CircleEvents {
	private SimpleIntegerProperty circleCounter;
	private SimpleDoubleProperty radius = new SimpleDoubleProperty(RADIUS);
	private SimpleBooleanProperty calculateArea= new SimpleBooleanProperty(false);
	private SimpleBooleanProperty filled = new SimpleBooleanProperty(false);
	private SimpleObjectProperty<Color> color = new SimpleObjectProperty<>(Color.BLACK);
	private ObservableMap<eventType, ObservableList<MyListener>> circleHashMap = FXCollections.observableHashMap();

	public CircleModel3(int circleCounter) {
		this.circleCounter = new SimpleIntegerProperty(circleCounter);
		for (eventType et : eventType.values())
			circleHashMap.put(et, FXCollections.observableArrayList());
	}

	public int getcircleCounter() {
		return circleCounter.get();
	}

	public void setcircleCounter(int circleCounter) {
		this.circleCounter = new SimpleIntegerProperty(circleCounter);
	}

	public double getRadius() {
		return radius.get();
	}

	public void setRadius(double radius) {
		this.radius = new SimpleDoubleProperty(radius);
		processEvent(eventType.RADIUS,new MyActionEvent(this, eventType.RADIUS.ordinal(), eventType.RADIUS.toString()));
	}

	public boolean isFilled() {
		return filled.get();
	}

	public void setFilled(Boolean filled) {
		this.filled = new SimpleBooleanProperty(filled);
		processEvent(eventType.FILLED,new MyActionEvent(this, eventType.FILLED.ordinal(), eventType.FILLED.toString()));
	}

	public Color getColor() {
		return color.get();
	}

	public void setColor(Color color) {
		this.color = new SimpleObjectProperty<>(color);
		processEvent(eventType.COLOR,new MyActionEvent(this, eventType.COLOR.ordinal(),eventType.COLOR.toString()));
	}

	public boolean calculateArea() {
		return calculateArea.get();
	}

	public void setCalculateArea(Boolean calculateArea) {
		this.calculateArea = new SimpleBooleanProperty(calculateArea);
		processEvent(eventType.AREA, new MyActionEvent(this, eventType.AREA.ordinal(), eventType.AREA.toString()));
	}

	public synchronized void addListener(MyListener l, eventType et) {
		ObservableList<MyListener> al;
		al = circleHashMap.get(et);
		if (al == null)
			al = FXCollections.observableArrayList();
		al.add(l);
		circleHashMap.put(et, al);
	}

	public synchronized void removeActionListener(MyListener l, eventType et) {
		ObservableList<MyListener> al;
		al = circleHashMap.get(et);
		if (al != null && al.contains(l))
			al.remove(l);
		circleHashMap.put(et, al);
	}

	private void processEvent(eventType et, MyActionEvent e) {
		ObservableList<MyListener> al;
		synchronized (this) {
			al = circleHashMap.get(et);
			if (al == null)
				return;
		}
		System.out.println("model number: " + (getcircleCounter() + 1) + " actionCommand: " + e.getActionCommand()
				+ " array size is: " + al.size());
		for (int i = 0; i < al.size(); i++) {
			MyListener listener = (MyListener) al.get(i);
			listener.actionPerformed(e);
		}
	}
}
