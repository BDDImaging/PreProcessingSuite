package mserMethods;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import ij.gui.Roi;
import interactivePreprocessing.InteractiveMethods;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.algorithm.componenttree.mser.MserTree;
import net.imglib2.type.NativeType;
import utility.Roiobject;

public class MSERSeg extends SwingWorker<Void, Void> {

	final InteractiveMethods parent;
	final JProgressBar jpb;

	public MSERSeg(final InteractiveMethods parent, final JProgressBar jpb) {

		this.parent = parent;
		this.jpb = jpb;

	}

	@Override
	protected Void doInBackground() throws Exception {

		utility.ProgressBar.SetProgressBar(jpb, "Computing Component Tree for MSER, Please Wait...");

		if (parent.darktobright)

			parent.newtree = MserTree.buildMserTree(parent.newimg, parent.delta, parent.minSize, parent.maxSize,
					parent.Unstability_Score, parent.minDiversity, true);

		else

			parent.newtree = MserTree.buildMserTree(parent.newimg, parent.delta, parent.minSize, parent.maxSize,
					parent.Unstability_Score, parent.minDiversity, false);

		return null;
	}

	@Override
	protected void done() {
		
		parent.overlay.clear();
		parent.Rois = utility.FinderUtils.getcurrentRois(parent.newtree);

		ArrayList<double[]> centerRoi = utility.FinderUtils.getRoiMean(parent.newtree);
		for (int index = 0; index < centerRoi.size(); ++index) {

			double[] center = new double[] { centerRoi.get(index)[0], centerRoi.get(index)[1] };

			Roi or = parent.Rois.get(index);

			or.setStrokeColor(parent.colorDrawMser);
			parent.overlay.add(or);
		}
	

		parent.imp.setOverlay(parent.overlay);
		parent.imp.updateAndDraw();
		utility.ProgressBar.SetProgressBar(jpb, "Done");
		try {
			get();
		} catch (InterruptedException e) {

		} catch (ExecutionException e) {

		}

	}

}