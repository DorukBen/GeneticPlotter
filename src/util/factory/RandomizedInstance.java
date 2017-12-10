package util.factory;

public interface RandomizedInstance<T> {
    T randomInstance(int ... dimen);
}
