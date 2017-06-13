package Parsing;

import Engine.Operateurs;

public class Automate {
	Automate fd;
	Automate fg;
	Operateurs node;
	
	public Automate(Automate fg, Operateurs node, Automate fd){
		this.fd = fd;
		this.fg = fg;
		this.node = node;
	}
}
