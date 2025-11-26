package com.napier.sem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MarkdownWriter {

    /**
     * Writes a Markdown table.
     *
     * @param filename name of the file to write (e.g., "Top10CitiesJapan.md")
     * @param headers the column headers
     * @param rows a list of lists (each inner list = one row)
     */
    public static void writeMarkdownTable(String filename,
                                          List<String> headers,
                                          List<List<String>> rows) {

        try {
            // Required by Lab 08 — use /tmp/reports inside Docker container
            File reportDir = new File("/tmp/reports/");
            reportDir.mkdirs();

            File out = new File(reportDir, filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(out));

            StringBuilder sb = new StringBuilder();

            // Header row
            sb.append("| ");
            for (String h : headers) {
                sb.append(h).append(" | ");
            }
            sb.append("\n");

            // Separator row
            sb.append("| ");
            for (int i = 0; i < headers.size(); i++) {
                sb.append("--- | ");
            }
            sb.append("\n");

            // Data rows
            for (List<String> row : rows) {
                sb.append("| ");
                for (String value : row) {
                    sb.append(value).append(" | ");
                }
                sb.append("\n");
            }

            writer.write(sb.toString());
            writer.close();

            System.out.println("✔ Markdown file written: " + out.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
