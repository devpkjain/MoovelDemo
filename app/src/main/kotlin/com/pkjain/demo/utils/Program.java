package com.pkjain.demo.utils;

/**
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
public class Program {

    private class Downloader implements Runnable {
        private final URL url;

        public Downloader(URL url) {
            this.url = url;
        }

        private String readAll(Reader reader) throws IOException {
            StringBuilder builder = new StringBuilder();
            int read = 0;
            while((read = reader.read()) != -1) {
                builder.append((char)read);
            }
            return builder.toString();
        }

        @Override
        public void run() {
            try {
                Reader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(url.openStream()));
                    String result = readAll(reader);
                    System.out.printf("Read %d characters from %s\n", result.length(), url);
                }
                finally {
                    if (reader != null)
                        reader.close();
                }
            }
            catch(IOException e) {
                System.err.println(e);
            }
        }
    }


    public void runIt() throws MalformedURLException {
        BlockingQueue<Runnable> runnables = new ArrayBlockingQueue<Runnable>(1024);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 16, 60, TimeUnit.SECONDS, runnables);

        executor.submit(new Downloader(new URL("http://www.google.com")));
        executor.submit(new Downloader(new URL("http://www.google.com")));
        executor.submit(new Downloader(new URL("http://www.google.com")));
        executor.submit(new Downloader(new URL("http://www.google.com")));
        executor.submit(new Downloader(new URL("http://www.google.com")));
        executor.submit(new Downloader(new URL("http://www.google.com")));
        executor.submit(new Downloader(new URL("http://www.google.com")));
        executor.submit(new Downloader(new URL("http://www.google.com")));
        executor.submit(new Downloader(new URL("http://www.google.com")));
        executor.submit(new Downloader(new URL("http://www.google.com")));
        executor.submit(new Downloader(new URL("http://www.google.com")));
        executor.submit(new Downloader(new URL("http://www.google.com")));

        executor.shutdown();
    }
    public static void main(String[] args) throws IOException {

        Program program = new Program();
        program.runIt();

        System.in.read();
    }
}