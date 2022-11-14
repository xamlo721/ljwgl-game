package com.xamlo.engine.world;

public class ScalableChunk {
	
	private int level;
	
	
	/*
	 * масштабируемый чанк
	 * Ниже него только настощие чанки с вокселямис
	 */
	public ScalableChunk(int level, Chunk[] childrens) {
		
		if (level != 0) {
			//throw;
		}
		
		if (childrens.length != 8) { //2^3
			//throw;
		}
		
		this.level = level;
		
	}
	
	public ScalableChunk(int level, ScalableChunk[] childrens) {
		
		this.level = level;
	
		//throw;
	}
	
}
