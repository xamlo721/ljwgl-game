package com.xamlo.core.engine.graphics.api.primitives;

public interface IVertexAttribute {

    int getSize();
    
    int getType();
    
    boolean isNormalized();
    
    int getStride();
    
    long getOffset();
    
}