package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions;

public class MauvaisNiveauException extends Exception {

    /**
     * Creates a new instance of <code>UtilisateurNotFoundException</code> without
     * detail message.
     */
    public MauvaisNiveauException() {
    }

    /**
     * Constructs an instance of <code>UtilisateurNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public MauvaisNiveauException(String msg) {
        super(msg);
    }

}
