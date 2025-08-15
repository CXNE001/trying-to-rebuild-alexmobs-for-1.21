/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad;

import com.github.alexthe666.citadel.repack.jaad.aac.Decoder;
import com.github.alexthe666.citadel.repack.jaad.aac.SampleBuffer;
import com.github.alexthe666.citadel.repack.jaad.adts.ADTSDemultiplexer;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URI;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;

public class Radio {
    private static final String USAGE = "usage:\nnet.sourceforge.jaad.Radio <url>";

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                Radio.printUsage();
            } else {
                Radio.decode(args[0]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("error while decoding: " + e.toString());
        }
    }

    private static void printUsage() {
        System.out.println(USAGE);
        System.exit(1);
    }

    private static void decode(String arg) throws Exception {
        SampleBuffer buf = new SampleBuffer();
        DataLine line = null;
        try {
            String x;
            URI uri = new URI(arg);
            Socket sock = new Socket(uri.getHost(), uri.getPort() > 0 ? uri.getPort() : 80);
            PrintStream out = new PrintStream(sock.getOutputStream());
            Object path = uri.getPath();
            if (path == null || ((String)path).equals("")) {
                path = "/";
            }
            if (uri.getQuery() != null) {
                path = (String)path + "?" + uri.getQuery();
            }
            out.println("GET " + (String)path + " HTTP/1.1");
            out.println("Host: " + uri.getHost());
            out.println();
            DataInputStream in = new DataInputStream(sock.getInputStream());
            while ((x = in.readLine()) != null && !x.trim().equals("")) {
            }
            ADTSDemultiplexer adts = new ADTSDemultiplexer(in);
            AudioFormat aufmt = new AudioFormat(adts.getSampleFrequency(), 16, adts.getChannelCount(), true, true);
            Decoder dec = new Decoder(adts.getDecoderSpecificInfo());
            while (true) {
                byte[] b = adts.readNextFrame();
                dec.decodeFrame(b, buf);
                if (line != null && Radio.formatChanged(line.getFormat(), buf)) {
                    line.stop();
                    line.close();
                    line = null;
                    aufmt = new AudioFormat(buf.getSampleRate(), buf.getBitsPerSample(), buf.getChannels(), true, true);
                }
                if (line == null) {
                    line = AudioSystem.getSourceDataLine(aufmt);
                    line.open();
                    line.start();
                }
                b = buf.getData();
                line.write(b, 0, b.length);
            }
        }
        catch (Throwable throwable) {
            if (line != null) {
                line.stop();
                line.close();
            }
            throw throwable;
        }
    }

    private static boolean formatChanged(AudioFormat af, SampleBuffer buf) {
        return af.getSampleRate() != (float)buf.getSampleRate() || af.getChannels() != buf.getChannels() || af.getSampleSizeInBits() != buf.getBitsPerSample() || af.isBigEndian() != buf.isBigEndian();
    }
}
