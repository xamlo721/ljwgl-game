#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 rawColor;

out vec3 inputColor;
uniform mat4 projectionMatrix;

void main() {

	gl_Position = vec4(position.x, position.y, position.z, 1.0) * projectionMatrix;
	inputColor = rawColor;


}
