package com.lay.arithmetic;

// 手写queue 不使用java所带类，例如LISt
public class CustomerQueue<T> {

    private T[] items;
    private int head;
    private int tail;
    private int count;

    @SuppressWarnings("unchecked")
    public CustomerQueue(int capacity) {
        items = (T[]) new Object[capacity];
        head = -1;
        tail = -1;
        count = 0;
    }

    public boolean enqueue(T item) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full.");
        }
        if (head == -1) {
            head = 0;
        }
        tail = (tail + 1) % items.length;
        items[tail] = item;
        count++;
        return true;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        T item = items[head];
        items[head] = null; // To enable garbage collection
        head = (head + 1) % items.length;
        count--;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return items[head];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == items.length;
    }

    public int size() {
        return count;
    }

    public static void main(String[] args) {
        CustomerQueue<Integer> queue = new CustomerQueue<>(2);
        queue.enqueue(1);
        queue.enqueue(2);
        System.out.println(queue.dequeue());
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

}
