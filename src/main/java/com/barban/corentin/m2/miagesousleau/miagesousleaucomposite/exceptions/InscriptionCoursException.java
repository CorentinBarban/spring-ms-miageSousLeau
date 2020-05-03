package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions;

public class InscriptionCoursException extends Exception {
    /**
     * Creates a new instance of <code>InscriptionException</code> without
     * detail message.
     */
    public InscriptionCoursException() {
    }

    /**
     * Constructs an instance of <code>InscriptionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InscriptionCoursException(String msg) {
        super(msg);
    }
}
