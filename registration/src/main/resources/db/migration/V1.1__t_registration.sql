-- noinspection SpellCheckingInspectionForFile

CREATE TABLE t_registration
(
    id                 varchar(37)  NOT NULL,
    phone              varchar(15)  NOT NULL,
    first_name         varchar(37)  NOT NULL,
    last_name          varchar(37)  NOT NULL,
    dob                date NULL,
    gender             varchar(10) default NULL,
    email              varchar(50) NOT NULL,
    created_by         varchar(50)  NOT NULL,
    created_date       timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_by   varchar(50)           DEFAULT NULL,
    last_modified_date timestamp(0) NULL     DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX idx_t_registration_phone
    ON t_registration (phone);
CREATE UNIQUE INDEX idx_t_registration_email
    ON t_registration (email);

