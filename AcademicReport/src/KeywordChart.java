import java.awt.Font;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class KeywordChart extends JFrame {
	private static final long serialVersionUID = 1L;

	private DefaultPieDataset dataset;
	private JFreeChart jfc;

	public KeywordChart()
	{
		dataset = new DefaultPieDataset();
	}

	public void setValue(String title, Double numDouble)
	{
		dataset.setValue(title, numDouble);
	}

	public void setChar(String title)
	{
		jfc = ChartFactory.createPieChart(title, dataset, true, true, false);

		PiePlot pp = (PiePlot) jfc.getPlot();
		pp.setSectionOutlinesVisible(false);
		pp.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		pp.setNoDataMessage("Nessun Dato Inserito");
		pp.setCircular(false);
		pp.setLabelGap(0.02);
	}

	private JPanel createPanel()
	{
		return new ChartPanel(jfc);
	}

	public void Show()
	{
		setContentPane(createPanel());
		setVisible(true);
	}

	public static void main(String[] args)
	{
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		double total = 100.0;

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:src/zotero.sqlite");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT count(tagID) AS total FROM tags t1 group by t1.name");
			total = resultSet.getDouble("total");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	        

		KeywordChart j = new KeywordChart();
		j.setTitle("Palavras Chave Chart...");
		j.setSize(640, 430);

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:src/zotero.sqlite");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT t1.name, count(tagID) AS contador FROM tags t1 group by t1.name");
			while (resultSet.next()) {
				j.setValue(resultSet.getString("name"), (resultSet.getDouble("contador")*100.0)/total);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	        

		j.setChar("Palavra Chave Chart...");

		j.Show();
	}
}