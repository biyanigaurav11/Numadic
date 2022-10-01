package Numadic.NUProducts.Utilities;

import java.io.File;
import java.util.Date;

public class FileUtilities {
	public static File CreateFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();// mkdir is used to create folder

        } else {
            System.out.println("Folder already created");
        }
        return file;
    }

    public static String Timestamp() {

        Date now = new Date();
        String Timestamp = now.toString().replace(":", "-");
        return Timestamp;
    }
}
