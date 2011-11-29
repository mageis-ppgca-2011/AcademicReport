package utfpr.ppgca.AcademicReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import utfpr.ppgca.AcademicReport.entities.ArticleByYear;

public class DBAccessLayer {
	
	public static final String QUERY_ARTICLES_BY_YEAR = 
"SELECT " +			
"   substr(d.value, 12) AS Year, " +
"	COUNT(*) AS Amount " +

"FROM " +
"	items i " +
"	LEFT JOIN itemDataValues d ON d.valueID = (SELECT itemData.valueID FROM itemData WHERE itemData.fieldID = (SELECT fieldID FROM fields WHERE fields.fieldName = 'date' LIMIT 1) AND itemData.itemID=i.itemID LIMIT 1) " +
"	LEFT JOIN deletedItems ON i.itemID = deletedItems.itemID " +

"WHERE 1 = 1 " +
"	AND deletedItems.itemID IS NULL " +
"	AND i.libraryID IS NULL " +
"	AND i.itemTypeID != 14 -- attachment " +
	
"GROUP BY " +
 "  Year " +

"ORDER BY  " +
"   Year";
	
	public DBAccessLayer() {
		
	}
	
	public static ArrayList<ArticleByYear> GetArticlesByYear() {
		
		Connection connection = null;
	    ResultSet resultSet = null;
	    Statement statement = null;
	    
	    ArrayList<ArticleByYear> articlesList = new ArrayList<ArticleByYear>(); 

	    try {
	        Class.forName("org.sqlite.JDBC");
	        //connection = DriverManager.getConnection("jdbc:sqlite:src/zotero.sqlite");
	        connection = DriverManager.getConnection("jdbc:sqlite:C:\\LocalDocuments\\Mestrado\\Laudelino\\zotero.sqlite");
	         
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery(QUERY_ARTICLES_BY_YEAR);
	        while (resultSet.next()) {
	            
	        	ArticleByYear articles = new ArticleByYear();
	        	try
	        	{
	        		articles.Year = Integer.parseInt(resultSet.getString("Year"));
	        		articles.Amount = Integer.parseInt(resultSet.getString("Amount"));
	        		articlesList.add(articles);
	        	}
	        	catch(Exception ex)
	        	{
	        		
	        	}
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
		
		return articlesList;
	}
}
