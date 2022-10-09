#version 300 es
precision mediump float;
out vec4 fragColor;
in vec4 aColor;
void main() {
    fragColor = aColor;
}