package utfpr.ppgca.AcademicReport;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AcademicReport {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Test the Chart Article By Year
		ChartArticleByYear chart = new ChartArticleByYear();
		
		JFrame frame = new JFrame();
		
		frame.setContentPane(chart.getChartPanel());
		frame.setBounds(0,  0, 800, 600);
		frame.setVisible(true);		
	}

}
