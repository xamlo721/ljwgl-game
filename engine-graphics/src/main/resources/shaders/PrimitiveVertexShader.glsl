#version 330 core

layout (location = 0) in vec3 position;
//layout (location = 1) in vec3 rawColor;
layout (location = 1) in vec2 texCoord;

//out vec3 inputColor;
out vec2 outTexCoord;

uniform mat4 projectionMatrix;
uniform mat4 worldMatrix;

void main() {

	gl_Position =  projectionMatrix * worldMatrix *vec4(position.x, position.y, position.z, 1.0)  ;
	//inputColor = rawColor;
    outTexCoord = texCoord;


}
