package ch.mibex.bamboo.plandsl.dsl

interface Logger {
    /**
     * Logs the passed string to the Bamboo build log.
     *
     * @param s The text to log
     */
    void println(String s)

    /**
     * Logs the passed string to the Bamboo build log, but only
     * if verbose logging is enabled in the task configuration.
     *
     * @param s The text to log
     */
    void info(String s)

    /**
     * Logs an error (in red) to the Bamboo build log.
     *
     * @param s The error text to log
     */
    void error(String s)
}
