package openihm.interfaces;

public interface Arguments {
	/*
	 * renvoie @String l'argument numero $ind
	 * $ind le numero de l'argument
	 */
	public char[] getArg(final int ind);
	
	/*
	 * renvoie @boolean:true s'il existe l'argument numero $ind 
	 * $ind le numero de l'argument
	 */
	public boolean isArg(final int ind);
	
	/*
	 * renvoie @int le nombre d'argument
	 */
	public int getNumberArgs();
	
	/*
	 * renvoie @String[] tout les arguments
	 */
	public char[][] getArgs();
}
