package org.isma.utils.zip;

import java.io.*;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    static final int BUFFER = 2048;


    public void zip(String srcDirectoryPath, String dstZipFilePath) throws IOException {
        final File rootDir = new File(srcDirectoryPath);
        FileOutputStream dest = new FileOutputStream(dstZipFilePath);
        CheckedOutputStream checksum = new CheckedOutputStream(dest, new Adler32());
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(checksum));
        zip(rootDir, srcDirectoryPath, out);
        out.close();
    }


    private void zip(final File rootDirectory, String srcDirectory, ZipOutputStream out) throws IOException {

        byte data[] = new byte[BUFFER];
        File files[] = new File(srcDirectory).listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                zip(rootDirectory, file.getAbsolutePath(), out);
            }
            else {
                zipFile(rootDirectory, out, data, file);
            }
        }
    }


    private void zipFile(final File rootDirectory, ZipOutputStream out, byte[] data, File file) throws IOException {
        BufferedInputStream origin;
        FileInputStream fi = new FileInputStream(file);
        origin = new BufferedInputStream(fi, BUFFER);
        ZipEntry entry = new ZipEntry(buildEntryName(rootDirectory, file));
        out.putNextEntry(entry);
        int count;
        while ((count = origin.read(data, 0, BUFFER)) != -1) {
            out.write(data, 0, count);
        }
        origin.close();
    }


    private String buildEntryName(File rootDir, File file) {
        return rootDir.getName()
               + "\\"
               + file.getAbsolutePath().replace(rootDir.getAbsolutePath() + "\\", "");
    }
}


