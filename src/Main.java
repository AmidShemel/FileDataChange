import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Main {

    public static File folder1 = new File("files\\dir1\\");
    public static File folder2 = new File("files\\dir2\\");
    public static File folder3 = new File("files\\dir3\\");

    public static void main(String[] args) throws IOException {


        File[] files1 = folder1.listFiles();
        File[] files2 = folder2.listFiles();

         copyAll(files1);
         copyAll(files2);

        fileChecker(files1, files2);

//        String fileName = "files\\dir1\\file1.txt";
//        //String fileName = "test.xls";
//        File file = new File(fileName);
//        long l = file.lastModified();
//        System.out.println(l);
//        System.out.println("Date of change: " + new Date(file.lastModified()));

    }


    public static void fileChecker(File[] files1, File[] files2) throws IOException {
        int count = 0;
        for (File f1 : files1) {
            //System.out.println(f1 + " (" + f1.lastModified() + ")");
            String sf1 = f1.getName();
            //System.out.println(sf1);
            for (File f2 : files2) {
                String sf2 = f2.getName();
                if (sf1.equals(sf2)) {
                    count++;
                    System.out.println(count + ": " + f1 + " | " + f2);
                    if (f1.lastModified() > f2.lastModified()) {
                        copyFile(f1, new File((folder3 + sf1)));
                    } else {
                        copyFile(f2, new File((folder3 + sf2)));
                    }
                }
            }
        }
        System.out.println(count + " sovpadeniy");
    }

    private static void copyFile(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath(), REPLACE_EXISTING);
    }

    private static void copyAll(File[] files) throws IOException {

        Path p1, p2 = folder3.toPath();

        for (File f : files) {
            p1 = f.toPath();
            Files.copy(p1, p2.resolve(p1.getFileName()), REPLACE_EXISTING);

        }

    }


}