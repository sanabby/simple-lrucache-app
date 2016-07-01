package com.simple.lru.lruexes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class LRUExecutor implements ExecutorService {
    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(LRUExecutor.class);

    private volatile boolean stopped;

    @Override
    public void shutdown() {
        stopped = true;
    }

    @Override
    public List<Runnable> shutdownNow() {
        stopped = true;
        return Collections.emptyList();
    }

    @Override
    public boolean isShutdown() {
        return stopped;
    }

    @Override
    public boolean isTerminated() {
        return stopped;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        throw new InterruptedException();
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return new LRUFutureTask<T>(task);
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return new LRUFutureTask<T>(task, result);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return new LRUFutureTask<>(task, null);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return Collections.emptyList();
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        throw new InterruptedException();
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        throw new InterruptedException();
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
    throws InterruptedException, ExecutionException, TimeoutException {
        throw new InterruptedException();
    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
