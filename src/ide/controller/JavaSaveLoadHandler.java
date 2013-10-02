package ide.controller;

public class JavaSaveLoadHandler extends SaveLoadHandler {

	private static final String FILE_EXTENSION = "java";
	private static final String DESCRIPTION = "Java source code (*." + FILE_EXTENSION + ")";
	
	String getFileExtension() {
		return FILE_EXTENSION;
	}
	
	String getFileDescription() {
		return DESCRIPTION;
	}
	

}
