package com.wjpdev.java.rxjava.demo1;

import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;

public class MainClassTest {

    @Test
    public void fun1(){
        Observable<String> myObservable = Observable.just("Hello, world! - wjpdeveloper");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        myObservable.subscribe(onNextAction);

    }

    @Test
    public void fun2(){
        Observable.just("Hello, world! - wjpdeveloper !")
                .subscribe(System.out::println);
    }

    @Test
    public void fun3(){

        Observable.just("Hello, world!")
                .map(s -> s + " - wjpdeveloper !")
                .subscribe(System.out::println);
    }

    @Test
    public void fun4(){
        Observable.just("Hello, world! - wjpdeveloper")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(System.out::println);
    }

    @Test
    public void fun5(){
        Observable.from(new String[]{"Ben", "George"}).subscribe(System.out::println);
    }
}