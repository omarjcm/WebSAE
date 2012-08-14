/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mad.objetos;

import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author Guillermo Pizarro
 */
public class Table<K, V> extends Hashtable<K, V> {

    public Table(Map<? extends K, ? extends V> t) {
        super(t);
    }

    public Table() {
    }

    public Table(int initialCapacity) {
        super(initialCapacity);
    }

    public Table(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    @Override
    public synchronized V get(Object key) {
        Object objeto = super.get(key);
        if (objeto.toString().contains("java.lang.Object@"))
            return null;
        else
            return (V) objeto;
    }
}
