package dev.culver.example;

import com.google.bigtable.v2.Mutation;
import com.google.bigtable.v2.Mutation.SetCell;
import com.google.protobuf.ByteString;
import java.util.Collections;
import java.util.UUID;
import org.apache.beam.sdk.coders.*;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.DoFn.OutputReceiver;
import org.apache.beam.sdk.values.*;

public class StringToMutationDoFn extends DoFn<String, KV<ByteString, Iterable<Mutation>>> {
  private static final long serialVersionUID = 1L;

  @ProcessElement
  public void processElement(
      @Element String element, OutputReceiver<KV<ByteString, Iterable<Mutation>>> out) {
    ByteString key = ByteString.copyFromUtf8(UUID.randomUUID().toString());
    Mutation mutation =
        Mutation.newBuilder()
            .setSetCell(
                SetCell.newBuilder()
                    .setFamilyName("cf1")
                    .setColumnQualifier(ByteString.copyFromUtf8("my_data"))
                    .setValue(ByteString.copyFromUtf8(element))
                    .build())
            .build();
    out.output(KV.of(key, Collections.singletonList(mutation)));
  }
}
