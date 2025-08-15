/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

import com.github.alexthe666.citadel.repack.jcodec.common.JCodecUtil2;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.FullBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class AliasBox
extends FullBox {
    public static final int DirectoryName = 0;
    public static final int DirectoryIDs = 1;
    public static final int AbsolutePath = 2;
    public static final int AppleShareZoneName = 3;
    public static final int AppleShareServerName = 4;
    public static final int AppleShareUserName = 5;
    public static final int DriverName = 6;
    public static final int RevisedAppleShare = 9;
    public static final int AppleRemoteAccessDialup = 10;
    public static final int UNIXAbsolutePath = 18;
    public static final int UTF16AbsolutePath = 14;
    public static final int UFT16VolumeName = 15;
    public static final int VolumeMountPoint = 19;
    private String type;
    private short recordSize;
    private short version;
    private short kind;
    private String volumeName;
    private int volumeCreateDate;
    private short volumeSignature;
    private short volumeType;
    private int parentDirId;
    private String fileName;
    private int fileNumber;
    private int createdLocalDate;
    private String fileTypeName;
    private String creatorName;
    private short nlvlFrom;
    private short nlvlTo;
    private int volumeAttributes;
    private short fsId;
    private List<ExtraField> extra;

    public static String fourcc() {
        return "alis";
    }

    public AliasBox(Header atom) {
        super(atom);
    }

    @Override
    public void parse(ByteBuffer is) {
        short len;
        byte[] bs;
        short type;
        super.parse(is);
        if ((this.flags & 1) != 0) {
            return;
        }
        this.type = NIOUtils.readString(is, 4);
        this.recordSize = is.getShort();
        this.version = is.getShort();
        this.kind = is.getShort();
        this.volumeName = NIOUtils.readPascalStringL(is, 27);
        this.volumeCreateDate = is.getInt();
        this.volumeSignature = is.getShort();
        this.volumeType = is.getShort();
        this.parentDirId = is.getInt();
        this.fileName = NIOUtils.readPascalStringL(is, 63);
        this.fileNumber = is.getInt();
        this.createdLocalDate = is.getInt();
        this.fileTypeName = NIOUtils.readString(is, 4);
        this.creatorName = NIOUtils.readString(is, 4);
        this.nlvlFrom = is.getShort();
        this.nlvlTo = is.getShort();
        this.volumeAttributes = is.getInt();
        this.fsId = is.getShort();
        NIOUtils.skip(is, 10);
        this.extra = new ArrayList<ExtraField>();
        while ((type = is.getShort()) != -1 && (bs = NIOUtils.toArray(NIOUtils.read(is, (len = is.getShort()) + 1 & 0xFFFFFFFE))) != null) {
            this.extra.add(new ExtraField(type, len, bs));
        }
    }

    @Override
    protected void doWrite(ByteBuffer out) {
        super.doWrite(out);
        if ((this.flags & 1) != 0) {
            return;
        }
        out.put(JCodecUtil2.asciiString(this.type), 0, 4);
        out.putShort(this.recordSize);
        out.putShort(this.version);
        out.putShort(this.kind);
        NIOUtils.writePascalStringL(out, this.volumeName, 27);
        out.putInt(this.volumeCreateDate);
        out.putShort(this.volumeSignature);
        out.putShort(this.volumeType);
        out.putInt(this.parentDirId);
        NIOUtils.writePascalStringL(out, this.fileName, 63);
        out.putInt(this.fileNumber);
        out.putInt(this.createdLocalDate);
        out.put(JCodecUtil2.asciiString(this.fileTypeName), 0, 4);
        out.put(JCodecUtil2.asciiString(this.creatorName), 0, 4);
        out.putShort(this.nlvlFrom);
        out.putShort(this.nlvlTo);
        out.putInt(this.volumeAttributes);
        out.putShort(this.fsId);
        out.put(new byte[10]);
        for (ExtraField extraField : this.extra) {
            out.putShort(extraField.type);
            out.putShort((short)extraField.len);
            out.put(extraField.data);
        }
        out.putShort((short)-1);
        out.putShort((short)0);
    }

    @Override
    public int estimateSize() {
        int sz = 166;
        if ((this.flags & 1) == 0) {
            for (ExtraField extraField : this.extra) {
                sz += 4 + extraField.data.length;
            }
        }
        return 12 + sz;
    }

    public int getRecordSize() {
        return this.recordSize;
    }

    public String getFileName() {
        return this.fileName;
    }

    public List<ExtraField> getExtras() {
        return this.extra;
    }

    public ExtraField getExtra(int type) {
        for (ExtraField extraField : this.extra) {
            if (extraField.type != type) continue;
            return extraField;
        }
        return null;
    }

    public boolean isSelfRef() {
        return (this.flags & 1) != 0;
    }

    public static AliasBox createSelfRef() {
        AliasBox alis = new AliasBox(new Header(AliasBox.fourcc()));
        alis.setFlags(1);
        return alis;
    }

    public String getUnixPath() {
        ExtraField extraField = this.getExtra(18);
        return extraField == null ? null : "/" + extraField.toString();
    }

    public static class ExtraField {
        short type;
        int len;
        byte[] data;

        public ExtraField(short type, int len, byte[] bs) {
            this.type = type;
            this.len = len;
            this.data = bs;
        }

        public String toString() {
            return Platform.stringFromCharset4(this.data, 0, this.len, this.type == 14 || this.type == 15 ? "UTF-16" : "UTF-8");
        }
    }
}
