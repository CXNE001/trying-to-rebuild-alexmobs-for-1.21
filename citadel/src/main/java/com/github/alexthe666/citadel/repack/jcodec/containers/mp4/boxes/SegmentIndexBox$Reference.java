/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes;

public static class SegmentIndexBox.Reference {
    public boolean reference_type;
    public long referenced_size;
    public long subsegment_duration;
    public boolean starts_with_SAP;
    public int SAP_type;
    public long SAP_delta_time;

    public String toString() {
        return "Reference [reference_type=" + this.reference_type + ", referenced_size=" + this.referenced_size + ", subsegment_duration=" + this.subsegment_duration + ", starts_with_SAP=" + this.starts_with_SAP + ", SAP_type=" + this.SAP_type + ", SAP_delta_time=" + this.SAP_delta_time + "]";
    }
}
