package com.revolut.mt.account;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class ConcurrentObject {

  private static ReadWriteLock lock = new ReentrantReadWriteLock();

  public void partialLock() {
    lock.readLock().lock();
  }

  public void partialUnlock() {
    lock.readLock().unlock();
  }

  public void fullLock() {
    lock.writeLock().lock();
  }

  public void fullUnlock() {
    lock.writeLock().unlock();
  }

}
