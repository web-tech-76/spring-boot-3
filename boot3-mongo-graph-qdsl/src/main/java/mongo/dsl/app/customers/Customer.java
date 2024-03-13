package mongo.dsl.app.customers;

import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@QueryEntity
@Document
public record Customer(@MongoId String id, String name, Boolean isLoggedIn) {
}
