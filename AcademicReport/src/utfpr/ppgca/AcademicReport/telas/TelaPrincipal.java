package utfpr.ppgca.AcademicReport.telas;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import utfpr.ppgca.AcademicReport.ChartArticleByItemType;
import utfpr.ppgca.AcademicReport.ChartArticleByYear;

public class TelaPrincipal extends JFrame {

	MenuTelaPrincipal menu = new MenuTelaPrincipal();
	
	public TelaPrincipal() {  
        super("AcademicReport v1.0");  
                
        montaTela();
        
        setaComportamentoMenu();
    }  
	      
    public void montaTela() {  
        montaJanela();  
        mostraJanela();  
    }  
      
    public void montaJanela() {  
        setLayout(new FlowLayout());  
        setSize(800,600);  
        setLocationRelativeTo(null);
        setJMenuBar(menu.getMenu());
    }  
  
    public void mostraJanela() {  
        setVisible(true);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }
    
    public MenuTelaPrincipal getMenuTelaPrincipal() {
    	return menu;
    }
    
	protected void showChartArticleByYear() {
		ChartArticleByYear year = new ChartArticleByYear();
		
		this.setContentPane(year.getChartPanel());
		this.setVisible(true);
	}
    
	protected void showChartArticleByItemType() {
		ChartArticleByItemType type = new ChartArticleByItemType();
		
		this.setContentPane(type.getChartPanel());
		this.setVisible(true);		
	}
	
    private void setaComportamentoMenu() {
    	menu.getByYear().addActionListener(
    						new ActionListener() {
    							public void actionPerformed(ActionEvent e) {
    								showChartArticleByYear();
    							}
    						}
    					);

    	menu.getByItemType().addActionListener(
							new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									showChartArticleByItemType();
								}
							}
						);
    	
    	menu.getExit().addActionListener(
							new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									System.exit(0);
								}
							}    			
    					);
    }
}
