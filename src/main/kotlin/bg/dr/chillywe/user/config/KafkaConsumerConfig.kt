package bg.dr.chillywe.user.config

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties

@EnableKafka
@Configuration
class KafkaConsumerConfig(
    @Value("\${kafka.bootstrapAddress}") private val kafkaBootstrapServer: String,
    @Value("\${kafka.deserializer.key}") private val deserializerKey: String,
    @Value("\${kafka.deserializer.value}") private val deserializerValue: String,
    @Value("\${kafka.schema-registry.url}") private val schemaRegistryUrl: String,
    @Value("\${kafka.group-id}") private val groupId: String
) {
    @Bean
    fun consumerFactory(): ConsumerFactory<String?, Any?> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaBootstrapServer
        props[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = deserializerKey
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = deserializerValue
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        props[KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG] = schemaRegistryUrl
        props[KafkaAvroDeserializerConfig.AUTO_REGISTER_SCHEMAS] = false
        props[KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG] = true
        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any>? {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.consumerFactory = consumerFactory()
        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
        factory.containerProperties.isSyncCommits = true;
        return factory
    }
}