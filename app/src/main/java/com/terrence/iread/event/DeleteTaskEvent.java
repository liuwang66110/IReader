package com.terrence.iread.event;


import com.terrence.iread.db.entity.CollBookBean;

/**
 */

public class DeleteTaskEvent {
    public CollBookBean collBook;

    public DeleteTaskEvent(CollBookBean collBook) {
        this.collBook = collBook;
    }
}
