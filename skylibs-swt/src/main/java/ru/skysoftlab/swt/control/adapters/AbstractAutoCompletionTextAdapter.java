package ru.skysoftlab.swt.control.adapters;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public abstract class AbstractAutoCompletionTextAdapter extends TextAdapter {
	
	private static String KEY_PRESS = "Ctrl+Space";

	@Override
	public Text createControl(Composite parent) {
		control = new Text(parent, SWT.BORDER);
		createAutoCompletionText(control);
		return control;
	}

	private void createAutoCompletionText(Text text) {
		// Method for autocompletion
		setAutoCompletion(text, null);

		text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				// Method for autocompletion
				setAutoCompletion(text, text.getText());
			}
		});
	}

	private void setAutoCompletion(Text text, String value) {
		try {
			ContentProposalAdapter adapter = null;
			String[] defaultProposals = getDefaultProposals(value);
			SimpleContentProposalProvider scp = new SimpleContentProposalProvider(defaultProposals);
			scp.setProposals(defaultProposals);
			KeyStroke ks = KeyStroke.getInstance(KEY_PRESS);
			adapter = new ContentProposalAdapter(text, new TextContentAdapter(), scp, ks, null);
			adapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract String[] getDefaultProposals(String value);
}
