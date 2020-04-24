package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions;

public class CoursNotFoundException extends Exception {
    /**
     * Creates a new instance of <code>CoursNotFoundException</code> without
     * detail message.
     */
    public CoursNotFoundException() {
    }

    /**
     * Constructs an instance of <code>CoursNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CoursNotFoundException(String msg) {
        super(msg);
    }
}
