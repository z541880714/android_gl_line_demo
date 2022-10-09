#version 300 es

//surfaceView 对应的尺寸.
uniform vec2 resolution;
//输入进来的坐标值为像素坐标值, 需要转换为 gl  -1, 1 坐标系对应的 值..
layout (location = 0) in vec4 vPosition;
layout (location = 1) in float vAlpha; //对应点的 alpha值.
out vec4 aColor;

void main() {
    gl_Position = vPosition;
    gl_Position.xy = vPosition.xy / resolution * 2. - 1.;
    gl_Position.y *= -1.; //view 的坐标原点再左上角,  gl 的坐标原点在左下角, y轴需要再翻转过来..
    aColor = vec4(1., 1., 1., 1.) * vAlpha;
}