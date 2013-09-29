package ide.controller;

public class MarvelSaveLoadHandler extends SaveLoadHandler {

	private static final String FILE_EXTENSION = "mvl";
	private static final String DESCRIPTION = "Marvel source code (*." + FILE_EXTENSION + ")";
	
	String getFileExtension() {
		return FILE_EXTENSION;
	}
	
	String getFileDescription() {
		return DESCRIPTION;
	}
	
}
