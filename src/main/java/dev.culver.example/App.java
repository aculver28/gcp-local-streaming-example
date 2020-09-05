package dev.culver.example;

import org.apache.beam.sdk.options.PipelineOptionsFactory;

public class App {
  public static void main(String[] args) {
    PipelineOptionsFactory.register(DemoPipelineOptions.class);
    new DemoPipeline(args).run();
  }
}
