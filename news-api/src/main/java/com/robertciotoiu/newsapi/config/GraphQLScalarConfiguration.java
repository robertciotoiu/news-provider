package com.robertciotoiu.newsapi.config;

import graphql.language.StringValue;
import graphql.schema.*;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Configuration
public class GraphQLScalarConfiguration {
    @Bean
    public GraphQLScalarType offsetDateTimeScalar() {
        return GraphQLScalarType.newScalar()
                .name("OffsetDateTime")
                .description("Java 8 OffsetDateTime as scalar.")
                .coercing(new Coercing<OffsetDateTime, String>() {
                    @Override
                    public String serialize(final @NonNull Object dataFetcherResult) {
                        if (dataFetcherResult instanceof OffsetDateTime) {
                            return dataFetcherResult.toString();
                        } else {
                            throw new CoercingSerializeException("Expected a LocalDate object.");
                        }
                    }

                    @Override
                    public @NonNull OffsetDateTime parseValue(final @NonNull Object input) {
                        try {
                            if (input instanceof String inputString) {
                                return OffsetDateTime.parse(inputString, DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss X", Locale.US));
                            } else {
                                throw new CoercingParseValueException("Expected a String");
                            }
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseValueException(String.format("Not a valid date: '%s'.", input), e
                            );
                        }
                    }

                    @Override
                    public @NonNull OffsetDateTime parseLiteral(final @NonNull Object input) {
                        if (input instanceof StringValue inputStringValue) {
                            try {
                                return OffsetDateTime.parse((inputStringValue).getValue(), DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss X", Locale.US));
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseLiteralException(e);
                            }
                        } else {
                            throw new CoercingParseLiteralException("Expected a StringValue.");
                        }
                    }
                }).build();
    }
}
