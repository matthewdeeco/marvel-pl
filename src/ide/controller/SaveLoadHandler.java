package ide.controller;

import java.io.*;
import java.util.Scanner;

import ide.view.DialogHandler;

public abstract class SaveLoadHandler {
	private DialogHandler dialogHandler;
	private String lastSavePath = null;
	private String lastLoadPath = null;
	
	abstract String getFileExtension();
	abstract String getFileDescription();
	
	public void saveState(String model) {
		if (lastSavePath == null) {
			if (lastLoadPath == null)
				saveStateAs(model);
			else
				saveState(model, lastLoadPath);
		}
		else
			saveState(model, lastSavePath);
	}
	
	public void saveStateAs(String model) {
		String filePath = dialogHandler.inputSaveFilePath(getFileDescription(), getFileDescription());
		if (filePath != null) {
			filePath = addFileExtensionIfNotThere(filePath);
			lastSavePath = filePath;
			saveState(model, filePath);
		}
	}

	private void saveState(String model, String filePath) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File(filePath));
			writer.write(model);
			lastSavePath = filePath;
		} catch (Exception ex) {
			String message = "Error saving the module!\n" + "Make sure you have the permissions "
					+ "to save to that folder.";
			dialogHandler.errorMessage(message);
			ex.printStackTrace();
		} finally {
			tryToClose(writer);
		}
	}

	private String addFileExtensionIfNotThere(String filePath) {
		if (!filePath.endsWith("." + getFileExtension()))
			filePath += "." + getFileExtension();
		return filePath;
	}

	private void tryToClose(Closeable closeable) {
		try {
			if (closeable != null)
				closeable.close();
		} catch (IOException ex) {
		}
	}

	public String loadState() {
		String filePath = dialogHandler.inputLoadFilePath(getFileDescription(), getFileExtension());
		if (filePath == null)
			return null;
		else
			return loadState(filePath);
	}

	private String loadState(String filePath) {
		String loadedGameModel = null;
		try {
			loadedGameModel = readFile(filePath);
			lastLoadPath = filePath;
			return loadedGameModel;
		} catch (Exception ex) {
			String message = "Error loading your save file!\n" + "Make sure that your module is in the \n"
					+ "correct format and that the file exists.";
			dialogHandler.errorMessage(message);
			return null;
		}
	}

	private static String readFile(String path) throws IOException {
		File file = new File(path);
		return new Scanner(file).useDelimiter("\\Z").next();
	}

	public void setDialogHandler(DialogHandler dialogHandler) {
		this.dialogHandler = dialogHandler;
	}

	public String getLastSavePath() {
		return lastSavePath;
	}

	public void setLastSavePath(String lastSavePath) {
		this.lastSavePath = lastSavePath;
	}
	
	public String getLastLoadPath() {
		return lastLoadPath;
	}
	
	public String getLastFileName() {
		String path = (lastSavePath != null) ? lastSavePath : lastLoadPath;
		if (path == null)
			return "";
		return path.substring(path.lastIndexOf("/") + 1);
	}
	
	public void resetPaths() {
		lastSavePath = null;
		lastLoadPath = null;
	}
}
