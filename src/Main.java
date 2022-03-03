import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

        List<String> dirList = Arrays.asList("", "\\src", "\\res", "\\savegames", "\\temp", "\\src\\main", "\\res\\drawables", "\\res\\vectors", "\\res\\icons");
        List<String> dirFiles = Arrays.asList("D:\\\\Games\\src\\main\\Main.java", "D:\\\\Games\\src\\main\\Utils.java", "D:\\\\Games\\temp\\temp.txt");

        createDirectory(dirList, sb);
        createFiles(dirFiles, sb);
        writeFiles("D:\\\\Games\\temp\\temp.txt", sb);
    }

    public static void writeFiles(String path, StringBuilder sb) {
        String text = sb.toString();
        //откроем байтовый поток записи в файл
        try (FileOutputStream fos = new FileOutputStream(path)) {
            // переводстрокивмассивбайтов
            byte[] bytes = text.getBytes();
            // запись байтов в файл
            fos.write(bytes, 0, bytes.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void createFiles(List<String> dirFiles, StringBuilder sb) {

        for (String tmpStr : dirFiles) {

            File myFile = new File(tmpStr);

            try {
                if (myFile.createNewFile()) {
                    System.out.println("Файл " + dirFiles + " был создан");
                    sb.append("Файл - " + dirFiles + " был создан\n");
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void createDirectory(List<String> dirList, StringBuilder sb) {

        for (String tmpStr : dirList) {

            File cr = new File("D://Games" + tmpStr);
            if (cr.mkdir()) {
                System.out.println("Каталог - D://Games" + tmpStr + " создан");
                sb.append("Каталог - D:\\\\Games" + tmpStr + " создан\n");
            }
        }
    }
}