package serialize;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order implements Externalizable {
    private long id;
    private String description;
    private BigDecimal totalCost = BigDecimal.valueOf(0);
    private List orderLines = new ArrayList();
    private Customer customer;

    public Order() {
    }

    public void readExternal(ObjectInput stream) throws IOException, ClassNotFoundException {
        this.id = stream.readLong();
        this.description = (String) stream.readObject();
        this.totalCost = (BigDecimal) stream.readObject();
        this.customer = (Customer) stream.readObject();
        this.orderLines = (List) stream.readObject();
    }

    public void writeExternal(ObjectOutput stream) throws IOException {
        stream.writeLong(this.id);
        stream.writeObject(this.description);
        stream.writeObject(this.totalCost);
        stream.writeObject(this.customer);
        stream.writeObject(this.orderLines);
    }
}

