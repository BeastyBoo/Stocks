package com.github.beastyboo.stocks.usecase.util;

import java.io.*;

/**
 * Created by Torbie on 27.11.2020.
 */
public class FileUtil {

    public void saveFile(File file, String json) {
        final FileWriter fw;
        try {
            file.createNewFile();
            fw = new FileWriter(file);
            fw.write(json);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);

        }
    }

    public String loadContent(File file) {
        if(file.exists()) {
            try {
                final BufferedReader reader = new BufferedReader(new FileReader(file));
                final  StringBuilder text = new StringBuilder();

                String line;

                while ((line = reader.readLine()) != null) {
                    text.append(line);
                }
                reader.close();
                return text.toString();
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }
        return "";
    }

}
