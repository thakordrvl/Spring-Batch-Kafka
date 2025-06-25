---

```markdown
# 📦 Spring Batch CSV to Kafka Integration

A Spring Boot application that integrates **Spring Batch** to read data from a **CSV file** and publish it to a **Kafka** topic. The batch job can be triggered through a REST API endpoint, making it ideal for automated and scalable data pipelines.

---

## 🚀 Features

- ✅ **CSV File Reading**: Reads records using Spring Batch’s `FlatFileItemReader`.
- 🔁 **Kafka Integration**: Publishes processed data to a Kafka topic via `KafkaTemplate`.
- 🌐 **REST API Trigger**: Exposes a simple API endpoint to start the batch job.
- 🧩 **Custom Serialization**: Configures Kafka producers to serialize `Person` objects correctly.
- 🛠️ **Modular Design**: Clear separation of concerns for easier testing and extension.

---

## 🛠️ Technologies Used

| Component      | Description                     |
|----------------|---------------------------------|
| Spring Boot    | Application framework           |
| Spring Batch   | Batch processing engine         |
| Apache Kafka   | Distributed event streaming     |
| REST Controller| For triggering batch jobs       |
| Maven          | Build and dependency management |

---

## 📂 Project Structure

```

src
├── main
│   ├── java
│   │   └── com.example.batchkafka
│   │       ├── config
│   │       │   └── KafkaConfig.java
│   │       ├── controller
│   │       │   └── JobLauncherController.java
│   │       ├── job
│   │       │   └── CsvToKafkaJobConfig.java
│   │       ├── model
│   │       │   └── Person.java
│   │       └── BatchKafkaApplication.java
│   └── resources
│       ├── application.properties
│       └── data.csv

````

---

## ⚙️ Setup and Installation

### 1. Clone the Repository

```bash
git clone <repository-url>
cd <repository-folder>
````

### 2. Configure Kafka

Update `src/main/resources/application.properties` with your Kafka details:

```properties
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.topic.name=person-topic
```

Make sure Kafka and Zookeeper are running locally or reachable remotely.

### 3. Build the Project

```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

---

## 🔁 REST API Endpoint

### ▶️ Trigger Batch Job

* **Method**: `GET`
* **Endpoint**: `/joblauncher/job1`
* **Description**: Starts the Spring Batch job which reads from a CSV file and sends records to Kafka.

**Example Usage:**

```bash
curl http://localhost:8080/joblauncher/job1
```

---

## 💬 Kafka Producer Configuration Example

```java
@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, Person> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Person> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
```

---

## 📄 CSV Format

Ensure your `data.csv` file (placed in `src/main/resources`) follows this structure:

```csv
firstName,lastName,email
John,Doe,john.doe@example.com
Jane,Smith,jane.smith@example.com
```

---

## 📌 Notes

* The batch job reads from `data.csv` each time it's triggered.
* You can extend the `Person` model or add processors/validators as needed.

---

## ✅ Future Enhancements

* [ ] Add Job Status & History tracking
* [ ] Support for multiple file formats
* [ ] Kafka consumer for downstream validation

---
---

```

---
```
