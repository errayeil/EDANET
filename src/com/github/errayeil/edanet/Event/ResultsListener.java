package com.github.errayeil.edanet.Event;

public interface ResultsListener {

    /**
     *
     * @param event
     */
    void resultAdded(ResultEvent event);

    /**
     *
     * @param event
     */
    void resultRemoved(ResultEvent event);
}
