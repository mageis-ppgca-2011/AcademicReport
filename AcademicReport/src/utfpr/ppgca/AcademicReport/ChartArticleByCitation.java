package utfpr.ppgca.AcademicReport;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


import utfpr.ppgca.AcademicReport.entities.ArticleByCitation;

public class ChartArticleByCitation {
	
	private JFreeChart chart = null;
	
	public ChartArticleByCitation()
	{
		ArrayList<ArticleByCitation> articles = DBAccessLayer.GetArticleByCitation();
        
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
		for (ArticleByCitation a : articles) {
			dataset.addValue(a.Amount, a.Title, a.Title);
			//addValue(a.Name + " - " + a.Amount, a.Amount);
			System.out.println(a.Title + " - " + a.Amount);
		}
		
		chart = ChartFactory.createStackedBarChart("Amount of Articles by Author", "Author", "Amount", dataset,PlotOrientation.HORIZONTAL , false, false, true);
				
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

