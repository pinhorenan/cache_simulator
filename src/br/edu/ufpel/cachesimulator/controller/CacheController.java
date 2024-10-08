package br.edu.ufpel.cachesimulator.controller;

public class CacheController {
    private Cache l1Cache;
    private Cache l2Cache;

    public CacheController(Cache l1, Cache l2) {
        this.l1Cache = l1;
        this.l2Cache = l2;
    }

    public boolean access(int address) {
        if (l1Cache.accessAddress(address)) {
            return true; // Hit na L1
        } else if (l2Cache != null && l2Cache.accessAddress(address)) {
            return true; // Hit na L2
        }
        return false; // Miss em ambas
    }
}
