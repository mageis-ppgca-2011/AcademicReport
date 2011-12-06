package utfpr.ppgca.AcademicReport.telas;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuTelaPrincipal {
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu mnCharts = new JMenu("Pie Chart");  

	private JMenuItem byYear = new JMenuItem("Amount of Articles by Year");  
	private JMenuItem byItemType = new JMenuItem("Amount of Articles by Item Type");  
	private JMenuItem exit = new JMenuItem("Exit");
	
	public MenuTelaPrincipal() {  
		criaMenus();  
	}  
	      
	private void criaMenus() {  
		// Menu 
		mnCharts.add(byYear);
		mnCharts.add(byItemType);
		mnCharts.add(exit);
	
		menuBar.add(mnCharts);
	}
		
	public JMenuBar getMenu() {
		return menuBar;
	}

	public JMenuItem getByYear() {
		return byYear;
	}

	public JMenuItem getByItemType() {
		return byItemType;
	}
	
	public JMenuItem getExit() {
		return exit;
	}
}
