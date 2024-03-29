package bg.dr.chillywe.user.messaging

import bg.dr.avro.chillywe.UserCreatedMessage
import bg.dr.chillywe.kafka.producer.KafkaService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset

interface UserKafkaService {
    fun sendUserCreatedMessage()
}

@Service
class UserKafkaServiceImpl(
    @Value("\${kafka.topic}") val topic: String,
    private val kafkaService: KafkaService
) : UserKafkaService {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun sendUserCreatedMessage() {
//        val userEntity = userService.getAllUsers().get(0)

        val jackHarrison = UserCreatedMessage.newBuilder()
            .setId(11L)
            .setUserName("crazyJack")
            .setFirstName("Jack")
            .setLastName("Harrison")
            .setEmail("jack.harrison@leeds.united")
            .setDayOfBirth(LocalDateTime.parse("1996-11-20T10:15:30").toInstant(ZoneOffset.UTC))
            .build()

        log.info("Sending message to Kafka {}", jackHarrison)
//        val message: Message<UserCreatedMessage> = MessageBuilder
//            .withPayload(jackHarrison)
//            .setHeader(KafkaHeaders.TOPIC, topic)
//            .setHeader("X-Custom-Header", "Custom header here")
//            .build()
        kafkaService.sendMessage(topic, jackHarrison)
        log.info("Message sent with success")
    }

}