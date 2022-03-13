package com.example.reply_api.domain.global;

import java.io.Serializable;
import java.util.function.Supplier;

public interface BaseFunction<E extends BaseEntity> extends Serializable {

    /**
     * 
     * @return
     */
    Supplier<E> identity();

    /**
     * 
     * @param e
     * @return
     */
    E clone(E e);

    /**
     * 
     * @param e
     * @return
     */
    E destory(E e);

    /**
     * 
     * @param e
     * @return
     */
    boolean validate(E e);

    void doSynchronizerRelationData();
}
