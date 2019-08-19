package com.revolut.mt.account;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class ConcurrentObject {

  private static ReadWriteLock lock = new ReentrantReadWriteLock();

  void partialLock() {
    lock.readLock().lock();
  }

  void partialUnlock() {
    lock.readLock().unlock();
  }

  void fullLock() {
    lock.writeLock().lock();
  }

  void fullUnlock() {
    lock.writeLock().unlock();
  }

}
