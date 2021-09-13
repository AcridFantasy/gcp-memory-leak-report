package com.example.gcp_report

import com.google.cloud.spring.pubsub.reactive.PubSubReactiveFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource

fun main() {
    SpringApplication.run(SpringConfig::class.java)
}

@SpringBootApplication
@PropertySource(value = ["classpath:creds.properties", "classpath:pubsub.properties"])
open class SpringConfig {

    private val subscriptionName = "dev-core-google-billing-pubsub-notifications-sub"
    private val pollIntervalMs = 1000L

    @Bean
    open fun googleBillingPubSubSubscriptionControlImpl(
        pubSubReactiveFactory: PubSubReactiveFactory
    ): PubSubMessageProcessor =
        PubSubMessageProcessor(pubSubReactiveFactory, subscriptionName, pollIntervalMs)

}
