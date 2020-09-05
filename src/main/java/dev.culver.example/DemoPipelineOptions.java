package dev.culver.example;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.Validation;
import org.apache.beam.sdk.options.ValueProvider;

public interface DemoPipelineOptions extends DataflowPipelineOptions {
  @Description("The Pub/Sub subscription to read from")
  @Validation.Required()
  ValueProvider<String> getPubsubSubscriptionName();

  void setPubsubSubscriptionName(ValueProvider<String> value);

  @Description("The GCP project ID where the BigTable instance exists")
  @Validation.Required()
  ValueProvider<String> getBigtableProjectId();

  void setBigtableProjectId(ValueProvider<String> value);

  @Description("The BigTable instance ID")
  @Validation.Required()
  ValueProvider<String> getBigtableInstanceId();

  void setBigtableInstanceId(ValueProvider<String> value);

  @Description("The name of the table in the BigTable instance we'll write our data")
  @Validation.Required()
  ValueProvider<String> getBigtableTableName();

  void setBigtableTableName(ValueProvider<String> value);
}
