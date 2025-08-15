/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode.aso;

public class SliceGroupMapBuilder {
    public static int[] buildInterleavedMap(int picWidthInMbs, int picHeightInMbs, int[] runLength) {
        int numSliceGroups = runLength.length;
        int picSizeInMbs = picWidthInMbs * picHeightInMbs;
        int[] groups = new int[picSizeInMbs];
        int i = 0;
        do {
            int iGroup = 0;
            while (iGroup < numSliceGroups && i < picSizeInMbs) {
                for (int j = 0; j < runLength[iGroup] && i + j < picSizeInMbs; ++j) {
                    groups[i + j] = iGroup;
                }
                i += runLength[iGroup++];
            }
        } while (i < picSizeInMbs);
        return groups;
    }

    public static int[] buildDispersedMap(int picWidthInMbs, int picHeightInMbs, int numSliceGroups) {
        int picSizeInMbs = picWidthInMbs * picHeightInMbs;
        int[] groups = new int[picSizeInMbs];
        for (int i = 0; i < picSizeInMbs; ++i) {
            int group;
            groups[i] = group = (i % picWidthInMbs + i / picWidthInMbs * numSliceGroups / 2) % numSliceGroups;
        }
        return groups;
    }

    public static int[] buildForegroundMap(int picWidthInMbs, int picHeightInMbs, int numSliceGroups, int[] topLeftAddr, int[] bottomRightAddr) {
        int picSizeInMbs = picWidthInMbs * picHeightInMbs;
        int[] groups = new int[picSizeInMbs];
        for (int i = 0; i < picSizeInMbs; ++i) {
            groups[i] = numSliceGroups - 1;
        }
        int tot = 0;
        for (int iGroup = numSliceGroups - 2; iGroup >= 0; --iGroup) {
            int yTopLeft = topLeftAddr[iGroup] / picWidthInMbs;
            int xTopLeft = topLeftAddr[iGroup] % picWidthInMbs;
            int yBottomRight = bottomRightAddr[iGroup] / picWidthInMbs;
            int xBottomRight = bottomRightAddr[iGroup] % picWidthInMbs;
            int sz = (yBottomRight - yTopLeft + 1) * (xBottomRight - xTopLeft + 1);
            tot += sz;
            boolean ind = false;
            for (int y = yTopLeft; y <= yBottomRight; ++y) {
                for (int x = xTopLeft; x <= xBottomRight; ++x) {
                    int mbAddr = y * picWidthInMbs + x;
                    groups[mbAddr] = iGroup;
                }
            }
        }
        return groups;
    }

    public static int[] buildBoxOutMap(int picWidthInMbs, int picHeightInMbs, boolean changeDirection, int numberOfMbsInBox) {
        int picSizeInMbs = picWidthInMbs * picHeightInMbs;
        int[] groups = new int[picSizeInMbs];
        int changeDirectionInt = changeDirection ? 1 : 0;
        for (int i = 0; i < picSizeInMbs; ++i) {
            groups[i] = 1;
        }
        int x = (picWidthInMbs - changeDirectionInt) / 2;
        int y = (picHeightInMbs - changeDirectionInt) / 2;
        int leftBound = x;
        int topBound = y;
        int rightBound = x;
        int bottomBound = y;
        int xDir = changeDirectionInt - 1;
        int yDir = changeDirectionInt;
        boolean mapUnitVacant = false;
        for (int k = 0; k < numberOfMbsInBox; k += mapUnitVacant ? 1 : 0) {
            int mbAddr = y * picWidthInMbs + x;
            boolean bl = mapUnitVacant = groups[mbAddr] == 1;
            if (mapUnitVacant) {
                groups[mbAddr] = 0;
            }
            if (xDir == -1 && x == leftBound) {
                x = leftBound = SliceGroupMapBuilder.Max(leftBound - 1, 0);
                xDir = 0;
                yDir = 2 * changeDirectionInt - 1;
                continue;
            }
            if (xDir == 1 && x == rightBound) {
                x = rightBound = SliceGroupMapBuilder.Min(rightBound + 1, picWidthInMbs - 1);
                xDir = 0;
                yDir = 1 - 2 * changeDirectionInt;
                continue;
            }
            if (yDir == -1 && y == topBound) {
                y = topBound = SliceGroupMapBuilder.Max(topBound - 1, 0);
                xDir = 1 - 2 * changeDirectionInt;
                yDir = 0;
                continue;
            }
            if (yDir == 1 && y == bottomBound) {
                y = bottomBound = SliceGroupMapBuilder.Min(bottomBound + 1, picHeightInMbs - 1);
                xDir = 2 * changeDirectionInt - 1;
                yDir = 0;
                continue;
            }
            x += xDir;
            y += yDir;
        }
        return groups;
    }

    private static int Min(int i, int j) {
        return i < j ? i : j;
    }

    private static int Max(int i, int j) {
        return i > j ? i : j;
    }

    public static int[] buildRasterScanMap(int picWidthInMbs, int picHeightInMbs, int sizeOfUpperLeftGroup, boolean changeDirection) {
        int i;
        int picSizeInMbs = picWidthInMbs * picHeightInMbs;
        int[] groups = new int[picSizeInMbs];
        int changeDirectionInt = changeDirection ? 1 : 0;
        for (i = 0; i < sizeOfUpperLeftGroup; ++i) {
            groups[i] = changeDirectionInt;
        }
        while (i < picSizeInMbs) {
            groups[i] = 1 - changeDirectionInt;
            ++i;
        }
        return groups;
    }

    public static int[] buildWipeMap(int picWidthInMbs, int picHeightInMbs, int sizeOfUpperLeftGroup, boolean changeDirection) {
        int picSizeInMbs = picWidthInMbs * picHeightInMbs;
        int[] groups = new int[picSizeInMbs];
        int changeDirectionInt = changeDirection ? 1 : 0;
        int k = 0;
        for (int j = 0; j < picWidthInMbs; ++j) {
            for (int i = 0; i < picHeightInMbs; ++i) {
                int mbAddr = i * picWidthInMbs + j;
                groups[mbAddr] = k++ < sizeOfUpperLeftGroup ? changeDirectionInt : 1 - changeDirectionInt;
            }
        }
        return groups;
    }
}
