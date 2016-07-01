package com.simple.lru.cache;

import java.util.HashMap;

public class LRUCache<K, V> {

    private transient Entry<K, V> header = new Entry<K, V>(null, null, null, null);
    private HashMap<K, IndexNode<Entry<K, V>>> indexMap = new HashMap<K, IndexNode<Entry<K, V>>>();
    private final int cacheLimit = 5;
    private int size;

    public LRUCache() {
        header.next = header;
        header.previous = header;
        this.size = 0;
    }

    public void put(K key, V value){
        Entry<K, V> newEntry = new Entry<K, V>(key, value, null, null);
        addBefore(newEntry, header);
    }

    private void addBefore(Entry<K, V> newEntry, Entry<K, V> entry){
        if((size+1)<(cacheLimit+1)){
            newEntry.next=entry;
            newEntry.previous=entry.previous;
            IndexNode<Entry<K, V>> indexNode = new IndexNode<Entry<K, V>>(newEntry);
            indexMap.put(newEntry.key, indexNode);
            newEntry.previous.next=newEntry;
            newEntry.next.previous=newEntry;
            size++;
        }else{
            Entry<K, V>  entryRemoved = remove(header.next);
            indexMap.remove(entryRemoved.key);
            addBefore(newEntry, entry);
        }
    }

    public void get(K key){
        if(indexMap.containsKey(key)){
            Entry<K, V> newEntry = remove(indexMap.get(key).pointer);
            addBefore(newEntry, header);
        }else{
            System.out.println("No such element - "+key+" - was cached.");
        }
    }

    private Entry<K, V> remove(Entry<K, V> entry){
        entry.previous.next=entry.next;
        entry.next.previous = entry.previous;
        size--;
        return entry;
    }

    public void display(){
        for(Entry<K, V> curr=header.next; curr!=header; curr=curr.next){
            System.out.println("key : "+curr.key+" value : " + curr.value);
        }
    }

    @SuppressWarnings("hiding")
    private static class IndexNode<Entry>{
        private Entry pointer;
        IndexNode(Entry pointer){
            this.pointer = pointer;
        }
    }

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> previous;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next, Entry<K, V> previous) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }
}
