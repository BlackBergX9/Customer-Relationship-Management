package org.blackbergx9.customerhandler;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "crm")
public record CrmProperties(String welcomeMessage, int maxResults) { }
