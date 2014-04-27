varying vec4 v_color;
varying vec2 v_texCoord0;

uniform vec2 u_resolution;
uniform sampler2D u_sampler2D;

void main() {
	vec4 color = texture2D(u_sampler2D, v_texCoord0) * v_color;
	color.rgb = color.rgb - (gl_FragCoord.y / u_resolution.y);
	gl_FragColor = color;
}