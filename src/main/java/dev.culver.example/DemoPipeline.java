package dev.culver.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.*;
import org.apache.beam.sdk.io.gcp.bigtable.BigtableIO;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.*;

public class DemoPipeline {
  final Pipeline pipeline;

  public DemoPipeline(String[] args) {
    try {
      this.pipeline = createPipeline(args);
    } catch (Exception ex) {
      throw new RuntimeException("Error creating pipeline", ex);
    }
  }

  public void run() {
    pipeline.run();
  }

  Pipeline createPipeline(String[] args) throws CannotProvideCoderException {
    // parse the PipelineOptions
    final DemoPipelineOptions opts =
        PipelineOptionsFactory.fromArgs(args).withValidation().as(DemoPipelineOptions.class);
    final Pipeline pipeline = Pipeline.create(opts);

    // set up a PubSub reader. These are unbounded by default, so this will run as a
    //  streaming pipeline.
    PubsubIO.Read<String> pubSubReader =
        PubsubIO.readStrings().fromSubscription(opts.getPubsubSubscriptionName());

    // set up the BigTable writer
    BigtableIO.Write bigTableWriter =
        BigtableIO.write()
            .withProjectId(opts.getBigtableProjectId())
            .withInstanceId(opts.getBigtableInstanceId())
            .withTableId(opts.getBigtableTableName());

    // build the pipeline to write the data
    pipeline
        .apply("Read messages from PubSub", pubSubReader)
        .apply("Create BigTable mutation", ParDo.of(new StringToMutationDoFn()))
        .apply("Write data to BigTable", bigTableWriter);

    return pipeline;
  }
}
