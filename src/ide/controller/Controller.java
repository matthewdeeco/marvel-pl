package ide.controller;

import ide.view.*;
import interpreter.*;
import logger.*;

public class Controller {
	private View view;
	private SaveLoadHandler marvelSaveLoadHandler, javaSaveLoadHandler;
	private Interpreter interpreter;
	private Logger logger;
	
	public Controller() {
		logger = new Logger(System.out, System.err);
		marvelSaveLoadHandler = new MarvelSaveLoadHandler();
		javaSaveLoadHandler = new JavaSaveLoadHandler();
		interpreter = new MarvelInterpreter(logger);
	}
	
	public void setView(View view) {
		this.view = view;
	}

	public void setDialogHandler(DialogHandler dialogHandler) {
		marvelSaveLoadHandler.setDialogHandler(dialogHandler);
		javaSaveLoadHandler.setDialogHandler(dialogHandler);
	}

	public void newFile() {
		marvelSaveLoadHandler.resetPaths();
		view.reset();
	}
	
	public void openFile() {
		String code = marvelSaveLoadHandler.loadState();
		if (code != null) {
			view.setText(code);
			view.setTitle(marvelSaveLoadHandler.getLastFileName() + " - " + marvelSaveLoadHandler.getLastLoadPath());
		}
	}
	
	public void saveFile() {
		marvelSaveLoadHandler.saveState(view.getText());
		view.setTitle(marvelSaveLoadHandler.getLastFileName() + " - " + marvelSaveLoadHandler.getLastSavePath());
	}

	public void quit() {
		System.exit(0);
	}
	
	public void runCode() {
		try {
			view.switchToConsole();
			interpreter.evaluate(view.getText());
		} catch (Exception ex) {
			logger.exception(ex);
		}
	}

	public void translateCode() {
		try {
			String filename = javaSaveLoadHandler.getLastFileName();
			if (filename == null)
				return;
			javaSaveLoadHandler.resetPaths();
			System.out.println(filename);
			String className = filename.substring(0, filename.lastIndexOf(".java"));
			String javaCode = interpreter.translateToJavaSource(className, view.getText());
			if (javaCode != null)
				javaSaveLoadHandler.saveStateAs(javaCode);
			logger.message("Translated successfully");
			view.switchToConsole();
		} catch (Exception ex) {
			logger.exception(ex);
		}
	}
}