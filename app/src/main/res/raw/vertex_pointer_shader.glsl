#version 300 es
layout (location = 0) in vec4 vPosition;
layout (location = 1) in float vAlpha; //对应点的 alpha值.
out vec4 aColor;
void main() {
    gl_Position = vPosition;
    gl_PointSize = 10.0;
    aColor = vec4(1., 1., 1., 1.) * vAlpha;
}