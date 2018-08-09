
public class MyActionEvent implements CircleEvents {
	Object source;
	int id;
	String command;

	public MyActionEvent(Object source, int id, String command) {
		this.id = id;
		this.source = source;
		this.command = command;
	}

	public String getActionCommand() {
		return this.command;
	}

	public Object getSource() {
		return this.source;
	}
}

interface MyListener {
	void actionPerformed(MyActionEvent e);
}
