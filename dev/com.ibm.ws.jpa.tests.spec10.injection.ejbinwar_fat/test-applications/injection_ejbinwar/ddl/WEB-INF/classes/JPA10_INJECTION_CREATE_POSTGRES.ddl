CREATE TABLE CIEntity (id INTEGER NOT NULL, strData VARCHAR(255), ENT_TYPE VARCHAR(31), version INTEGER, PRIMARY KEY (id));
CREATE INDEX I_CINTITY_DTYPE ON CIEntity (ENT_TYPE);