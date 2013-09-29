package ide.view;

import java.awt.BorderLayout;
import javax.swing.*;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.RTextScrollPane;

public class CodeEditorPanel extends TextPanel {
	private RSyntaxTextArea textArea;
	
	public CodeEditorPanel() {
		setLayout(new BorderLayout());
		textArea = new RSyntaxTextArea();
		AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory)TokenMakerFactory.getDefaultInstance();
		atmf.putMapping("text/marvel", "ide.view.MarvelTokenMaker");
		textArea.setCodeFoldingEnabled(true);
		textArea.setAntiAliasingEnabled(true);
		textArea.setSyntaxEditingStyle("text/marvel");
		add(createScrollPane(textArea), BorderLayout.CENTER);
	}
	
	private RTextScrollPane createScrollPane(RSyntaxTextArea textArea) {
		RTextScrollPane scrollPane = new RTextScrollPane(textArea);
		scrollPane.setFoldIndicatorEnabled(true);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		return scrollPane;
	}
	
	@Override
	public void setText(String text) {
		textArea.setText(text);
	}
	
	@Override
	public String getText() {
		return textArea.getText();
	}
	
	@Override
	public boolean isEmpty() {
		return textArea.getText().isEmpty();
	}
}
