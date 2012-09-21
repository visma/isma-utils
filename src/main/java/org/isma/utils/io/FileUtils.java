package org.isma.utils.io;

import java.io.*;

import static org.apache.commons.io.FileUtils.forceDelete;

public class FileUtils {
    private FileUtils() {
    }


    //Utile pour unifier les comparaisons lors des tests quand on ne controle pas le format attendu
    public static String suppressBackSlashes(String path) {
        return path.replace("\\", "/");
    }


    public static void forceDeleteIfExists(File file) throws IOException {
        if (file.exists()) {
            forceDelete(file);
        }
    }


    public static void writeToFile(String fileName, InputStream iStream)
          throws IOException {
        File file = new File(fileName);

        if (file.exists()) {
            String msg = file.isDirectory() ? "directory" : (!file.canWrite() ? "not writable" : null);
            if (msg != null) {
                throw new IOException("file '" + fileName + "' is " + msg);
            }
        }
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        BufferedOutputStream fOut = null;
        try {
            fOut = new BufferedOutputStream(new FileOutputStream(file));
            byte[] buffer = new byte[32 * 1024];
            int bytesRead;
            while ((bytesRead = iStream.read(buffer)) != -1) {
                fOut.write(buffer, 0, bytesRead);
            }
        }
        catch (Exception e) {
            throw new IOException("failed, got: " + e.toString());
        }
        finally {
            close(iStream, fOut);
        }
    }

    //TODO tester
    public static String getExtension(File file) {
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index + 1, fileName.length());
    }

    private static void close(InputStream iStream, OutputStream oStream)
          throws IOException {
        try {
            if (iStream != null) {
                iStream.close();
            }
        }
        finally {
            if (oStream != null) {
                oStream.close();
            }
        }
    }


}
