package utfpr.ppgca.AcademicReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import utfpr.ppgca.AcademicReport.entities.ArticleByYear;
import utfpr.ppgca.AcademicReport.entities.ArticleCitation;
import utfpr.ppgca.AcademicReport.util.Parse;

public class DBAccessLayer {
	
	public static final String QUERY_ARTICLES_BY_YEAR = 
" SELECT " +			
"   substr(d.value, 12) AS Year, " +
"	COUNT(*) AS Amount " +

" FROM " +
"	items i " +
"	LEFT JOIN itemDataValues d ON d.valueID = (SELECT itemData.valueID FROM itemData WHERE itemData.fieldID = (SELECT fieldID FROM fields WHERE fields.fieldName = 'date' LIMIT 1) AND itemData.itemID=i.itemID LIMIT 1) " +
"	LEFT JOIN deletedItems ON i.itemID = deletedItems.itemID " +

" WHERE 1 = 1 " +
"	AND deletedItems.itemID IS NULL " +
"	AND i.libraryID IS NULL " +
"	AND i.itemTypeID != 14 " +
	
" GROUP BY " +
"  Year " +

" ORDER BY  " +
"   Year";

	public static final String QUERY_ARTICLES_CITATIONS = 		
	"SELECT DISTINCT(i1ID), " +
	"	T110.value AS Title, " +
	"	T18.value AS callNumber " +
	"FROM " +
	"	(SELECT DISTINCT(itemID) as i1ID, value FROM itemData, itemDataValues WHERE fieldID='110' AND itemData.valueID = itemDataValues.valueID) AS T110 " + 
	"LEFT JOIN " + 
	"	(SELECT DISTINCT(itemID) as i2ID, value FROM itemData, itemDataValues WHERE fieldID='18' AND itemData.valueID = itemDataValues.valueID) AS T18 " +
	"ON i1ID=i2ID";

	public static final String QUERY_TESTE =
	"	(SELECT DISTINCT(itemID) as i1ID, value AS Title FROM itemData, itemDataValues WHERE fieldID='110' AND itemData.valueID = itemDataValues.valueID) ";
	
	public DBAccessLayer() {
		
	}
	
	public static ArrayList<ArticleByYear> GetArticlesByYear() {
		
		Connection connection = null;
	    ResultSet resultSet = null;
	    Statement statement = null;
	    
	    ArrayList<ArticleByYear> articlesList = new ArrayList<ArticleByYear>(); 

	    try {
	        Class.forName("org.sqlite.JDBC");
	        connection = DriverManager.getConnection("jdbc:sqlite:src/zotero.sqlite");
	        //connection = DriverManager.getConnection("jdbc:sqlite:C:\\LocalDocuments\\Mestrado\\Laudelino\\zotero.sqlite");
	         
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery(QUERY_ARTICLES_BY_YEAR);
	        while (resultSet.next()) {
	            
	        	ArticleByYear article = new ArticleByYear();
	        	try {
	        		if (resultSet.getObject("Year") == null) continue;
	        		
	        		article.Year = Parse.getYear(resultSet.getString("Year"));
	        		if (article.Year == 0) continue;
	        		
	        		article.Amount = resultSet.getInt("Amount");
	        		
	        		sunOrAddArticle(articlesList, article); 
	        		
	        		//System.out.println(article.Year + " - " + article.Amount);
	        	}
	        	catch(Exception ex) {
	        		System.out.println("error on parse values");
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
	
	public static ArrayList<ArticleByYear> sunOrAddArticle(ArrayList<ArticleByYear> list, ArticleByYear article) {
		
		if (list.size() == 0) {
			list.add(article);
			return list;
		}
		
		for (int i = 0; i < list.size(); i++) {
			ArticleByYear current = list.get(i);
			
			
			if (current.Year == article.Year) {
				current.Amount += article.Amount;
				break;
			}
			else if (article.Year > current.Year ) {
				if (i == list.size() - 1) {
					list.add(article);
					break;
				}
				else if (list.get(i + 1).Year > article.Year) {
					list.add(i + 1, article);
					break;
				}
			}
		}
		
		return list;
	}
	
	public static ArrayList<ArticleCitation> GetArticleCitation() {
		
		Connection connection = null;
	    ResultSet resultSet = null;
	    Statement statement = null;
	    
	    ArrayList<ArticleCitation> articlesList = new ArrayList<ArticleCitation>(); 

	    try {
	        Class.forName("org.sqlite.JDBC");
	        connection = DriverManager.getConnection("jdbc:sqlite:src/zotero.sqlite");
         
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery(QUERY_ARTICLES_CITATIONS);
	        while (resultSet.next()) {
	            
	        	ArticleCitation article = new ArticleCitation();
	        	try {
        		
	        		article.Title = resultSet.getString("Title");
	        		if (resultSet.getString("callNumber") == null )
	        			article.Amount = 0;
	        		else
	        			article.Amount = resultSet.getInt("callNumber");
	        		
	        		articlesList.add(article);
	        		
	        		System.out.println(article.Title+" - "+ article.Amount);
	        	}
	        	catch(Exception ex) {
	        		System.out.println("error on parse values");
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
