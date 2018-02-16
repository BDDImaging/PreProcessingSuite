package interactivePreprocessing;

import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollBar;

import interactivePreprocessing.InteractiveMethods.ValueChange;



public class PreThresholdListener implements AdjustmentListener {
	final Label label;
	InteractiveMethods parent;
	final String string;
	final float min, max;
	final int scrollbarSize;
	final JScrollBar scrollbar;
	
	public PreThresholdListener(final InteractiveMethods parent, final Label label, final String string, final float min, final float max, final int scrollbarSize, final JScrollBar scrollbar) {
		this.parent = parent;
		this.label = label;
		this.string = string;

		this.min = min;
		this.max = max;
		this.scrollbar = scrollbar;
		this.scrollbarSize = scrollbarSize;
		scrollbar.addMouseListener( new PreStandardMouseListener( parent, ValueChange.DOG ) );

		
	}

	@Override
	public void adjustmentValueChanged(final AdjustmentEvent event) {
	
		parent.threshold = utility.ScrollbarUtils.computeValueFromScrollbarPosition(event.getValue(), min, max,
				scrollbarSize);
		scrollbar.setValue(utility.ScrollbarUtils.computeScrollbarPositionFromValue(parent.threshold, min, max, scrollbarSize));

		label.setText(string +  " = "  + parent.threshold);
	
	}
}


