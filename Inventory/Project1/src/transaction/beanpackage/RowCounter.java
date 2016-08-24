package transaction.beanpackage;

import java.io.Serializable;


public class RowCounter implements Serializable {

    private transient int row = 1;

    public int getRow() {
    	return ++row;
    }

}