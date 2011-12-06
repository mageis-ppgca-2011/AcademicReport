package utfpr.ppgca.AcademicReport;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import utfpr.ppgca.AcademicReport.entities.*;

public class ChartArticleByItemType {
	
	private JFreeChart chart = null;
	 
	public ChartArticleByItemType()
	{	
		ArrayList<ArticleByItemType> lista = DBAccessLayer.GetArticlesByItemType();
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		DecimalFormat df = new DecimalFormat("###.##");
		
		for (ArticleByItemType a : lista) {
			dataset.setValue(a.getItemType() + " - " + a.getAmount() + "(" + df.format(a.getPercent()*100) + "%)", a.getPercent()*100);
		}
		
		chart = ChartFactory.createPieChart("Pie Chart - Amount of Articles by Item Type", dataset, false, false, false);
		
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
