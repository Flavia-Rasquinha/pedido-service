package com.restaurante.pedidoservice;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;


@Configuration
public class MongoConfig {

    @Bean
    public MongodConfig mongodConfig() throws UnknownHostException {
        return MongodConfig.builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net("localhost", 27017, Network.localhostIsIPv6()))
                .build();
    }
}