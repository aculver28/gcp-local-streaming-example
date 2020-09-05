# GCP Local Streaming Data Pipelines

This repository contains code samples used in [this blog post](https://anthony.culver.dev/posts/using-gcp-emulators). Please refer to the referenced article prior to executing any code here.

## Starting All Three Emulators
```bash
. script/start-all
```

Or

```bash
source script/start-all
```

## Stopping All Three Emulators
```bash
. script/stop-all
```

Or

```bash
source script/stop-all
```

## Publishing Pre-defined Messages to the Pub/Sub Emulator
```bash
script/publisher $PUBSUB_PROJECT_ID publish my_emulated_topic
```
