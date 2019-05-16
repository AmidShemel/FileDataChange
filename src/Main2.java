import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Програма шукає файли в папках ініціалізованих в масиві sourceFolders та копіює ВСІ файли в destDir.
 * У разі збігу в назві файлу залишається той, дата редагування якого остання
 */

public class Main2 {

    private static File[] sourceFolders = new File[]{
            new File("files\\dir1"),
            new File("files\\dir2"),
            new File("files\\dir0")};

    private static Path destDir = Paths.get("files\\dir3");

    private static List<File[]> fs = new ArrayList<>();


    public static void main(String[] args) throws IOException {

        for (File f : sourceFolders) {
            fs.add(f.listFiles());
        }

        // Показує в консоль імена всіх файлів з усіх цільвих папок
        for (File[] f : fs) {
            for (File f2 : f) {
                System.out.print(f2 + ", ");
            }
            System.out.println();
        }

        copyAll2(fs);

    }

    private static void copyAll2(List<File[]> list) throws IOException {
        for (File[] sourceDir : list) {
            for (File sourceFile : sourceDir) {
                if (sourceFile.isFile()) {
                    if (Files.exists(destDir.resolve(sourceFile.getName()))) {
                        System.out.print("exist. ");
                        if (sourceFile.lastModified() > destDir.resolve(sourceFile.getName()).toFile().lastModified()) {
                            Files.copy(sourceFile.toPath(), destDir.resolve(sourceFile.getName()), REPLACE_EXISTING);
                            System.out.println("replased");
                        } else {
                            System.out.println("not replased");
                        }
                    } else {
                        Files.copy(sourceFile.toPath(), destDir.resolve(sourceFile.getName()));
                        System.out.println("copied from: " + sourceFile);
                    }
                }
            }
        }
    }
}