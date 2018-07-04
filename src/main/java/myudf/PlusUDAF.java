package myudf;

import com.aliyun.odps.io.Writable;
import com.aliyun.odps.udf.ExecutionContext;
import com.aliyun.odps.udf.UDFException;
import com.aliyun.odps.udf.Aggregator;
import com.aliyun.odps.udf.annotation.Resolve;

// TODO define input and output types, e.g. "double->double".
@Resolve({""})
public class PlusUDAF extends Aggregator {

    @Override
    public void setup(ExecutionContext ctx) throws UDFException {

    }

    @Override
    public Writable newBuffer() {
        // TODO
        return null;
    }

    @Override
    public void iterate(Writable buffer, Writable[] args) throws UDFException {
        // TODO
    }

    @Override
    public void merge(Writable buffer, Writable partial) throws UDFException {
        // TODO
    }

    @Override
    public Writable terminate(Writable buffer) throws UDFException {
        // TODO
        return null;
    }

    @Override
    public void close() throws UDFException {

    }

}