package interactivePreprocessing;

import ij.IJ;

public class ShowView {

	
	final InteractiveMethods parent;
	
	
	public ShowView(final InteractiveMethods parent) {
		
		this.parent = parent;
		
	}
	
	
	public void shownewZ() {

		if (parent.thirdDimension > parent.thirdDimensionSize) {
			IJ.log("Max Z stack exceeded, moving to last Z instead");
			parent.thirdDimension = parent.thirdDimensionSize;
			
			
			parent.CurrentView = utility.Slicer.getCurrentView(parent.originalimg, (int)parent.thirdDimension,
					(int)parent.thirdDimensionSize, (int)parent.fourthDimension, (int)parent.fourthDimensionSize);
			
		} else {

			parent.CurrentView = utility.Slicer.getCurrentView(parent.originalimg, (int)parent.thirdDimension,
					(int)parent.thirdDimensionSize, (int)parent.fourthDimension, (int)parent.fourthDimensionSize);
			
		}

		
	}

	
	
	public void shownewT() {

		if (parent.fourthDimension > parent.fourthDimensionSize) {
			IJ.log("Max time point exceeded, moving to last time point instead");
			parent.fourthDimension = parent.fourthDimensionSize;
			
			
			parent.CurrentView = utility.Slicer.getCurrentView(parent.originalimg,(int) parent.thirdDimension,
					(int)parent.thirdDimensionSize,(int) parent.fourthDimension, (int)parent.fourthDimensionSize);
			
		} else {

			parent.CurrentView = utility.Slicer.getCurrentView(parent.originalimg,(int) parent.thirdDimension,
					(int)parent.thirdDimensionSize, (int)parent.fourthDimension, (int)parent.fourthDimensionSize);
			
		}

		
		
	

		
	}
	
}