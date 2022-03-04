import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        //Первая задача

        StringBuilder sb = new StringBuilder();

        List<String> dirList = Arrays.asList("", "\\src", "\\res", "\\savegames", "\\temp", "\\src\\main", "\\res\\drawables", "\\res\\vectors", "\\res\\icons");
        List<String> dirFiles = Arrays.asList("D:\\\\Games\\src\\main\\Main.java", "D:\\\\Games\\src\\main\\Utils.java", "D:\\\\Games\\temp\\temp.txt");

        createDirectory(dirList, sb);
        createFiles(dirFiles, sb);
        writeFiles("D:\\\\Games\\temp\\temp.txt", sb);


        //Вторая задача - сохранение

        GameProgress gameProgress1 = new GameProgress(100, 10, 10, 1000);
        GameProgress gameProgress2 = new GameProgress(200, 20, 20, 2000);
        GameProgress gameProgress3 = new GameProgress(300, 30, 30, 3000);
        List<GameProgress> listGameProgress = Arrays.asList(gameProgress1, gameProgress2, gameProgress3);
        saveGameProgress(listGameProgress, sb);
        zipGameProgress("D:\\Games\\savegames\\output_save.zip", sb);
         delGameProgress();
        gameProgress1 = new GameProgress(1, 1, 1, 1);
        gameProgress2 = new GameProgress(1, 2, 2, 2);
        gameProgress3 = new GameProgress(1, 3, 2, 3);
        //Третья задача задача - загрузка
        unZipGameProgress();
        openProgress();

    }
    public static void openProgress() {
        GameProgress gameProgress = null;


        try (FileInputStream fis = new FileInputStream("D:\\Games\\savegames\\packed_save.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // десериализуем объект и скастим его в класс
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(gameProgress);
    }


    public static void unZipGameProgress() {

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream("D:\\Games\\savegames\\output_save.zip"))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName(); // получим название файла
                System.out.println(name);
// распаковка
                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void delGameProgress() {

        File dir = new File("D:\\Games\\savegames\\save.dat");
        File dir2 = new File("D:\\Games\\savegames\\save.dat");
        if (dir.delete())
            System.out.println("Файл D:\\Games\\savegames\\save.dat удален");
    }


    public static void zipGameProgress(String path, StringBuilder sb) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("D:\\Games\\savegames\\output_save.zip"));
             FileInputStream fis = new FileInputStream("D:\\Games\\savegames\\save.dat")) {
            ZipEntry entry = new ZipEntry("D:\\Games\\savegames\\packed_save.dat");
            zout.putNextEntry(entry);
// считываем содержимое файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
// добавляем содержимое к архиву
            zout.write(buffer);
// закрываем текущую запись для новой записи
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }


    public static void saveGameProgress(List<GameProgress> listGameProgress, StringBuilder sb) {
        for (GameProgress tmpGp : listGameProgress) {
            System.out.println(tmpGp);
            try (FileOutputStream fos = new FileOutputStream("D:\\Games\\savegames\\save.dat");
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
// запишем экземпляр класса в файл
                oos.writeObject(tmpGp);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
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