package org.example.app;

import java.util.concurrent.locks.ReentrantLock;

public class MyService {

    ReentrantLock lock = new ReentrantLock();

    public String work() {

        String result = null;

        try {
            lock.lock();

            // --- avoid concurrent accesses
            MyInternalComponent comp = new MyInternalComponent();
            result = comp.doInternalWork();
            // ---

            lock.unlock();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean isLocked() {
        return lock.isLocked();
    }
}
