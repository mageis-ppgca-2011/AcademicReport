package utfpr.ppgca.AcademicReport;

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
		//ChartArticleByCitation chartByCitation = new ChartArticleByCitation();
		//CharArticleByItemType chartItemType = new CharArticleByItemType();
				
		//JFrame frame = new JFrame();
		
		//frame.setContentPane(chartItemType.getChartPanel());
		//frame.setBounds(0,  0, 800, 600);
		//frame.setVisible(true);
		new AcademicReport();
	}

}
