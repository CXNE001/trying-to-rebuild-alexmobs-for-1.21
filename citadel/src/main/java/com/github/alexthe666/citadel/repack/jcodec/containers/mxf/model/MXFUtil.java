/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model;

import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.MXFMetadata;
import com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model.UL;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MXFUtil {
    public static <T> T resolveRef(List<MXFMetadata> metadata, UL refs, Class<T> class1) {
        List<T> res = MXFUtil.resolveRefs(metadata, new UL[]{refs}, class1);
        return res.size() > 0 ? (T)res.get(0) : null;
    }

    public static <T> List<T> resolveRefs(List<MXFMetadata> metadata, UL[] refs, Class<T> class1) {
        ArrayList<MXFMetadata> copy = new ArrayList<MXFMetadata>(metadata);
        Iterator iterator = copy.iterator();
        while (iterator.hasNext()) {
            MXFMetadata next = (MXFMetadata)iterator.next();
            if (next.getUid() != null && Platform.isAssignableFrom(class1, next.getClass())) continue;
            iterator.remove();
        }
        ArrayList<MXFMetadata> result = new ArrayList<MXFMetadata>();
        for (int i = 0; i < refs.length; ++i) {
            for (MXFMetadata meta : copy) {
                if (!meta.getUid().equals(refs[i])) continue;
                result.add(meta);
            }
        }
        return result;
    }

    public static <T> T findMeta(Collection<MXFMetadata> metadata, Class<T> class1) {
        for (MXFMetadata mxfMetadata : metadata) {
            if (!Platform.isAssignableFrom(mxfMetadata.getClass(), class1)) continue;
            return (T)mxfMetadata;
        }
        return null;
    }

    public static <T> List<T> findAllMeta(Collection<MXFMetadata> metadata, Class<T> class1) {
        ArrayList<MXFMetadata> result = new ArrayList<MXFMetadata>();
        for (MXFMetadata mxfMetadata : metadata) {
            if (!Platform.isAssignableFrom(class1, mxfMetadata.getClass())) continue;
            result.add(mxfMetadata);
        }
        return result;
    }
}
