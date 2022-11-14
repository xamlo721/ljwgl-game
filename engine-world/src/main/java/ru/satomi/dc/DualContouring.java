package ru.satomi.dc;

import ru.satomi.dc.primitive.Face3i;
import ru.satomi.dc.primitive.Face4i;
import ru.satomi.dc.primitive.Point3d;
import ru.satomi.dc.primitive.Point4d;
import ru.satomi.dc.primitive.QuadMesh;
import ru.satomi.dc.primitive.TriandgleMesh;

public class DualContouring {
	
    public int gX;
    public int gY;
    public int gZ;
    
    public Point3d minP;
    public Point3d maxP;
    
    public DualContouring(int x, int y, int z, Point3d min, Point3d max) {
    	this.gX = x + 2;
    	this.gY = y + 2;
    	this.gZ = z + 2;
    	this.minP = min;
    	this.maxP = max;
	}
    
    public double[][][] sample(Function f) {
    	
    	return DCUtils.sample(f, minP, maxP, gX, gY, gZ);
    }
    
    public QuadMesh generateQuadMesh(Function f, double[][][] value) {
    	

    	FilledCube filledCube = fillCube(value);
				
    	QuadMesh mesh = new QuadMesh(filledCube.vertexNumber);
    	
		double iX = (maxP.x-minP.x)/(gX-1);
		double iY = (maxP.y-minP.y)/(gY-1);
		double iZ = (maxP.z-minP.z)/(gZ-1);
		
		//Cube in x direction
		for(int i = 1; i < gZ-1; i++) {
			
		    double z = minP.z + i*iZ;
		    
		    for(int j = 1; j < gY-1; j++) {
		    	
				double y = minP.y + j*iY;
				
				for(int k = 1; k < gX-2; k++) {
					
				    double v1 = value[i][j][k];
				    double v2 = value[i][j][k+1];
				    
				    //Если 2 красные точки соединены, мы рисуем красную линию
				    if(v1 * v2 < 0) {
				    	
						double x1 = minP.x + k*iX;
						double x2 = minP.x + (k+1)*iX;
			
						//point

						Point3d redPoint = DCUtils.root(f, new Point4d(x1, y, z, v1), new Point4d(x2, y, z, v2), minP, maxP);
						Point3d normaleOfRedPoint =  DCUtils.grad(f, redPoint, minP, maxP);
						
						double len = Math.sqrt(normaleOfRedPoint.x*normaleOfRedPoint.x + normaleOfRedPoint.y*normaleOfRedPoint.y + normaleOfRedPoint.z*normaleOfRedPoint.z);    
						
						if((double)len == 0) {
						    continue;
						}
						
						normaleOfRedPoint.x = (double)(normaleOfRedPoint.x/len);
						normaleOfRedPoint.y = (double)(normaleOfRedPoint.y/len);
						normaleOfRedPoint.z = (double)(normaleOfRedPoint.z/len);
			
						int i1 = filledCube.arrayData[i-1][j-1][k];
						int i2 = filledCube.arrayData[i-1][j]  [k];
						int i3 = filledCube.arrayData[i]  [j-1][k];
						int i4 = filledCube.arrayData[i]  [j]  [k];
			            
						if(i1 < 0 || i2 < 0 || i3 < 0 || i4 < 0){
						    System.out.println("Error: found a strange edge?");
						    continue;
						}
			
						if(v1 > v2) {
						    mesh.addFace(new Face4i(i1, i2, i4, i3, normaleOfRedPoint));
						} else {
						    mesh.addFace(new Face4i(i2, i1, i3, i4, normaleOfRedPoint));
						}
						mesh.degree[i1]++;
						mesh.degree[i2]++;
						mesh.degree[i3]++;
						mesh.degree[i4]++;
			            
						mesh.vertexs.get(i1).append(redPoint);
						mesh.vertexs.get(i2).append(redPoint);
						mesh.vertexs.get(i3).append(redPoint);
						mesh.vertexs.get(i4).append(redPoint);

				    }
				}
		    }
		}
	
		//Cube in y direction
		for(int i=1; i<gX-1; i++) {
			
		    double x = minP.x + i*iX;
		    
		    for(int j=1; j<gZ-1; j++) {
		    	
				double z = minP.z + j*iZ;
				
				for(int k=1; k<gY-2; k++) {
					
				    double v1 = value[j][k][i];
				    double v2 = value[j][k+1][i];
				    
				    if(v1*v2 < 0) {
				    	
						double y1 = minP.y + k*iY;
						double y2 = minP.y + (k+1)*iY;
			            
						
						Point3d redPoint = DCUtils.root(f, new Point4d(x, y1, z, v1), new Point4d(x, y2, z, v2), minP, maxP);
						Point3d normaleOfRedPoint = DCUtils.grad(f, redPoint, minP, maxP);
						
						double len = Math.sqrt(normaleOfRedPoint.x*normaleOfRedPoint.x + normaleOfRedPoint.y*normaleOfRedPoint.y + normaleOfRedPoint.z*normaleOfRedPoint.z);
						
						if((double)len == 0) {
						    continue;
						}
						
						normaleOfRedPoint.x = (double)(normaleOfRedPoint.x/len);
						normaleOfRedPoint.y = (double)(normaleOfRedPoint.y/len);
						normaleOfRedPoint.z = (double)(normaleOfRedPoint.z/len);
			            
						int i1 = filledCube.arrayData[j-1][k]  [i-1];
						int i2 = filledCube.arrayData[j]  [k]  [i-1];
						int i3 = filledCube.arrayData[j-1][k]  [i];
						int i4 = filledCube.arrayData[j]  [k]  [i];
			
						if(i1 < 0 || i2 < 0 || i3 < 0 || i4 < 0){
						    System.out.println( "Error: found a strange edge?");
						    continue;
						}
			
						if(v1 > v2)
						    mesh.addFace(new Face4i(i1, i2, i4, i3, normaleOfRedPoint));
						else
						    mesh.addFace(new Face4i(i2, i1, i3, i4, normaleOfRedPoint));
			            
						mesh.degree[i1]++;
						mesh.degree[i2]++;
						mesh.degree[i3]++;
						mesh.degree[i4]++;
			            
						mesh.vertexs.get(i1).append(redPoint);
						mesh.vertexs.get(i2).append(redPoint);
						mesh.vertexs.get(i3).append(redPoint);
						mesh.vertexs.get(i4).append(redPoint);
				    }
				}
		    }
		}
	    
		//Cube in z direction
		for(int i=1; i<gY-1; i++) {
			
		    double y = minP.y + i*iY;
		    
		    for(int j=1; j<gX-1; j++) {
		    	
				double x = minP.x + j*iX;
				
				for(int k=1; k<gZ-2; k++) {
					
				    double v1 = value[k][i][j];
				    double v2 = value[k+1][i][j];
				    
				    if(v1*v2 < 0) {
				    	
						double z1 = minP.z + k*iZ;
						double z2 = minP.z + (k+1)*iZ;
			            

						Point3d redPoint = DCUtils.root(f, new Point4d(x, y, z1, v1), new Point4d( x, y, z2, v2), minP, maxP);
						Point3d faceNormale = DCUtils.grad(f, redPoint, minP, maxP);
						double len = Math.sqrt(faceNormale.x*faceNormale.x + faceNormale.y*faceNormale.y + faceNormale.z*faceNormale.z);
						
						if((double)len == 0) {
						    continue;
				    	}
						
						//Нормировать
						faceNormale.x = (double)(faceNormale.x/len);
						faceNormale.y = (double)(faceNormale.y/len);
						faceNormale.z = (double)(faceNormale.z/len);
			            
						int i1 = filledCube.arrayData[k][i-1][j-1];
						int i2 = filledCube.arrayData[k][i-1][j];
						int i3 = filledCube.arrayData[k][i]  [j-1];
						int i4 = filledCube.arrayData[k][i]  [j];
			            
			
						if(i1 < 0 || i2 < 0 || i3 < 0 || i4 < 0) {
						    System.out.println("found a strange vertex");
						    continue;
						}
			         
						if(v1 > v2) {
						    mesh.addFace(new Face4i(i1, i2, i4, i3, faceNormale));
						} else {
						    mesh.addFace(new Face4i(i2, i1, i3, i4, faceNormale));
						}
						
						mesh.degree[i1]++;
						mesh.degree[i2]++;
						mesh.degree[i3]++;
						mesh.degree[i4]++;
			            
						mesh.vertexs.get(i1).append(redPoint);
						mesh.vertexs.get(i2).append(redPoint);
						mesh.vertexs.get(i3).append(redPoint);
						mesh.vertexs.get(i4).append(redPoint);
				    }
				}
		    }
		}
		
		//Станная процедура
		for(int i=0; i < mesh.vertexs.size(); i++) {
			
		    if(mesh.degree[i] == 0) {
		    	continue;
		    }
		    
		    double ideg = 1.0f/mesh.degree[i];
		    
		    //centroid of edge points
		    mesh.vertexs.get(i).x *= ideg;
		    mesh.vertexs.get(i).y *= ideg;
		    mesh.vertexs.get(i).z *= ideg;
		
		}
		
		return mesh;
    }

	private FilledCube fillCube(double[][][] value) {
		
		return DCUtils.fillCube(value, gX, gY, gZ);
	}

	public TriandgleMesh generateTriangleMesh(QuadMesh mesh) {
		
		int i;
		
		int fN = mesh.faces.size();
		
		Face3i new_face[] = new Face3i[2*fN];
		
		for(i=0; i < fN; i++) {
			
			Face4i face = mesh.faces.get(i);
		    int i1 = face.vertexIndex1;
		    int i2 = face.vertexIndex2;
		    int i3 = face.vertexIndex3;
		    int i4 = face.vertexIndex4;
	      
		    double min1, min2;
		    
		    //cut along (i1,i3)
		    double a1 = DCUtils.angle(mesh.vertexs.get(i1), mesh.vertexs.get(i2), mesh.vertexs.get(i3));
		    double a2 = DCUtils.angle(mesh.vertexs.get(i3), mesh.vertexs.get(i4), mesh.vertexs.get(i1));
		    
		    if(a1 < a2) {
		    	min1 = a1;
		    } else {
		    	min1 = a2;
		    }
		    
		    //cut along (i2,i3)
		    a1 = DCUtils.angle(mesh.vertexs.get(i1), mesh.vertexs.get(i2), mesh.vertexs.get(i4));
		    a2 = DCUtils.angle(mesh.vertexs.get(i3), mesh.vertexs.get(i4), mesh.vertexs.get(i2));
		    
		    if(a1 < a2) {
		    	min2 = a1;
		    } else {
		    	min2 = a2;
		    }
		    
		    if(min1 > min2){
		    	
				new_face[2*i] = new Face3i(i1, i2, i3);
				new_face[2*i+1] = new Face3i(i3, i4, i1);

		    } else{
				new_face[2*i] = new Face3i(i1, i2, i4);
				new_face[2*i+1] = new Face3i(i3, i4, i2);

		    }
		}
	    
		//Construct output mesh
		fN *= 2;
	
		// creates a mesh of triangles for output: ply2, povray, stl
		TriandgleMesh t_mesh = new TriandgleMesh(mesh.vertex_N);
		t_mesh.setFaceN(fN);
		
		for(i=0;i<mesh.vertex_N;i++){
		    t_mesh.vertexs.add(i, mesh.vertexs.get(i));
		}
		
		for(i=0;i<fN;i++){
		    t_mesh.faces[i]=new_face[i];
		}
		return t_mesh;
	}
    

}
