package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions;

public class CreationCoursException extends Exception {

    /**
     * Creates a new instance of <code>UtilisateurNotFoundException</code> without
     * detail message.
     */
    public CreationCoursException() {
    }

    /**
     * Constructs an instance of <code>UtilisateurNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CreationCoursException(String msg) {
        super(msg);
    }

}
