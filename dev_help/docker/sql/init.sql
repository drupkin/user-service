CREATE USER user_service WITH PASSWORD 'e40dcd54-01e2-4899-931f-263b6392d042';
CREATE DATABASE user_service_db WITH ENCODING='UTF8' OWNER=user_service LC_COLLATE='en_US.utf8';

CREATE USER keycloak WITH PASSWORD 'keycloakpassword';
CREATE DATABASE keycloak WITH ENCODING='UTF8' OWNER=keycloak LC_COLLATE='en_US.utf8';