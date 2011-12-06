package utfpr.ppgca.AcademicReport;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utfpr.ppgca.AcademicReport.telas.TelaPrincipal;

public class AcademicReport {

	TelaPrincipal tela;
	
	public AcademicReport() {
		tela = new TelaPrincipal();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Test the Chart Article By Year
		// ChartArticleByYear chartYear = new ChartArticleByYear();
		//ChartArticleCitation chartCitation = new ChartArticleCitation();
		//CharArticleByItemType chartItemType = new CharArticleByItemType();
				
		//JFrame frame = new JFrame();
		
		//frame.setContentPane(chartItemType.getChartPanel());
		//frame.setBounds(0,  0, 800, 600);
		//frame.setVisible(true);
		new AcademicReport();
	}

}
