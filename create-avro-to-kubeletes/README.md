
# Deploy kafka producer and consumer on Kubeletes

### 1. Deploy kafka, zookeeper, schema registry on Kubeetes by [Confluent platform Helm Charts](https://github.com/confluentinc/cp-helm-charts)

- Install helm on mac

```
   brew install helm
```
- Clone the Confluent Helm Chart repo
```
  helm repo add confluentinc https://confluentinc.github.io/cp-helm-charts/
```
- update helm repo
```
  helm repo update
```
- helm install kafka, zookeeper, schema registry with chart name my-confluent
```
  helm install my-confluence confluentinc/cp-helm-charts -f create-confluent-value.yaml

```
- check kubeletes pods, services,statefulsets

```
For example:

  kubectl get pods

  kubectl get services

  kubectl get statefulsets my-confluence-cp-kafka -o yaml
```

### 2. Deploy kafka producer and consumer on Kubeletes

- deploy kafka producer
```
   kubectl create -f create-avro-producer.yaml
```

- deploy kafka consumer
```
   kubectl create -f create-avro-consumer.yaml
```



