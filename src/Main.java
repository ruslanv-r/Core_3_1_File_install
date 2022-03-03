import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

        List<String> dirList = Arrays.asList("", "\\src", "\\res", "\\savegames", "\\temp", "\\src\\main");
        List<String> dirFiles = Arrays.asList("", "\\src", "\\res", "\\savegames", "\\temp", "\\src\\main");

        createDirectory(dirList);

//        File dir1 = new File("D:\\Games");
//        if (dir1.mkdir()) {
//            System.out.println("Каталог Games создан");
//            sb.append("Каталог Games создан\n");
//        }
//
//        File dirSrc = new File("D://Games\\src");
//        if (dirSrc.mkdir()) {
//            System.out.println("Каталог src создан");
//            sb.append("Каталог src создан\n");
//        }
//
//        File dirRes = new File("D://Games\\res");
//        if (dirRes.mkdir()) {
//            System.out.println("Каталог res создан");
//            sb.append("Каталог res создан\n");
//        }
//
//        File dirSavegames = new File("D://Games\\savegames");
//        if (dirSavegames.mkdir()) {
//            System.out.println("Каталог savegames создан");
//            sb.append("Каталог savegames создан\n");
//        }
//
//        File dirTemp = new File("D://Games\\temp");
//        if (dirTemp.mkdir()) {
//            System.out.println("Каталог temp создан");
//            sb.append("Каталог temp создан\n");
//        }
//
//        File dirSrcMain = new File("D://Games\\src\\main");
//        if (dirSrcMain.mkdir()) {
//            System.out.println("Каталог main создан");
//            sb.append("Каталог main создан\n");
//        }
//
//        File dirSrcTest = new File("D://Games\\src\\test");
//        if (dirSrcTest.mkdir()) {
//            System.out.println("Каталог test создан");
//            sb.append("Каталог test создан\n");
//        }
//
//        System.out.println(sb.toString());

        File myFile = new File("D://Games\\src\\main//Main.java");

        try {
            if (myFile.createNewFile()) {
                System.out.println("Файл Main.java был создан");
                sb.append("Файл Main.java создан\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        File myFile2 = new File("D://Games\\src\\main//Utils.java");

        try {
            if (myFile2.createNewFile()) {
                System.out.println("Файл Utils.java был создан");
                sb.append("Файл Main.java создан\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


//        String text = "Hello world!";
//// откроем байтовый поток записи в файл
//        try (FileOutputStream fos = new FileOutputStream("notes.txt")) {
//// перевод строки в массив байтов
//            byte[] bytes = text.getBytes();
//// запись байтов в файл
//            fos.write(bytes, 0, bytes.length);
//        } catch (IOException ex)
//        { System.out.println(ex.getMessage(
//        ));
//        }

    }

    public static void createDirectory(List<String> dirList) {

        for (String tmpStr : dirList) {

            File cr = new File("D://Games"+tmpStr);
            if (cr.mkdir()) {
                System.out.println("Каталог - D://Games"+tmpStr+" создан");
            }
        }
    }

}