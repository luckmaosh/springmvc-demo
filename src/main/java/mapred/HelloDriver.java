package mapred;

import com.aliyun.odps.OdpsException;
import com.aliyun.odps.data.TableInfo;
import com.aliyun.odps.mapred.JobClient;
import com.aliyun.odps.mapred.RunningJob;
import com.aliyun.odps.mapred.conf.JobConf;
import com.aliyun.odps.mapred.utils.InputUtils;
import com.aliyun.odps.mapred.utils.OutputUtils;
import com.aliyun.odps.mapred.utils.SchemaUtils;

public class HelloDriver {

    public static void main(String[] args) throws OdpsException {

        JobConf job = new JobConf();

        // TODO: specify map output types
        job.setMapOutputKeySchema(SchemaUtils.fromString( "entity_subtype:STRING"));
        job.setMapOutputValueSchema(SchemaUtils.fromString( "entity_itm_num:bigint"));

        // TODO: specify input and output tables
        InputUtils.addTable(TableInfo.builder().tableName( "ifd.s_gx_kg_entity_pop").build(), job);
        //OutputUtils.addTable(TableInfo.builder().tableName( ?).build(), job);

        // TODO: specify a mapper
        //job.setMapperClass( ?);
        // TODO: specify a reducer
        //job.setReducerClass( ?);

        RunningJob rj = JobClient.runJob(job);
        rj.waitForCompletion();

    }

}