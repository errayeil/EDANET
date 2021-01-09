package com.github.errayeil.edanet.Event;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class ResultEvent {

    /**
     *
     */
    private long timestamp;

    /**
     *
     */
    private Result source;

    /**
     *
     */
    protected ResultEvent(Result result) {
        source = result;
    }

    /**
     *
     * @return
     */
    public Result getSource() {
        return source;
    }

    /**
     *
     * @return
     */
    public long getTimestamp() {
        return timestamp;
    }
}
