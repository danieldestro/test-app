
INSERT INTO country values 
	(NEXTVAL('SEQ_COUNTRY'), 'BR'),
	(NEXTVAL('SEQ_COUNTRY'), 'GB'),
	(NEXTVAL('SEQ_COUNTRY'), 'US');

INSERT INTO proposal VALUES (NEXTVAL('SEQ_PROPOSAL'), 'STANDARD PROPOSAL');

INSERT INTO service VALUES (NEXT VALUE FOR SEQ_SERVICE, 'Service One', CURRVAL('SEQ_PROPOSAL'), 5);
INSERT INTO service VALUES (NEXT VALUE FOR SEQ_SERVICE, 'Service Two', CURRVAL('SEQ_PROPOSAL'), 11);