/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.movtool;

import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.Header;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MetaBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.MovieFragmentBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.NodeBox;
import com.github.alexthe666.citadel.repack.jcodec.containers.mp4.boxes.UdtaMetaBox;
import com.github.alexthe666.citadel.repack.jcodec.movtool.MP4Edit;
import com.github.alexthe666.citadel.repack.jcodec.movtool.MetadataEditor;

class MetadataEditor.1
implements MP4Edit {
    final /* synthetic */ MetadataEditor val$self;

    MetadataEditor.1(MetadataEditor metadataEditor) {
        this.val$self = metadataEditor;
    }

    @Override
    public void applyToFragment(MovieBox mov, MovieFragmentBox[] fragmentBox) {
    }

    @Override
    public void apply(MovieBox movie) {
        MetaBox meta1 = NodeBox.findFirst(movie, MetaBox.class, MetaBox.fourcc());
        MetaBox meta2 = NodeBox.findFirstPath(movie, MetaBox.class, new String[]{"udta", MetaBox.fourcc()});
        if (this.val$self.keyedMeta != null && this.val$self.keyedMeta.size() > 0) {
            if (meta1 == null) {
                meta1 = MetaBox.createMetaBox();
                movie.add(meta1);
            }
            meta1.setKeyedMeta(this.val$self.keyedMeta);
        }
        if (this.val$self.itunesMeta != null && this.val$self.itunesMeta.size() > 0) {
            if (meta2 == null) {
                meta2 = UdtaMetaBox.createUdtaMetaBox();
                NodeBox udta = NodeBox.findFirst(movie, NodeBox.class, "udta");
                if (udta == null) {
                    udta = new NodeBox(Header.createHeader("udta", 0L));
                    movie.add(udta);
                }
                udta.add(meta2);
            }
            meta2.setItunesMeta(this.val$self.itunesMeta);
        }
    }
}
