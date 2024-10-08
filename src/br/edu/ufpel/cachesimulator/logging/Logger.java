package br.edu.ufpel.cachesimulator.logging;

public class Logger {
    private boolean verbose;

    public Logger(boolean verbose) {
        this.verbose = verbose;
    }

    public void log(String message) {
        if (verbose) {
            System.out.println(message);
        }
    }
}
