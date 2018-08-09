// Nofar Alfasi
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SingleCircleMVC1 extends Application implements CircleEvents {
	private Button btController = new Button("Show Controller");
	private Button btView = new Button("Show View");
	private CircleModel1 model = new CircleModel1();

	@Override
	public void start(Stage mvcStage) throws Exception {
		btController.setMinSize(B_WIDTH, B_HEIGHT);
		btView.setMinSize(B_WIDTH, B_HEIGHT);
		btController.setOnAction(e -> createNewControllerWindow());
		btView.setOnAction(e -> createNewViewWindow());			
		GridPane gp = new GridPane();
		gp.add(btController, 0, 0);
		gp.add(btView, 1, 0);
		gp.setHgap(SPACE);
		gp.setPadding(new Insets(SPACE*2, SPACE, SPACE, SPACE*2));
		Scene mvcScene = new Scene(gp, S_WIDTH, S_HEIGHT/4);
		mvcStage.setTitle("SingleCircleMvc1");
		mvcStage.setScene(mvcScene);		
		mvcStage.setX(0);
		mvcStage.setY(0);	
		mvcStage.setResizable(false);
		mvcStage.setAlwaysOnTop(true);
		mvcStage.setOnCloseRequest(e -> Platform.exit());
		mvcStage.show();
	}

	private void createNewViewWindow() {
		CircleView1 view = new CircleView1();
		Stage viewStage = new Stage();
		Scene viewScene = new Scene(view, S_WIDTH*2, S_HEIGHT);
		view.setModel(model);
		viewStage.setScene(viewScene);
		viewStage.setTitle("View");
		viewStage.setMinWidth(P_WIDTH);
		viewStage.setX(X*2);
		viewStage.setY(Y*2);	
		viewStage.setAlwaysOnTop(true);
		viewStage.setOnCloseRequest(e->e.consume());	
		viewStage.show();
	}

	private void createNewControllerWindow() {
		CircleController1 controller = new CircleController1();
		Stage controllStage = new Stage();
		Scene controllScene = new Scene(controller, S_WIDTH, S_HEIGHT/2);
		controller.setModel(model);		
		controllStage.setTitle("Controller");
		controllStage.setX(X);
		controllStage.setY(Y);
		controllStage.setScene(controllScene);		
		controllStage.setResizable(false);
		controllStage.setAlwaysOnTop(true);
		controllStage.setOnCloseRequest(e->e.consume());
		controllStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
