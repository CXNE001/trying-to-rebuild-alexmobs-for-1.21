/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.aso;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.aso.FlatMBlockMapper;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.aso.MBToSliceGroupMap;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.aso.Mapper;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.aso.PrebuiltMBlockMapper;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.aso.SliceGroupMapBuilder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.PictureParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SeqParameterSet;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SliceHeader;

public class MapManager {
    private SeqParameterSet sps;
    private PictureParameterSet pps;
    private MBToSliceGroupMap mbToSliceGroupMap;
    private int prevSliceGroupChangeCycle;

    public MapManager(SeqParameterSet sps, PictureParameterSet pps) {
        this.sps = sps;
        this.pps = pps;
        this.mbToSliceGroupMap = this.buildMap(sps, pps);
    }

    private MBToSliceGroupMap buildMap(SeqParameterSet sps, PictureParameterSet pps) {
        int numGroups = pps.numSliceGroupsMinus1 + 1;
        if (numGroups > 1) {
            int[] map;
            int picWidthInMbs = sps.picWidthInMbsMinus1 + 1;
            int picHeightInMbs = SeqParameterSet.getPicHeightInMbs(sps);
            if (pps.sliceGroupMapType == 0) {
                int[] runLength = new int[numGroups];
                for (int i = 0; i < numGroups; ++i) {
                    runLength[i] = pps.runLengthMinus1[i] + 1;
                }
                map = SliceGroupMapBuilder.buildInterleavedMap(picWidthInMbs, picHeightInMbs, runLength);
            } else if (pps.sliceGroupMapType == 1) {
                map = SliceGroupMapBuilder.buildDispersedMap(picWidthInMbs, picHeightInMbs, numGroups);
            } else if (pps.sliceGroupMapType == 2) {
                map = SliceGroupMapBuilder.buildForegroundMap(picWidthInMbs, picHeightInMbs, numGroups, pps.topLeft, pps.bottomRight);
            } else {
                if (pps.sliceGroupMapType >= 3 && pps.sliceGroupMapType <= 5) {
                    return null;
                }
                if (pps.sliceGroupMapType == 6) {
                    map = pps.sliceGroupId;
                } else {
                    throw new RuntimeException("Unsupported slice group map type");
                }
            }
            return this.buildMapIndices(map, numGroups);
        }
        return null;
    }

    private MBToSliceGroupMap buildMapIndices(int[] map, int numGroups) {
        int i;
        int[] ind = new int[numGroups];
        int[] indices = new int[map.length];
        for (int i2 = 0; i2 < map.length; ++i2) {
            int n = map[i2];
            ind[n] = ind[n] + 1;
        }
        int[][] inverse = new int[numGroups][];
        for (i = 0; i < numGroups; ++i) {
            inverse[i] = new int[ind[i]];
        }
        ind = new int[numGroups];
        i = 0;
        while (i < map.length) {
            int sliceGroup;
            int n = sliceGroup = map[i];
            int n2 = ind[n];
            ind[n] = n2 + 1;
            inverse[sliceGroup][n2] = i++;
        }
        return new MBToSliceGroupMap(map, indices, inverse);
    }

    private void updateMap(SliceHeader sh) {
        int mapType = this.pps.sliceGroupMapType;
        int numGroups = this.pps.numSliceGroupsMinus1 + 1;
        if (numGroups > 1 && mapType >= 3 && mapType <= 5 && (sh.sliceGroupChangeCycle != this.prevSliceGroupChangeCycle || this.mbToSliceGroupMap == null)) {
            int sizeOfUpperLeftGroup;
            this.prevSliceGroupChangeCycle = sh.sliceGroupChangeCycle;
            int mapUnitsInSliceGroup0 = sh.sliceGroupChangeCycle * (this.pps.sliceGroupChangeRateMinus1 + 1);
            int picWidthInMbs = this.sps.picWidthInMbsMinus1 + 1;
            int picHeightInMbs = SeqParameterSet.getPicHeightInMbs(this.sps);
            int picSizeInMapUnits = picWidthInMbs * picHeightInMbs;
            mapUnitsInSliceGroup0 = mapUnitsInSliceGroup0 > picSizeInMapUnits ? picSizeInMapUnits : mapUnitsInSliceGroup0;
            int n = sizeOfUpperLeftGroup = this.pps.sliceGroupChangeDirectionFlag ? picSizeInMapUnits - mapUnitsInSliceGroup0 : mapUnitsInSliceGroup0;
            int[] map = mapType == 3 ? SliceGroupMapBuilder.buildBoxOutMap(picWidthInMbs, picHeightInMbs, this.pps.sliceGroupChangeDirectionFlag, mapUnitsInSliceGroup0) : (mapType == 4 ? SliceGroupMapBuilder.buildRasterScanMap(picWidthInMbs, picHeightInMbs, sizeOfUpperLeftGroup, this.pps.sliceGroupChangeDirectionFlag) : SliceGroupMapBuilder.buildWipeMap(picWidthInMbs, picHeightInMbs, sizeOfUpperLeftGroup, this.pps.sliceGroupChangeDirectionFlag));
            this.mbToSliceGroupMap = this.buildMapIndices(map, numGroups);
        }
    }

    public Mapper getMapper(SliceHeader sh) {
        this.updateMap(sh);
        int firstMBInSlice = sh.firstMbInSlice;
        if (this.pps.numSliceGroupsMinus1 > 0) {
            return new PrebuiltMBlockMapper(this.mbToSliceGroupMap, firstMBInSlice, this.sps.picWidthInMbsMinus1 + 1);
        }
        return new FlatMBlockMapper(this.sps.picWidthInMbsMinus1 + 1, firstMBInSlice);
    }
}
