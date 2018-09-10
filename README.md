# my-action-java-rxjava

我的 Java-RxJava 实践

## Rx(ReactiveX)

响应式编程

ReactiveX是Reactive Extensions的缩写，一般简写为Rx

ReactiveX.io给的定义是，Rx是一个使用可观察数据流进行异步编程的编程接口，ReactiveX结合了观察者模式、迭代器模式和函数式编程的精华。

## Handbook

> 一个在 Java VM 上使用可观测的序列来组成异步的、基于事件的程序的库

#### 1. RxJava好在哪

简洁、简洁、简洁

#### 2. RxJava 的异步实现，是通过一种扩展的观察者模式来实现

四个基本概念:
- Observable (可观察者，即被观察者)
- Observer (观察者)
- subscribe (订阅)
- 事件
  - onNext()
  - onCompleted()
  - onError()

#### 3. 基本实现

1. 创建 Observer
2. 创建 Observable
3. Subscribe

#### 4. 线程控制 —— Scheduler

在 RxJava 的默认规则中，事件的发出和消费都是在同一个线程的。
也就是说，如果只用上面的方法，实现出来的只是一个同步的观察者模式。
观察者模式本身的目的就是『后台处理，前台回调』的异步机制，因此异步对于 RxJava 是至关重要的。
而要实现异步，则需要用到 RxJava 的另一个概念：Scheduler。

API List:
- Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。
- Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
- Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。
- Schedulers.computation(): 计算所使用的 Scheduler。
- AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。

```
Observable.just(1, 2, 3, 4)
    .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
    .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
    .subscribe(new Action1<Integer>() {
        @Override
        public void call(Integer number) {
            Log.d(tag, "number:" + number);
        }
    });
```



#### 5. 变换

- map(): 事件对象的直接变换
- flatMap()
- compose()

#### 6. 线程的自由控制
- observeOn()

```
Observable.just(1, 2, 3, 4) // IO 线程，由 subscribeOn() 指定
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.newThread())
    .map(mapOperator) // 新线程，由 observeOn() 指定
    .observeOn(Schedulers.io())
    .map(mapOperator2) // IO 线程，由 observeOn() 指定
    .observeOn(AndroidSchedulers.mainThread) 
    .subscribe(subscriber);  // Android 主线程，由 observeOn() 指定
```

Subscriber 的 onStart() 可以用作流程开始前的初始化

- subscribeOn()
- doOnSubscribe()

#### 7. 适用场景和使用方式

1. 与 Retrofit 的结合
2. RxBinding
3. 各种异步操作
4. RxBus

## Resource

- [给 Android 开发者的 RxJava 详解](https://gank.io/post/560e15be2dca930e00da1083)
- [ReactiveX/RxJava文档中文版](https://github.com/mcxiaoke/RxDocs)
- [RxJava资源合集](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0430/2815.html)
- [那些年我们错过的响应式编程](responsive-programming-that-we-missed-in-those-years.md)
- [RxJava开发精要1-从.NET到RxJava](RxJava-development-essentials-1.md)
- [RxJava开发精要2-为什么是Observables?](RxJava-development-essentials-2.md)
- [RxJava开发精要3-向响应式世界问好](RxJava-development-essentials-3.md)
- [RxJava开发精要4 - Observables过滤](RxJava-development-essentials-4.md)
- [RxJava开发精要5 - Observables变换](RxJava-development-essentials-5.md)
- [RxJava开发精要6 - 组合Observables](RxJava-development-essentials-6.md)
- [RxJava开发精要7 - Schedulers-解决Android主线程问题](RxJava-development-essentials-7.md)
- [RxJava开发精要8 - 与REST无缝结合-RxJava和Retrofit](RxJava-development-essentials-8.md)