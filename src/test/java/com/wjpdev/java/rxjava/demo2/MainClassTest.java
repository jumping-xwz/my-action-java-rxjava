package com.wjpdev.java.rxjava.demo2;

import org.junit.Test;
import rx.Observable;
import rx.Observer;

import java.util.concurrent.TimeUnit;

public class MainClassTest {

    @Test
    public void testfun1() throws InterruptedException {
        Observable.interval(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("error");
                    }

                    @Override
                    public void onNext(Long number) {
                        System.out.println("hello wjpdeveloper!");
                    }
                });

        Thread.sleep(10000);
    }


    @Test
    public void testfun2() throws InterruptedException {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("error");
                    }

                    @Override
                    public void onNext(Long number) {
                        System.out.println("hello wjpdeveloper!");
                    }
                });

        Thread.sleep(10000);
    }

}