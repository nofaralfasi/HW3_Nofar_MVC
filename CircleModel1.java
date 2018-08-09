import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

public class CircleModel1 implements CircleEvents {
	private SimpleDoubleProperty radius = new SimpleDoubleProperty(RADIUS);
	private SimpleBooleanProperty filled = new SimpleBooleanProperty(false);
	private SimpleObjectProperty<Color> color = new SimpleObjectProperty<>(Color.BLACK);
	private ObservableList<MyListener> actionListenerList = FXCollections.observableArrayList();

	public double getRadius() {
		return radius.get();
	}

	public void setRadius(double radius) {
		this.radius = new SimpleDoubleProperty(radius);
		processEvent(new MyActionEvent(this, eventType.RADIUS.ordinal(), "radius"));
	}

	public boolean isFilled() {
		return filled.get();
	}

	public void setFilled(Boolean filled) {
		this.filled = new SimpleBooleanProperty(filled);
		processEvent(new MyActionEvent(this, eventType.FILLED.ordinal(), "filled"));
	}

	public Color getColor() {
		return color.get();
	}

	public void setColor(Color color) {
		this.color = new SimpleObjectProperty<>(color);
		processEvent(new MyActionEvent(this, eventType.COLOR.ordinal(), "color"));
	}

	/** Register an action event listener */
	public synchronized void addListener(MyListener l) {
		if (actionListenerList == null)
			actionListenerList = FXCollections.observableArrayList();
		actionListenerList.add(l);
	}

	/** Remove an action event listener */
	public synchronized void removeListener(MyListener l) {
		if (actionListenerList != null && actionListenerList.contains(l))
			actionListenerList.remove(l);
	}

	/** Fire Event */
	private void processEvent(MyActionEvent e) {
		ObservableList<MyListener> list;
		System.out.println("size of actionListenerList is: " + actionListenerList.size());
		synchronized (this) {
			if (actionListenerList == null)
				return;
			list = FXCollections.observableArrayList(actionListenerList);
		}
		for (int i = 0; i < list.size(); i++) {
			MyListener listener = list.get(i);
			System.out.println("event is: " + e.getActionCommand());
			listener.actionPerformed(e);
		}
	}
}