CREATE TABLE "model" (
	"id" serial NOT NULL,
	"name" varchar(100) NOT NULL,
	"car_kit" varchar(100) NOT NULL,
	"engine_type" varchar(100) NOT NULL,
	"body_type" varchar(100) NOT NULL,
	"brand_id" integer NOT NULL,
	"created " TIMESTAMP NOT NULL,
	"modified " TIMESTAMP,
	CONSTRAINT model_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "brand" (
	"id" serial NOT NULL UNIQUE,
	"name" varchar(100) NOT NULL,
	"created " TIMESTAMP NOT NULL,
	"modified " TIMESTAMP,
	CONSTRAINT brand_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "legal_entity" (
	"id" serial NOT NULL,
	"name" varchar(100) NOT NULL,
	"address" varchar(100) NOT NULL,
	"phone_number" varchar(100) NOT NULL,
	"email" varchar(100),
	"created " TIMESTAMP NOT NULL,
	"modified " TIMESTAMP NOT NULL,
	CONSTRAINT legal_entity_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "car" (
	"id" serial NOT NULL,
	"user_id" integer NOT NULL,
	"release_year" integer NOT NULL,
	"model_id" integer NOT NULL,
	"legal_entity_id" integer NOT NULL,
	"status" BOOLEAN NOT NULL DEFAULT 'false',
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	"created " TIMESTAMP NOT NULL,
	"modified " TIMESTAMP NOT NULL,
	CONSTRAINT car_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "authentication" (
	"user_id" integer NOT NULL,
	"login" varchar(100) NOT NULL,
	"password" varchar(100) NOT NULL,
	"created " TIMESTAMP NOT NULL,
	"modified " TIMESTAMP NOT NULL,
	CONSTRAINT authentication_pk PRIMARY KEY ("user_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user" (
	"id" serial NOT NULL,
	"name" varchar(100) NOT NULL,
	"last_name" varchar(100) NOT NULL,
	"birthday" TIMESTAMP NOT NULL,
	"address" varchar(100) NOT NULL,
	"phone_number" varchar(100) NOT NULL,
	"email" varchar(100),
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	"created " TIMESTAMP NOT NULL,
	"modified " TIMESTAMP NOT NULL,
	"role" varchar(100) NOT NULL UNIQUE,
	CONSTRAINT user_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "car_option" (
	"id" serial NOT NULL,
	"name" varchar(100) NOT NULL,
	"created " TIMESTAMP NOT NULL,
	"modified " TIMESTAMP NOT NULL,
	CONSTRAINT car_option_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "car_2_car_option" (
	"car_id" integer NOT NULL,
	"car_option_id" integer NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "rate" (
	"id" serial NOT NULL,
	"name" varchar(100) NOT NULL,
	"price_landing" double NOT NULL,
	"price_kilometr" double NOT NULL,
	"price_minute_wait" double NOT NULL,
	"created " TIMESTAMP NOT NULL,
	"modified " TIMESTAMP NOT NULL,
	CONSTRAINT rate_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order" (
	"id" serial NOT NULL,
	"car_id" integer NOT NULL,
	"order_time" TIMESTAMP NOT NULL,
	"begin" TIMESTAMP NOT NULL,
	"order_end" TIMESTAMP NOT NULL,
	"distance" integer NOT NULL,
	"summ" DOUBLE NOT NULL,
	"rate_id" integer NOT NULL,
	"departure_address" varchar(200) NOT NULL,
	"arrival_address" varchar(200) NOT NULL,
	"inactivity_minutes" integer NOT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'true',
	"created " TIMESTAMP NOT NULL,
	"modified " TIMESTAMP NOT NULL,
	"user_id" integer NOT NULL,
	CONSTRAINT order_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "model" ADD CONSTRAINT "model_fk0" FOREIGN KEY ("brand_id") REFERENCES "brand"("id");



ALTER TABLE "car" ADD CONSTRAINT "car_fk0" FOREIGN KEY ("user_id") REFERENCES "user"("id");
ALTER TABLE "car" ADD CONSTRAINT "car_fk1" FOREIGN KEY ("model_id") REFERENCES "model"("id");
ALTER TABLE "car" ADD CONSTRAINT "car_fk2" FOREIGN KEY ("legal_entity_id") REFERENCES "legal_entity"("id");

ALTER TABLE "authentication" ADD CONSTRAINT "authentication_fk0" FOREIGN KEY ("user_id") REFERENCES "user"("id");



ALTER TABLE "car_2_car_option" ADD CONSTRAINT "car_2_car_option_fk0" FOREIGN KEY ("car_id") REFERENCES "car"("id");
ALTER TABLE "car_2_car_option" ADD CONSTRAINT "car_2_car_option_fk1" FOREIGN KEY ("car_option_id") REFERENCES "car_option"("id");


ALTER TABLE "order" ADD CONSTRAINT "order_fk0" FOREIGN KEY ("car_id") REFERENCES "car"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk1" FOREIGN KEY ("rate_id") REFERENCES "rate"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk2" FOREIGN KEY ("user_id") REFERENCES "user"("id");
