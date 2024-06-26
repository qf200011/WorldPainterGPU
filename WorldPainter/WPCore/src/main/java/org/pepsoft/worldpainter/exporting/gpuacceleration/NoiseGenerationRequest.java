package org.pepsoft.worldpainter.exporting.gpuacceleration;

import org.pepsoft.worldpainter.exporting.NoiseHardwareAccelerator;
import org.pepsoft.worldpainter.exporting.NoiseHardwareAcceleratorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NoiseGenerationRequest {

    private static final Logger log = LoggerFactory.getLogger(NoiseGenerationRequest.class);
    //request info
    private final long seed;
    private final int regionX;
    private final int regionY; //y direction on a map, not in game
    private final int minHeight;
    private final int maxHeight;
    private final int heightOffset;
    private final float blobSize;

    private final GPUOptimizable gpuOptimizable;

    public static final int HEIGHT_SIZE=32;


    public NoiseGenerationRequest(long seed, int regionX, int regionY, int minHeight, int maxHeight, int heightOffset, float blobSize, GPUOptimizable gpuOptimizable) {
        this.seed = seed;
        this.regionX = regionX;
        this.regionY = regionY;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.heightOffset=heightOffset;
        this.blobSize=blobSize;
        this.gpuOptimizable=gpuOptimizable;
    }

    public abstract NoiseHardwareAcceleratorResponse getRegionNoiseData(NoiseHardwareAccelerator.GPUNoiseRequest request);

    public long getSeed() {
        return seed;
    }

    public int getRegionX() {
        return regionX;
    }

    public int getRegionY() {
        return regionY;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getHeightOffset() {
        return heightOffset;
    }

    public float getBlobSize() {
        return blobSize;
    }

    public void executeCallback(long threadId, int processId, int[] outputIndexes){
        gpuOptimizable.computeCallBack(threadId,processId,outputIndexes);
    }
}
