package com.example.reply_api.model;

// 부모 데이터 모델링
@FunctionalInterface
public interface parentDataModelingInterface<T, R> {

    /**
     * T타입의 인자를 받고, R타입의 객체를 리턴
     * 
     * @param t
     * @return
     */
    R parentDataModeling(T t);
}
