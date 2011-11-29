package utfpr.ppgca.AcademicReport.util;

public class Parse {
	
	public static int getYear(String date) {
		for (int i = 0; i <= date.length() - 4; i++) {
			try{
				int year = Integer.parseInt(date.substring(i, i + 4));
				return year;
			}
			catch (Exception ex) {
				
			}
		}
				
		return 0;
	}
}
