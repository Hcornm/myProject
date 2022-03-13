package com.example.reply_api.model;

@FunctionalInterface
public interface dataModelingInterface<T> {

    /**
     * T 타입의 객체를 인자로 받고 리턴 값 없음
     * 
     * @param t
     */
    void dataModeling(T t);

}
