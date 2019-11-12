package com.nagp.assgnmnt.mservices.config;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SluethConfig
{
    /**
     * Slueth Configuration
     *
     * @return
     */
    @Bean
    public Sampler defaultSampler()
    {
        return Sampler.ALWAYS_SAMPLE;
    }

}
