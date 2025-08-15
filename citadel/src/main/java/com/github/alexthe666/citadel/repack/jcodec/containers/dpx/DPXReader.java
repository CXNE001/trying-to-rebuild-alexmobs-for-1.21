/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.dpx;

import com.github.alexthe666.citadel.repack.jcodec.common.StringUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.FileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.io.IOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.containers.dpx.DPXMetadata;
import com.github.alexthe666.citadel.repack.jcodec.containers.dpx.FileHeader;
import com.github.alexthe666.citadel.repack.jcodec.containers.dpx.FilmHeader;
import com.github.alexthe666.citadel.repack.jcodec.containers.dpx.ImageElement;
import com.github.alexthe666.citadel.repack.jcodec.containers.dpx.ImageHeader;
import com.github.alexthe666.citadel.repack.jcodec.containers.dpx.ImageSourceHeader;
import com.github.alexthe666.citadel.repack.jcodec.containers.dpx.TelevisionHeader;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ReadableByteChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DPXReader {
    private static final int READ_BUFFER_SIZE = 3072;
    static final int IMAGEINFO_OFFSET = 768;
    static final int IMAGESOURCE_OFFSET = 1408;
    static final int FILM_OFFSET = 1664;
    static final int TVINFO_OFFSET = 1920;
    public static final int SDPX = 1396985944;
    private final ByteBuffer readBuf = ByteBuffer.allocate(3072);
    private final int magic;
    private boolean eof;

    public DPXReader(SeekableByteChannel ch) throws IOException {
        this.initialRead(ch);
        this.magic = this.readBuf.getInt();
        if (this.magic == 1396985944) {
            this.readBuf.order(ByteOrder.BIG_ENDIAN);
        } else {
            this.readBuf.order(ByteOrder.LITTLE_ENDIAN);
        }
    }

    public DPXMetadata parseMetadata() {
        DPXMetadata dpx = new DPXMetadata();
        dpx.file = DPXReader.readFileInfo(this.readBuf);
        dpx.file.magic = this.magic;
        this.readBuf.position(768);
        dpx.image = DPXReader.readImageInfoHeader(this.readBuf);
        this.readBuf.position(1408);
        dpx.imageSource = DPXReader.readImageSourceHeader(this.readBuf);
        this.readBuf.position(1664);
        dpx.film = DPXReader.readFilmInformationHeader(this.readBuf);
        this.readBuf.position(1920);
        dpx.television = DPXReader.readTelevisionInfoHeader(this.readBuf);
        dpx.userId = DPXReader.readNullTermString(this.readBuf, 32);
        return dpx;
    }

    private void initialRead(ReadableByteChannel ch) throws IOException {
        this.readBuf.clear();
        if (ch.read(this.readBuf) == -1) {
            this.eof = true;
        }
        this.readBuf.flip();
    }

    private static FileHeader readFileInfo(ByteBuffer bb) {
        FileHeader h = new FileHeader();
        h.imageOffset = bb.getInt();
        h.version = DPXReader.readNullTermString(bb, 8);
        h.filesize = bb.getInt();
        h.ditto = bb.getInt();
        h.genericHeaderLength = bb.getInt();
        h.industryHeaderLength = bb.getInt();
        h.userHeaderLength = bb.getInt();
        h.filename = DPXReader.readNullTermString(bb, 100);
        h.created = DPXReader.tryParseISO8601Date(DPXReader.readNullTermString(bb, 24));
        h.creator = DPXReader.readNullTermString(bb, 100);
        h.projectName = DPXReader.readNullTermString(bb, 200);
        h.copyright = DPXReader.readNullTermString(bb, 200);
        h.encKey = bb.getInt();
        return h;
    }

    static Date tryParseISO8601Date(String dateString) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        String noTZ = "yyyy:MM:dd:HH:mm:ss";
        if (dateString.length() == noTZ.length()) {
            return DPXReader.date(dateString, noTZ);
        }
        if (dateString.length() == noTZ.length() + 4) {
            dateString = dateString + "00";
        }
        return DPXReader.date(dateString, "yyyy:MM:dd:HH:mm:ss:Z");
    }

    private static Date date(String dateString, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.US);
        try {
            return format.parse(dateString);
        }
        catch (ParseException e) {
            return null;
        }
    }

    private static String readNullTermString(ByteBuffer bb, int length) {
        ByteBuffer b = ByteBuffer.allocate(length);
        bb.get(b.array(), 0, length);
        return NIOUtils.readNullTermString(b);
    }

    public static DPXReader readFile(File file) throws IOException {
        FileChannelWrapper _in = NIOUtils.readableChannel(file);
        try {
            DPXReader dPXReader = new DPXReader(_in);
            return dPXReader;
        }
        finally {
            IOUtils.closeQuietly(_in);
        }
    }

    private static TelevisionHeader readTelevisionInfoHeader(ByteBuffer r) {
        TelevisionHeader h = new TelevisionHeader();
        h.timecode = r.getInt();
        h.userBits = r.getInt();
        h.interlace = r.get();
        h.filedNumber = r.get();
        h.videoSignalStarted = r.get();
        h.zero = r.get();
        h.horSamplingRateHz = r.getInt();
        h.vertSampleRateHz = r.getInt();
        h.frameRate = r.getInt();
        h.timeOffset = r.getInt();
        h.gamma = r.getInt();
        h.blackLevel = r.getInt();
        h.blackGain = r.getInt();
        h.breakpoint = r.getInt();
        h.referenceWhiteLevel = r.getInt();
        h.integrationTime = r.getInt();
        return h;
    }

    private static FilmHeader readFilmInformationHeader(ByteBuffer r) {
        FilmHeader h = new FilmHeader();
        h.idCode = DPXReader.readNullTermString(r, 2);
        h.type = DPXReader.readNullTermString(r, 2);
        h.offset = DPXReader.readNullTermString(r, 2);
        h.prefix = DPXReader.readNullTermString(r, 6);
        h.count = DPXReader.readNullTermString(r, 4);
        h.format = DPXReader.readNullTermString(r, 32);
        return h;
    }

    private static ImageSourceHeader readImageSourceHeader(ByteBuffer r) {
        ImageSourceHeader h = new ImageSourceHeader();
        h.xOffset = r.getInt();
        h.yOffset = r.getInt();
        h.xCenter = r.getFloat();
        h.yCenter = r.getFloat();
        h.xOriginal = r.getInt();
        h.yOriginal = r.getInt();
        h.sourceImageFilename = DPXReader.readNullTermString(r, 100);
        h.sourceImageDate = DPXReader.tryParseISO8601Date(DPXReader.readNullTermString(r, 24));
        h.deviceName = DPXReader.readNullTermString(r, 32);
        h.deviceSerial = DPXReader.readNullTermString(r, 32);
        h.borderValidity = new short[]{r.getShort(), r.getShort(), r.getShort(), r.getShort()};
        h.aspectRatio = new int[]{r.getInt(), r.getInt()};
        return h;
    }

    private static ImageHeader readImageInfoHeader(ByteBuffer r) {
        ImageHeader h = new ImageHeader();
        h.orientation = r.getShort();
        h.numberOfImageElements = r.getShort();
        h.pixelsPerLine = r.getInt();
        h.linesPerImageElement = r.getInt();
        h.imageElement1 = new ImageElement();
        h.imageElement1.dataSign = r.getInt();
        return h;
    }
}
