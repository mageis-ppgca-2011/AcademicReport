
package utfpr.ppgca.AcademicReport;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import utfpr.ppgca.AcademicReport.entities.ArticleByYear;

public class ChartArticleByYear {
	
	private JFreeChart chart = null;
	
	public ChartArticleByYear()
	{
		ArrayList<ArticleByYear> articles = DBAccessLayer.GetArticlesByYear();
		
		DefaultPieDataset dataset = new DefaultPieDataset();
        
		for (ArticleByYear a : articles) {
			dataset.setValue(String.valueOf(a.Year) + " - " + a.Amount, a.Amount);
		}
		
		chart = ChartFactory.createPieChart("Pie Chart - Amount of Articles by Year", dataset, false, false, false);
		
	}
	
	public JPanel getChartPanel()
	{
		return new ChartPanel(chart);
	}
	
	public void saveAsJpeg(String path)
	{
		try
		{
			ChartUtilities.saveChartAsJPEG(new File(path), chart, 800, 600);
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
}
