import java.io.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            // Replace "file_path" with the path to your text file
            BufferedReader bufferedReader = new BufferedReader(new FileReader("text.txt"));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
