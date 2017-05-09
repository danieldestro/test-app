
INSERT INTO country (name) values ('BR'), ('GB'), ('US');

INSERT INTO proposal (name) VALUES ('STANDARD PROPOSAL');

INSERT INTO service (name,proposal_id) VALUES ('Service One', select max(id) from proposal);
INSERT INTO service (name,proposal_id) VALUES ('Service Two', select max(id) from proposal);