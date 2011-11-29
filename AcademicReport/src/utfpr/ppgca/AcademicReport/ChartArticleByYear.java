
package utfpr.ppgca.AcademicReport;

import java.io.File;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
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
			dataset.setValue(String.valueOf(a.Year), a.Amount);
		}
		
		chart = ChartFactory.createPieChart("Chart 1", dataset, false, false, false);
		
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
