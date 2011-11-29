package utfpr.ppgca.AcademicReport;

public class AcademicReport {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Test the Chart Article By Year
		ChartArticleByYear chart = new ChartArticleByYear();
		chart.saveAsJpeg("c:\\temp\\chart.jpg");
	}

}
