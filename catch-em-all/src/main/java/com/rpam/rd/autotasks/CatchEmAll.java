package com.rpam.rd.autotasks;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CatchEmAll {

    //You may set another exception in this field;

    static Exception exception = new FileNotFoundException();

    public static void riskyMethod() throws Exception {
        throw exception;
    }

    public static void main(String[] args) throws Exception {
        try {
            riskyMethod();
        } catch (FileNotFoundException fileNotFoundException) {
            throw new IllegalArgumentException("Resource is missing", fileNotFoundException);
        } catch (IOException ioException) {
            throw new IllegalArgumentException("Resource error", ioException);
        } catch (NumberFormatException | ArithmeticException numberFormatException) {
            System.err.println(numberFormatException.getMessage());
        }
    }
}
