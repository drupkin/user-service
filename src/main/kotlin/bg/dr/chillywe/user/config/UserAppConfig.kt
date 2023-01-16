package bg.dr.chillywe.user.config

import bg.dr.chillywe.kafka.producer.KafkaService
import bg.dr.chillywe.kafka.producer.KafkaServiceImpl
import bg.dr.chillywe.kafka.producer.config.KafkaProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserAppConfig(
    @Value("\${kafka.bootstrapAddress}") private val kafkaBootstrapServer: String,
    @Value("\${kafka.serializer.key}") private val serializerKey: String,
    @Value("\${kafka.serializer.value}") private val serializerValue: String,
    @Value("\${kafka.schema-registry.url}") private val schemaRegistryUrl: String
) {
    val producerConfig = KafkaProducerConfig(kafkaBootstrapServer, serializerKey, serializerValue, schemaRegistryUrl)

    @Bean
    fun kafkaService(): KafkaService {
        return KafkaServiceImpl(producerConfig.kafkaTemplate())
    }
}