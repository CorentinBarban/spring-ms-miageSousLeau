package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions;

public class PiscineNotFoundException extends Exception {
    /**
     * Creates a new instance of <code>PiscineNotFoundException</code> without
     * detail message.
     */
    public PiscineNotFoundException() {
    }

    /**
     * Constructs an instance of <code>PiscineNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PiscineNotFoundException(String msg) {
        super(msg);
    }
}
