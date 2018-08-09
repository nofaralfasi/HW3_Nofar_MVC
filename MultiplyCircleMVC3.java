import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MultiplyCircleMVC3 extends Application implements CircleEvents {
	private Button btControllerView = new Button("Show Controller and View");
	private CircleController3 circleController;
	private CircleView3 circleView;
	private CircleModel3 circleModel;
	private int circleCounter = 0;

	@Override
	public void start(Stage mvcStage) throws Exception {	
		BorderPane pane = new BorderPane();
		pane.setCenter(btControllerView);
		btControllerView.setOnAction(e->createNewControllerAndViewWindow());
		mvcStage.setTitle("MultiplyCircleMVC3");
		mvcStage.setOnCloseRequest(e->Platform.exit());
			
		Scene scene = new Scene(pane, S_WIDTH, S_HEIGHT/4);		
		mvcStage.setScene(scene);
		mvcStage.setX(0);
		mvcStage.setY(0);
		mvcStage.setResizable(false);
		mvcStage.setAlwaysOnTop(true);
		mvcStage.show();
	}

	public void createNewControllerAndViewWindow() {
		circleModel = new CircleModel3(circleCounter);
		circleController = new CircleController3(circleCounter);
		circleView = new CircleView3(circleCounter);
		circleCounter++;
		
		Scene controllScene = new Scene(circleController,S_WIDTH, S_HEIGHT/2);
		circleController.setModel(circleModel);
		Stage controllStage=new Stage();		
		controllStage.setTitle("Controller number " + circleCounter);
		controllStage.setScene(controllScene);
		controllStage.setMinWidth(S_WIDTH);
		controllStage.setX(X);
		controllStage.setY(Y);	
		controllStage.setResizable(false);
		controllStage.setAlwaysOnTop(true);
		controllStage.setOnCloseRequest(e->e.consume());
		controllStage.show();
			
		Scene viewScene = new Scene(circleView, S_WIDTH*2, S_HEIGHT);
		circleView.setModel(circleModel);
		Stage viewStage=new Stage();
		viewStage.setTitle("View number " + circleCounter);
		viewStage.setScene(viewScene);	
		viewStage.setMinWidth(S_WIDTH);
		viewStage.setX(X*2);
		viewStage.setY(Y*2);
		viewStage.setOnCloseRequest(e->e.consume());	
		viewStage.setAlwaysOnTop(true);
		viewStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}