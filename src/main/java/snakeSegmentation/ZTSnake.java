package snakeSegmentation;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import interactivePreprocessing.InteractiveMethods;
import interactivePreprocessing.InteractiveMethods.ValueChange;
import net.imglib2.Interval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.logic.BitType;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.util.Intervals;
import net.imglib2.view.Views;
import utility.PreRoiobject;

public class ZTSnake extends SwingWorker<Void, Void> {

	final InteractiveMethods parent;

	public ZTSnake(final InteractiveMethods parent) {

		this.parent = parent;

	}

	@Override
	protected Void doInBackground() throws Exception {

		for (int t = parent.fourthDimensionsliderInit; t <= parent.fourthDimensionSize; ++t) {

			// For each T go in Z and make a 3D object to track with

			for (int z = parent.thirdDimensionsliderInit; z <= parent.thirdDimensionSize; ++z) {

				parent.snakeinprogress = true;

				parent.thirdDimension = z;
				parent.fourthDimension = t;
				System.out.println(z + " " + t);
				String uniqueID = Integer.toString(z) + Integer.toString(t);
				
				parent.CurrentView = utility.Slicer.getCurrentView(parent.originalimg, z, parent.thirdDimensionSize, t,
						parent.fourthDimensionSize);
				parent.updatePreview(ValueChange.THIRDDIMmouse);
				ArrayList<PreRoiobject> currentRoi = parent.CurrentPreRoiobject;

				// Expand the image by 10 pixels

				Interval spaceinterval = Intervals.createMinMax(new long[] { parent.CurrentView.min(0),
						parent.CurrentView.min(1), parent.CurrentView.max(0), parent.CurrentView.max(1) });
				Interval interval = Intervals.expand(spaceinterval, 10);
				parent.CurrentView = Views.interval(Views.extendBorder(parent.CurrentView), interval);

				SnakeonZT applysnake = new SnakeonZT(parent, parent.CurrentView, currentRoi);
				applysnake.process();
				ArrayList<PreRoiobject> resultrois = applysnake.getResult();
				parent.ZTRois.put(uniqueID, resultrois);

				
				parent.updatePreview(ValueChange.SNAKE);

			}
		}
		return null;

	}

	@Override
	protected void done() {
		parent.snakeinprogress = false;
		try {
			utility.ProgressBar.SetProgressBar(parent.jpb, "Done");
			get();
		} catch (ExecutionException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
