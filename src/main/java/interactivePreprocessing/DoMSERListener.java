package interactivePreprocessing;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import interactivePreprocessing.InteractiveMethods.ValueChange;

public class DoMSERListener implements ItemListener {
	
	final InteractiveMethods  parent;
	
	public DoMSERListener( InteractiveMethods parent) {
		
		this.parent = parent;
		
	}
	
	
	@Override
	public void itemStateChanged(final ItemEvent arg0) {
		
		
		
		
		if (arg0.getStateChange() == ItemEvent.DESELECTED) {
			parent.showDOG = false;
			parent.showMSER = false;
			parent.showWatershed = false;
		} else if (arg0.getStateChange() == ItemEvent.SELECTED) {
			parent.showDOG = false;
			parent.showMSER = true;
			parent.showWatershed = false;
			parent.updatePreview(ValueChange.MSER);
		}

	}
	

}
