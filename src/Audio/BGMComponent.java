package Audio;

import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * 
 * @author Farrah Aina Mohd Zulkifli
 *
 */

public class BGMComponent extends JPanel
{
	public BGMComponent(BGM easter, int min, int max, int initial)
	{
		super();
		
		//model
		BGMModel model = new BGMModel(easter);
		
		//view
		BGMView est = new BGMView(model);
		
		//make view observe model
		model.addObserver(est);
		
		//create control
		JSlider slider = new JSlider(min, max, initial);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing((max - min) / 4);
		slider.setPaintLabels(true);
		slider.setLabelTable(slider.createStandardLabels((max - min) / 4));
		
		//create listener
		SliderListener listen = new SliderListener(model, slider);
		
		//make listeners listen to controls
		slider.addChangeListener(listen);
		
		//place views and controls on panel
		add(slider);
		add(est);
	}
}
