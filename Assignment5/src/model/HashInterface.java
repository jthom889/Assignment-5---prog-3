package model;

/**
 * this interfaced
 * @author this was provided
 *
 * @param <T> is a generic type so it can 
 * be used with different instances
 */
public interface HashInterface<T> {
int gethashCode(T key);
void put(T key);
T remove(T key);
void reset();
void printTable();
}
