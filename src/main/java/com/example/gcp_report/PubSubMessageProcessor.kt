package com.example.gcp_report

import com.google.cloud.spring.pubsub.reactive.PubSubReactiveFactory
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener

class PubSubMessageProcessor(
    private val pubSubReactiveFactory: PubSubReactiveFactory,
    private val subscriptionName: String,
    private val pollIntervalMs: Long
) : ApplicationListener<ApplicationStartedEvent> {

    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        pubSubReactiveFactory.poll(subscriptionName, pollIntervalMs)
            .map {
                println(it.pubsubMessage.data.toStringUtf8())
                it.ack()
            }
            .subscribe()
    }

}
