package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController {
	
	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");;
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	
	//@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}
		//synchronized garante q processamento nao será interrompido durante multi trhead
	private synchronized void loadView(String absoluteName)  {
		try {		
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		    VBox newVbox = loader.load();//carregou view
		    //abaixo mostrar a view dentro da janela principal(tem q pegar referencia da cena)
		    Scene mainScene = Main.getMainScene();
		    //mainScene.getRoot() pega o primeiro elemento da view q é o ScrollPane / feito casting do mainScene.getRoot para ScrollPane
		    VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();//getContent ja é referencia para o que está dentro do ScrollPane
		    			//(VBox) - casting pegando referencia da janela principal
		    //preservar <MenuBar> do MainView e excluir o q tiver no filho(children) do Vbox e incluir o menuBar e em seguida os filhos do VBox da tela About
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();//limpa todos os filhos do mainVbox
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVbox.getChildren());
		    
		    
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private synchronized void loadView2(String absoluteName)  {
		try {		
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		    VBox newVbox = loader.load();//carregou view
		    //abaixo mostrar a view dentro da janela principal(tem q pegar referencia da cena)
		    Scene mainScene = Main.getMainScene();
		    //mainScene.getRoot() pega o primeiro elemento da view q é o ScrollPane / feito casting do mainScene.getRoot para ScrollPane
		    VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();//getContent ja é referencia para o que está dentro do ScrollPane
		    			//(VBox) - casting pegando referencia da janela principal
		    //preservar <MenuBar> do MainView e excluir o q tiver no filho(children) do Vbox e incluir o menuBar e em seguida os filhos do VBox da tela About
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();//limpa todos os filhos do mainVbox
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVbox.getChildren());
		    
			DepartmentListController controller = loader.getController();//loader é o obj usado para carregar a view, a partir desse obj posso carregar view e acessar controller
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
		
	}

}
