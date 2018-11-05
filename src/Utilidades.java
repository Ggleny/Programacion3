import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utilidades {
	public static ArrayList<String> LeerArchivoCSV(String path){
		String csvFile = path;
		BufferedReader br = null;
		String line = "";
		ArrayList<String> datosSend=new ArrayList<>();
		System.out.println(csvFile);
		try {
			
		    br = new BufferedReader(new FileReader(csvFile));
		    while ((line = br.readLine()) != null) {
		        datosSend.add(line);
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (br != null) {
		        try {
		            br.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
	    return datosSend;
	}
}